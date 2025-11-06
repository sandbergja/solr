package org.apache.solr.ui.components.indexAndQuery.store

import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.apache.solr.ui.components.indexAndQuery.data.RequestState

class IndexAndQueryStoreProviderTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testProvide() = runTest {
        val expectedState = RequestState.Success(listOf("collection1"))
        val client = MockedIndexAndQueryStoreClient(onFetchCollections = { expectedState })

        val store = IndexAndQueryStoreProvider(
            storeFactory = DefaultStoreFactory(),
            client = client,
            mainContext = StandardTestDispatcher(testScheduler),
            ioContext = StandardTestDispatcher(testScheduler),
        ).provide()

        assertTrue(store.stateFlow.value.collectionListState is RequestState.Loading<*>)

        advanceUntilIdle()

        assertTrue(store.stateFlow.value.collectionListState is RequestState.Success)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `it can handle a FetchCollectionsData intent`() = runTest {
        val expectedState = RequestState.Success(listOf("collection1"))
        val client = MockedIndexAndQueryStoreClient(onFetchCollections = { expectedState })

        val store = IndexAndQueryStoreProvider(
            storeFactory = DefaultStoreFactory(),
            client = client,
            mainContext = StandardTestDispatcher(testScheduler),
            ioContext = StandardTestDispatcher(testScheduler),
        ).provide()

        // Send an intent
        store.accept(IndexAndQueryStore.Intent.FetchCollectionsData)
        assertTrue(store.stateFlow.value.collectionListState is RequestState.Loading<*>)

        advanceUntilIdle()

        assertTrue(store.stateFlow.value.collectionListState is RequestState.Success)
    }
}
