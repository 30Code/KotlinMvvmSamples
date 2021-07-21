package cn.linhome.kotlinmvvmsamples.ui.project

import android.os.Bundle
import android.view.View
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.linhome.common.adapter.PagingLoadStateAdapter
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.OnItemClickListener
import cn.linhome.common.base.OnItemLongClickListener
import cn.linhome.common.widget.ErrorReload
import cn.linhome.common.widget.RequestStatusCode
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentProjectTypeBinding
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : ProjectTypeFragment
 *  Created by 30Code
 *  date : 2021/7/21
 */
class ProjectTypeFragment : BaseFragment<FragmentProjectTypeBinding>() {

    private val mCid by lazy { arguments?.getInt(CID) }

    private val mViewModel by viewModel<ProjectViewModel>()

    private val mAdapter by lifecycleScope.inject<ProjectTypePagingAdapter>()

    override fun getLayoutId(): Int = R.layout.fragment_project_type

    override fun actionOnViewInflate() {
        launch {
            mCid?.let {
                mViewModel.getProjects(it)
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

    companion object {
        private const val CID = "projectCid"
        fun newInstance(cid : Int) : ProjectTypeFragment {
            val fragment = ProjectTypeFragment()
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            fragment.arguments = bundle
            return fragment
        }
    }
}