package se.wintren.freyr.presentation.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_forecast.view.*
import se.wintren.freyr.R
import se.wintren.freyr.databinding.FragmentForecastBinding
import se.wintren.freyr.domain.data.Forecast
import se.wintren.freyr.presentation.adapter.ForecastAdapter
import se.wintren.freyr.presentation.viewmodel.ForecastViewModel
import javax.inject.Inject

class ForecastFragment : Fragment() {

    private val args: ForecastFragmentArgs by navArgs()

    lateinit var viewModel: ForecastViewModel

    lateinit var binding: FragmentForecastBinding

    lateinit var adapter: ForecastAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forecast, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ForecastAdapter()
        val grid = binding.root.forecastGrid
        grid.layoutManager = GridLayoutManager(context, 3)
        grid.itemAnimator = DefaultItemAnimator()
        grid.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastViewModel::class.java)
        viewModel.weather.observe(this, weatherObserver)
        args.run { viewModel.loadWeather(latitude, longitude) }
    }

    private val weatherObserver = Observer<List<Forecast>> {
        binding.loadingSpinner.visibility = INVISIBLE
        binding.today = it[0]
        adapter.submitForecast(it.subList(1, it.size))
    }

}
