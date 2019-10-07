package com.eiappcompany.datamodule.repositories

import android.util.Log
import androidx.annotation.NonNull
import com.eiappcompany.base.util.helper.AppHelper
import com.eiappcompany.datamodule.extensions.makeObservable
import com.eiappcompany.datamodule.radioService.BaseRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
Created by EiAppCompany
07-10-2019
17:19
 **/


class ExampleRepository @Inject
constructor(
    @NonNull appHelper: AppHelper
) : BaseRepository(appHelper) {

    fun login(
    ): Observable<Any> {
        return getApi().login()
            .makeObservable(null)
            .flatMap { interceptResponseExample(it) }
    }

    fun deneme() {
        Log.d("asdasıdaıusdıasd", "asdbhayuhsbd")
    }
}