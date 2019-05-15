package se.wintren.freyr.extensions

import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import se.wintren.freyr.util.EMPTY_STRING
import se.wintren.freyr.util.TextChangedWatcher

fun EditText.onTextChanged(lambda: (String) -> Unit) {
    addTextChangedListener(object : TextChangedWatcher() {
        override fun afterTextChanged(editable: Editable?) {
            val text = editable?.toString() ?: EMPTY_STRING
            lambda.invoke(text)
        }

    })
}

fun EditText.closeKeybordOnFocusLost(): View.OnFocusChangeListener {
    return View.OnFocusChangeListener { view, hasFocus ->
        if (!hasFocus) {
            context.getInputManager().hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }.also {
        onFocusChangeListener = it
    }
}

