package cn.linhome.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cn.linhome.common.base.constPagerConfig
import cn.linhome.common.entity.UserArticleDetail
import cn.linhome.common.utils.SearchHistoryUtils
import cn.linhome.home.ui.search.SearchPagingSource
import cn.linhome.home.ui.search.SearchRepository
import cn.linhome.lib.utils.context.FContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/8/1
 */
class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    val resultMode = MutableLiveData<Boolean>()
    val listHistory = MutableLiveData<MutableList<String>>()

    init {
        resultMode.postValue(false)
    }

    /**
     * 搜索热词
     */
    fun getListHotKeys() = flow {
        emit(repository.getListHotKeys())
    }

    fun updateHistory() {
        listHistory.postValue(SearchHistoryUtils.fetchHistoryKeys(FContext.get()))
    }

    fun getListSearchResult(key : String) : Flow<PagingData<UserArticleDetail>> {
        return Pager(config = constPagerConfig) {
            SearchPagingSource(repository, key)
        }.flow.cachedIn(viewModelScope)
    }
}