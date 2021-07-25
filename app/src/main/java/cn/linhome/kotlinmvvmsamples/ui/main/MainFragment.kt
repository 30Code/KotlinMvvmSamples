package cn.linhome.kotlinmvvmsamples.ui.main

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.linhome.blogs.ui.BlogCategoriesFragment
import cn.linhome.common.Constant
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.handleResult
import cn.linhome.common.vm.AppViewModel
import cn.linhome.home.ui.HomeFragment
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentMainBinding
import cn.linhome.kotlinmvvmsamples.databinding.UserProfileHeaderBinding
import cn.linhome.kotlinmvvmsamples.ui.project.ProjectFragment
import cn.linhome.kotlinmvvmsamples.ui.share.ShareFragment
import cn.linhome.lib.utils.context.FPreferencesUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

/**
 *  des : MainFragment
 *  Created by 30Code
 *  date : 2021/7/18
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mAppViewModel by sharedViewModel<AppViewModel>()
    private val mViewModel by sharedViewModel<MainViewModel>()

    private var mCoinsJob: Job? = null

    private val mListFragment = arrayListOf<Fragment>()
    private val mHomeFragment by lazy { HomeFragment() }
    private val mBlogCategoriesFragment by lazy { BlogCategoriesFragment() }
    private val mProjectFragment by lazy { ProjectFragment() }
    private val mShareFragment by lazy { ShareFragment() }

    init {
        mListFragment.run {
            add(mHomeFragment)
            add(mBlogCategoriesFragment)
            add(mProjectFragment)
            add(mShareFragment)
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_main

    private val mHeaderBinding by lazy {
        DataBindingUtil.inflate<UserProfileHeaderBinding>(
            layoutInflater, R.layout.user_profile_header, mBinding?.userProfileDrawer, false)
    }

    override fun actionOnViewInflate() {
        mBinding?.run {
            mHeaderBinding.holder = this@MainFragment
            userProfileDrawer.addHeaderView(mHeaderBinding.root)
        }
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            holder = this@MainFragment

            limit = 2
            adapter = object : FragmentStateAdapter(this@MainFragment) {
                override fun createFragment(position: Int) = mListFragment[position]

                override fun getItemCount() = mListFragment.size
            }
            mainViewpager.run {
                isUserInputEnabled = false
            }

            navView.setOnNavigationItemSelectedListener(onNavigationItemSelected)

            mViewModel.hasLogin.observe(this@MainFragment, {
                userProfileDrawer.menu.let { menus -> menus.findItem(R.id.user_collections).isVisible = it
                    menus.findItem(R.id.login_out).isVisible = it
                    menus.findItem(R.id.todo_group).isVisible = it
                    menus.findItem(R.id.share).isVisible = it
                }

                mHeaderBinding.userCoins.isVisible = it
                mHeaderBinding.loginState = it
                val username = FPreferencesUtil.getString(Constant.DiskKey.USERNAME, "")
                mHeaderBinding.name = if (it) username else requireContext().getString(R.string.click_to_login)
                mHeaderBinding.avatarKey = username.run {
                    if (isNullOrBlank()) "A" else toCharArray()[0].toString().toUpperCase(Locale.getDefault())
                }

                if (it) {
                    getCoins()
                }
            })

            handleUserProfile()
        }
    }

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                switchFragment(0)
            }
            R.id.blog -> {
                switchFragment(1)
            }
            R.id.project -> {
                switchFragment(2)
            }
            R.id.profile -> {
                switchFragment(3)
            }
        }
        true
    }

    private fun switchFragment(position: Int): Boolean {
        mBinding?.currentItem = position
//        mBinding?.mainViewpager?.setCurrentItem(position, false)
        return true
    }

    private fun getCoins() {
        mCoinsJob?.cancel()
        mCoinsJob = launch {
            mViewModel.getCoinInfo().collectLatest {
                if (it == null) return@collectLatest

                mHeaderBinding.coinSpan = SpannableStringBuilder("${it.coinCount}").apply {
                    setSpan(
                        ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.coin_color)),
                        0, length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                    )

                    setSpan(
                        ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.colorPrimary)),
                        run { append("\t/\t\t"); length },
                        run { append("Lv${it.level}"); length },
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                    )

                    setSpan(
                        ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.colorAccent)),
                        run { append("\t\t/\t\t"); length },
                        run { append("R${it.rank}"); length },
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        closeMenu()
    }

    private fun handleUserProfile() {
        mBinding?.userProfileDrawer?.setNavigationItemSelectedListener { menu ->
            when(menu.itemId) {
                R.id.login_out -> requireContext().alert("是否退出登录") {
                    yesButton { loginOut() }
                    noButton {  }
                }.show()
            }
            true
        }
    }

    private fun loginOut() {
        launch {
            mViewModel.loginOut().catch {
                context?.toast(R.string.no_network)
            }.onStart {
                mAppViewModel.showLoading()
            }.onCompletion {
                mAppViewModel.dismissLoading()
            }.collectLatest {
                it.handleResult({
                    context?.toast(R.string.login_out_failed)
                }, {
                    context?.toast(R.string.login_out_succeed)
                    mViewModel.hasLogin.postValue(false)
                    mViewModel.clearUserInfo()
                })
            }
        }
    }

    fun closeMenu(animate: Boolean = true) {

    }

    fun showWxDialog(view: View) {

    }

    fun headerLogin(view: View) {

    }

    fun userCoins(view: View) {

    }
}