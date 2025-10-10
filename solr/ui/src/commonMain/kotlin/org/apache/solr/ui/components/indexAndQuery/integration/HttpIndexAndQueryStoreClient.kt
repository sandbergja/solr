package org.apache.solr.ui.components.indexAndQuery.integration

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import org.apache.solr.ui.components.indexAndQuery.data.ListCollections

class HttpIndexAndQueryStoreClient(private val httpClient: HttpClient) {
    suspend fun fetchCollections(): Result<ListCollections> {
        val response = httpClient.get("api/collections")
        return when {
            response.status.isSuccess() -> Result.success(response.body())
            else -> Result.failure(Exception("Unknown Error"))
        }
    }
}
