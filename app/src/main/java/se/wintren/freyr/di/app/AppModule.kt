package se.wintren.freyr.di.app

import dagger.Module
import dagger.Provides
import se.wintren.freyr.domain.mapper.GeoCodeDataMapperImpl
import se.wintren.freyr.domain.mapper.contract.GeoCodeDataMapper
import se.wintren.freyr.domain.usecase.GetGeoCodeUseCaseImpl
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.repository.LocationsRepositoryImpl
import se.wintren.freyr.repository.contracts.LocationsRepository
import se.wintren.freyr.repository.network.GeoCodingAPI
import se.wintren.freyr.util.RuntimeRxSchedulers
import se.wintren.freyr.util.RxSchedulers
import javax.inject.Singleton

@Module
class AppModule {

    //region Provide Repositories
    @Provides
    @Singleton
    fun provideLocationsRepository(
        api: GeoCodingAPI
    ): LocationsRepository {
        return LocationsRepositoryImpl(api)
    }
    //endregion

    //region Provide Use Cases
    @Provides
    @Singleton
    fun provideGetGeoCodeUseCase(
        repository: LocationsRepository,
        mapper: GeoCodeDataMapper
    ): GetGeoCodeUseCase {
        return GetGeoCodeUseCaseImpl(repository, mapper)
    }
    //endregion

    //region Provide Mappers
    @Provides
    @Singleton
    fun provideGeoCodeMapper(): GeoCodeDataMapper = GeoCodeDataMapperImpl()
    //endregion

    //region Provide utils
    @Provides
    @Singleton
    fun provideRxSchedulers(): RxSchedulers = RuntimeRxSchedulers()
    //endregion

}