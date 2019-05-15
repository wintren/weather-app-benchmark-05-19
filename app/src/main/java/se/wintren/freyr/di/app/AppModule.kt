package se.wintren.freyr.di.app

import dagger.Module
import dagger.Provides
import se.wintren.freyr.domain.mapper.GeoCodeDataMapperImpl
import se.wintren.freyr.domain.mapper.contract.GeoCodeDataMapper
import se.wintren.freyr.domain.mapper.contract.GeoCodeToLocationMapper
import se.wintren.freyr.domain.mapper.GeoCodeToLocationMapperImpl
import se.wintren.freyr.domain.mapper.LocationEntityMapperImpl
import se.wintren.freyr.domain.mapper.contract.LocationEntityMapper
import se.wintren.freyr.domain.usecase.GetGeoCodeUseCaseImpl
import se.wintren.freyr.domain.usecase.GetLocationsUseCaseImpl
import se.wintren.freyr.domain.usecase.StoreLocationUseCaseImpl
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.domain.usecase.contracts.GetLocationsUseCase
import se.wintren.freyr.domain.usecase.contracts.StoreLocationUseCase
import se.wintren.freyr.repository.LocationsRepositoryImpl
import se.wintren.freyr.repository.contracts.LocationsRepository
import se.wintren.freyr.repository.database.LocationDao
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
        api: GeoCodingAPI,
        schedulers: RxSchedulers,
        locationEntityMapper: LocationEntityMapper,
        locationDao: LocationDao
    ): LocationsRepository {
        return LocationsRepositoryImpl(api, locationDao, locationEntityMapper, schedulers)
    }
    //endregion

    //region Provide Use Cases
    @Provides
    @Singleton
    fun provideGetGeoCodeUseCase(
        repository: LocationsRepository,
        mapper: GeoCodeDataMapper,
        schedulers: RxSchedulers
    ): GetGeoCodeUseCase {
        return GetGeoCodeUseCaseImpl(repository, mapper, schedulers)
    }

    @Provides
    @Singleton
    fun provideStoreLocationUseCase(
        repository: LocationsRepository,
        mapper: GeoCodeToLocationMapper
    ): StoreLocationUseCase {
        return StoreLocationUseCaseImpl(repository, mapper)
    }

    @Provides
    @Singleton
    fun provideGetLocationsUseCase(
        repository: LocationsRepository
    ): GetLocationsUseCase {
        return GetLocationsUseCaseImpl(repository)
    }
    //endregion

    //region Provide Mappers
    @Provides
    @Singleton
    fun provideGeoCodeToLocationMapper(): GeoCodeToLocationMapper {
        return GeoCodeToLocationMapperImpl()
    }

    @Provides
    @Singleton
    fun provideLocationEntityMapper(): LocationEntityMapper {
        return LocationEntityMapperImpl()
    }

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