package com.sheraz.listrepos.shared

import com.google.gson.Gson
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity

val amiandoRepo = Gson().fromJson("{\n" +
        "      \"id\":2637328,\n" +
        "      \"node_id\":\"MDEwOlJlcG9zaXRvcnkyNjM3MzI4\",\n" +
        "      \"name\":\"amiando\",\n" +
        "      \"full_name\":\"xing/amiando\",\n" +
        "      \"private\":false,\n" +
        "      \"owner\":{\n" +
        "         \"login\":\"xing\",\n" +
        "         \"id\":27901,\n" +
        "         \"node_id\":\"MDEyOk9yZ2FuaXphdGlvbjI3OTAx\",\n" +
        "         \"avatar_url\":\"https://avatars2.githubusercontent.com/u/27901?v=4\",\n" +
        "         \"gravatar_id\":\"\",\n" +
        "         \"url\":\"https://api.github.com/users/xing\",\n" +
        "         \"html_url\":\"https://github.com/xing\",\n" +
        "         \"followers_url\":\"https://api.github.com/users/xing/followers\",\n" +
        "         \"following_url\":\"https://api.github.com/users/xing/following{/other_user}\",\n" +
        "         \"gists_url\":\"https://api.github.com/users/xing/gists{/gist_id}\",\n" +
        "         \"starred_url\":\"https://api.github.com/users/xing/starred{/owner}{/repo}\",\n" +
        "         \"subscriptions_url\":\"https://api.github.com/users/xing/subscriptions\",\n" +
        "         \"organizations_url\":\"https://api.github.com/users/xing/orgs\",\n" +
        "         \"repos_url\":\"https://api.github.com/users/xing/repos\",\n" +
        "         \"events_url\":\"https://api.github.com/users/xing/events{/privacy}\",\n" +
        "         \"received_events_url\":\"https://api.github.com/users/xing/received_events\",\n" +
        "         \"type\":\"Organization\",\n" +
        "         \"site_admin\":false\n" +
        "      },\n" +
        "      \"html_url\":\"https://github.com/xing/amiando\",\n" +
        "      \"description\":\"Amiando Api Ruby implementation\",\n" +
        "      \"fork\":false,\n" +
        "      \"url\":\"https://api.github.com/repos/xing/amiando\",\n" +
        "      \"forks_url\":\"https://api.github.com/repos/xing/amiando/forks\",\n" +
        "      \"keys_url\":\"https://api.github.com/repos/xing/amiando/keys{/key_id}\",\n" +
        "      \"collaborators_url\":\"https://api.github.com/repos/xing/amiando/collaborators{/collaborator}\",\n" +
        "      \"teams_url\":\"https://api.github.com/repos/xing/amiando/teams\",\n" +
        "      \"hooks_url\":\"https://api.github.com/repos/xing/amiando/hooks\",\n" +
        "      \"issue_events_url\":\"https://api.github.com/repos/xing/amiando/issues/events{/number}\",\n" +
        "      \"events_url\":\"https://api.github.com/repos/xing/amiando/events\",\n" +
        "      \"assignees_url\":\"https://api.github.com/repos/xing/amiando/assignees{/user}\",\n" +
        "      \"branches_url\":\"https://api.github.com/repos/xing/amiando/branches{/branch}\",\n" +
        "      \"tags_url\":\"https://api.github.com/repos/xing/amiando/tags\",\n" +
        "      \"blobs_url\":\"https://api.github.com/repos/xing/amiando/git/blobs{/sha}\",\n" +
        "      \"git_tags_url\":\"https://api.github.com/repos/xing/amiando/git/tags{/sha}\",\n" +
        "      \"git_refs_url\":\"https://api.github.com/repos/xing/amiando/git/refs{/sha}\",\n" +
        "      \"trees_url\":\"https://api.github.com/repos/xing/amiando/git/trees{/sha}\",\n" +
        "      \"statuses_url\":\"https://api.github.com/repos/xing/amiando/statuses/{sha}\",\n" +
        "      \"languages_url\":\"https://api.github.com/repos/xing/amiando/languages\",\n" +
        "      \"stargazers_url\":\"https://api.github.com/repos/xing/amiando/stargazers\",\n" +
        "      \"contributors_url\":\"https://api.github.com/repos/xing/amiando/contributors\",\n" +
        "      \"subscribers_url\":\"https://api.github.com/repos/xing/amiando/subscribers\",\n" +
        "      \"subscription_url\":\"https://api.github.com/repos/xing/amiando/subscription\",\n" +
        "      \"commits_url\":\"https://api.github.com/repos/xing/amiando/commits{/sha}\",\n" +
        "      \"git_commits_url\":\"https://api.github.com/repos/xing/amiando/git/commits{/sha}\",\n" +
        "      \"comments_url\":\"https://api.github.com/repos/xing/amiando/comments{/number}\",\n" +
        "      \"issue_comment_url\":\"https://api.github.com/repos/xing/amiando/issues/comments{/number}\",\n" +
        "      \"contents_url\":\"https://api.github.com/repos/xing/amiando/contents/{+path}\",\n" +
        "      \"compare_url\":\"https://api.github.com/repos/xing/amiando/compare/{base}...{head}\",\n" +
        "      \"merges_url\":\"https://api.github.com/repos/xing/amiando/merges\",\n" +
        "      \"archive_url\":\"https://api.github.com/repos/xing/amiando/{archive_format}{/ref}\",\n" +
        "      \"downloads_url\":\"https://api.github.com/repos/xing/amiando/downloads\",\n" +
        "      \"issues_url\":\"https://api.github.com/repos/xing/amiando/issues{/number}\",\n" +
        "      \"pulls_url\":\"https://api.github.com/repos/xing/amiando/pulls{/number}\",\n" +
        "      \"milestones_url\":\"https://api.github.com/repos/xing/amiando/milestones{/number}\",\n" +
        "      \"notifications_url\":\"https://api.github.com/repos/xing/amiando/notifications{?since,all,participating}\",\n" +
        "      \"labels_url\":\"https://api.github.com/repos/xing/amiando/labels{/name}\",\n" +
        "      \"releases_url\":\"https://api.github.com/repos/xing/amiando/releases{/id}\",\n" +
        "      \"deployments_url\":\"https://api.github.com/repos/xing/amiando/deployments\",\n" +
        "      \"created_at\":\"2011-10-24T16:09:04Z\",\n" +
        "      \"updated_at\":\"2013-12-02T16:19:35Z\",\n" +
        "      \"pushed_at\":\"2013-08-01T18:09:42Z\",\n" +
        "      \"git_url\":\"git://github.com/xing/amiando.git\",\n" +
        "      \"ssh_url\":\"git@github.com:xing/amiando.git\",\n" +
        "      \"clone_url\":\"https://github.com/xing/amiando.git\",\n" +
        "      \"svn_url\":\"https://github.com/xing/amiando\",\n" +
        "      \"homepage\":\"http://rdoc.info/github/xing/amiando/master/frames\",\n" +
        "      \"size\":521,\n" +
        "      \"stargazers_count\":8,\n" +
        "      \"watchers_count\":8,\n" +
        "      \"language\":\"Ruby\",\n" +
        "      \"has_issues\":true,\n" +
        "      \"has_projects\":true,\n" +
        "      \"has_downloads\":true,\n" +
        "      \"has_wiki\":true,\n" +
        "      \"has_pages\":false,\n" +
        "      \"forks_count\":10,\n" +
        "      \"mirror_url\":null,\n" +
        "      \"archived\":false,\n" +
        "      \"open_issues_count\":4,\n" +
        "      \"license\":{\n" +
        "         \"key\":\"mit\",\n" +
        "         \"name\":\"MIT License\",\n" +
        "         \"spdx_id\":\"MIT\",\n" +
        "         \"url\":\"https://api.github.com/licenses/mit\",\n" +
        "         \"node_id\":\"MDc6TGljZW5zZTEz\"\n" +
        "      },\n" +
        "      \"forks\":10,\n" +
        "      \"open_issues\":4,\n" +
        "      \"watchers\":8,\n" +
        "      \"default_branch\":\"master\"\n" +
        "   }", GitHubRepoEntity::class.java)


