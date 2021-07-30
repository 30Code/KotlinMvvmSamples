package cn.linhome.home.ui.coin

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import cn.linhome.common.adapter.ViewPager2FragmentAdapter
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.setupWithViewPager2
import cn.linhome.home.R
import cn.linhome.home.databinding.FragmentCoinBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 *  des : 积分，排行版
 *  Created by 30Code
 *  date : 2021/7/30
 */
class CoinFragment : BaseFragment<FragmentCoinBinding>() {

    private val mCoinRecordFragment by inject<CoinSubFragment> {
        parametersOf(0)
    }

    private val mCoinRankFragment by inject<CoinSubFragment> {
        parametersOf(1)
    }

    private val mPagerAdapter by inject<ViewPager2FragmentAdapter> {
        parametersOf(this, mutableListOf(mCoinRecordFragment, mCoinRankFragment))
    }

    override fun getLayoutId(): Int = R.layout.fragment_coin

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {

            toolbar.run {
                title = getString(R.string.coins)
                setNavigationIcon(R.drawable.arrow_back)
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }

            coinVp.run {
                offscreenPageLimit = 2
                adapter = mPagerAdapter
            }

            coinIndicator.setupWithViewPager2(coinVp, intArrayOf(R.string.coin_record, R.string.coin_rank))
        }
    }

}