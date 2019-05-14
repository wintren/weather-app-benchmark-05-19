package se.wintren.freyr

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: LocationData): Long

    @Query("SELECT * FROM `LocationData`")
    fun getAllLocations(): List<LocationData>

}
