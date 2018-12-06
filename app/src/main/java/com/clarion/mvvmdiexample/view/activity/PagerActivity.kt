package com.clarion.mvvmdiexample.view.activity

import android.os.Bundle
import com.clarion.mvvmdiexample.BR
import com.clarion.mvvmdiexample.R
import com.clarion.mvvmdiexample.databinding.ActivityPagerBinding
import com.clarion.mvvmdiexample.view.adapter.BasePagerAdapter
import com.clarion.mvvmdiexample.viewmodel.activity.PagerViewModel
import javax.inject.Inject

/**
 * Created by Pravin Divraniya on 12/15/2017.
 */
class PagerActivity : BaseActivity<ActivityPagerBinding, PagerViewModel>(){
    @Inject
    protected lateinit var viewModel: PagerViewModel

    @Inject
    lateinit var adapter:BasePagerAdapter

    override fun getBindingVariable() = BR.pagerViewModel
    override fun getMyViewModel() = viewModel
    override fun getLayoutId() = R.layout.activity_pager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        viewModel.adapter.set(adapter)
    }
}