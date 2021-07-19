package cn.linhome.common.base

import android.content.Context
import android.os.Build
import android.text.Html
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/19
 */
fun Context.stringValue(@StringRes stringRes: Int) = resources.getString(stringRes)

fun Context.drawableValue(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)

suspend fun <T> BaseResultData<T>.handleResult(
    fail: suspend (String) -> Unit = { /*App.instance.toast(it)*/ },
    ok: suspend (T) -> Unit = {}
) {
    if (errorCode == 0) ok(data)
    else fail(errorMsg)
}

@Suppress("DEPRECATION")
fun String.renderHtml(): String =
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
    else Html.fromHtml(this).toString()