package se.wintren.freyr.di.lifecycle

import dagger.Module
import dagger.android.ContributesAndroidInjector
import se.wintren.freyr.presentation.lifecycle.AddLocationFragment
import se.wintren.freyr.presentation.lifecycle.ForecastFragment
import se.wintren.freyr.presentation.lifecycle.LocationsFragment
import se.wintren.freyr.presentation.lifecycle.SettingsFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLocationsFragment(): LocationsFragment

    @ContributesAndroidInjector
    abstract fun contributeAddLocationFragment(): AddLocationFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeForecastFragment(): ForecastFragment
}
