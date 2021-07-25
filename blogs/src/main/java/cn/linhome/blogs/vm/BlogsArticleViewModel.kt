package cn.linhome.blogs.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cn.linhome.blogs.ui.BlogsPagingSource
import cn.linhome.blogs.ui.BlogsRepository
import cn.linhome.common.base.constPagerConfig
import cn.linhome.common.entity.ArticleCategoriesData
import cn.linhome.common.entity.UserArticleDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *  des : BlogsViewModel
 *  Created by 30Code
 *  date : 2021/7/25
 */
class BlogsArticleViewModel(val repository: BlogsRepository) : ViewModel() {

    /**
     * 公众号分类
     */
    fun getBlogsCategories() : Flow<MutableList<ArticleCategoriesData>> {
        return flow {
            emit(repository.getBlogsCategories())
        }
    }

    /**
     * 加载分类下的文章列表分页数据
     */
    fun getListBlogsArticles(pid : Int) : Flow<PagingData<UserArticleDetail>> {
        return Pager(constPagerConfig) {
            BlogsPagingSource(repository, pid)
        }.flow.apply {

        }.cachedIn(viewModelScope)
    }

}