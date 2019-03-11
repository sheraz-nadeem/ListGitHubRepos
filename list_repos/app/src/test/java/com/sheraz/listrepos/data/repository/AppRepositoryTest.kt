package com.sheraz.listrepos.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.*
import com.sheraz.listrepos.data.CoroutinesDispatcherProvider
import com.sheraz.listrepos.data.db.dao.GitHubRepoEntityDao
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import com.sheraz.listrepos.data.db.mapper.DbRepoMapper
import com.sheraz.listrepos.data.network.GitHubNetworkDataSource
import com.sheraz.listrepos.shared.afQuickLookView
import com.sheraz.listrepos.shared.amiandoRepo
import com.sheraz.listrepos.shared.provideFakeCoroutinesDispatcherProvider
import com.sheraz.listrepos.ui.models.GitHubRepoItem
import com.sheraz.listrepos.utils.Logger
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer


/**
 * Tests for [AppRepository] class
 */
@RunWith(MockitoJUnitRunner::class)
class AppRepositoryTest {

    // Executes tasks in the Architecture Components in the same thread
    // We don't want anything happening on background thread,
    // We want things to happen synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val dbRepoMapper: DbRepoMapper = mock()

    private val repoList = listOf(amiandoRepo, afQuickLookView)

    private val logger: Logger = mock()
    private val gitHubRepoEntityDao: GitHubRepoEntityDao = mock()
    private val gitHubNetworkDataSource: GitHubNetworkDataSource = mock()
    private val dispatcherProvider: CoroutinesDispatcherProvider = provideFakeCoroutinesDispatcherProvider()
    private val fakeLiveData: LiveData<Result<List<GitHubRepoEntity>>> = mock()

    private lateinit var appRepository: AppRepository

    @Before
    fun buildAppRepository() {

        whenever(gitHubNetworkDataSource.downloadedGitHubRepoList).thenReturn(fakeLiveData)
        appRepository =
            AppRepositoryImpl(logger, dbRepoMapper, gitHubRepoEntityDao, gitHubNetworkDataSource, dispatcherProvider)
    }

    @Test
    fun refreshList_withAtLeastOneNetworkCall() = runBlocking {

        // Given the refresh repos list happens
        appRepository.refreshReposList()

        // Verify that at least once the network sources calls loadGitHubRepos() method
        verify(gitHubNetworkDataSource).loadGitHubRepos(1, AppRepository.NETWORK_PAGE_SIZE)

    }

    @Test
    fun refreshList_withAtLeastOneNetworkCall_withArgumentCaptor() = runBlocking {

        // Given the refresh repos list happens
        appRepository.refreshReposList()

        // Verify that only once loadGitHubRepos() method is called
        // with correct arguments
        val captor = argumentCaptor<Int>().apply {
            verify(gitHubNetworkDataSource, times(1)).loadGitHubRepos(capture(), capture())
        }

        assertEquals(2, captor.allValues.size)
        assertEquals(1, captor.firstValue)
        assertEquals(AppRepository.NETWORK_PAGE_SIZE, captor.secondValue)
    }

    @Test
    fun clearCache_withAtLeastOneDaoMethodCall() {

        // Given the clear cache is triggered
        appRepository.clearCache()

        // Verify that once DAO method call is invoked
        verify(gitHubRepoEntityDao, times(1)).deleteAll()
    }

//    @Test
//    fun getLiveData() {
//
//        // Given the live data is requested
//        whenever(gitHubRepoEntityDao.getAllReposPaged()).thenReturn(fakeDataSourceEntity)
//        whenever(dbRepoMapper.fromDb(any())).thenReturn(gitRepoItemList[0])
//        whenever(dbRepoMapper.fromDb(any())).thenAnswer(Answer<GitHubRepoItem> { return@Answer gitRepoItemList[0] })
//        whenever(fakeDataSourceEntity.map { dbRepoMapper.fromDb(any()) as GitHubRepoItem }).thenAnswer(Answer<GitHubRepoItem> { return@Answer gitRepoItemList[0] })
//
//        whenever(appRepository.getAllReposPagedFactory()).thenReturn(fakeDataSourceItem)
//
//        // When we request live data
//        val liveData = appRepository.getLiveDataPagedList()
//
//        assertNotNull(liveData)
//    }
}