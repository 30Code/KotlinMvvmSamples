package cn.linhome.kotlinmvvmsamples.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.linhome.kotlinmvvmsamples.model.repository.RepositoryHelper
import kotlinx.coroutines.*

/**
 *  des : BaseViewModel
 *  Created by 30Code
 *  date : 2020/4/24
 */
open class BaseViewModel : ViewModel()/*, LifecycleObserver*/ {

//    private val mException : MutableLiveData<Exception> = MutableLiveData()
//
//    val mErrorMsg: MutableLiveData<String> = MutableLiveData()
//
//    val repository by lazy {
//        RepositoryHelper()
//    }
//
//    private fun launchOnUI(block : suspend CoroutineScope.() -> Unit) {
//        MainScope().launch { block() }
//    }
//
//    suspend fun <T> launchIO(block: suspend CoroutineScope.() -> T) {
//        withContext(Dispatchers.IO) {
//            block
//        }
//    }
//
//    fun launch(tryBlock : suspend CoroutineScope.() -> Unit) {
//        launchOnUI {
//            tryCatch(tryBlock, {}, {}, true)
//        }
//    }
//
//    fun launchOnUITryCatch(
//        tryBlock: suspend CoroutineScope.() -> Unit,
//        catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
//        finallyBlock: suspend CoroutineScope.() -> Unit,
//        handleCancellationExceptionManually: Boolean
//    ) {
//        launchOnUI {
//            tryCatch(tryBlock, catchBlock, finallyBlock, handleCancellationExceptionManually)
//        }
//    }
//
//    fun launchOnUITryCatch(
//        tryBlock: suspend CoroutineScope.() -> Unit,
//        handleCancellationExceptionManually: Boolean = false
//    ) {
//        launchOnUI {
//            tryCatch(tryBlock, {}, {}, handleCancellationExceptionManually)
//        }
//    }
//
//    private suspend fun tryCatch(
//        tryBlock: suspend CoroutineScope.() -> Unit,
//        catchBlock : suspend CoroutineScope.(Throwable) -> Unit,
//        finallyBlock : suspend CoroutineScope.() -> Unit,
//        handleCancellationExceptionManually : Boolean = false
//    ){
//        coroutineScope {
//            try {
//                tryBlock()
//            } catch (e : Exception) {
//                if (e !is CancellationException || handleCancellationExceptionManually) {
//                    mException.value = e
//                    catchBlock(e)
//                } else {
//                    throw e
//                }
//            } finally {
//                finallyBlock()
//            }
//        }
//    }

    open class UiState<T>(
        val isLoading: Boolean = false,
        val isRefresh: Boolean = false,
        val isSuccess: T? = null,
        val isError: String?= null
    )


    open class BaseUiModel<T>(
        var showLoading: Boolean = false,
        var showError: String? = null,
        var showSuccess: T? = null,
        var showEnd: Boolean = false, // 加载更多
        var isRefresh: Boolean = false // 刷新

    )

    val mException: MutableLiveData<Throwable> = MutableLiveData()


    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {

        viewModelScope.launch { block() }

    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }
}