package se.wintren.freyr.tests

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyFloat
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import se.wintren.freyr.mock.currentWeatherResponse
import se.wintren.freyr.mock.weatherForecastResponse
import se.wintren.freyr.repository.CombinedWeatherDTO
import se.wintren.freyr.repository.WeatherRepositoryImpl
import se.wintren.freyr.repository.contracts.WeatherRepository
import se.wintren.freyr.repository.network.WeatherAPI
import se.wintren.freyr.util.RxSchedulers

class WeatherRepositoryTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    lateinit var repository: WeatherRepository

    @Mock
    lateinit var schedulers: RxSchedulers

    @Mock
    lateinit var api: WeatherAPI

    @Before
    fun setup() {
        repository = WeatherRepositoryImpl(api, schedulers)

        whenever(schedulers.io()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `Get Weather successful`() {
        whenever(api.getCurrentWeather(anyFloat(), anyFloat())).thenReturn(Single.just(currentWeatherResponse))
        whenever(api.getWeatherForecast(anyFloat(), anyFloat())).thenReturn(Single.just(weatherForecastResponse))
        val combined: CombinedWeatherDTO = currentWeatherResponse to weatherForecastResponse

        val testObserver = repository.getWeather(13F, 37F).test()
        verify(api).getCurrentWeather(anyFloat(), anyFloat())
        verify(api).getWeatherForecast(anyFloat(), anyFloat())
        testObserver.assertValue(combined)
    }

}