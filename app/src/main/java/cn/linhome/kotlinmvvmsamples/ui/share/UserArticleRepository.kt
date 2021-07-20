package cn.linhome.kotlinmvvmsamples.ui.share

import cn.linhome.common.Constant
import cn.linhome.common.bean.UserArticleDetail
import cn.linhome.common.network.ApiService
import cn.linhome.lib.utils.context.FPreferencesUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : UserArticleRepository
 *  Created by 30Code
 *  date : 2021/7/20
 */
class UserArticleRepository(val api :ApiService) {

    suspend fun fetchUserArticles(page: Int): MutableList<UserArticleDetail>? = withContext(Dispatchers.IO) {
        val cookie = FPreferencesUtil.getString(Constant.DiskKey.COOKIE, "")
        api.shareArticles(page, cookie).data.datas
    }

}