package se.wintren.freyr.util

import android.text.TextWatcher

abstract class TextChangedWatcher : TextWatcher {

    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // do nothing
    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // do nothing
    }

}
