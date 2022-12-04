package com.app.abnezarutask.basic

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.abnezarutask.utils.showLongToast
import com.app.abnezarutask.utils.showOrHide
import kotlinx.coroutines.launch

abstract class BaseActivity<BindingT : ViewDataBinding, ViewModelT : BaseViewModel> : AppCompatActivity()
{
    lateinit var mBinding: BindingT
    lateinit var mViewModel: ViewModelT

    abstract fun getViewModelClass(): ViewModelT

    abstract fun initializeUI()

    abstract fun getResourceLayout(): Int

    private lateinit var progressBarWidget : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModelClass()
        mBinding = DataBindingUtil.setContentView(this, getResourceLayout())
        mBinding.setLifecycleOwner { lifecycle }
        initializeUI()
        initObservers()
    }

    fun setProgressbar(progressBar: ProgressBar){
        progressBarWidget = progressBar
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

                launch {
                    mViewModel.progressIndicator.collect{
                        if (::progressBarWidget.isInitialized) progressBarWidget.showOrHide(it)
                    }
                }

                launch {
                    mViewModel.errorMessage.collect{
                        if(it.isNotEmpty())
                        showLongToast(it)
                    }
                }

            }
        }
    }

}