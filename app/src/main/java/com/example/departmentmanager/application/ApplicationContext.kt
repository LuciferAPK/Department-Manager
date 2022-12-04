package com.example.departmentmanager.application

import android.content.ClipData.Item
import com.example.departmentmanager.data.model.ItemFucntion

private val appInfoContext = AppInfoContext()
private val sessionContext = SessionContext()
private val networkContext = NetworkContext()

object ApplicationContext {
    val functions = listOf(
        ItemFucntion(title = "Quản lí nhân sự"),
        ItemFucntion(title = "Quản lí phòng ban"),
        ItemFucntion(title = "Tasks"),
        ItemFucntion(title = "Xem thống kê"),
        ItemFucntion(title = "Thông báo")
    )

    /** Update field dynamic in AppInfoContext */
    fun updateAppInfoContext(
        campaignIdSet: String? = null,
        clickItemInListPoint: Int? = null,
        clickDownloadPoint: Int? = null,
        clickFavoritesPoint: Int? = null,
        clickSettingPoint: Int? = null,
        hashtagInPosPoint: List<Double>? = null,
        searchHistorySize: Int? = null,
        minRam: Float? = null
    ) {
        if (campaignIdSet != null) appInfoContext.campaignId = campaignIdSet
        if (clickItemInListPoint != null) appInfoContext.clickItemInListPoint = clickItemInListPoint
        if (clickDownloadPoint != null) appInfoContext.clickDownloadPoint = clickDownloadPoint
        if (clickFavoritesPoint != null) appInfoContext.clickFavoritesPoint = clickFavoritesPoint
        if (clickSettingPoint != null) appInfoContext.clickSettingPoint = clickSettingPoint
        if (hashtagInPosPoint != null) appInfoContext.hashtagInPosPoint = hashtagInPosPoint
        if (searchHistorySize != null) appInfoContext.searchHistorySize = searchHistorySize
        if (minRam != null) appInfoContext.minRam = minRam
    }

    /** Update field dynamic in SessionContext */
    fun updateSessionContext(userInfoSet: String? = null, isVipSet: Boolean? = null) {
        if (userInfoSet != null) sessionContext.userInfo = userInfoSet
        if (isVipSet != null) sessionContext.isVip = isVipSet
    }

    fun getAppInfoContext() : AppInfoContext = appInfoContext
    fun getNetworkContext() : NetworkContext = networkContext
    fun getSessionContext() : SessionContext {
        sessionContext.idAutoIncrement += 1
        return sessionContext
    }
}
