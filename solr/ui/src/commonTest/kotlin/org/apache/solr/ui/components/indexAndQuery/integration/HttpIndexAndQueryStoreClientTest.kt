package org.apache.solr.ui.components.indexAndQuery

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestState
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
        val response = HttpIndexAndQueryStoreClient(successfulCollectionListClient).fetchCollections()
        assertTrue(response is RequestState.Success)

        val iterator = response.iterator()
        assertEquals(iterator.next(), "collection1")
        assertEquals(iterator.next(), "collection2")
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun `test GIVEN unsuccessful fetch of collection list THEN it is a failure`() = runTest {
        val errorClient = testHttpClient(
            engine = MockEngine { request ->
                respondError(
                    status = HttpStatusCode.Unauthorized,
                    """<html>HTTP ERROR 401 Authentication failed, Response code: 401</html>""",
                )
            },
        )

        val response = HttpIndexAndQueryStoreClient(errorClient).fetchCollections()
        assertTrue(response is RequestState.Error<*>)
        assertFalse { response.iterator().hasNext() }
        assertEquals(response.exceptionIterator().next().message, "Received status code 401")
    }
}
