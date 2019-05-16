package se.wintren.freyr.repository

import android.content.SharedPreferences

class DegreesService(private val preferences: SharedPreferences) {

    private val useFahrenheit: Boolean
        get() = preferences.getBoolean("displayFahrenheit", false)

    val unitSymbol
        get() = if (useFahrenheit) "f" else "c"

    fun convertKelvin(kelvin: Float): Int {
        return if (useFahrenheit) kelvin.toFahrenheit() else kelvin.toCelsius()
    }

    private fun Float.toFahrenheit(): Int = (this * fahrenheitConstA - fahrenheitConstB).toInt()

    private fun Float.toCelsius(): Int = (this - celsiusConst).toInt()

    companion object {
        const val fahrenheitConstA = 9.0 / 5.0
        const val fahrenheitConstB = 459.67

        const val celsiusConst = 273.15
    }

}