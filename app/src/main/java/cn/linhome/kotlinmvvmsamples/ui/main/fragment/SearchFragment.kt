package cn.linhome.kotlinmvvmsamples.ui.main.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.navigation.Navigation
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.adapter.HomePageArticleAdapter
import cn.linhome.kotlinmvvmsamples.base.BaseVMFragment
import cn.linhome.kotlinmvvmsamples.databinding.FragmentSearchBinding
import cn.linhome.kotlinmvvmsamples.model.bean.Hot
import cn.linhome.kotlinmvvmsamples.ui.main.vm.SearchViewModel
import cn.linhome.kotlinmvvmsamples.view.CustomLoadMoreView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
class SearchFragment : BaseVMFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val mSearchViewModel by viewModel<SearchViewModel>()
    private val mSearchAdapter by lazy { HomePageArticleAdapter() }

    private var mKey = ""
    private lateinit var mEmptyView : View

    private val mListHot = mutableListOf<Hot>()

    override fun initView() {
        binding.run {
            viewModel = mSearchViewModel
            adapter = mSearchAdapter
        }

        initTagLayout()
        initAdapter()
    }

    override fun initData() {
        mSearchViewModel.getHotSearch()

        et_search.setOnEditorActionListener(mKeyListener)
    }

    override fun startObserve() {
        mSearchViewModel.getUiState().observe(viewLifecycleOwner, {
            it.showSuccess?.let {
                list ->
                mSearchAdapter.run {
                    emptyView = mEmptyView
                    if (it.isRefresh) {
                        replaceData(list.datas)
                    } else {
                        addData(list.datas)
                    }
                    setEnableLoadMore(true)
                    loadMoreComplete()
                }
            }

            if (it.showEnd) {
                mSearchAdapter.loadMoreEnd()
            }

            it.showHotSearch?.let { data ->
                mListHot.clear()
                mListHot.addAll(data)
                hotTagLayout.adapter.notifyDataChanged()
            }


        })
    }

    private val mKeyListener =
        TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) //键盘上点击搜索按钮
            {
                mSearchViewModel.refreshSearch()
                return@OnEditorActionListener true
            }
            false
        }

    private fun initTagLayout() {
        hotTagLayout.run {
            adapter = object : TagAdapter<Hot>(mListHot) {

                override fun getCount(): Int = mListHot.size

                override fun getView(parent: FlowLayout, position: Int, t: Hot): View {
                    val tv = LayoutInflater.from(parent.context).inflate(R.layout.tag, parent, false) as TextView
                    tv.text = t.name
                    return tv
                }

            }

            setOnTagClickListener { view, position, parent ->
                mKey = mListHot[position].name
                mSearchViewModel.setEditTextContent(mKey)
                mSearchViewModel.searchHot(true, mKey)
                true
            }
        }
    }

    private fun initAdapter() {
        mSearchAdapter.run {
            onItemChildClickListener = this@SearchFragment.onItemChildClickListener
            setLoadMoreView(CustomLoadMoreView())
            setOnLoadMoreListener({loadMore()}, searchRecycleView)
        }

        mEmptyView = layoutInflater.inflate(R.layout.empty_view, searchRecycleView.parent as ViewGroup, false)
        val emptyTv = mEmptyView.findViewById<TextView>(R.id.emptyTv)
        emptyTv.text = getString(R.string.try_another_key)
    }

    private fun loadMore() {
        mSearchViewModel.searchHot(false, mKey)
    }

    private val onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
        when (view.id) {
            R.id.articleStar -> {
                if (isLogin()) {
                    mSearchAdapter.run {
                        data[position].run {
                            collect = !collect
                            mSearchViewModel.collectArticle(id, collect)
                        }
                        notifyDataSetChanged()
                    }
                } else {
                    Navigation.findNavController(searchRecycleView).navigate(R.id.action_tab_to_login)
                }
            }
        }
    }
}