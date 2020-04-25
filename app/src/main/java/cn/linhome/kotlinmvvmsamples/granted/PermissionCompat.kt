/*
 * Copyright 2016 czy1121
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.linhome.kotlinmvpsamples.granted

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings

/**
 * 权限设置开启跳转
 */
object PermissionCompat {
    private val MARK = Build.MANUFACTURER.toLowerCase()

    /**
     * 跳转到应用权限设置页面
     *
     * @param context 上下文对象
     * @param newTask 是否使用新的任务栈启动
     */
    fun start(context: Context, newTask: Boolean) {
        var intent: Intent
        intent = if (MARK.contains("huawei")) {
            huawei(context)
        } else if (MARK.contains("xiaomi")) {
            xiaomi(context)
        } else if (MARK.contains("oppo")) {
            oppo(context)
        } else if (MARK.contains("vivo")) {
            vivo(context)
        } else if (MARK.contains("meizu")) {
            meizu(context)
        } else {
            google(context)
        }
        if (newTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            intent = google(context)
            context.startActivity(intent)
        }
    }

    private fun google(context: Context): Intent {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", context.packageName, null)
        return intent
    }

    private fun huawei(context: Context): Intent {
        val intent = Intent()
        intent.component = ComponentName(
            "com.huawei.systemmanager",
            "com.huawei.permissionmanager.ui.MainActivity"
        )
        if (hasIntent(context, intent)) return intent
        intent.component = ComponentName(
            "com.huawei.systemmanager",
            "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity"
        )
        if (hasIntent(context, intent)) return intent
        intent.component = ComponentName(
            "com.huawei.systemmanager",
            "com.huawei.notificationmanager.ui.NotificationManagmentActivity"
        )
        return intent
    }

    private fun xiaomi(context: Context): Intent {
        val intent = Intent("miui.intent.action.APP_PERM_EDITOR")
        intent.putExtra("extra_pkgname", context.packageName)
        if (hasIntent(context, intent)) return intent
        intent.setPackage("com.miui.securitycenter")
        if (hasIntent(context, intent)) return intent
        intent.setClassName(
            "com.miui.securitycenter",
            "com.miui.permcenter.permissions.AppPermissionsEditorActivity"
        )
        if (hasIntent(context, intent)) return intent
        intent.setClassName(
            "com.miui.securitycenter",
            "com.miui.permcenter.permissions.PermissionsEditorActivity"
        )
        return intent
    }

    private fun oppo(context: Context): Intent {
        val intent = Intent()
        intent.putExtra("packageName", context.packageName)
        intent.setClassName(
            "com.color.safecenter",
            "com.color.safecenter.permission.floatwindow.FloatWindowListActivity"
        )
        if (hasIntent(context, intent)) return intent
        intent.setClassName(
            "com.coloros.safecenter",
            "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity"
        )
        if (hasIntent(context, intent)) return intent
        intent.setClassName("com.oppo.safe", "com.oppo.safe.permission.PermissionAppListActivity")
        return intent
    }

    private fun vivo(context: Context): Intent {
        val intent = Intent()
        intent.setClassName(
            "com.iqoo.secure",
            "com.iqoo.secure.ui.phoneoptimize.FloatWindowManager"
        )
        intent.putExtra("packagename", context.packageName)
        if (hasIntent(context, intent)) return intent
        intent.component = ComponentName(
            "com.iqoo.secure",
            "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"
        )
        return intent
    }

    private fun meizu(context: Context): Intent {
        val intent = Intent("com.meizu.safe.security.SHOW_APPSEC")
        intent.putExtra("packageName", context.packageName)
        intent.component = ComponentName(
            "com.meizu.safe",
            "com.meizu.safe.security.AppSecActivity"
        )
        return intent
    }

    private fun hasIntent(context: Context, intent: Intent): Boolean {
        return context.packageManager
            .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size > 0
    }
}