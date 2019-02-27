package com.sheraz.listrepos.ui.modules.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sheraz.listrepos.R
import com.sheraz.listrepos.ui.models.GitHubRepoItem
import com.sheraz.listrepos.utils.GitHubRepoDiffCallback
import com.sheraz.listrepos.utils.Logger
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*


class HomeAdapter (
    private val mPicasso: Picasso
) : PagedListAdapter<GitHubRepoItem, HomeAdapter.ViewHolder>(GitHubRepoDiffCallback()) {


    private var mListener: View.OnClickListener? = null

    init { Logger.d(TAG, "init(): ") }

    override fun onCreateViewHolder(viewGroup: ViewGroup, view_type: Int): ViewHolder {
        Logger.d(TAG, "onCreateViewHolder: ")
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_home, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Logger.d(TAG, "onBindViewHolder: position: $position, currentList.size: ${currentList?.size}")
        viewHolder.bind(getItem(position))
    }

    fun setListener(listener: View.OnClickListener) {
        Logger.d(TAG, "setListener: ")
        mListener = listener
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(gitHubRepoItem: GitHubRepoItem?) {

            if (gitHubRepoItem == null) return

            setUpViews(gitHubRepoItem)
            handleClicks()

        }

        private fun setUpViews(gitHubRepoItem: GitHubRepoItem?) {

            if (itemView.tag == null) {
                itemView.tag = gitHubRepoItem
            }

            Logger.d(TAG, "setUpViews: position: $adapterPosition, ownerAvatarUrl: ${gitHubRepoItem?.ownerAvatarUrl}")

            mPicasso.load(gitHubRepoItem?.ownerAvatarUrl)
                .noFade()
                .placeholder(R.drawable.octocat)
                .into(itemView.ivOwnerAvatar)

            itemView.tvRepoName.text = TextUtils.concat(gitHubRepoItem?.name, " ", gitHubRepoItem?.id.toString())
            itemView.tvOwnerLogin.text = gitHubRepoItem?.ownerLogin
            itemView.tvRepoFullName.text = gitHubRepoItem?.fullName
            itemView.tvRepoDescription.text = gitHubRepoItem?.description

            when (gitHubRepoItem?.fork) {
                true -> {
                    itemView.tvForked.text = "FORKED"
                    itemView.llRootContainer.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.white))
                }
                false -> {
                    itemView.tvForked.text = "NOT FORKED"
                    itemView.llRootContainer.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimaryVeryLight))
                }
            }

        }

        private fun handleClicks() {

            itemView.setOnLongClickListener {

                Logger.d(TAG, "OnLongClickListener: position: $adapterPosition")
                mListener?.onClick(it)
                return@setOnLongClickListener true

            }
        }

    }



    companion object {
        private val TAG = HomeAdapter::class.java.simpleName
    }
}
