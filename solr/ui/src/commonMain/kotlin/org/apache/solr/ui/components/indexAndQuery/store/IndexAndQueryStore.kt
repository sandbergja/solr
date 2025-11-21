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

import com.arkivanov.mvikotlin.core.store.Store
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestHandler
import org.apache.solr.ui.components.indexAndQuery.data.RequestState
import org.apache.solr.ui.components.indexAndQuery.store.IndexAndQueryStore.Intent
import org.apache.solr.ui.components.indexAndQuery.store.IndexAndQueryStore.State

/**
 * The business logic and state of the Index and Query screen
 */
internal interface IndexAndQueryStore : Store<Intent, State, Nothing> {
    /**
     * Intent that represents actions the user wants to take on the IndexAndQuery screen
     */
    sealed interface Intent {

        /**
         * Intent for requesting the list of collections
         */
        data object FetchCollectionsData : Intent

        /**
         * Intent for requesting the list of request handlers for the selected collection
         */
        data object FetchRequestHandlersData : Intent
    }

    data class State(
        val selectedCollection: CollectionName?,
        val selectedRequestHandler: RequestHandler?,
        val collectionListState: RequestState<CollectionName> = RequestState.Loading<CollectionName>(),
    )
}
