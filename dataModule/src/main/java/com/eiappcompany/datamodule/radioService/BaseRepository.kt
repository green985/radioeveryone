package com.eiappcompany.datamodule.radioService

import androidx.annotation.NonNull
import com.eiappcompany.base.util.helper.AppHelper
import com.eiappcompany.base.util.viewState.ViewState
import com.eiappcompany.datamodule.R
import com.eiappcompany.datamodule.repositories.GenericResponse
import io.reactivex.Observable
import org.jetbrains.annotations.Contract
import retrofit2.Response
import javax.inject.Inject

/**
Created by EiAppCompany
07-10-2019
17:13
 **/

abstract class BaseRepository(
    @param:NonNull private val appHelper: AppHelper
) {

    @Inject
    lateinit var radioServiceAPI: RadioServiceAPI


    fun getApi(): RadioServiceAPI {
        return radioServiceAPI
    }


    @Contract("null->fail")
    protected fun <T> interceptResponse(@NonNull genericResponse: Response<GenericResponse<T>>): Observable<T> {
        val requestCode = genericResponse.code()
        if (requestCode != 200) {
            return Observable.error<T>(Throwable(appHelper.getString(R.string.network_error_msg)))
        }
        if (!genericResponse.isSuccessful) {
            return Observable.error<T>(Throwable(appHelper.getString(R.string.network_error_msg)))
        }
        genericResponse.body().apply {
            if (this == null) {
                return Observable.error<T>(Throwable(appHelper.getString(R.string.network_error_msg)))
            }
            if (ResultStatus != true) {
                return if (ResultMessage != null)
                    Observable.error<T>(Throwable(ResultMessage))
                else
                    Observable.error<T>(Throwable(appHelper.getString(R.string.network_error_msg)))
            }
            if (ResultObject == null) {
                return if (ResultMessage != null)
                    Observable.error<T>(Throwable(ResultMessage))
                else
                    Observable.error<T>(Throwable(appHelper.getString(R.string.network_error_msg)))
            }
            return Observable.just<T>(genericResponse.body()?.ResultObject)
        }
    }

    @Contract("null->fail")
    protected fun <T> interceptResponseExample(@NonNull genericResponse: Response<T>): Observable<T> {
        return Observable.just<T>(genericResponse.body())
    }



    /**
    Server tarafından gelen verinin ayıklanarak error ve success statuslerinin kontrol edilmesi
     Generic response için veya herhangi response çeşidine gore özelleştirilebilir.
     ViewState durumlarını içinde barındırır.
     */
    @Contract("null->fail")
    protected fun <T> interceptResponseExample2(@NonNull genericResponse: Response<GenericResponse<T>>): Observable<ViewState<T>> {
        val requestCode = genericResponse.code()
        if (requestCode != 200) {
            return Observable.just<ViewState<T>>(ViewState.error(appHelper.getString(R.string.network_error_msg) + "\"asdasd1111"))
        }
        if (!genericResponse.isSuccessful) {
            return Observable.just<ViewState<T>>(ViewState.error(appHelper.getString(R.string.network_error_msg) + "\"asdasd\"233123"))
        }

        genericResponse.body().apply {
            if (this == null) {
                return Observable.just<ViewState<T>>(ViewState.error(appHelper.getString(R.string.network_error_msg) + "asdasd"))
            }

            if (ResultStatus != true) {
                return if (ResultMessage != null)
                    Observable.just<ViewState<T>>(ViewState.error(ResultMessage + "asdasd"))
                else
                    Observable.just<ViewState<T>>(ViewState.error(appHelper.getString(R.string.network_error_msg)))
            }

            if (ResultObject == null) {
                return if (ResultMessage != null)
                    Observable.just<ViewState<T>>(ViewState.error(ResultMessage + "asdasd"))
                else
                    Observable.just<ViewState<T>>(ViewState.error(appHelper.getString(R.string.network_error_msg)))
            }
            return Observable.just<ViewState<T>>(ViewState.success(genericResponse.body()!!.ResultObject))
        }

    }


}