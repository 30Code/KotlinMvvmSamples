package cn.linhome.kotlinmvvmsamples.ui.main.vm

import androidx.lifecycle.MutableLiveData
import cn.linhome.kotlinmvvmsamples.base.BaseViewModel
import cn.linhome.kotlinmvvmsamples.model.bean.BaseResponseResult
import cn.linhome.kotlinmvvmsamples.model.bean.WXChapterBean

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/26
 */
class WechatViewModel : BaseViewModel() {

    val mWXChapterBean : MutableLiveData<BaseResponseResult<MutableList<WXChapterBean>>> = MutableLiveData()

    fun getWXChapters() {
//        launch {
//            val response = withContext(Dispatchers.IO) {
//                repository.getWXChapters()
//            }
//            executeResponse(response,
//                {mWXChapterBean.value = response},
//                {mErrorMsg.value = response.errorMsg})
//        }
    }
}