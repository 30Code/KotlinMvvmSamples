package cn.linhome.kotlinmvvmsamples.ui.main.vm

import androidx.lifecycle.MutableLiveData
import cn.linhome.kotlinmvvmsamples.base.BaseViewModel
import cn.linhome.kotlinmvvmsamples.model.bean.ArticleResponseBody
import cn.linhome.kotlinmvvmsamples.model.bean.BaseResponseResult

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/26
 */
class WechatListViewModel : BaseViewModel() {

    val mArticleResponseBody : MutableLiveData<BaseResponseResult<ArticleResponseBody>> = MutableLiveData()

    fun getArticlesList(page: Int, cid: Int) {
//        launch {
//            val response  = withContext(Dispatchers.IO) {
//                repository.getArticlesList(page, cid)
//            }
//            executeResponse(response,
//                {mArticleResponseBody.value = response},
//                {mErrorMsg.value = response.errorMsg})
//        }
    }

}