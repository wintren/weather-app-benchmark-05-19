package se.wintren.freyr.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import se.wintren.freyr.domain.usecase.contracts.GetGeoCodeUseCase
import se.wintren.freyr.util.RxSchedulers
import javax.inject.Inject

class AddLocationViewModel @Inject constructor(
//    val storeLocation : StoreLocationUseCase,
    val getGeoCode: GetGeoCodeUseCase,
    val schedulers: RxSchedulers
) : ViewModel() {

    fun test() {
        Log.d("Test", "I am a ViewModel: $getGeoCode, $schedulers")
    }

}