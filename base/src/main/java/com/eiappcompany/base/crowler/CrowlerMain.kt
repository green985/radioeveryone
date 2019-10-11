package com.eiappcompany.base.crowler

import com.eiappcompany.base.crowler.crowlerModel.CrowlerRadioModel
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File


/**
Created by EiAppCompany
09-10-2019
15:00
 **/

var radioSize = 0

fun main() {
    val url = "http://appradiofm.com/radio-stations-by-country/Turkey/?cc=TR&pg="
    //getRadioFromUrl(url)
    //File("deneme").appendText("[")
    for (i in 1..53) {
        getRadioFromUrl(url + "" + i)
        //getRadioFromUrl(url + "" + i)
    }
    while (true) {

    }

}

fun getRadioFromUrl(url: String) {
    println("url = " + url)
    var doc: Document? = null
    try {
        doc = Jsoup.connect(url).timeout(4000).get()
    } catch (e: Exception) {

    }
    if (doc == null) {
        getRadioFromUrl(url)
        return
    }

    val link = doc.getElementsByClass("col s12 m6 l4 margin-all")
//    println("url = " + link.size)


    makeAsyncTaskForKotlin({
        var radioModelList = ArrayList<CrowlerRadioModel>()
        link.forEach {
            //Get Single radio info
            try {
                var radioModel = CrowlerRadioModel()
                radioModel.radioUrl = it.select("a[href]").attr("href")
                radioModel.detectRadioLanguage(url)
                //DONE
                radioModel.radioImageUrl = it.select("img").attr("data-original")
                radioModel.radioName = it.select("img").attr("alt")
                radioModel.radioListenNumber = it.getElementsByClass("play-ico").text()
                radioModel.detectCategory(it.select("p").first().text())

                if (radioModel.isValidData()) {
                    File("deneme").appendText("," + Gson().toJson(radioModel))
                } else {
                    println("Some value doesn't fill" + Gson().toJson(radioModel))

                }
                //radioModelList.add(radioModel)
                //println(Gson().toJson(radioModel))
//                radioSize++

            } catch (e: Exception) {
                println(e.message)

            }

        }
        radioModelList
    }, {
        //File("deneme").appendText(Gson().toJson(radioModelList))
        println("Sucsessss == " + it.size)
        radioSize += it.size
        println("Sucsessss == " + radioSize)
    })
    //println(radioSize)
    //println(Gson().toJson(radioModelList))
}


fun <T> Observable<T>.makeObservableForCrowler(): Observable<T> {
    return this.subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.newThread())
}


fun <T> makeAsyncTaskForKotlin(
    action: () -> T,
    successAction: (T) -> Unit,
    errorAction: (() -> Unit)? = null
): Disposable {
    return Observable.fromCallable { action() }
        .makeObservableForCrowler()
        .subscribe(
            {
                successAction(it)
            },
            { errorAction?.let { it() } })

}

