package se.wintren.freyr.di.app

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import se.wintren.freyr.repository.database.AppDatabase
import se.wintren.freyr.repository.database.LocationDao
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "Freyr.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideLocationDao(appDatabase: AppDatabase): LocationDao {
        return appDatabase.locationDao()
    }

}
