package se.wintren.freyr.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import se.wintren.freyr.R
import se.wintren.freyr.domain.data.Forecast
import se.wintren.freyr.presentation.util.DataBindingAdapter

class ForecastAdapter : DataBindingAdapter<Forecast>(DiffCallback()) {

    private val forecasts = mutableListOf<Forecast>()

    fun submitForecast(newForecasts: List<Forecast>) {
        forecasts.clear()
        forecasts.addAll(newForecasts)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = forecasts.size

    override fun getItemViewType(position: Int): Int = R.layout.item_day_forecast

    override fun getItem(position: Int): Forecast = forecasts[position]

    class DiffCallback : DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return false
        }
    }
}