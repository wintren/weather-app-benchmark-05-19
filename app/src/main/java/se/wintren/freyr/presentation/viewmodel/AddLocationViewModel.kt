package se.wintren.freyr.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.domain.usecase.contracts.StoreLocationUseCase
import se.wintren.freyr.util.RxSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddLocationViewModel @Inject constructor(
    private val storeLocationUseCase: StoreLocationUseCase,
    private val getGeoCodeUseCase: GetGeoCodeUseCase,
    schedulers: RxSchedulers
) : ViewModel() {

    private var result: GeoCode = GeoCode()

    private val geoCodeQuerySubject: PublishSubject<String> = PublishSubject.create()

    private val queryDisposable: Disposable

    // Using delegates instead of LiveData for now

    var onLocationResult: ((city: String, country: String) -> Unit)? = null

    var onLocationMissing: (() -> Unit)? = null

    var onSearchStart: (() -> Unit)? = null

    init {
        queryDisposable = geoCodeQuerySubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() }
            .observeOn(schedulers.mainThread())
            .doOnNext { onSearchStart?.invoke() }
            .flatMap { getGeoCodeUseCase.getGeoCode(it) }
            .observeOn(schedulers.mainThread())
            .subscribe(
                ::onSuccess,
                ::onError
            )
    }

    fun searchTextUpdated(text: String) {
        if (text.isEmpty()) {
            onError()
        } else {
            geoCodeQuerySubject.onNext(text)
        }
    }

    private fun onError(throwable: Throwable? = null) {
        throwable?.printStackTrace()
        onLocationMissing?.invoke()
    }

    private fun onSuccess(geoCode: GeoCode) {
        if (geoCode.isEmpty()) {
            return onError()
        }
        onLocationResult?.invoke(geoCode.city, geoCode.country)
        result = geoCode
    }

    fun onSaveLocation() {
        storeLocationUseCase.storeLocation(result)
        onAbort()
    }

    fun onAbort() {
        queryDisposable.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }

}