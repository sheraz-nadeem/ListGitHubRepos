package com.sheraz.listrepos.ui.modules.adapters

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
    private val logger: Logger,
    private val mPicasso: Picasso
) : PagedListAdapter<GitHubRepoItem, HomeAdapter.ViewHolder>(GitHubRepoDiffCallback()) {


    private var mListener: View.OnClickListener? = null

    init { logger.d(TAG, "init(): ") }

    override fun onCreateViewHolder(viewGroup: ViewGroup, view_type: Int): ViewHolder {
        logger.d(TAG, "onCreateViewHolder: ")
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_home, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        logger.d(TAG, "onBindViewHolder: position: $position, currentList.size: ${currentList?.size}")
        viewHolder.bind(getItem(position))
    }

    fun setListener(listener: View.OnClickListener) {
        logger.d(TAG, "setListener: ")
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

            logger.d(TAG, "setUpViews: position: $adapterPosition, ownerAvatarUrl: ${gitHubRepoItem?.ownerAvatarUrl}")

            mPicasso.load(gitHubRepoItem?.ownerAvatarUrl)
                .noFade()
                .placeholder(R.drawable.octocat)
                .into(itemView.ivOwnerAvatar)

            itemView.tvRepoName.text = gitHubRepoItem?.name
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

                logger.d(TAG, "OnLongClickListener: position: $adapterPosition")
                mListener?.onClick(it)
                return@setOnLongClickListener true

            }
        }

    }



    companion object {
        private val TAG = HomeAdapter::class.java.simpleName
    }
}
