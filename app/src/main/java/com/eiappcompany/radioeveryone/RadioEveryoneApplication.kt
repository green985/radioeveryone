package com.eiappcompany.radioeveryone

import androidx.databinding.DataBindingUtil
import com.eiappcompany.radioeveryone.BuildConfig.DEBUG
import com.eiappcompany.radioeveryone.di.component.DaggerAppComponent
import com.eiappcompany.radioeveryone.di.component.DaggerBindingComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


/**
Created by EiAppCompany
04-10-2019
11:34
 **/
class RadioEveryoneApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.uprootAll()
        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }


    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this).also { appComponent ->
            appComponent.inject(this@RadioEveryoneApplication)

            val bindingComponent =
                DaggerBindingComponent.builder().appComponent(appComponent).build()
            DataBindingUtil.setDefaultComponent(bindingComponent)
        }
    }
}
