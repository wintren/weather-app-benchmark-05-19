package se.wintren.freyr

import androidx.annotation.UiThread
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import se.wintren.freyr.domain.data.GeoCode
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.domain.usecase.contracts.StoreLocationUseCase
import se.wintren.freyr.presentation.lifecycle.AddLocationFragment
import se.wintren.freyr.presentation.viewmodel.AddLocationViewModel
import se.wintren.freyr.util.RxSchedulers
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class AddLocationFragmentTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    companion object {
        val storeLocationUseCase = mock<StoreLocationUseCase>()
        val getGeoCodeUseCase = mock<GetGeoCodeUseCase>()
        val schedulers = mock<RxSchedulers>()
        val testScheduler = TestScheduler()
    }

    class TestAddLocationFragment : AddLocationFragment() {
        override fun injectMembers() {
            viewModel = AddLocationViewModel(
                storeLocationUseCase,
                getGeoCodeUseCase,
                schedulers
            )
        }
    }

    lateinit var scenario: FragmentScenario<AddLocationFragment>

    @Before
    fun setup() {
        whenever(schedulers.computation()).thenReturn(testScheduler)
        whenever(schedulers.mainThread()).thenReturn(testScheduler)
        whenever(schedulers.io()).thenReturn(testScheduler)
        scenario = launchFragmentInContainer(null, R.style.AppTheme) { TestAddLocationFragment() }
    }

    @Test
    fun testInitialState() {
        onView(withId(R.id.inputEditText)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.resultCard)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }

    private val londonGeoCode: GeoCode = GeoCode("London", "United Kingdom", 51.5073509, -0.1277583)

    /** Test failing because of threading issue */
//    @Test
//    fun successfulSearchFlow() {
//        whenever(getGeoCodeUseCase.getGeoCode(anyString())).thenReturn(Observable.just(londonGeoCode))
//        onView(withId(R.id.inputEditText)).perform(typeText("London"))
//        testScheduler.triggerActions()
//        testScheduler.advanceTimeBy(600, TimeUnit.MILLISECONDS)
//        onView(withId(R.id.resultCity)).check(matches(withText("London")))
//        onView(withId(R.id.resultCountry)).check(matches(withText("United Kingdom")))
//    }

}
