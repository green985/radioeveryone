package com.eiappcompany.datamodule.dataModuleDi

import com.eiappcompany.base.BuildConfig
import com.eiappcompany.base.baseDi.AppUtilModule
import com.eiappcompany.base.util.helper.SharedHelper
import com.eiappcompany.datamodule.radioService.RadioServiceAPI
import com.eiappcompany.datamodule.util.AuthInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [AppUtilModule::class])
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideDeneme(): String {
        return "I Provide it"
    }


    /**
     * Returns an instance of AuthInterceptor
     *
     * @return an instance of [AuthInterceptor]
     */
    @Provides
    @Singleton
    internal fun provideAuthInterceptor(sharedHelper: SharedHelper): AuthInterceptor {
        return AuthInterceptor(sharedHelper)
    }


    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            interceptors().add(authInterceptor)
            interceptors().add(httpLoggingInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.baseUrl)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttpClient)
        }.build()
    }

    @Provides
    @Singleton
    internal fun provideRadioServiceAPI(retrofit: Retrofit): RadioServiceAPI {
        return retrofit.create(RadioServiceAPI::class.java)
    }

    companion object {
        private const val DEFAULT_TIMEOUT = 60L
    }

}
