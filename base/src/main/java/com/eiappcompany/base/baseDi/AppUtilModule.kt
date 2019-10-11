package com.eiappcompany.base.baseDi

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull
import com.eiappcompany.base.BuildConfig
import com.eiappcompany.base.util.helper.AppHelper
import com.eiappcompany.base.util.helper.SharedHelper
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
Created by EiAppCompany
07-10-2019
16:57
 **/

@Module
class AppUtilModule {


    @Singleton
    @Provides
    internal fun provideSharedPreference(@NonNull context: Context): SharedPreferences {
        return context.getSharedPreferences(BuildConfig.BASE_SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().apply {
            setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            serializeNulls()
            setLenient()
        }.create()
    }

    @Provides
    @Singleton
    internal fun provideSharedHelper(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): SharedHelper {
        return SharedHelper(sharedPreferences, gson)
    }

    @Provides
    @Singleton
    internal fun provideAppHelper(
        context: Context
    ): AppHelper {
        return AppHelper(context)
    }


}