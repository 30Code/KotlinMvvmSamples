package cn.linhome.blogs.ui

import android.os.Bundle
import android.view.View
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.linhome.blogs.R
import cn.linhome.blogs.databinding.FragmentBlogArticlesListBinding
import cn.linhome.blogs.vm.BlogsArticleViewModel
import cn.linhome.common.Constant
import cn.linhome.common.adapter.PagingLoadStateAdapter
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.OnItemClickListener
import cn.linhome.common.base.OnItemLongClickListener
import cn.linhome.common.base.handleResult
import cn.linhome.common.entity.UserArticleDetail
import cn.linhome.common.vm.CollectionViewModel
import cn.linhome.common.widget.ErrorReload
import cn.linhome.common.widget.RequestStatusCode
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : BlogArticlesFragment
 *  Created by 30Code
 *  date : 2021/7/25
 */
class BlogArticlesFragment : BaseFragment<FragmentBlogArticlesListBinding>() {

    private val mCid by lazy { arguments?.getInt(CID) }

    private val mViewModel by viewModel<BlogsArticleViewModel>()

    private val mCollectionViewModel by viewModel<CollectionViewModel>()

    private val mAdapter by lifecycleScope.inject<BlogArticlesPagingAdapter>()

    override fun getLayoutId(): Int = R.layout.fragment_blog_articles_list

    override fun actionOnViewInflate() {
        launch {
            mCid?.let {
                mViewModel.getListBlogsArticles(it)
                    .catch {
                        mBinding?.statusCode = RequestStatusCode.Error
                    }.collectLatest {
                        mAdapter.submitData(it)
                    }
            }
        }
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            refreshColor = R.color.colorAccent
            refreshListener = SwipeRefreshLayout.OnRefreshListener {
                mAdapter.refresh()
            }

            adapter = mAdapter.apply {
                collectListener = {data: UserArticleDetail, position: Int ->
                    if (!data.collect) {
                        collectArticle(data, position)
                    }
                }
                
                addLoadStateListener {
                    refreshing = it.refresh is LoadState.Loading
                    statusCode = when (it.refresh) {
                        is LoadState.Loading -> RequestStatusCode.Loading
                        is LoadState.Error -> RequestStatusCode.Error
                        else -> {
                            if (itemCount == 0) {
                                RequestStatusCode.Empty
                            } else {
                                RequestStatusCode.Succeed
                            }
                        }
                    }
                }
            }.withLoadStateFooter(PagingLoadStateAdapter {
                mAdapter.retry()
            })

            itemClick = OnItemClickListener { position, view ->
                mAdapter.getItemData(position)?.let {
                    ARouter.getInstance()
                        .build(Constant.ARouterPath.PATH_WEBVIEW)
                        .withString(Constant.ExtraType.EXTRA_URL, it.link)
                        .withString(Constant.ExtraType.EXTRA_TITLE, it.title)
                        .navigation()
                }
            }

            itemLongClick = OnItemLongClickListener { position, view ->
                mAdapter.getItemData(position)?.let {

                }
            }

            errorReload = ErrorReload {
                mAdapter.retry()
            }
        }
    }

    /**
     * 收藏
     */
    private fun collectArticle(data: UserArticleDetail, position: Int) {
        launch {
            mCollectionViewModel.collectArticle(data.id)
                .catch {
                    context?.toast(R.string.no_network)
                }.onStart {

                }.collectLatest {
                    it.handleResult {
                        mAdapter.getItemData(position)?.collect = true
                        mAdapter.notifyItemChanged(position)
                        context?.toast(R.string.add_favourite_succeed)
                    }
                }
        }
    }

    companion object {
        private const val CID = "blogCid"
        fun newInstance(cid : Int) : BlogArticlesFragment {
            val fragment = BlogArticlesFragment()
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            fragment.arguments = bundle
            return fragment
        }
    }
}