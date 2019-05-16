package se.wintren.freyr.presentation.util

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import se.wintren.freyr.BR

class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}