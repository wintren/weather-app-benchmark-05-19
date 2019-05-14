package se.wintren.freyr.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import se.wintren.freyr.repository.database.model.LocationEntity

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

}
