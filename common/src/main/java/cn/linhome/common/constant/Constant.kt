package cn.linhome.common.constant

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/19
 */
object Constant {

    const val BASE_URL = "https://www.wanandroid.com"

    object ARouterPath {
        const val PATH_WEBVIEW: String = "/webview/WebviewActivity"
        const val PATH_LOGIN: String = "/login/LoginActivity"
        const val PATH_REGISTER: String = "/login/RegisterActivity"
        const val PATH_SEARCH: String = "/search/SearchActivity"
    }

    /**
     * intent 传值类型
     */
    object ExtraType {
        //String
        const val EXTRA_URL = "extra_url"
        //String
        const val EXTRA_TITLE = "extra_title"
    }

    object DiskKey {
        const val COOKIE = "cookie"
        const val USERID = "user_id"
        const val USERNAME = "user_name"
    }

}