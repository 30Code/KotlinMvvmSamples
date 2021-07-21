package cn.linhome.kotlinmvvmsamples.ui.share

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import cn.linhome.common.base.constPagerConfig

/**
 *  des : UserArticleViewModel
 *  Created by 30Code
 *  date : 2021/7/20
 */
class UserArticleViewModel(val repository: UserArticleRepository) : ViewModel() {

    fun getSharedArticles() = Pager(constPagerConfig) {
        UserArticlePagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}