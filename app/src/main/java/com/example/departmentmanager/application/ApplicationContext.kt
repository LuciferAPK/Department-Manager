package com.example.departmentmanager.application

import com.example.departmentmanager.R
import com.example.departmentmanager.data.model.ItemFucntion

private val appInfoContext = AppInfoContext()
private val networkContext = NetworkContext()

object ApplicationContext {
    val functions = listOf(
        ItemFucntion(title = "Quản lí nhân sự", image = R.drawable.ic_human_resource),
        ItemFucntion(title = "Quản lí phòng ban", image = R.drawable.ic_department),
        ItemFucntion(title = "Tasks", image = R.drawable.ic_task),
        ItemFucntion(title = "Xem thống kê", image = R.drawable.ic_statistic),
        ItemFucntion(title = "Thông báo", image = R.drawable.ic_notification),
        ItemFucntion(title = "Trợ giúp", image = R.drawable.ic_help)
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

//    /** Update field dynamic in SessionContext */
//    fun updateSessionContext(userInfoSet: String? = null, isVipSet: Boolean? = null) {
//        if (userInfoSet != null) sessionContext.userInfo = userInfoSet
//        if (isVipSet != null) sessionContext.isVip = isVipSet
//    }

    fun getAppInfoContext() : AppInfoContext = appInfoContext
    fun getNetworkContext() : NetworkContext = networkContext
//    fun getSessionContext() : SessionContext {
//        sessionContext.idAutoIncrement += 1
//        return sessionContext
//    }
}
