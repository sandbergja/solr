package org.apache.solr.ui.components.indexAndQuery.integration

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.Empty
import org.apache.solr.ui.components.indexAndQuery.data.Error
import org.apache.solr.ui.components.indexAndQuery.data.ListCollections
import org.apache.solr.ui.components.indexAndQuery.data.RequestState
import org.apache.solr.ui.components.indexAndQuery.data.Success
import org.apache.solr.ui.components.indexAndQuery.store.IndexAndQueryStoreProvider

class HttpIndexAndQueryStoreClient(private val httpClient: HttpClient) : IndexAndQueryStoreProvider.Client {
    override suspend fun fetchCollections(): RequestState<CollectionName> {
        val response = httpClient.get("api/collections")
        return when {
            response.status.isSuccess() -> {
                val list: ListCollections = response.body()
                if (list.collections.isEmpty()) Empty() else Success(list.collections)
            }
            else -> Error<CollectionName>(Exception("Unknown Error"))
        }
    }
}
