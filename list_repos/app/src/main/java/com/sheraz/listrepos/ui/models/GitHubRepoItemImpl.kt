package com.sheraz.listrepos.ui.models

data class GitHubRepoItemImpl(
    override val id: Int,
    override val fullName: String,
    override val name: String,
    override val isRepoPrivate: Boolean,
    override val htmlUrl: String,
    override val url: String,
    override val description: String?,
    override val fork: Boolean
) : GitHubRepoItem