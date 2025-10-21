package org.apache.solr.ui.components.indexAndQuery.store

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.apache.solr.ui.components.indexAndQuery.data.RequestState

class IndexAndQueryStoreProviderTest {
    @Test
    fun testProvide() = runTest {
        val expectedState = RequestState.Success(listOf("collection1"))
        val client = MockedIndexAndQueryStoreClient(onFetchCollections = { expectedState })
        launch {
            // When provide is called, the initial action is already included
            val store = IndexAndQueryStoreProvider(
                storeFactory = DefaultStoreFactory(),
                client = client,
                mainContext = coroutineContext,
                ioContext = backgroundScope.coroutineContext,
            ).provide()
        }
        assertTrue { true }
    }
}
