package com.sheraz.listrepos.ui.modules.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sheraz.listrepos.Injector
import com.sheraz.listrepos.databinding.ActivityHomeBinding
import com.sheraz.listrepos.ui.models.GitHubRepoItem
import com.sheraz.listrepos.ui.modules.adapters.HomeAdapter
import com.sheraz.listrepos.ui.modules.base.BaseActivity
import com.sheraz.listrepos.utils.Logger
import kotlinx.android.synthetic.main.activity_home.*
import android.content.Intent
import android.net.Uri
import androidx.paging.PagedList
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.sheraz.listrepos.BR
import com.sheraz.listrepos.R
import android.view.Menu
import android.view.MenuItem
import com.sheraz.listrepos.internal.bindViewModel


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    private lateinit var activityHomeBinding: ActivityHomeBinding
    private val homeAdapter: HomeAdapter
    private val homeViewModel by bindViewModel<HomeViewModel>(viewModelFactory)

    init {

        Logger.d(TAG, "init(): ")
        homeAdapter = Injector.get().homeAdapter()

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        Logger.d(TAG, "onCreate(): ")
        super.onCreate(savedInstanceState)
        activityHomeBinding = getViewDataBinding()
        initUI()
        setUpListeners()
        subscribeUi()

    }

    override fun initUI() {

        Logger.d(TAG, "initUI(): ")

        rvGitHubRepoList.layoutManager = LinearLayoutManager(this)
        rvGitHubRepoList.adapter = homeAdapter

    }

    private fun setUpListeners() {

        Logger.d(TAG, "setUpListeners(): ")

        homeAdapter.setListener(View.OnClickListener {
            if (it.tag != null) {
                openGoToUrlBottomSheet(it.tag as GitHubRepoItem)
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.onRefresh()
        }

    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_home
    }

    override fun getBindingVariable(): Int {
        return BR.homeViewModel
    }

    override fun getViewModel(): HomeViewModel {

        Logger.d(TAG, "getViewModel(): ")
        return homeViewModel

    }

    override fun subscribeUi() {

        Logger.d(TAG, "subscribeUi(): ")

        homeViewModel.getPagedListAsLiveData().observe(this, Observer { pagedList ->
            Logger.i(TAG, "pagedList.Observer(): pagedList.size: ${pagedList?.size}")
            Logger.i(TAG, "pagedList.Observer(): pagedList: ${pagedList?.toString()}")
            submitList(pagedList, false)
        })

        homeViewModel.getLoadingLiveData().observe(this, Observer { isFetchInProgress ->
            Logger.d(TAG, "loading.Observer(): isFetchInProgress: $isFetchInProgress")
            handleFetchInProgress(isFetchInProgress)
        })

        homeViewModel.getNetworkErrorLiveData().observe(this, Observer { exception ->
            Logger.d(TAG, "networkError.Observer(): exception: $exception")
            handleNetworkError(exception)
        })

    }

    override fun onChooseUrl(chosenUrl: String) {

        Logger.d(TAG, "onChooseUrl(): chosenUrl: $chosenUrl")
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(chosenUrl)))
        } catch (e: Exception) {
            Logger.e(TAG, "onChooseUrl(): chosenUrl: $chosenUrl, Exception occurred while parsing, Error => ${e.message}")
        }

    }

    private fun submitList(pagedList: PagedList<GitHubRepoItem>?, isRefreshing: Boolean) {

        Logger.d(TAG, "submitList(): pagedList: ${pagedList?.size}, isRefreshing: $isRefreshing")
        homeAdapter.submitList(pagedList)
        swipeRefreshLayout.isRefreshing = isRefreshing

    }

    private fun handleFetchInProgress(isFetchInProgress: Boolean) {

        Logger.d(TAG, "handleFetchInProgress(): isFetchInProgress: $isFetchInProgress")
        homeViewModel.setIsLoading(isFetchInProgress)
        swipeRefreshLayout.isRefreshing = false

    }

    private fun handleNetworkError(exception: Exception) {

        Logger.d(TAG, "handleNetworkError(): exception: $exception")
        swipeRefreshLayout.isRefreshing = false
        Snackbar.make(activityHomeBinding.root, exception.message.toString(), LENGTH_LONG).show()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.clearLocalDbCache -> homeViewModel.onClearCache()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = HomeActivity::class.java.simpleName
    }
}
