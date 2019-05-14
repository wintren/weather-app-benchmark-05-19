package se.wintren.freyr.repository.database.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class LocationEntity(
    @SerializedName("id") val id: Long,
    @SerializedName("cityName") val cityName: String,
    @SerializedName("region") val region: String,
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString()!!,
        source.readString()!!,
        source.readDouble(),
        source.readDouble()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(cityName)
        writeString(region)
        writeDouble(lon)
        writeDouble(lat)
    }

    companion object CREATOR : Parcelable.Creator<LocationEntity> {
        override fun createFromParcel(parcel: Parcel): LocationEntity {
            return LocationEntity(parcel)
        }

        override fun newArray(size: Int): Array<LocationEntity?> {
            return arrayOfNulls(size)
        }
    }
}
