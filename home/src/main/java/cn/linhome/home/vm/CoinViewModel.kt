package cn.linhome.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import cn.linhome.common.base.constPagerConfig
import cn.linhome.home.ui.coin.CoinRankPagingSource
import cn.linhome.home.ui.coin.CoinRecordPagingSource
import cn.linhome.home.ui.coin.CoinRepository

/**
 *  des : CoinViewModel
 *  Created by 30Code
 *  date : 2021/7/29
 */
class CoinViewModel(private val repository: CoinRepository) : ViewModel() {

    fun getListCoinRecord() = Pager(constPagerConfig) {
        CoinRecordPagingSource(repository)
    }.flow.cachedIn(viewModelScope)


    fun getListCoinRank() = Pager(constPagerConfig) {
        CoinRankPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}