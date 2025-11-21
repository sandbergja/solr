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
package org.apache.solr.ui.components.indexAndQuery.integration

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.ListCollections
import org.apache.solr.ui.components.indexAndQuery.data.RequestState
import org.apache.solr.ui.components.indexAndQuery.store.IndexAndQueryStoreProvider

class HttpIndexAndQueryStoreClient(private val httpClient: HttpClient) : IndexAndQueryStoreProvider.Client {
    override suspend fun fetchCollections(): RequestState<CollectionName> {
        val response = httpClient.get("api/collections")
        return when {
            response.status.isSuccess() -> {
                val list: ListCollections = response.body()
                if (list.collections.isEmpty()) RequestState.Empty() else RequestState.Success(list.collections)
            }
            else -> RequestState.Error<CollectionName>(Exception("Received status code ${response.status.value}"))
        }
    }
}
