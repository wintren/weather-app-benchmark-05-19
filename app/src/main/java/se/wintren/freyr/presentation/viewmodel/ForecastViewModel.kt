package se.wintren.freyr.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import se.wintren.freyr.domain.data.Forecast
import se.wintren.freyr.domain.usecase.contracts.GetWeatherForecastUseCase
import se.wintren.freyr.extensions.forceValue
import se.wintren.freyr.util.RxSchedulers
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherForecastUseCase,
    private val schedulers: RxSchedulers
) : ViewModel() {

    val weather: LiveData<List<Forecast>> = MutableLiveData()

    private val disposables = CompositeDisposable()

    fun loadWeather(latitude: Float, longitude: Float) {
        val disposable = getWeatherUseCase
            .getWeather(latitude, longitude)
            .observeOn(schedulers.mainThread())
            .subscribe(
                { weather.forceValue(it) },
                { it.printStackTrace() }
            )
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
    }
}
