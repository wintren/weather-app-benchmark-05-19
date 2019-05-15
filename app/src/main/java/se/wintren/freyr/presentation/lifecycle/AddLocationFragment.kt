package se.wintren.freyr.presentation.lifecycle

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_add_location.*
import se.wintren.freyr.R
import se.wintren.freyr.extensions.closeKeybordOnFocusLost
import se.wintren.freyr.extensions.enableIcon
import se.wintren.freyr.extensions.onTextChanged
import se.wintren.freyr.presentation.viewmodel.AddLocationViewModel
import javax.inject.Inject

class AddLocationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: AddLocationViewModel

    lateinit var doneMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initViewModel()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_location_menu, menu)
        doneMenuItem = menu.findItem(R.id.menuActionDone)
        doneMenuItem.enableIcon(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_add_location, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputEditText.onTextChanged(viewModel::searchTextUpdated)
        inputEditText.closeKeybordOnFocusLost()
    }

    override fun onDestroy() {
        viewModel.onAbort()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.onSaveLocation()
        findNavController().navigateUp()
        return true
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddLocationViewModel::class.java)
        viewModel.onLocationResult = ::onResult
        viewModel.onSearchStart = ::onSearchStarted
        viewModel.onLocationMissing = ::onLocationMissing
    }

    // View methods

    private fun onResult(city: String, country: String) {
        resultCity.text = city
        resultCountry.text = country
        showResultCard(showCard = true)
        doneMenuItem.enableIcon(true)
    }

    private fun onSearchStarted() {
        showResultCard(showCard = true, loading = true)
    }

    private fun onLocationMissing() {
        showResultCard(showCard = false, loading = true)
        doneMenuItem.enableIcon(false)
    }

    private fun showResultCard(showCard: Boolean, loading: Boolean = false) {
        resultCard.visibility = if (showCard) View.VISIBLE else View.INVISIBLE

        if (loading) {
            loadingSpinner.visibility = View.VISIBLE
            locationResultsContainer.visibility = View.INVISIBLE
        } else {
            loadingSpinner.visibility = View.INVISIBLE
            locationResultsContainer.visibility = View.VISIBLE
        }
    }

}
