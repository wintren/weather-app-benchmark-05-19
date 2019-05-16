package se.wintren.freyr.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.forceValue(value: T) {
    val mutable = this as MutableLiveData<T>
    mutable.value = value
}