package cn.linhome.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cn.linhome.common.base.constPagerConfig
import cn.linhome.common.entity.UserArticleDetail
import cn.linhome.home.ui.HomeArticlePagingSource
import cn.linhome.home.ui.HomeArticleRepository
import kotlinx.coroutines.flow.Flow

/**
 *  des : HomeArticleViewModel
 *  Created by 30Code
 *  date : 2021/7/22
 */
class HomeArticleViewModel(val repository: HomeArticleRepository) : ViewModel() {

    fun getHomeArticles() : Flow<PagingData<UserArticleDetail>> {
        return Pager(constPagerConfig) {
            HomeArticlePagingSource(repository)
        }.flow.cachedIn(viewModelScope)
    }

}