val afQuickLookView = Gson().fromJson("{\n" +
        "    \"id\": 8337219,\n" +
        "    \"node_id\": \"MDEwOlJlcG9zaXRvcnk4MzM3MjE5\",\n" +
        "    \"name\": \"AFQuickLookView\",\n" +
        "    \"full_name\": \"xing/AFQuickLookView\",\n" +
        "    \"private\": false,\n" +
        "    \"owner\": {\n" +
        "      \"login\": \"xing\",\n" +
        "      \"id\": 27901,\n" +
        "      \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjI3OTAx\",\n" +
        "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/27901?v=4\",\n" +
        "      \"gravatar_id\": \"\",\n" +
        "      \"url\": \"https://api.github.com/users/xing\",\n" +
        "      \"html_url\": \"https://github.com/xing\",\n" +
        "      \"followers_url\": \"https://api.github.com/users/xing/followers\",\n" +
        "      \"following_url\": \"https://api.github.com/users/xing/following{/other_user}\",\n" +
        "      \"gists_url\": \"https://api.github.com/users/xing/gists{/gist_id}\",\n" +
        "      \"starred_url\": \"https://api.github.com/users/xing/starred{/owner}{/repo}\",\n" +
        "      \"subscriptions_url\": \"https://api.github.com/users/xing/subscriptions\",\n" +
        "      \"organizations_url\": \"https://api.github.com/users/xing/orgs\",\n" +
        "      \"repos_url\": \"https://api.github.com/users/xing/repos\",\n" +
        "      \"events_url\": \"https://api.github.com/users/xing/events{/privacy}\",\n" +
        "      \"received_events_url\": \"https://api.github.com/users/xing/received_events\",\n" +
        "      \"type\": \"Organization\",\n" +
        "      \"site_admin\": false\n" +
        "    },\n" +
        "    \"html_url\": \"https://github.com/xing/AFQuickLookView\",\n" +
        "    \"description\": \"AFNetworking Extension for document previews of remote files\",\n" +
        "    \"fork\": false,\n" +
        "    \"url\": \"https://api.github.com/repos/xing/AFQuickLookView\",\n" +
        "    \"forks_url\": \"https://api.github.com/repos/xing/AFQuickLookView/forks\",\n" +
        "    \"keys_url\": \"https://api.github.com/repos/xing/AFQuickLookView/keys{/key_id}\",\n" +
        "    \"collaborators_url\": \"https://api.github.com/repos/xing/AFQuickLookView/collaborators{/collaborator}\",\n" +
        "    \"teams_url\": \"https://api.github.com/repos/xing/AFQuickLookView/teams\",\n" +
        "    \"hooks_url\": \"https://api.github.com/repos/xing/AFQuickLookView/hooks\",\n" +
        "    \"issue_events_url\": \"https://api.github.com/repos/xing/AFQuickLookView/issues/events{/number}\",\n" +
        "    \"events_url\": \"https://api.github.com/repos/xing/AFQuickLookView/events\",\n" +
        "    \"assignees_url\": \"https://api.github.com/repos/xing/AFQuickLookView/assignees{/user}\",\n" +
        "    \"branches_url\": \"https://api.github.com/repos/xing/AFQuickLookView/branches{/branch}\",\n" +
        "    \"tags_url\": \"https://api.github.com/repos/xing/AFQuickLookView/tags\",\n" +
        "    \"blobs_url\": \"https://api.github.com/repos/xing/AFQuickLookView/git/blobs{/sha}\",\n" +
        "    \"git_tags_url\": \"https://api.github.com/repos/xing/AFQuickLookView/git/tags{/sha}\",\n" +
        "    \"git_refs_url\": \"https://api.github.com/repos/xing/AFQuickLookView/git/refs{/sha}\",\n" +
        "    \"trees_url\": \"https://api.github.com/repos/xing/AFQuickLookView/git/trees{/sha}\",\n" +
        "    \"statuses_url\": \"https://api.github.com/repos/xing/AFQuickLookView/statuses/{sha}\",\n" +
        "    \"languages_url\": \"https://api.github.com/repos/xing/AFQuickLookView/languages\",\n" +
        "    \"stargazers_url\": \"https://api.github.com/repos/xing/AFQuickLookView/stargazers\",\n" +
        "    \"contributors_url\": \"https://api.github.com/repos/xing/AFQuickLookView/contributors\",\n" +
        "    \"subscribers_url\": \"https://api.github.com/repos/xing/AFQuickLookView/subscribers\",\n" +
        "    \"subscription_url\": \"https://api.github.com/repos/xing/AFQuickLookView/subscription\",\n" +
        "    \"commits_url\": \"https://api.github.com/repos/xing/AFQuickLookView/commits{/sha}\",\n" +
        "    \"git_commits_url\": \"https://api.github.com/repos/xing/AFQuickLookView/git/commits{/sha}\",\n" +
        "    \"comments_url\": \"https://api.github.com/repos/xing/AFQuickLookView/comments{/number}\",\n" +
        "    \"issue_comment_url\": \"https://api.github.com/repos/xing/AFQuickLookView/issues/comments{/number}\",\n" +
        "    \"contents_url\": \"https://api.github.com/repos/xing/AFQuickLookView/contents/{+path}\",\n" +
        "    \"compare_url\": \"https://api.github.com/repos/xing/AFQuickLookView/compare/{base}...{head}\",\n" +
        "    \"merges_url\": \"https://api.github.com/repos/xing/AFQuickLookView/merges\",\n" +
        "    \"archive_url\": \"https://api.github.com/repos/xing/AFQuickLookView/{archive_format}{/ref}\",\n" +
        "    \"downloads_url\": \"https://api.github.com/repos/xing/AFQuickLookView/downloads\",\n" +
        "    \"issues_url\": \"https://api.github.com/repos/xing/AFQuickLookView/issues{/number}\",\n" +
        "    \"pulls_url\": \"https://api.github.com/repos/xing/AFQuickLookView/pulls{/number}\",\n" +
        "    \"milestones_url\": \"https://api.github.com/repos/xing/AFQuickLookView/milestones{/number}\",\n" +
        "    \"notifications_url\": \"https://api.github.com/repos/xing/AFQuickLookView/notifications{?since,all,participating}\",\n" +
        "    \"labels_url\": \"https://api.github.com/repos/xing/AFQuickLookView/labels{/name}\",\n" +
        "    \"releases_url\": \"https://api.github.com/repos/xing/AFQuickLookView/releases{/id}\",\n" +
        "    \"deployments_url\": \"https://api.github.com/repos/xing/AFQuickLookView/deployments\",\n" +
        "    \"created_at\": \"2013-02-21T14:54:12Z\",\n" +
        "    \"updated_at\": \"2018-02-14T23:05:03Z\",\n" +
        "    \"pushed_at\": \"2013-04-05T20:12:06Z\",\n" +
        "    \"git_url\": \"git://github.com/xing/AFQuickLookView.git\",\n" +
        "    \"ssh_url\": \"git@github.com:xing/AFQuickLookView.git\",\n" +
        "    \"clone_url\": \"https://github.com/xing/AFQuickLookView.git\",\n" +
        "    \"svn_url\": \"https://github.com/xing/AFQuickLookView\",\n" +
        "    \"homepage\": null,\n" +
        "    \"size\": 231,\n" +
        "    \"stargazers_count\": 75,\n" +
        "    \"watchers_count\": 75,\n" +
        "    \"language\": \"Objective-C\",\n" +
        "    \"has_issues\": true,\n" +
        "    \"has_projects\": true,\n" +
        "    \"has_downloads\": true,\n" +
        "    \"has_wiki\": true,\n" +
        "    \"has_pages\": false,\n" +
        "    \"forks_count\": 6,\n" +
        "    \"mirror_url\": null,\n" +
        "    \"archived\": false,\n" +
        "    \"open_issues_count\": 10,\n" +
        "    \"license\": {\n" +
        "      \"key\": \"mit\",\n" +
        "      \"name\": \"MIT License\",\n" +
        "      \"spdx_id\": \"MIT\",\n" +
        "      \"url\": \"https://api.github.com/licenses/mit\",\n" +
        "      \"node_id\": \"MDc6TGljZW5zZTEz\"\n" +
        "    },\n" +
        "    \"forks\": 6,\n" +
        "    \"open_issues\": 10,\n" +
        "    \"watchers\": 75,\n" +
        "    \"default_branch\": \"master\"\n" +
        "  }", GitHubRepoEntity::class.java)