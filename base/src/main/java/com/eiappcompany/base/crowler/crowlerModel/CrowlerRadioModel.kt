package com.eiappcompany.base.crowler.crowlerModel

/**
Created by EiAppCompany
09-10-2019
16:04
 **/

class CrowlerRadioModel() {

    var radioUrl: String = ""
        set(value) {
            var strTmp = value.split("-")
            radioKey = strTmp.last()
            field = value
        }

    var radioImageUrl = ""

    var radioKey: String = ""

    var radioName: String = ""

    var radioListenNumber: String = ""

    var radioLanguage: String = ""

    var radioCategoryList: ArrayList<String> = ArrayList()

    fun detectCategory(category: String) {
        category.split(",").forEach {
            radioCategoryList.add(it.capitalize())
        }
    }

}