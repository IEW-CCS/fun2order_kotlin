package com.iew.fun2order.ui.my_setup
import android.view.View
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ItemsLV_Canditate (var Name: String, var imageName: String, var tokenid: String, var ostype:String, var displayName:String, var checked:Boolean): Parcelable



@Parcelize
data class ItemsLV_ContactAddFriend (var UUID: String,  var tokenid: String, var ContactName:String, var imagePath: String,  var imageURL: String, var displayName:String, var checked:Boolean): Parcelable



