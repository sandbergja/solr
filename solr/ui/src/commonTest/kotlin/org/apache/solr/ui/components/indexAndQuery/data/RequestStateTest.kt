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
package org.apache.solr.ui.components.indexAndQuery.data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlinx.io.IOException

class RequestStateTest {
    @Test
    fun emptyCanIterateButHasNoElementsTest() {
        val iterator = RequestState.Empty<CollectionName>().iterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun emptyCanIterateExceptionsButHasNoElementsTest() {
        val iterator = RequestState.Empty<CollectionName>().exceptionIterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun loadingCanIterateButHasNoElementsTest() {
        val iterator = RequestState.Loading<CollectionName>().iterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun loadingCanIterateExceptionsButHasNoElementsTest() {
        val iterator = RequestState.Loading<CollectionName>().exceptionIterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun errorCanIterateButHasNoElementsTest() {
        val exception = IOException("I am an exception")
        val iterator = RequestState.Error<CollectionName>(exception).iterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun errorCanIterateExceptionsButHasNoElementsTest() {
        val exception = IOException("I am an exception")
        val iterator = RequestState.Error<CollectionName>(exception).exceptionIterator()
        assertEquals(iterator.next().message, "I am an exception")
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun successCanIterateTest() {
        val list = listOf("catalog-production1", "catalog-production2")
        val iterator = RequestState.Success(list).iterator()
        assertEquals(iterator.next(), "catalog-production1")
        assertEquals(iterator.next(), "catalog-production2")
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun successCanIterateExceptionsButHasNoElementsTest() {
        val iterator = RequestState.Success(listOf("catalog-production1")).exceptionIterator()
        assertFalse { iterator.hasNext() }
    }
}
