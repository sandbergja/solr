package org.apache.solr.ui.components.indexAndQuery

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.apache.solr.ui.components.indexAndQuery.integration.HttpIndexAndQueryStoreClient
import org.apache.solr.ui.testHttpClient

class HttpIndexAndQueryStoreClientTest {
    @Test
    fun `test GIVEN successful fetch of collection list THEN it is a success`() = runTest {
        val successfulCollectionListClient = testHttpClient(
            engine = MockEngine { request ->
                respond(
                    content = """{"responseHeader":{"status":0,"QTime":1},"collections":["collection1","collection2"]}""",
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json"),
                )
            },
        )
        assertTrue(HttpIndexAndQueryStoreClient(successfulCollectionListClient).fetchCollections().isSuccess)
    }
}
