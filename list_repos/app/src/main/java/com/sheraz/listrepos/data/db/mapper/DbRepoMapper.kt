package com.sheraz.listrepos.data.db.mapper

import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import com.sheraz.listrepos.ui.models.GitHubRepoItemImpl
import javax.inject.Inject


class DbRepoMapper @Inject constructor() {

    /**
     * Helper class to map DB objects into Domain layer objects.
     */

    fun fromDb(from: GitHubRepoEntity) = GitHubRepoItemImpl(
        from.rowId,
        from.repoId,
        from.fullName,
        from.name,
        from.isRepoPrivate,
        from.htmlUrl,
        from.url,
        from.description,
        from.fork,
        from.owner.avatarUrl,
        from.owner.htmlUrl,
        from.owner.type,
        from.owner.login
    )


//    fun toDb(from: GitHubRepoItemImpl) = GitHubRepoEntity(from.id, from.fullName, from.name, from.isRepoPrivate, from.htmlUrl, from.url, from.description, from.fork)
}