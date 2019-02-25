package com.sheraz.listrepos.utils

import androidx.recyclerview.widget.DiffUtil
import com.sheraz.listrepos.ui.models.GitHubRepoItem
import javax.inject.Inject

class GitHubRepoDiffCallback @Inject constructor() : DiffUtil.ItemCallback<GitHubRepoItem>() {

    override fun areContentsTheSame(oldItem: GitHubRepoItem, newItem: GitHubRepoItem): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: GitHubRepoItem, newItem: GitHubRepoItem): Boolean {
        return oldItem.id == newItem.id
    }

}