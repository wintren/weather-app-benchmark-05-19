package se.wintren.freyr.di.lifecycle

import dagger.Module
import dagger.android.ContributesAndroidInjector
import se.wintren.freyr.AddLocationFragment
import se.wintren.freyr.ForecastFragment
import se.wintren.freyr.LocationsFragment
import se.wintren.freyr.SettingsFragment

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
