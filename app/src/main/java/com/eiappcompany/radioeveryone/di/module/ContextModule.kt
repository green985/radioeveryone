package com.eiappcompany.radioeveryone.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull
import com.eiappcompany.base.BuildConfig.BASE_SHARED_PREF_KEY
import com.eiappcompany.base.util.helper.SharedHelper

import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ContextModule {

    @Binds
    internal abstract fun bindsContext(application: Application): Context

}
