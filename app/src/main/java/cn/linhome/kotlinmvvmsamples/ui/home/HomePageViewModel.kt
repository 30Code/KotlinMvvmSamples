package cn.linhome.kotlinmvvmsamples.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import cn.linhome.kotlinmvvmsamples.model.api.ResultData
import cn.linhome.kotlinmvvmsamples.base.BaseViewModel
import cn.linhome.kotlinmvvmsamples.model.bean.ArticleList
import cn.linhome.kotlinmvvmsamples.model.bean.Banner
import cn.linhome.kotlinmvvmsamples.model.repository.HomePageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
class HomePageViewModel(private val homePageRepository: HomePageRepository) : BaseViewModel() {

    private var currentPage = 0

    private val uiState = MutableLiveData<ArticleUiModel>()

    fun getUiState() : LiveData<ArticleUiModel> {
        return uiState
    }

    val mBanners : LiveData<List<Banner>> = liveData {
        kotlin.runCatching {
            val data = homePageRepository.getBanner()
            if (data is ResultData.Success) {
                emit(data.data)
            }
        }
    }

    private fun getArticleList(isRefresh: Boolean = false, cid: Int = 0) {
        viewModelScope.launch(Dispatchers.Main) {
            emitArticleUiState(true)
            if (isRefresh) {
                currentPage = 0
            }

            val result = homePageRepository.getArticleList(currentPage)
            if (result is ResultData.Success) {
                if (result.data.offset >= result.data.total) {
                    emitArticleUiState(showLoading = false, showEnd = true)
                    return@launch
                }
                currentPage++
                emitArticleUiState(showLoading = false, showSuccess = result.data, isRefresh = isRefresh)
            } else if (result is ResultData.Error) {
                emitArticleUiState(showLoading = false, showError = result.exception.message)
            }
        }
    }

    private fun emitArticleUiState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: ArticleList? = null,
        showEnd: Boolean = false,
        isRefresh: Boolean = false,
        needLogin: Boolean? = null) {
        val uiModel = ArticleUiModel(showLoading, showError, showSuccess, showEnd, isRefresh, needLogin)
        uiState.value = uiModel
    }

    data class ArticleUiModel(
        val showLoading: Boolean,
        val showError: String?,
        val showSuccess: ArticleList?,
        val showEnd: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null
    )

}