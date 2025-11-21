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
import org.apache.solr.ui.components.indexAndQuery.data.RequestState
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
        assertEquals("collection1", iterator.next())
        assertEquals("collection2", iterator.next())
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
        assertEquals("Received status code 401", response.exceptionIterator().next().message)
    }
}
