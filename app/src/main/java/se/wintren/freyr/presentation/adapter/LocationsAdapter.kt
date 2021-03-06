package se.wintren.freyr.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import se.wintren.freyr.R
import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.presentation.util.DataBindingAdapter
import se.wintren.freyr.presentation.util.DataBindingViewHolder

class LocationsAdapter(private val onClick: (location: Location) -> Unit) :
    DataBindingAdapter<Location>(DiffCallback()) {

    private val locations = mutableListOf<Location>()

    fun submitLocations(newLocations: List<Location>) {
        locations.clear()
        locations.addAll(newLocations)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = locations.size

    override fun getItemViewType(position: Int): Int = R.layout.item_location

    override fun getItem(position: Int): Location = locations[position]

    override fun onBindViewHolder(holder: DataBindingViewHolder<Location>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.findViewById<ViewGroup>(R.id.container).setOnClickListener {
            onClick.invoke(getItem(position))
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return areContentsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return (oldItem.city == newItem.city)
                    && (oldItem.country == newItem.country)
        }
    }
}
