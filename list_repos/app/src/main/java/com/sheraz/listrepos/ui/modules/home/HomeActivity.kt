package com.sheraz.listrepos.ui.modules.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sheraz.listrepos.BR
import com.sheraz.listrepos.R
import com.sheraz.listrepos.ui.modules.base.BaseActivity
import com.sheraz.listrepos.databinding.ActivityHomeBinding
import com.sheraz.listrepos.utils.Logger

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var activityHomeBinding: ActivityHomeBinding

    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        Logger.d(TAG, "onCreate(): ")
        super.onCreate(savedInstanceState)
        activityHomeBinding = getViewDataBinding()
        initUI()
        subscribeUi()
        homeViewModel.fetchGitHubReposFromNetworkByPage(currentPage)

    }

    override fun initUI() {

        Logger.d(TAG, "initUI(): ")

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_home
    }

    override fun getBindingVariable(): Int {
        return BR.homeViewModel
    }

    override fun getViewModel(): HomeViewModel {

        Logger.d(TAG, "getViewModel(): ")
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return homeViewModel

    }

    override fun subscribeUi() {

        Logger.d(TAG, "subscribeUi(): ")
        homeViewModel.getLiveData().observe(this, Observer { pagedList ->

            homeViewModel.setIsLoading(false)
            if (pagedList != null) {

                Logger.i(TAG, "homeViewModel.Observer(): pagedList.size: ${pagedList.size}")
                val currentItemsCount = homeViewModel.getTotalItemsCount()
                val totalItemsCount = currentItemsCount + pagedList.size
                homeViewModel.setTotalItemsCount(totalItemsCount)

            }
        })

    }


    companion object {
        private val TAG = HomeActivity::class.java.simpleName
    }
}
