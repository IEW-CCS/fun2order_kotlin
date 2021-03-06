package com.iew.fun2order.db.firebase

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class USER_MENU_ORDER(
    var brandName: String? = "",
    var contentItems: MutableList<ORDER_MEMBER>? = mutableListOf(),
    var createTime: String? = "",
    var dueTime: String? = "",
    var limitedMenuItems:  MutableList<PRODUCT>? = mutableListOf(),
    var locations: MutableList<String>? = mutableListOf(),
    var menuNumber: String? = "",
    var orderNumber: String? = "",
    var orderOwnerID: String? = "",
    var orderOwnerName: String? = "",
    var orderStatus: String? = "",
    var orderTotalPrice: Int = 0,
    var orderTotalQuantity: Int = 0,
    var orderType: String? = "",
    var needContactInfoFlag: Boolean? = null,
    var storeInfo: STORE_INFO? = STORE_INFO(),

    var deliveryInfo: MenuOrderDeliveryInformation? = null,
    var orderHistory: MutableMap<String,OrderHistoryRecord>? = null,
    var coworkBrandFlag: Boolean? = false,
    var groupOrderFlag: Boolean? = false



): Parcelable {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "brandName" to brandName,
            "contentItems" to contentItems,
            "createTime" to createTime,
            "dueTime" to dueTime,
            "limitedMenuItems" to limitedMenuItems,
            "locations" to locations,
            "menuNumber" to menuNumber,
            "orderNumber" to orderNumber,
            "orderOwnerID" to orderOwnerID,
            "orderOwnerName" to orderOwnerName,
            "orderStatus" to orderStatus,
            "orderTotalPrice" to orderTotalPrice,
            "orderTotalQuantity" to orderTotalQuantity,
            "orderType" to orderType,
            "needContactInfoFlag" to needContactInfoFlag,
            "storeInfo" to storeInfo,
            "deliveryInfo" to deliveryInfo,
            "orderHistory" to orderHistory,
            "coworkBrandFlag" to coworkBrandFlag,
            "groupOrderFlag" to groupOrderFlag
        )
    }
}
