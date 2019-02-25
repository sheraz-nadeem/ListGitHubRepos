package com.sheraz.listrepos.ui.models

data class GitHubRepoItemImpl(
    override val id: Int,
    override val repoId: Int,
    override val fullName: String,
    override val name: String,
    override val isRepoPrivate: Boolean,
    override val htmlUrl: String,
    override val url: String,
    override val description: String?,
    override val fork: Boolean,
    override val ownerAvatarUrl: String?,
    override val ownerHtmlUrl: String,
    override val ownerType: String?,
    override val ownerLogin: String?
) : GitHubRepoItem {

    override fun equals(other: Any?): Boolean {

        val item = other as GitHubRepoItemImpl
        return (item.id == this.id && item.repoId == this.repoId)

    }
}