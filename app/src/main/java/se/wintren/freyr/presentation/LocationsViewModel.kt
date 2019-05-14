package se.wintren.freyr.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import se.wintren.freyr.GeoCodingAPI
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val geoApi: GeoCodingAPI
) : ViewModel() {

    fun testApi(context: Context) {
        Log.d("Bajs", "I has geo?: $geoApi")
        geoApi.geocode("London")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }, {
                it.printStackTrace()
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }

}
