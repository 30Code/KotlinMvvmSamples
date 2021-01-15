package cn.linhome.kotlinmvvmsamples.ui.main.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cn.linhome.kotlinmvvmsamples.base.BaseViewModel
import cn.linhome.kotlinmvvmsamples.model.bean.ArticleList
import cn.linhome.kotlinmvvmsamples.model.bean.Hot
import cn.linhome.kotlinmvvmsamples.model.repository.SearchRepository

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/15
 */
class SearchViewModel(val searchRepository: SearchRepository) : BaseViewModel() {

    val searchKey = ObservableField<String>("")

    private val uiState = MutableLiveData<SearchUiModel>()
    fun getUiState() : LiveData<SearchUiModel> {
        return uiState
    }

    val refreshSearch : () -> Unit = {}

    val searchInput: (String) -> Unit = {  }

    fun getHotSearch() {
        launchOnUI {

        }
    }

    data class SearchUiModel(
        val showHot: Boolean,
        val showLoading: Boolean,
        val showError: String?,
        val showSuccess: ArticleList?,
        val showEnd: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val showWebSites: List<Hot>?,
        val showHotSearch: List<Hot>?
    )

}