package com.eiappcompany.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity


/**
Created by EiAppCompany
04-10-2019
11:23
 **/

abstract class BaseActivity : DaggerAppCompatActivity() {

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        Log.d("asdasdnasd", "asdasd")
    }
}