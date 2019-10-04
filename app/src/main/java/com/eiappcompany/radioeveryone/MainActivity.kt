package com.eiappcompany.radioeveryone

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.eiappcompany.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var context: Context

    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(
            "asdasd",
            "asdad"
        )
    }
}
