package cn.linhome.common.network

import cn.linhome.lib.utils.FCollectionUtil
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

internal class LocalCookieJar : CookieJar {
    //cookie的本地化存储
    private val cache = mutableListOf<Cookie>()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        //过期的Cookie
        val invalidCookies: MutableList<Cookie> = ArrayList()
        //有效的Cookie
        val validCookies: MutableList<Cookie> = ArrayList()

        if (!FCollectionUtil.isEmpty(cache)) {
            for (cookie in cache) {
                if (cookie.expiresAt < System.currentTimeMillis()) {
                    //判断是否过期
                    invalidCookies.add(cookie)
                } else if (cookie.matches(url)) {
                    //匹配Cookie对应url
                    validCookies.add(cookie)
                }
            }
        }
        //缓存中移除过期的Cookie
        cache.removeAll(invalidCookies)

        //返回List<Cookie>让Request进行设置
        return validCookies
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cache.addAll(cookies)
    }

}