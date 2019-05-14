package se.wintren.freyr

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocationData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

}
