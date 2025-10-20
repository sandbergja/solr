package org.apache.solr.ui.components.indexAndQuery.data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlinx.io.IOException

class RequestStateTest {
    @Test
    fun emptyCanIterateButHasNoElementsTest() {
        val iterator = Empty<CollectionName>().iterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun emptyCanIterateExceptionsButHasNoElementsTest() {
        val iterator = Empty<CollectionName>().exceptionIterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun loadingCanIterateButHasNoElementsTest() {
        val iterator = Loading<CollectionName>().iterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun loadingCanIterateExceptionsButHasNoElementsTest() {
        val iterator = Loading<CollectionName>().exceptionIterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun errorCanIterateButHasNoElementsTest() {
        val exception = IOException("I am an exception")
        val iterator = Error<CollectionName>(exception).iterator()
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun errorCanIterateExceptionsButHasNoElementsTest() {
        val exception = IOException("I am an exception")
        val iterator = Error<CollectionName>(exception).exceptionIterator()
        assertEquals(iterator.next().message, "I am an exception")
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun successCanIterateTest() {
        val list = listOf("catalog-production1", "catalog-production2")
        val iterator = Success(list).iterator()
        assertEquals(iterator.next(), "catalog-production1")
        assertEquals(iterator.next(), "catalog-production2")
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun successCanIterateExceptionsButHasNoElementsTest() {
        val iterator = Success(listOf("catalog-production1")).exceptionIterator()
        assertFalse { iterator.hasNext() }
    }
}
