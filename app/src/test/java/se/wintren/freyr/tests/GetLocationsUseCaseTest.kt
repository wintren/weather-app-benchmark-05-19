package se.wintren.freyr.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import se.wintren.freyr.domain.usecase.GetLocationsUseCaseImpl
import se.wintren.freyr.domain.usecase.contracts.GetLocationsUseCase
import se.wintren.freyr.mock.locations
import se.wintren.freyr.repository.contracts.LocationsRepository
import org.mockito.Mockito.`when` as whenever

/**
 * Test to verify testing rules using LiveData because of Android Main Thread handling
 */
class GetLocationsUseCaseTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    lateinit var useCase: GetLocationsUseCase

    @Mock
    lateinit var locationRepository: LocationsRepository

    @Before
    fun setup() {
        useCase = GetLocationsUseCaseImpl(locationRepository)
    }

    @Test
    fun `Test get Locations successful`() {
        whenever(locationRepository.getLocations()).thenReturn(MutableLiveData(locations))
        val liveData = useCase.getLocations()

        assertEquals(locations, liveData.value)
    }

}