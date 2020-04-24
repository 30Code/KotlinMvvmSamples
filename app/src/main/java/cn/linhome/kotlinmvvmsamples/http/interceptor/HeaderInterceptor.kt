package cn.linhome.kotlinmvvmsamples.http.interceptor

import cn.linhome.kotlinmvvmsamples.http.constant.HttpConstant
import cn.linhome.lib.utils.context.FPreferencesUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 *  des : HeaderInterceptor 设置请求头
 *  Created by 30Code
 *  date : 2020/4/24
 */
class HeaderInterceptor : Interceptor {

    /**
     * token
     */
    private var token = FPreferencesUtil.getString(HttpConstant.TOKEN_KEY, "")

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("Content-type", "application/json; charset=utf-8")
        // .header("token", token)
        // .method(request.method(), request.body())

        val domain = request.url.host
        val url = request.url.toString()
        FPreferencesUtil.putString(HttpConstant.DOMAIN, domain)
        if (domain.isNotEmpty()) {
            val spDomain = FPreferencesUtil.getString(HttpConstant.DOMAIN, domain)
            val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
            if (cookie.isNotEmpty()) {
                // 将 Cookie 添加到请求头
                builder.addHeader(HttpConstant.COOKIE_NAME, cookie)
            }
        }

        return chain.proceed(builder.build())
    }

}