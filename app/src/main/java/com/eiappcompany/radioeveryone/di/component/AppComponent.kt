package com.eiappcompany.radioeveryone.di.component

import android.app.Application
import com.eiappcompany.radioeveryone.RadioEveryoneApplication
import com.eiappcompany.radioeveryone.di.module.ActivityBuilder
import com.eiappcompany.radioeveryone.di.module.ContextModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    /*
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ContextModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        DataModule::class,
        DomainModule::class
    ]

     */
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        ContextModule::class
    ]
)
interface AppComponent : AndroidInjector<RadioEveryoneApplication> {

    override fun inject(instance: RadioEveryoneApplication?)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
