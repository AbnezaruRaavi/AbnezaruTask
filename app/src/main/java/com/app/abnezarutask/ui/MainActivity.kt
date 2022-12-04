package com.app.abnezarutask.ui

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.abnezarutask.R
import com.app.abnezarutask.basic.BaseActivity
import com.app.abnezarutask.databinding.ActivityMainBinding
import com.app.abnezarutask.generic.GenericRecycleViewAdapter
import com.app.abnezarutask.utils.isInternetAvailable
import com.app.abnezarutask.utils.showLongToast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun getViewModelClass() = mainViewModel

    override fun getResourceLayout(): Int = R.layout.activity_main

    private lateinit var recyclerAdapter: GenericRecycleViewAdapter<com.app.abnezarutask.models.Result>


    override fun initializeUI() {
        mBinding.viewModel = mainViewModel

        initRecycler()

        initObservers()

        setProgressbar(mBinding.progressBar)

        if (isInternetAvailable())
            mViewModel.callMatchesApi()
        else
            mViewModel.getResultsFromLocal()
    }

    private fun initRecycler() {
        recyclerAdapter = GenericRecycleViewAdapter(R.layout.layout_custom_list_item)

        recyclerAdapter.setOnListItemViewClickListener(object :
            GenericRecycleViewAdapter.OnListItemViewClickListener {
            override fun onClick(view: View, position: Int) {
                mViewModel.saveAcceptOrDeclineStatusLocal(position, view.id == R.id.btAccept)
                var item =
                    recyclerAdapter.getItemByPosition(position) as com.app.abnezarutask.models.Result
                item.isAccepted = if (view.id == R.id.btAccept) 1 else 2
                recyclerAdapter.onItemUpdated(item, position)
            }
        })

        mBinding.rvMatchesList.adapter = recyclerAdapter
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.apiResult.collect {
                    recyclerAdapter.replaceItems(it)
                }
            }
        }
    }
}