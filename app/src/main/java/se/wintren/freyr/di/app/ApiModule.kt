package se.wintren.freyr.di.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import se.wintren.freyr.BuildConfig
import se.wintren.freyr.repository.network.GeoCodingAPI
import se.wintren.freyr.repository.network.WeatherAPI
import javax.inject.Named
import javax.inject.Singleton

typealias ApiKey = Pair<String, String>

@Module
class ApiModule {

    //region GeoAPI
    @Provides
    @Singleton
    fun provideGeoApiService(
        @Named("GeoAPI") retrofit: Retrofit
    ): GeoCodingAPI {
        return retrofit.create(GeoCodingAPI::class.java)
    }

    @Provides
    @Singleton
    @Named("GeoAPI")
    fun provideGeoRetrofit(
        @Named("GeoAPI") client: OkHttpClient,
        gson: Gson
    ): Retrofit = createRetrofit(gson, client, "https://maps.googleapis.com")

    @Provides
    @Singleton
    @Named("GeoAPI")
    fun provideGeoApiOkHttpClient(
        @Named("GeoAPI") apiKey: ApiKey
    ): OkHttpClient = createOkHttpClient(apiKey)

    @Provides
    @Singleton
    @Named("GeoAPI")
    fun provideGeoApiKey(): ApiKey = "key" to BuildConfig.GOOGLE_API_KEY
    //endregion

    //region WeatherAPI
    @Provides
    @Singleton
    fun provideWeatherApiService(
        @Named("WeatherAPI") retrofit: Retrofit
    ): WeatherAPI {
        return retrofit.create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    @Named("WeatherAPI")
    fun provideWeatherRetrofit(
        @Named("WeatherAPI") client: OkHttpClient,
        gson: Gson
    ): Retrofit = createRetrofit(gson, client, "https://api.openweathermap.org")

    @Provides
    @Singleton
    @Named("WeatherAPI")
    fun provideWeatherApiOkHttpClient(
        @Named("WeatherAPI") apiKey: ApiKey
    ): OkHttpClient = createOkHttpClient(apiKey)


    @Provides
    @Singleton
    @Named("WeatherAPI")
    fun provideWeatherApiKey(): ApiKey = "appid" to BuildConfig.OPEN_WEATHER_API_KEY
    //endregion

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    //region Create helper classes
    private fun createRetrofit(gson: Gson, client: OkHttpClient, host: String): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(host)
            client(client)
            addConverterFactory(GsonConverterFactory.create(gson))
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }.build()
    }

    private fun createOkHttpClient(apiKey: ApiKey): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addInterceptor { chain ->
                val request = chain.request().let { req ->
                    val url = req.url().newBuilder()
                        .addQueryParameter(apiKey.first, apiKey.second)
                        .build()
                    req.newBuilder().url(url).build()
                }
                chain.proceed(request)
            }
        }.build()
    }
    //endregion

}
