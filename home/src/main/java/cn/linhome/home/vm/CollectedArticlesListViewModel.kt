package cn.linhome.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import cn.linhome.common.base.constPagerConfig
import cn.linhome.home.ui.collect.CollectedArticlesListPagingSource
import cn.linhome.home.ui.collect.CollectedArticlesListRepository
import kotlinx.coroutines.flow.flow


/**
 *  des : CollectedArticlesViewModel
 *  Created by 30Code
 *  date : 2021/7/30
 */
class CollectedArticlesListViewModel(private val repository: CollectedArticlesListRepository) : ViewModel() {

    /**
     * 获取收藏列表
     */
    fun getListCollectArticles() = Pager(constPagerConfig) {
        CollectedArticlesListPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    /**
     * 收藏列表中取消收藏文章
     */
    fun removeCollectedArticle(articleId: Int, originId: Int) = flow {
        emit(repository.removeCollectedArticle(articleId, originId))
    }

}