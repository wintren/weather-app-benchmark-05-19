package se.wintren.freyr.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import se.wintren.freyr.repository.database.model.LocationEntity

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: LocationEntity): Long

    @Query("SELECT * FROM LocationEntity")
    fun getAllLocations(): LiveData<List<LocationEntity>>

}
