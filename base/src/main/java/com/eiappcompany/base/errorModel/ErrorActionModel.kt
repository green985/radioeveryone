package com.eiappcompany.base.errorModel


class ErrorActionModel(var throwable: Throwable, var action: (() -> Unit)? = null) {

//    lateinit var throwable: Throwable
//    var action: (() -> Unit)? = null

//    constructor(throwable: Throwable, action: (() -> Unit)? = null) : this() {
//        this.throwable = throwable
//        this.action = action
//    }

}