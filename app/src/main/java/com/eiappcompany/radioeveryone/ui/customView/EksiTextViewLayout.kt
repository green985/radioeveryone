package com.eiappcompany.radioeveryone.ui.customView

import android.content.Context
import android.graphics.Color
import android.text.style.QuoteSpan
import android.text.style.URLSpan
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.binaryfork.spanny.Spanny
import com.eiappcompany.radioeveryone.R
import kotlinx.android.synthetic.main.eksi_text_layout.view.*
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList


class EksiTextViewLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {


    var hardCodedString = ""
    var formatetString: Spanny? = null

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.eksi_text_layout, this, true)
    }

    fun setHardcodedString(hardString: String) {
        hardCodedString = hardString
        splitWithDelimeterExample(hardCodedString)
        //exampleRegex()
    }

    private fun setSpannyString(hardString: String) {
        //Do your magic

        val allMatches = ArrayList<String>()
        val pattern = Pattern.compile(httpUrlRegex)

        val matchGroup = pattern.matcher(hardString)

        if (matchGroup.find()) {
            allMatches.add(matchGroup.group())
        }

        repeat(allMatches.size) {
            formatetString?.findAndSpan(allMatches[it]) {
                return@findAndSpan URLSpan(allMatches[it].split(" ")[0])
            }
        }


    }

    private fun exampleRegex() {
        val input =
            "dasjdajndasnd  asd  asdasda  [http://djasdasdj.com deneme] asdajsdasdjasd ıawndnaw"
        val regex = "\\[(.*?)\\]"
        val output = input.replace(regex.toRegex(), "<u>$0</u>")
        println(output)
        customText.text = output
    }

    private fun testRegex(hardString: String) {
        var tmpString = hardString


    }


    private fun splitWithDelimeterExample(hardString: String) {

        Log.d(
            "denemeasdad",
            "asjdasd7[asdasd6awd]aw6dawd7".split(Regex("(?=(\\[(.*?)\\]))")).toString()
        )


        val p = Pattern.compile(".s")//. represents single character
        val m = p.matcher("as")
        val b = m.matches()

        val pattern = Pattern.compile(httpUrlRegex)

        var resultSpanny = Spanny("")

        var tmpString = hardString

        var tmpList = tmpString.split(Regex(splitVersion2))

        if (tmpList.isNotEmpty()) {
            repeat(tmpList.size) { tmpListIndex ->
                //Regexe gore böldü ve regex olan yer cumlenin sonunda
                var regexString = tmpList[tmpListIndex]

                var regexList = regexString.split(Regex(httpUrlRegex))

                if (regexList.size > 1) {
                    //Split harici neden çalışmıyor ?
                    var minusTwoString = regexString.replace(regexList[1], "")
                    var thisWillBeSpanny = minusTwoString
                    thisWillBeSpanny?.let {
                        var splitSpanny = thisWillBeSpanny.split(" ")


                        resultSpanny.append(splitSpanny[1], URLSpan(splitSpanny[0]))
                        //resultSpanny.append(regexList[1])
                    }
                    resultSpanny.append(regexList[1])

                } else {
                    resultSpanny.append(regexList[0])
                }


            }
            customText.text = resultSpanny
        } else {
            customText.text = "bir boklar yanlıs gıttı "
        }

    }

    private fun setWithHardReplaceAndSpan(hardString: String) {
        var spannyString = Spanny(hardString)
        var tmpString = hardString
        var regonizer = "asd#"
        tmpString = hardString.replace(Regex(httpUrlRegex)) { it2 ->
            var stringReturn = regonizer.plus(it2.value).plus(regonizer)
            stringReturn
        }


    }

    private fun setWithReplace(hardString: String) {
        var spannyString = Spanny(hardString)



        spannyString = Spanny(spannyString.replace(Regex(httpUrlRegex)) {
            var tmpString = Spanny("")
            var splitHttpAndStr = it.value.split(" ")
            tmpString.append(splitHttpAndStr[1], URLSpan(splitHttpAndStr[0]))
        })

        customText.text = spannyString
    }

    private fun setWithDelimeter(hardString: String) {
        //Not work
        val pattern = Pattern.compile(httpUrlRegex)
        var spannableString = Spanny()

        //var listSplit = hardString.split(String.format(WITH_DELIMITER, httpUrlRegex))
        var listSplit = hardString.split(look)
        if (listSplit.isNotEmpty()) {
            repeat(listSplit.size) { listSplitIndex ->
                if (pattern.matcher(listSplit[listSplitIndex]).matches()) {
                    var splitHttpAndStr = listSplit[listSplitIndex].split(" ")
                    spannableString.append(splitHttpAndStr[1], URLSpan(splitHttpAndStr[0]))
                } else {
                    spannableString.append(listSplit[listSplitIndex])
                }
            }
            customText.text = spannableString
        } else {
            customText.text = "Bişi olmadı"
        }
    }

}

val WITH_DELIMITER = "((?<=%1\$s)|(?=%1\$s))"
const val httpUrlRegex = "((\\[)(.*?)(\\]))"
const val httpUrlRegex2 = "(;)(.*?)(;)"
const val splitVersion = "(?<=\$s)"
const val s3plitVersion = "(?=;)"
const val splitVersion2 = "(?=(\\[(.*?)\\]))"
const val splitVersion3 = "(?=(;(.*?);))"
const val look = "((?<=".plus("\\[(.*?)").plus(")|(?=".plus("(.*?)\\]").plus("))"))