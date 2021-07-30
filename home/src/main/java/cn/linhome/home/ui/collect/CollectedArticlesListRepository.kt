package cn.linhome.home.ui.collect

import cn.linhome.common.api.ApiService
import cn.linhome.common.entity.UserCollectDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : 收藏列表请求
 *  Created by 30Code
 *  date : 2021/7/30
 */
class CollectedArticlesListRepository(private val api : ApiService) {

    /**
     * 获取收藏列表
     */
    suspend fun getListCollectArticles(page : Int) : MutableList<UserCollectDetail> = withContext(Dispatchers.IO) {
        api.getCollectedArticles(page).data.datas
    }

    /**
     * 收藏列表中取消收藏文章
     */
    suspend fun removeCollectedArticle(articleId: Int, originId: Int) =
        withContext(Dispatchers.IO) {
            api.unCollectCollection(articleId, originId)
        }

}