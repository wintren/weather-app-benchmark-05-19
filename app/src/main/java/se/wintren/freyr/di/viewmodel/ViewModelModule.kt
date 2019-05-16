package se.wintren.freyr.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import se.wintren.freyr.presentation.viewmodel.AddLocationViewModel
import se.wintren.freyr.presentation.viewmodel.ForecastViewModel
import se.wintren.freyr.presentation.viewmodel.LocationsViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    protected abstract fun bindLocationsViewModel(viewModel: LocationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddLocationViewModel::class)
    protected abstract fun bindAddLocationViewModel(viewModel: AddLocationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    protected abstract fun bindForecastViewModel(viewModel: ForecastViewModel): ViewModel

}
