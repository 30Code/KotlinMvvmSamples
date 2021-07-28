package cn.linhome.common.vm

import androidx.lifecycle.ViewModel
import cn.linhome.common.ui.CollectionRepository
import kotlinx.coroutines.flow.flow

/**
 *  des : CollectionViewModel
 *  Created by 30Code
 *  date : 2021/7/28
 */
class CollectionViewModel(private val repository: CollectionRepository) : ViewModel() {

    fun collectArticle(id : Int) = flow {
        emit(repository.collectArticle(id))
    }

}