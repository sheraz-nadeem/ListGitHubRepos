package com.sheraz.listrepos.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sheraz.listrepos.data.CoroutinesDispatcherProvider
import com.sheraz.listrepos.data.db.dao.GitHubRepoEntityDao
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import com.sheraz.listrepos.data.db.mapper.DbRepoMapper
import com.sheraz.listrepos.data.network.GitHubNetworkDataSource
import com.sheraz.listrepos.shared.afQuickLookView
import com.sheraz.listrepos.shared.amiandoRepo
import com.sheraz.listrepos.shared.provideFakeCoroutinesDispatcherProvider
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Tests for [AppRepository] class
 */
class AppRepositoryTest {

    // Executes tasks in the Architecture Components in the same thread
    // We don't want anything happening on background thread,
    // We want things to happen synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val repoList = listOf(amiandoRepo, afQuickLookView)

    private val dbRepoMapper: DbRepoMapper = DbRepoMapper()
    private val gitHubRepoEntityDao: GitHubRepoEntityDao = mock()
    private val gitHubNetworkDataSource: GitHubNetworkDataSource = mock()
    private val dispatcherProvider: CoroutinesDispatcherProvider = provideFakeCoroutinesDispatcherProvider()
    private val fakeLiveData: LiveData<Result<List<GitHubRepoEntity>>> = mock()

    private lateinit var appRepository: AppRepository

    @Before
    fun buildAppRepository() {

        whenever(gitHubNetworkDataSource.downloadedGitHubRepoList).thenReturn(fakeLiveData)
        appRepository =
            AppRepositoryImpl(dbRepoMapper, gitHubRepoEntityDao, gitHubNetworkDataSource, dispatcherProvider)
    }

    @Test
    fun refreshList_withAtLeastOneNetworkCall() = runBlocking {

        // Given the refresh repos list happens
        appRepository.refreshReposList()

        // Verify that at least once the network sources calls loadGitHubRepos() method
        verify(gitHubNetworkDataSource).loadGitHubRepos(1, AppRepository.NETWORK_PAGE_SIZE)

    }

}