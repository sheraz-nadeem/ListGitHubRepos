package com.sheraz.listrepos.ui.modules.base

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sheraz.listrepos.ViewModelProviderFactory
import com.sheraz.listrepos.utils.Logger

abstract class BaseActivity<VIEW_DATA_BINDING: ViewDataBinding, VIEW_MODEL: BaseViewModel> : AppCompatActivity() {

    private lateinit var progressDialog: Dialog
    private lateinit var viewDataBinding: VIEW_DATA_BINDING
    private lateinit var viewModel: VIEW_MODEL


    init {
        Logger.d(TAG, "init(): ")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Logger.d(TAG, "onCreate(): ")
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    fun getViewDataBinding(): VIEW_DATA_BINDING {
        Logger.d(TAG, "getViewDataBinding(): ")
        return viewDataBinding
    }

    private fun performDataBinding() {
        Logger.d(TAG, "performDataBinding(): ")
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutResId())
        viewModel = getViewModel()
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }

//    fun showLoading() {
//        hideLoading()
//        mProgressDialog = new Dialog(context);
//        if (progressDialog.getWindow() != null) {
//            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }
//        progressDialog.setContentView(R.layout.progress_dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.show();
//    }
//
//    fun hideLoading() {
//        if (mProgressDialog != null && mProgressDialog.isShowing) {
//            mProgressDialog.cancel()
//        }
//    }

    /**********
     * Abstract Methods
     **********/


    /**
     * Child classes will use this method to
     * initialize their UI elements in this method
     */
    abstract fun initUI()


    /**
     * @return layout resource id
     */
    @LayoutRes abstract fun getLayoutResId(): Int

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * Override for set view model
     * @return view model instance
     */
    abstract fun getViewModel(): VIEW_MODEL


    companion object {
        private val TAG = BaseActivity::class.java.simpleName
    }
}