package se.wintren.freyr.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Context.getInputManager() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager