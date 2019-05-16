package se.wintren.freyr.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun ImageView.loadImageUrl(url: String?) {
    Glide.with(context).load(url).into(this)
}
