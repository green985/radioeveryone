package com.eiappcompany.base.crowler.crowlerModel

import org.jsoup.Jsoup
import java.net.URI

/**
Created by EiAppCompany
09-10-2019
16:04
 **/

class CrowlerRadioModel {

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
    var radioLanguageCode: String = ""

    var radioCategoryList: ArrayList<String> = ArrayList()

    fun detectCategory(category: String) {
        //Detect when category not full

        if (category.contains("..")) {
            // Enter detail and get category from detail page
            getRadioCategoryFromDetailPage(radioUrl)
            return
        }



        category.split(",").forEach {
            radioCategoryList.add(it.capitalize())
        }
    }


    private fun getRadioCategoryFromDetailPage(detailPageUrl: String) {
        val doc = Jsoup.connect(detailPageUrl).get()

        //Get Radio div on page
        val categoryString = doc.select("td").first().text()

        detectCategory(categoryString)
    }

    fun detectRadioLanguage(radioUrl: String) {
        var uri = URI(radioUrl)
        uri.query
        radioLanguage = uri.path.split("/")[2]
        radioLanguageCode = uri.query.split("&")[0].split("=")[1]
    }


}