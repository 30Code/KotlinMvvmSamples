package cn.linhome.kotlinmvvmsamples.ui.main.vm

import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.linhome.kotlinmvvmsamples.base.BaseViewModel
import cn.linhome.kotlinmvvmsamples.model.api.ResultData
import cn.linhome.kotlinmvvmsamples.model.bean.ArticleList
import cn.linhome.kotlinmvvmsamples.model.bean.Hot
import cn.linhome.kotlinmvvmsamples.model.repository.CollectRepository
import cn.linhome.kotlinmvvmsamples.model.repository.SearchRepository
import cn.linhome.lib.utils.FKeyboardUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/15
 */
class SearchViewModel(private val searchRepository: SearchRepository, private val collectRepository: CollectRepository) : BaseViewModel() {

    private var currentPage = 0

    val searchKey = ObservableField<String>("")
    val isShowSearchContent = ObservableField<Boolean>(false)
    val isShowHotSearch = ObservableField<Boolean>(true)

    private val uiState = MutableLiveData<SearchUiModel>()
    fun getUiState() : LiveData<SearchUiModel> {
        return uiState
    }

    fun setEditTextContent(key : String) {
        searchKey.set(key)
    }

    fun refreshSearch() {
        searchHot(isRefresh = false, searchKey.get().toString())
    }

    val searchInput: (String) -> Unit = {
        if (it.isNullOrEmpty()) {
            isShowHotSearch.set(true)
            isShowSearchContent.set(false)

            setEditTextContent("")
        } else {
            isShowHotSearch.set(false)
            isShowSearchContent.set(true)

            refreshSearch()
        }
    }

    fun collectArticle(articleId: Int, boolean: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                if (boolean) collectRepository.collectArticle(articleId)
                else collectRepository.unCollectArticle(articleId)
            }
        }
    }

    fun searchHot(isRefresh: Boolean = false, key : String) {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main) {emitArticleUiState(showLoading = true)}
            if (isRefresh) {
                currentPage = 0
            }

            val result = searchRepository.searchHot(currentPage, key)

            withContext(Dispatchers.Main) {
                if (result is ResultData.Success) {
                    val listArticle = result.data
                    if (listArticle.offset >= listArticle.total) {
                        if (listArticle.offset > 0) {
                            emitArticleUiState(showLoading = false, showEnd = true)
                        } else {
                            emitArticleUiState(showLoading = false, isRefresh = true, showSuccess =
                            ArticleList(0, 0, 0, 0, 0, false, emptyList()))
                        }
                        return@withContext
                    }
                    currentPage++
                    emitArticleUiState(showLoading = false, showSuccess = listArticle, isRefresh = isRefresh)
                } else if (result is ResultData.Error) {
                    emitArticleUiState(showLoading = false, showError = result.exception.message)
                }
            }
        }
    }

    fun getHotSearch() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {searchRepository.getHotSearch()}
            if (result is ResultData.Success) {
                emitArticleUiState(showHot = true, showHotSearch = result.data)
            }
        }
    }

    private fun emitArticleUiState(
        showHot: Boolean = false,
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: ArticleList? = null,
        showEnd: Boolean = false,
        isRefresh: Boolean = false,
        showWebSites: List<Hot>? = null,
        showHotSearch: List<Hot>? = null
    ) {
        if (showHot) {
            isShowHotSearch.set(true)
            isShowSearchContent.set(false)
        } else {
            isShowHotSearch.set(false)
            isShowSearchContent.set(true)
        }
        val uiModel = SearchUiModel(showHot, showLoading, showError, showSuccess, showEnd, isRefresh, showWebSites, showHotSearch)
        uiState.value = uiModel
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