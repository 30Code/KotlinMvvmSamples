package cn.linhome.home.ui.search

import cn.linhome.common.entity.UserArticleDetail
import cn.linhome.home.api.HomeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : SearchRepository
 *  Created by 30Code
 *  date : 2021/8/1
 */
class SearchRepository(private val api : HomeApiService) {

    /**
     * 搜索热词
     */
    suspend fun getListHotKeys() = withContext(Dispatchers.IO) {
        api.getListHotKeys().data
    }

    /**
     * 搜索结果
     */
    suspend fun getListSearchArticles(page: Int, key: String) : MutableList<UserArticleDetail> = withContext(Dispatchers.IO) {
        api.getSearchArticles(page, key).data.datas
    }
}