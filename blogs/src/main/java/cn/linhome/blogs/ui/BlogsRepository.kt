package cn.linhome.blogs.ui

import cn.linhome.blogs.api.BlogsApiService
import cn.linhome.common.entity.UserArticleDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : BlogsRepository
 *  Created by 30Code
 *  date : 2021/7/25
 */
class BlogsRepository(val api : BlogsApiService) {

    /**
     * 公众号分类
     */
    suspend fun getBlogsCategories() = withContext(Dispatchers.IO) {
        api.getBlogsCategory().data
    }

    /**
     * 加载分类下的文章列表
     */
    suspend fun getListBlogsArticles(page : Int, cid : Int) : MutableList<UserArticleDetail> = withContext(Dispatchers.IO) {
        api.getBlogsArticles(page, cid).data.datas
    }

}