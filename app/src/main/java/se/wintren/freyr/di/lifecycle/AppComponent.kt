package se.wintren.freyr.di.lifecycle

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import se.wintren.freyr.di.app.ApiModule
import se.wintren.freyr.di.app.AppModule
import se.wintren.freyr.di.app.DbModule
import se.wintren.freyr.presentation.lifecycle.FreyrApplication
import se.wintren.freyr.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        AppModule::class,
        ApiModule::class,
        DbModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(freyrApplication: FreyrApplication)
}
