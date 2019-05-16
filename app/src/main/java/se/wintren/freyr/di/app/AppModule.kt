package se.wintren.freyr.di.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import se.wintren.freyr.domain.mapper.GeoCodeDataMapperImpl
import se.wintren.freyr.domain.mapper.GeoCodeToLocationMapperImpl
import se.wintren.freyr.domain.mapper.LocationEntityMapperImpl
import se.wintren.freyr.domain.mapper.WeatherForecastDataMapperImpl
import se.wintren.freyr.domain.mapper.contract.GeoCodeDataMapper
import se.wintren.freyr.domain.mapper.contract.GeoCodeToLocationMapper
import se.wintren.freyr.domain.mapper.contract.LocationEntityMapper
import se.wintren.freyr.domain.mapper.contract.WeatherForecastDataMapper
import se.wintren.freyr.domain.usecase.GetGeoCodeUseCaseImpl
import se.wintren.freyr.domain.usecase.GetLocationsUseCaseImpl
import se.wintren.freyr.domain.usecase.GetWeatherForecastUseCaseImpl
import se.wintren.freyr.domain.usecase.StoreLocationUseCaseImpl
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.domain.usecase.contracts.GetLocationsUseCase
import se.wintren.freyr.domain.usecase.contracts.GetWeatherForecastUseCase
import se.wintren.freyr.domain.usecase.contracts.StoreLocationUseCase
import se.wintren.freyr.repository.LocationsRepositoryImpl
import se.wintren.freyr.repository.DegreesService
import se.wintren.freyr.repository.WeatherRepositoryImpl
import se.wintren.freyr.repository.contracts.LocationsRepository
import se.wintren.freyr.repository.contracts.WeatherRepository
import se.wintren.freyr.repository.database.LocationDao
import se.wintren.freyr.repository.network.GeoCodingAPI
import se.wintren.freyr.repository.network.WeatherAPI
import se.wintren.freyr.util.RuntimeRxSchedulers
import se.wintren.freyr.util.RxSchedulers
import javax.inject.Singleton

@Module
class AppModule() {

    @Provides
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    //region Provide Repositories
    @Provides
    @Singleton
    fun provideLocationsRepository(
        api: GeoCodingAPI,
        schedulers: RxSchedulers,
        locationEntityMapper: LocationEntityMapper,
        locationDao: LocationDao
    ): LocationsRepository = LocationsRepositoryImpl(
        api,
        locationDao,
        locationEntityMapper,
        schedulers
    )

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherAPI,
        schedulers: RxSchedulers
    ): WeatherRepository = WeatherRepositoryImpl(api, schedulers)
    //endregion

    //region Provide Use Cases
    @Provides
    @Singleton
    fun provideGetGeoCodeUseCase(
        repository: LocationsRepository,
        mapper: GeoCodeDataMapper,
        schedulers: RxSchedulers
    ): GetGeoCodeUseCase = GetGeoCodeUseCaseImpl(repository, mapper, schedulers)

    @Provides
    @Singleton
    fun provideStoreLocationUseCase(
        repository: LocationsRepository,
        mapper: GeoCodeToLocationMapper
    ): StoreLocationUseCase = StoreLocationUseCaseImpl(repository, mapper)

    @Provides
    @Singleton
    fun provideGetLocationsUseCase(
        repository: LocationsRepository
    ): GetLocationsUseCase = GetLocationsUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetWeatherUseCase(
        repository: WeatherRepository,
        mapper: WeatherForecastDataMapper,
        schedulers: RxSchedulers
    ): GetWeatherForecastUseCase = GetWeatherForecastUseCaseImpl(repository, mapper, schedulers)
    //endregion

    //region Provide Mappers
    @Provides
    @Singleton
    fun provideGeoCodeToLocationMapper(): GeoCodeToLocationMapper = GeoCodeToLocationMapperImpl()

    @Provides
    @Singleton
    fun provideLocationEntityMapper(): LocationEntityMapper = LocationEntityMapperImpl()

    @Provides
    @Singleton
    fun provideGeoCodeMapper(): GeoCodeDataMapper = GeoCodeDataMapperImpl()

    @Provides
    @Singleton
    fun provideWeatherMapper(
        preferenceService: DegreesService
    ): WeatherForecastDataMapper = WeatherForecastDataMapperImpl(preferenceService)
    //endregion

    //region Provide utils
    @Provides
    @Singleton
    fun provideRxSchedulers(): RxSchedulers = RuntimeRxSchedulers()
    //endregion

    @Provides
    @Singleton
    fun providePreferenceService(
        preferences: SharedPreferences
    ): DegreesService = DegreesService(preferences)

    @Provides
    @Singleton
    fun provideSharedPreferences(
        context: Context
    ): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}