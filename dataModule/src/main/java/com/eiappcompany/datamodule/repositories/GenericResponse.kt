package com.eiappcompany.datamodule.repositories

class GenericResponse<T> {


    val ResultObject: T? = null
    val ResultMessage: String? = null
    val ResultCode: Int? = null
    val ResultStatus: Boolean? = null
    val ResultInnerMessage: String? = null
}