package cn.linhome.home.ui

import cn.linhome.common.entity.UserArticleDetail
import cn.linhome.home.api.HomeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : HomeArticleRepository
 *  Created by 30Code
 *  date : 2021/7/22
 */
class HomeArticleRepository(val api : HomeApiService) {

    /**
     * 首页文章列表
     */
    suspend fun getHomeArticles(page: Int): MutableList<UserArticleDetail> = withContext(Dispatchers.IO) {
        api.getHomeArticles(page).data.datas
    }

}