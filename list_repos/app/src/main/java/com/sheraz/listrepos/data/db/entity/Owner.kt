package com.sheraz.listrepos.data.db.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Owner(

    @ColumnInfo(name = "owner_id")
    val id: Int,

    @ColumnInfo(name = "owner_type")
    val type: String,

    @ColumnInfo(name = "owner_url")
    val url: String,

    @ColumnInfo(name = "owner_html_url")
    @SerializedName("html_url")
    val htmlUrl: String,

    @ColumnInfo(name = "owner_avatar_url")
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "owner_login")
    val login: String,

    @ColumnInfo(name = "owner_repos_url")
    @SerializedName("repos_url")
    val reposUrl: String,

    @ColumnInfo(name = "owner_site_admin")
    @SerializedName("site_admin")
    val siteAdmin: Boolean,

    @ColumnInfo(name = "owner_events_url")
    @SerializedName("events_url")
    val eventsUrl: String,

    @ColumnInfo(name = "owner_followers_url")
    @SerializedName("followers_url")
    val followersUrl: String,

    @ColumnInfo(name = "owner_following_url")
    @SerializedName("following_url")
    val followingUrl: String,

    @ColumnInfo(name = "owner_gists_url")
    @SerializedName("gists_url")
    val gistsUrl: String,

    @ColumnInfo(name = "owner_gravatar_id")
    @SerializedName("gravatar_id")
    val gravatarId: String,

    @ColumnInfo(name = "owner_node_id")
    @SerializedName("node_id")
    val nodeId: String,

    @ColumnInfo(name = "owner_organizations_url")
    @SerializedName("organizations_url")
    val organizationsUrl: String,

    @ColumnInfo(name = "owner_received_events_url")
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,

    @ColumnInfo(name = "owner_starred_url")
    @SerializedName("starred_url")
    val starredUrl: String,

    @ColumnInfo(name = "owner_subscriptions_url")
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String

)