package cn.linhome.common.utils

import android.content.Context
import android.text.TextUtils
import cn.linhome.lib.utils.context.FPreferencesUtil

/**
 *  des : SearchHistoryUtils
 *  Created by 30Code
 *  date : 2021/8/1
 */
object SearchHistoryUtils {
    private const val SHARE_KEY_HISTORY = "search.history"

    fun hasHistory(context: Context) =
        FPreferencesUtil.getString(SHARE_KEY_HISTORY, "").isNotEmpty()

    /**
     * 添加搜索记录
     */
    fun saveHistory(context: Context, keyword: String) {
        val content = FPreferencesUtil.getString(SHARE_KEY_HISTORY, "")

        val store = if (keyword.contains(",")) keyword.replace(",", " ") else keyword

        val list = when {
            content.contains(",") -> {
                val results = content.split(",") as ArrayList
                if (results.contains(store)) results.remove(store)
                results.add(0, store)
                results
            }
            content.isNotBlank() -> {
                if (TextUtils.equals(store, content))
                    arrayListOf(store)
                else
                    arrayListOf(store, content)
            }

            else -> arrayListOf(store)
        }

        saveListAsString(context, list)
    }

    /**
     * 删除搜索记录
     */
    fun removeKeyword(context: Context, keyword: String) {
        val content = FPreferencesUtil.getString(SHARE_KEY_HISTORY, "")

        val list = when {
            content.contains(",") -> {
                val results = content.split(",") as ArrayList
                if (results.contains(keyword)) results.remove(keyword)
                results
            }

            content.isNotBlank() ->
                if (TextUtils.equals(content, keyword)) arrayListOf()
                else arrayListOf(content)

            else -> arrayListOf()
        }

        saveListAsString(context, list)
    }

    /**
     * 获取全部搜索记录
     */
    fun fetchHistoryKeys(context: Context): ArrayList<String>? {
        val content = FPreferencesUtil.getString(SHARE_KEY_HISTORY, "")

        return when {
            content.contains(",") -> content.split(",") as ArrayList<String>
            content.isBlank() -> arrayListOf()
            else -> arrayListOf(content)
        }
    }

    /**
     * 列表转字符
     */
    private fun saveListAsString(context: Context, strings: List<String>) {
        val sb = StringBuilder()

        strings.forEach { sb.append(it).append(",") }

        val result =
            if (sb.contains(","))
                sb.substring(0, sb.length - 1)
            else sb.toString()

        FPreferencesUtil.putString(SHARE_KEY_HISTORY, result)
    }
}