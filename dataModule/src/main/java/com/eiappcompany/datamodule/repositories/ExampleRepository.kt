package com.eiappcompany.datamodule.repositories

import android.util.Log
import androidx.annotation.NonNull
import com.eiappcompany.base.util.helper.AppHelper
import com.eiappcompany.base.util.helper.extensions.makeObservableWithStatus3
import com.eiappcompany.base.util.viewState.ViewState
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
    ): Observable<ViewState<LoginResponseObject>> {
        return getApi().login(LoginRequest("deneme2@gmail.com", "Asd123"))
            .makeObservableWithStatus3()
            .flatMap {
                interceptResponseExample2(it)
            }
    }

    fun deneme() {
        Log.d("asdasıdaıusdıasd", "asdbhayuhsbd")
    }
}