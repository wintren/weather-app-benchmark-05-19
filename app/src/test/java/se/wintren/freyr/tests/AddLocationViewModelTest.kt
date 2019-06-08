package se.wintren.freyr.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.domain.usecase.contracts.StoreLocationUseCase
import se.wintren.freyr.mock.londonGeoCode
import se.wintren.freyr.presentation.viewmodel.AddLocationViewModel
import se.wintren.freyr.presentation.viewmodel.AddLocationViewModel.Event.*
import se.wintren.freyr.util.RxSchedulers
import java.util.concurrent.TimeUnit

class AddLocationViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    lateinit var viewModel: AddLocationViewModel

    @Mock
    lateinit var storeLocationUseCase: StoreLocationUseCase

    @Mock
    lateinit var getGeoCodeUseCase: GetGeoCodeUseCase

    @Mock
    lateinit var schedulers: RxSchedulers

    @Mock
    lateinit var eventObserver: Observer<AddLocationViewModel.Event>

    @Mock
    lateinit var cityObserver: Observer<String>

    @Mock
    lateinit var countryObserver: Observer<String>

    private val scheduler = TestScheduler()

    @Before
    fun setup() {
        Mockito.`when`(schedulers.computation()).thenReturn(scheduler)
        Mockito.`when`(schedulers.mainThread()).thenReturn(scheduler)

        viewModel = AddLocationViewModel(
            storeLocationUseCase,
            getGeoCodeUseCase,
            schedulers
        )

        viewModel.city.observeForever(cityObserver)
        viewModel.country.observeForever(countryObserver)
        viewModel.events.observeForever(eventObserver)
    }

    @Test
    fun `Successful location search`() {
        whenever(getGeoCodeUseCase.getGeoCode(anyString())).thenReturn(Observable.just(londonGeoCode))
        viewModel.searchTextUpdated("London")
        scheduler.advanceTimeBy(800, TimeUnit.MILLISECONDS)
        scheduler.triggerActions()

        verify(eventObserver).onChanged(Loading)
        verify(getGeoCodeUseCase).getGeoCode(anyString())
        verify(eventObserver).onChanged(Result)
        verify(cityObserver).onChanged("London")
        verify(countryObserver).onChanged("United Kingdom")
    }

    @Test
    fun `Unqualified location search`() {
        viewModel.searchTextUpdated("")
        verify(eventObserver).onChanged(ResultMissing)
    }

    @Test
    fun `Error during location search`() {
        whenever(getGeoCodeUseCase.getGeoCode(anyString())).thenReturn(
            Observable.error(NullPointerException("Successful Error"))
        )
        viewModel.searchTextUpdated("Flight mode")
        scheduler.advanceTimeBy(800, TimeUnit.MILLISECONDS)
        scheduler.triggerActions()

        verifyZeroInteractions(cityObserver)
        verifyZeroInteractions(countryObserver)
    }


}