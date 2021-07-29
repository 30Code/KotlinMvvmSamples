package cn.linhome.home.ui.coin

import cn.linhome.home.api.HomeApiService
import cn.linhome.home.entity.CoinRankDetail
import cn.linhome.home.entity.CoinRecordDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : CoinRepository
 *  Created by 30Code
 *  date : 2021/7/29
 */
class CoinRepository(private val api : HomeApiService) {

    /**
     * 个人积分获取记录
     */
    suspend fun getListCoinRecord(page : Int) : MutableList<CoinRecordDetail> = withContext(Dispatchers.IO) {
        api.getListCoinRecord(page).data.datas
    }

    /**
     * 积分排行榜
     */
    suspend fun getListCoinRank(page : Int) : MutableList<CoinRankDetail> = withContext(Dispatchers.IO) {
        api.getListCoinRank(page).data.datas
    }
}