package com.eiappcompany.radioeveryone.di.builder

import com.eiappcompany.radioeveryone.ui.main.MainActivity
import com.eiappcompany.radioeveryone.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}
