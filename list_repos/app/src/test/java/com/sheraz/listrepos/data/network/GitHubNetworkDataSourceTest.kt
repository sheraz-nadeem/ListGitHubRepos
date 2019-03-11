package com.sheraz.listrepos.data.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import com.sheraz.listrepos.data.network.GitHubNetworkDataSource.Companion.ERROR_MESSAGE
import com.sheraz.listrepos.shared.LiveDataTestUtil
import com.sheraz.listrepos.shared.afQuickLookView
import com.sheraz.listrepos.shared.amiandoRepo
import com.sheraz.listrepos.utils.Logger
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.io.IOException

/**
 * Tests for [GitHubNetworkDataSource] that mocks the [GitHubApiService] class to run tests
 */
class GitHubNetworkDataSourceTest {

    // Executes tasks in the Architecture Components in the same thread
    // We don't want anything happening on background thread,
    // We want things to happen synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val repoList = listOf(amiandoRepo, afQuickLookView)

    private val logger: Logger = mock()
    private val service: GitHubApiService = mock()
    private val networkDataSource = GitHubNetworkDataSourceImpl(logger, service)


    @Test
    fun loadData_whenResultSuccessful() = runBlocking{

        // Given that service responds with success result
        val result = Response.success(repoList)
        whenever(service.getReposWithPageAsync(1, 2)).thenReturn(CompletableDeferred(result))

        // When load data happens
        networkDataSource.loadGitHubRepos(1, 2)

        // Then verify the followings
        val liveDataEvent: Result<List<GitHubRepoEntity>> = LiveDataTestUtil.getValue(networkDataSource.downloadedGitHubRepoList)
        assertEquals(Result.success(repoList), Result.success(liveDataEvent.getOrNull()))

    }

    @Test
    fun loadData_whenResultError() = runBlocking{

        // Given that service responds with error result
        val errorResponse: Response<List<GitHubRepoEntity>> = Response.error(400, ResponseBody.create(null, ERROR_MESSAGE))
        whenever(service.getReposWithPageAsync(1, 2)).thenReturn(CompletableDeferred(errorResponse))

        // When load data happens
        networkDataSource.loadGitHubRepos(1, 2)

        // Then verify the followings
        val expectedIOException = IOException(ERROR_MESSAGE)
        val liveDataEvent: Result<List<GitHubRepoEntity>> = LiveDataTestUtil.getValue(networkDataSource.downloadedGitHubRepoList)
        val actualException = liveDataEvent.exceptionOrNull()!!
        assertTrue(actualException is IOException)
        assertTrue(actualException.message.equals(expectedIOException.message))
    }

}