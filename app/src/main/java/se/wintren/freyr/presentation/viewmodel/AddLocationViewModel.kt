package se.wintren.freyr.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.domain.usecase.contracts.StoreLocationUseCase
import se.wintren.freyr.extensions.forceValue
import se.wintren.freyr.util.RxSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddLocationViewModel @Inject constructor(
    private val storeLocationUseCase: StoreLocationUseCase,
    private val getGeoCodeUseCase: GetGeoCodeUseCase,
    schedulers: RxSchedulers
) : ViewModel() {

    private val geoCodeQuerySubject: PublishSubject<String> = PublishSubject.create()

    private val queryDisposable: Disposable

    private val geoCode: MutableLiveData<GeoCode> = MutableLiveData()
    val city: LiveData<String> = MutableLiveData()
    val country: LiveData<String> = MutableLiveData()
    val events: LiveData<Event> = MutableLiveData()

    init {
        queryDisposable = geoCodeQuerySubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() }
            .observeOn(schedulers.mainThread())
            .doOnNext { events.forceValue(Event.Loading) }
            .flatMap { getGeoCodeUseCase.getGeoCode(it) }
            .observeOn(schedulers.mainThread())
            .subscribe(
                ::onSuccess,
                ::onError
            )
    }

    fun searchTextUpdated(text: String) {
        if (text.isEmpty()) {
            events.forceValue(Event.ResultMissing)
        } else {
            geoCodeQuerySubject.onNext(text)
        }
    }

    private fun onError(throwable: Throwable? = null) {
        throwable?.printStackTrace()
    }

    private fun onSuccess(result: GeoCode) {
        if (result.isEmpty()) {
            return events.forceValue(Event.ResultMissing)
        }
        geoCode.value = result
        city.forceValue(result.city)
        country.forceValue(result.country)
        events.forceValue(Event.Result)
    }

    fun onSaveLocation(): Boolean {
        val save = geoCode.value ?: return false
        if (save.isEmpty()) return false
        storeLocationUseCase.storeLocation(save)
        return true
    }

    override fun onCleared() {
        queryDisposable.apply {
            if (!isDisposed) {
                dispose()
            }
        }
    }

    enum class Event {
        ResultMissing, Loading, Result
    }

}