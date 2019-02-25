package com.sheraz.listrepos.ui.modules.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sheraz.listrepos.R
import com.sheraz.listrepos.ui.models.GitHubRepoItem
import com.sheraz.listrepos.ui.modules.home.HomeActivity
import com.sheraz.listrepos.utils.GitHubRepoDiffCallback
import com.sheraz.listrepos.utils.Logger
import kotlinx.android.synthetic.main.item_home.view.*


class HomeAdapter(

    private val mListener: View.OnClickListener

) : PagedListAdapter<GitHubRepoItem, HomeAdapter.ViewHolder>(GitHubRepoDiffCallback()) {


    override fun onCreateViewHolder(viewGroup: ViewGroup, view_type: Int): ViewHolder {
        Logger.d(TAG, "onCreateViewHolder: ")
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_home, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Logger.d(TAG, "onBindViewHolder: position: $position, currentList.size: ${currentList?.size}")
        viewHolder.bind(getItem(position))
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(gitHubRepoItem: GitHubRepoItem?) {

            if (gitHubRepoItem == null) return

            setUpViews(gitHubRepoItem)
            handleClicks()

            (itemView.context as HomeActivity).setTotalItemsCount(currentList?.size!!)

        }

        private fun setUpViews(gitHubRepoItem: GitHubRepoItem?) {

            if (itemView.tag == null) {
                itemView.tag = gitHubRepoItem
            }

//            Picasso.get().load(itemContentModel.driverImage)
//                .noFade()
//                .placeholder(R.drawable.ic_driver)
//                .into(itemView.ivProfileImage)

            itemView.tvRepoName.text = TextUtils.concat(gitHubRepoItem?.name, " ", gitHubRepoItem?.id.toString())
            itemView.tvOwnerLogin.text = gitHubRepoItem?.ownerLogin
            itemView.tvRepoFullName.text = gitHubRepoItem?.fullName
            itemView.tvRepoDescription.text = gitHubRepoItem?.description

            when (gitHubRepoItem?.fork) {
                true -> itemView.tvForked.text = "FORKED"
                false -> itemView.tvForked.text = "NOT FORKED"
            }

        }

        private fun handleClicks() {

            itemView.setOnLongClickListener {

                Logger.d(TAG, "OnLongClickListener: position: $adapterPosition")
                mListener.onClick(it)
                return@setOnLongClickListener true

            }
        }

    }



    companion object {
        private val TAG = HomeAdapter::class.java.simpleName
    }
}
