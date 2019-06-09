package se.wintren.freyr.presentation.lifecycle

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_add_location.*
import se.wintren.freyr.R
import se.wintren.freyr.databinding.FragmentAddLocationBinding
import se.wintren.freyr.extensions.closeKeybordOnFocusLost
import se.wintren.freyr.extensions.enableIcon
import se.wintren.freyr.extensions.onTextChanged
import se.wintren.freyr.presentation.viewmodel.AddLocationViewModel
import se.wintren.freyr.presentation.viewmodel.AddLocationViewModel.Event.*
import javax.inject.Inject

open class AddLocationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: AddLocationViewModel

    private lateinit var doneMenuItem: MenuItem

    private lateinit var binding: FragmentAddLocationBinding

    override fun onAttach(context: Context) {
        injectMembers()
        super.onAttach(context)
    }

    protected open fun injectMembers() {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddLocationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_location, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputEditText.onTextChanged(viewModel::searchTextUpdated)
        inputEditText.closeKeybordOnFocusLost()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.saveLocation()
        findNavController().navigateUp()
        return true
    }

    private fun initViewModel() {
        viewModel.events.observe(this, eventsObserver)
    }

    private val eventsObserver = Observer<AddLocationViewModel.Event> {
        when (it) {
            Loading -> showResultCard(showCard = true, loading = true)
            Result -> showResultCard(showCard = true)
            ResultMissing -> showResultCard(showCard = false)
        }
    }

    private fun showResultCard(showCard: Boolean, loading: Boolean = false) {
        resultCard.visibility = if (showCard) View.VISIBLE else View.INVISIBLE
        doneMenuItem.enableIcon(showCard && !loading)
        if (loading) {
            loadingSpinner.visibility = View.VISIBLE
            locationResultsContainer.visibility = View.INVISIBLE
        } else {
            loadingSpinner.visibility = View.INVISIBLE
            locationResultsContainer.visibility = View.VISIBLE
        }
    }

}
