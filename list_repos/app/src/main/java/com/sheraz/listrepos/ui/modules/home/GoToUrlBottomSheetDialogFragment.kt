package com.sheraz.listrepos.ui.modules.home

import android.app.Dialog
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sheraz.listrepos.Injector
import com.sheraz.listrepos.R
import com.sheraz.listrepos.databinding.FragmentGoToUrlBottomSheetBinding
import com.sheraz.listrepos.ui.models.GitHubRepoItem
import com.sheraz.listrepos.ui.models.GitHubRepoItemImpl
import com.sheraz.listrepos.utils.Logger
import kotlinx.android.synthetic.main.fragment_go_to_url_bottom_sheet.*


class GoToUrlBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentGoToUrlBottomSheetBinding

    // Data
    private lateinit var repoItem: GitHubRepoItem
    private var chosenUrl: String = ""

    // Interfaces
    private var chooseGoToUrlListener: OnChooseGoToUrlListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        logger.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        initData()
    }

    private fun initData() {

        logger.d(TAG, "initData: ")
        if (arguments != null && arguments!!.getParcelable<Parcelable>(ARG_REPO_DATA_ITEM) != null) {
            repoItem = arguments!!.getParcelable<Parcelable>(ARG_REPO_DATA_ITEM) as GitHubRepoItemImpl
            logger.v(TAG, "initData: repoItem: " + repoItem!!.toString())
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        logger.d(TAG, "onCreateDialog: ")
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logger.d(TAG, "onCreateView: ")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_go_to_url_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logger.d(TAG, "onViewCreated: ")

        super.onViewCreated(view, savedInstanceState)

        tvChooseGoToUrlHeader.text = resources.getString(R.string.go_to_url_bottom_sheet_header_text)

        llRepoCheckContainer.setOnClickListener {
            cbRepoHtmlUrl.isChecked = true
            cbOwnerHtmlUrl.isChecked = false
            chosenUrl = repoItem.htmlUrl
        }
        llOwnerCheckContainer.setOnClickListener {
            cbRepoHtmlUrl.isChecked = false
            cbOwnerHtmlUrl.isChecked = true
            chosenUrl = repoItem.ownerHtmlUrl
        }

        ibDoneAction.setOnClickListener {
            logger.d(TAG, "ibDoneAction.onClick: chosenUrl: $chosenUrl")
            chooseGoToUrlListener?.onChooseUrl(chosenUrl)
            dismiss()
        }
    }

    override fun onResume() {
        logger.d(TAG, "onResume: ")
        super.onResume()
    }

    override fun onPause() {
        logger.d(TAG, "onPause: ")
        super.onPause()
    }

    override fun onDestroy() {
        logger.d(TAG, "onDestroy: ")
        super.onDestroy()
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */

    interface OnChooseGoToUrlListener {
        fun onChooseUrl(chosenUrl: String)
    }

    fun setGoToUrlListener(listener: OnChooseGoToUrlListener) {
        this.chooseGoToUrlListener = listener
    }

    companion object {
        val TAG = GoToUrlBottomSheetDialogFragment::class.java.simpleName
        val logger: Logger = Injector.get().logger()
        private const val ARG_REPO_DATA_ITEM = "arg_list_items"


        fun newInstance(repoItem: GitHubRepoItem): GoToUrlBottomSheetDialogFragment {
            val fragment = GoToUrlBottomSheetDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_REPO_DATA_ITEM, repoItem as GitHubRepoItemImpl)
            fragment.arguments = bundle
            return fragment
        }
    }
}
