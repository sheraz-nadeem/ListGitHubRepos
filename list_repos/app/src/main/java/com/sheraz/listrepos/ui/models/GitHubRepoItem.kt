package com.sheraz.listrepos.ui.models

interface GitHubRepoItem {

    /**
     * Items to be shown in the UI
     * It's not a good practice to pass
     * around whole database objects
     * or list of objects, as these objects
     * certainly have lot of stuff we don't
     * want in the UI
     */

    val id: Int
    val repoId: Int
    val fullName: String
    val name: String
    val isRepoPrivate: Boolean
    val htmlUrl: String
    val url: String
    val description: String?
    val fork: Boolean
    val ownerAvatarUrl: String?
    val ownerHtmlUrl: String
    val ownerType: String?
    val ownerLogin: String?

}