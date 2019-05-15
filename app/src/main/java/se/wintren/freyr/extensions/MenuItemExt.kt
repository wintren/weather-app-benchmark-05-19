package se.wintren.freyr.extensions

import android.view.MenuItem

fun MenuItem.enableIcon(enable: Boolean) {
    val alpha = if (enable) { 255 } else { 130 }
    isEnabled = enable
    icon.alpha = alpha
}