package com.eiappcompany.datamodule.repositories

import androidx.annotation.NonNull
import com.eiappcompany.base.util.helper.AppHelper
import com.eiappcompany.base.util.helper.extensions.makeObservableWithStatus3
import com.eiappcompany.base.util.viewState.ViewState
import com.eiappcompany.datamodule.radioService.BaseRepository
import com.eiappcompany.datamodule.repositories.radioModels.RadioResponseModel
import com.eiappcompany.datamodule.repositories.radioModels.RadioUrlRequestModel
import io.reactivex.Observable
import javax.inject.Inject

/**
Created by EiAppCompany
25-10-2019
16:24
 **/

class RadioDataRepository @Inject
constructor(
    @NonNull var appHelper: AppHelper
) : BaseRepository(appHelper) {

    fun getRadioStreamData(
        radioId: String
    ): Observable<ViewState<RadioResponseModel>> {
        return getApi().getRadioUrlData(RadioUrlRequestModel(radioId))
            .makeObservableWithStatus3()
            .flatMap {
                interceptResponseExample2(it)
            }
    }
}