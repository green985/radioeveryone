package com.eiappcompany.radioeveryone

import com.eiappcompany.radioeveryone.BuildConfig.DEBUG

/**
Created by EiAppCompany
04-10-2019
11:34
 **/
class RadyoEveryoneApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.uprootAll()
        if (DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this).also { appComponent ->
            appComponent.inject(this@MazeApp)

            val bindingComponent =
                DaggerBindingComponent.builder().appComponent(appComponent).build()
            DataBindingUtil.setDefaultComponent(bindingComponent)
        }
    }
}
