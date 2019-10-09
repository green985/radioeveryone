package com.eiappcompany.base.crowler

import com.eiappcompany.base.crowler.crowlerModel.CrowlerRadioModel
import com.google.gson.Gson
import org.jsoup.Jsoup


/**
Created by EiAppCompany
09-10-2019
15:00
 **/
fun main() {
    val url = "http://appradiofm.com/radio-stations-by-country/Turkey/?cc=TR"
    getRadioFromUrl(url)
    while (true) {

    }

}

fun getRadioFromUrl(url: String) {
    val doc = Jsoup.connect(url).get()

    //Get Radio div on page
    val link = doc.getElementsByClass("col s12 m6 l4 margin-all")

    var radioModelList = ArrayList<CrowlerRadioModel>()

    link.forEach {
        //Get Single radio info
        var radioModel = CrowlerRadioModel()
        //radioModel.radioLanguage = url.
        //DONE
        // radioModel.radioUrl = it.select("a[href]").attr("href")
//        radioModel.radioImageUrl = it.select("img").attr("data-original")
//        radioModel.radioName = it.select("img").attr("alt")


        //radioModel.radioListenNumber = it.getElementsByClass("play-ico").attr("span")

        radioModel.detectCategory(it.select("p").first().text())

        radioModelList.add(radioModel)

    }
    print(Gson().toJson(radioModelList))


}


fun detectLanguageFullName(tmpString: String) {
    var uriList = tmpString.split("/")
}

