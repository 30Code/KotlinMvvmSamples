package cn.linhome.common.ui

import cn.linhome.common.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : CollectionRepository
 *  Created by 30Code
 *  date : 2021/7/28
 */
class CollectionRepository(private val api : ApiService) {

    suspend fun collectArticle(id : Int) = withContext(Dispatchers.IO) {
//        val cookie = FPreferencesUtil.getString(Constant.DiskKey.COOKIE, "")
        api.collectArticleOrProject(id)
    }

}