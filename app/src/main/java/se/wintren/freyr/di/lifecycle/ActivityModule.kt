package se.wintren.freyr.di.lifecycle

import dagger.Module
import dagger.android.ContributesAndroidInjector
import se.wintren.freyr.presentation.lifecycle.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

}
