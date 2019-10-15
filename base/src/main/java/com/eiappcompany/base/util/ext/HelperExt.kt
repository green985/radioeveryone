package com.eiappcompany.base.util.ext

import com.eiappcompany.base.crowler.crowlerModel.RadioDataModelCrowler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
Created by EiAppCompany
15-10-2019
11:36
 **/

class HelperExt {


    companion object {
        fun makeListFromJsonString(dataString: String): ArrayList<RadioDataModelCrowler> {
            val gson = Gson()
            dataString.replace("\r", "")
            dataString.replace("\n", "")
            //val jsonString = gson.toJson(dataString)
            val listType = object : TypeToken<ArrayList<RadioDataModelCrowler>>() {}.type
            val newList = gson.fromJson<ArrayList<RadioDataModelCrowler>>(dataString, listType)
            return newList
        }
    }


}
