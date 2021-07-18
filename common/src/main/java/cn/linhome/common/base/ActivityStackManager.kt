package cn.linhome.common.base

import android.app.Activity

/**
 *  des : 栈管理
 *  Created by 30Code
 *  date : 2021/7/18
 */
object ActivityStackManager {

    private val activities = mutableListOf<Activity>()

    fun addActivity(activity: Activity) = activities.add(activity)

    fun removeActivity(activity: Activity) {
        if (activities.contains(activity)) {
            activities.remove(activity)
            activity.finish()
        }
    }

    fun getTopActivity(): Activity? =
        if (activities.isEmpty()) null else activities[activities.size - 1]

    fun finishAll() =
        activities.filterNot { it.isFinishing }.forEach { it.finish() }
}