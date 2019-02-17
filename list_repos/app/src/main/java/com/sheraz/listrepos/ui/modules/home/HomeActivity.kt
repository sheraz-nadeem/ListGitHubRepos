package com.sheraz.listrepos.ui.modules.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sheraz.listrepos.BR
import com.sheraz.listrepos.R
import com.sheraz.listrepos.ui.modules.base.BaseActivity
import com.sheraz.listrepos.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var activityHomeBinding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = getViewDataBinding()
        initUI()
        homeViewModel.fetchRepos()
    }

    override fun initUI() {
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_home
    }

    override fun getBindingVariable(): Int {
        return BR.homeViewModel
    }

    override fun getViewModel(): HomeViewModel {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return homeViewModel
    }
}
