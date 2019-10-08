package com.eiappcompany.datamodule.repositories

data class LoginResponseObject(
    val UserId: Int?,
    var FirstName: String?,
    var LastName: String?,
    val Email: String?,
    val Phone: String?,
    var TcNo: String?,
    val PlateNumber: String?,
    val Password: String?,
    val DeviceId: String?,
    val CreateDate: String?,
    var BirthDate: String?,
    val IsActive: Boolean?,
    val IsDelete: Boolean?,
    val UpdateDate: String?,
    val PartnerNo: String?
)