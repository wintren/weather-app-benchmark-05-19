package se.wintren.freyr.tests

import com.nhaarman.mockito_kotlin.any
import io.reactivex.Observable.error
import io.reactivex.Observable.just
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import se.wintren.freyr.domain.mapper.contract.GeoCodeDataMapper
import se.wintren.freyr.domain.usecase.GetGeoCodeUseCaseImpl
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.mock.londonGeoCode
import se.wintren.freyr.mock.londonResponse
import se.wintren.freyr.repository.contracts.LocationsRepository
import se.wintren.freyr.util.RxSchedulers
import org.mockito.Mockito.`when` as whenever

/**
 * Test to verify the simple responsibilities of a repository/api use case
 */
class GetGeoCodeUseCaseTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    lateinit var useCase: GetGeoCodeUseCase

    @Mock
    lateinit var locationRepository: LocationsRepository

    @Mock
    lateinit var mapper: GeoCodeDataMapper

    @Mock
    lateinit var schedulers: RxSchedulers

    @Before
    fun setup() {
        useCase = GetGeoCodeUseCaseImpl(
            locationRepository,
            mapper,
            schedulers
        )

        whenever(schedulers.computation()).thenReturn(Schedulers.trampoline())
    }

    @Test
    fun `Get GeoCode successful`() {
        whenever(locationRepository.getGeoCode(anyString())).thenReturn(just(londonResponse))
        whenever(mapper.mapResponse(any())).thenReturn(londonGeoCode)

        val testObserver = useCase.getGeoCode("London").test()
        testObserver.assertValue(londonGeoCode)
    }

    @Test
    fun `Get geocode error`() {
        whenever(locationRepository.getGeoCode(anyString())).thenReturn(error(NullPointerException()))

        val testObserver = useCase.getGeoCode("Kashyyyk").test()
        testObserver.assertError { it is NullPointerException }
    }

    @Test
    fun `Get geocode mapper error`() {
        whenever(locationRepository.getGeoCode(anyString())).thenReturn(just(londonResponse))
        whenever(mapper.mapResponse(any())).thenThrow(IllegalArgumentException("Bad format!"))

        val testObserver = useCase.getGeoCode("Grandmas favorite recipe").test()
        testObserver.assertError { it is IllegalArgumentException }
    }

}