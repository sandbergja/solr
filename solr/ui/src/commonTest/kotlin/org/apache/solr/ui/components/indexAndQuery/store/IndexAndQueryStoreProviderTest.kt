/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
