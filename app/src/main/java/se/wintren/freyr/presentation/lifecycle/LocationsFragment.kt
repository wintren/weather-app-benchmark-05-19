package se.wintren.freyr.presentation.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_locations.*
import se.wintren.freyr.R
import se.wintren.freyr.domain.data.Location
import se.wintren.freyr.presentation.adapter.LocationsAdapter
import se.wintren.freyr.presentation.viewmodel.LocationsViewModel
import javax.inject.Inject

class LocationsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LocationsViewModel

    private lateinit var locationsAdapter: LocationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LocationsViewModel::class.java)
        viewModel.locations.observe(this, locationObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_locations, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addLocationFAB.setOnClickListener(::onAddLocationClick)

        locationsAdapter = LocationsAdapter(::onLocationClicked)
        locationsList.adapter = locationsAdapter
        locationsList.layoutManager = LinearLayoutManager(context)
    }

    private val locationObserver = Observer<List<Location>> {
        locationsAdapter.submitLocations(it)
        noLocationsHint.visibility = if (it.isEmpty()) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    private fun onAddLocationClick(v: View) {
        val action = LocationsFragmentDirections.toAddLocation()
        findNavController().navigate(action)
    }

    private fun onLocationClicked(location: Location) {
        val action = with(location) {
            LocationsFragmentDirections.toForecast(formatted, lon.toFloat(), lat.toFloat())
        }
        findNavController().navigate(action)
    }

}
