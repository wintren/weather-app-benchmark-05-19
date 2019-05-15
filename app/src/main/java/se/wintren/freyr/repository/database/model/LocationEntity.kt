package se.wintren.freyr.repository.database.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["city"])
data class LocationEntity(
    @SerializedName("city") val city: String,
    @SerializedName("country") val country: String,
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readDouble(),
        source.readDouble()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(city)
        writeString(country)
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
