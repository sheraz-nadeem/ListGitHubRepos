package com.sheraz.listrepos.data.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "github_repo")
data class GitHubRepoEntity(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val rowId: Int,

    @ColumnInfo(name = "repo_id")
    @SerializedName("id")
    val repoId: Int,

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    @NonNull
    val fullName: String,

    @ColumnInfo(name = "name")
    @NonNull
    val name: String,

    @ColumnInfo(name = "is_repo_private")
    @SerializedName("private")
    val isRepoPrivate: Boolean,

    @Embedded(prefix = "owner_")
    @NonNull
    val owner: Owner,

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    @NonNull
    val htmlUrl: String,

    @ColumnInfo(name = "url")
    @NonNull
    val url: String,

    @ColumnInfo(name = "description")
    val description: String? = "",

    @ColumnInfo(name = "fork")
    val fork: Boolean,

    @ColumnInfo(name = "watchers")
    val watchers: Int,

    @ColumnInfo(name = "watchers_count")
    @SerializedName("watchers_count")
    val watchersCount: Int,

    @ColumnInfo(name = "archive_url")
    @SerializedName("archive_url")
    val archiveUrl: String? = "",

    @ColumnInfo(name = "archived")
    val archived: Boolean,

    @ColumnInfo(name = "assignees_url")
    @SerializedName("assignees_url")
    val assigneesUrl: String? = "",

    @ColumnInfo(name = "blobs_url")
    @SerializedName("blobs_url")
    val blobsUrl: String? = "",

    @ColumnInfo(name = "branches_url")
    @SerializedName("branches_url")
    val branchesUrl: String? = "",

    @ColumnInfo(name = "clone_url")
    @SerializedName("clone_url")
    val cloneUrl: String? = "",

    @ColumnInfo(name = "collaborators_url")
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String? = "",

    @ColumnInfo(name = "comments_url")
    @SerializedName("comments_url")
    val commentsUrl: String? = "",

    @ColumnInfo(name = "commits_url")
    @SerializedName("commits_url")
    val commitsUrl: String? = "",

    @ColumnInfo(name = "compare_url")
    @SerializedName("compare_url")
    val compareUrl: String? = "",

    @ColumnInfo(name = "contents_url")
    @SerializedName("contents_url")
    val contentsUrl: String? = "",

    @ColumnInfo(name = "contributors_url")
    @SerializedName("contributors_url")
    val contributorsUrl: String? = "",

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    val createdAt: String? = "",

    @ColumnInfo(name = "default_branch")
    @SerializedName("default_branch")
    val defaultBranch: String? = "",

    @ColumnInfo(name = "deployments_url")
    @SerializedName("deployments_url")
    val deploymentsUrl: String? = "",

    @ColumnInfo(name = "downloads_url")
    @SerializedName("downloads_url")
    val downloadsUrl: String? = "",

    @ColumnInfo(name = "events_url")
    @SerializedName("events_url")
    val eventsUrl: String? = "",

    @ColumnInfo(name = "forks")
    val forks: Int,

    @ColumnInfo(name = "forks_count")
    @SerializedName("forks_count")
    val forksCount: Int,

    @ColumnInfo(name = "forks_url")
    @SerializedName("forks_url")
    val forksUrl: String? = "",

    @ColumnInfo(name = "git_commits_url")
    @SerializedName("git_commits_url")
    val gitCommitsUrl: String? = "",

    @ColumnInfo(name = "git_refs_url")
    @SerializedName("git_refs_url")
    val gitRefsUrl: String? = "",

    @ColumnInfo(name = "git_tags_url")
    @SerializedName("git_tags_url")
    val gitTagsUrl: String? = "",

    @ColumnInfo(name = "git_url")
    @SerializedName("git_url")
    val gitUrl: String? = "",

    @ColumnInfo(name = "has_downloads")
    @SerializedName("has_downloads")
    val hasDownloads: Boolean,

    @ColumnInfo(name = "has_issues")
    @SerializedName("has_issues")
    val hasIssues: Boolean,

    @ColumnInfo(name = "has_pages")
    @SerializedName("has_pages")
    val hasPages: Boolean,

    @ColumnInfo(name = "has_projects")
    @SerializedName("has_projects")
    val hasProjects: Boolean,

    @ColumnInfo(name = "has_wiki")
    @SerializedName("has_wiki")
    val hasWiki: Boolean,

    @ColumnInfo(name = "homepage")
    val homepage: String? = "",

    @ColumnInfo(name = "hooks_url")
    @SerializedName("hooks_url")
    val hooksUrl: String? = "",

    @ColumnInfo(name = "issue_comment_url")
    @SerializedName("issue_comment_url")
    val issueCommentUrl: String? = "",

    @ColumnInfo(name = "issue_events_url")
    @SerializedName("issue_events_url")
    val issueEventsUrl: String? = "",

    @ColumnInfo(name = "issues_url")
    @SerializedName("issues_url")
    val issuesUrl: String? = "",

    @ColumnInfo(name = "keys_url")
    @SerializedName("keys_url")
    val keysUrl: String? = "",

    @ColumnInfo(name = "labels_url")
    @SerializedName("labels_url")
    val labelsUrl: String? = "",

    @ColumnInfo(name = "language")
    val language: String? = "",

    @ColumnInfo(name = "languages_url")
    @SerializedName("languages_url")
    val languagesUrl: String? = "",

    @ColumnInfo(name = "merges_url")
    @SerializedName("merges_url")
    val mergesUrl: String? = "",

    @ColumnInfo(name = "milestones_url")
    @SerializedName("milestones_url")
    val milestonesUrl: String? = "",

    @ColumnInfo(name = "mirror_url")
    @SerializedName("mirror_url")
    val mirrorUrl: String? = "",

    @ColumnInfo(name = "node_id")
    @SerializedName("node_id")
    val nodeId: String? = "",

    @ColumnInfo(name = "notifications_url")
    @SerializedName("notifications_url")
    val notificationsUrl: String? = "",

    @ColumnInfo(name = "open_issues")
    @SerializedName("open_issues")
    val openIssues: Int,

    @ColumnInfo(name = "open_issues_count")
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,

    @ColumnInfo(name = "pulls_url")
    @SerializedName("pulls_url")
    val pullsUrl: String? = "",

    @ColumnInfo(name = "pushed_at")
    @SerializedName("pushed_at")
    val pushedAt: String? = "",

    @ColumnInfo(name = "releases_url")
    @SerializedName("releases_url")
    val releasesUrl: String? = "",

    @ColumnInfo(name = "size")
    val size: Int,

    @ColumnInfo(name = "ssh_url")
    @SerializedName("ssh_url")
    val sshUrl: String? = "",

    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    val stargazersCount: Int,

    @ColumnInfo(name = "stargazers_url")
    @SerializedName("stargazers_url")
    val stargazersUrl: String? = "",

    @ColumnInfo(name = "statuses_url")
    @SerializedName("statuses_url")
    val statusesUrl: String? = "",

    @ColumnInfo(name = "subscribers_url")
    @SerializedName("subscribers_url")
    val subscribersUrl: String? = "",

    @ColumnInfo(name = "subscription_url")
    @SerializedName("subscription_url")
    val subscriptionUrl: String? = "",

    @ColumnInfo(name = "svn_url")
    @SerializedName("svn_url")
    val svnUrl: String? = "",

    @ColumnInfo(name = "tags_url")
    @SerializedName("tags_url")
    val tagsUrl: String? = "",

    @ColumnInfo(name = "teams_url")
    @SerializedName("teams_url")
    val teamsUrl: String? = "",

    @ColumnInfo(name = "trees_url")
    @SerializedName("trees_url")
    val treesUrl: String? = "",

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    val updatedAt: String? = ""

)