package org.apache.solr.ui.components.indexAndQuery.data

sealed class RequestState<out T>
class Empty<T> :
    RequestState<Nothing>(),
    Iterable<T> {
    override fun iterator(): Iterator<T> = emptyList<T>().iterator()
    fun exceptionIterator(): Iterator<Throwable> = emptyList<Throwable>().iterator()
}

class Loading<T> :
    RequestState<Nothing>(),
    Iterable<T> {
    override fun iterator(): Iterator<T> = emptyList<T>().iterator()
    fun exceptionIterator(): Iterator<Throwable> = emptyList<Throwable>().iterator()
}

data class Error<T>(val exception: Throwable) :
    RequestState<Nothing>(),
    Iterable<T> {
    override fun iterator(): Iterator<T> = emptyList<T>().iterator()
    fun exceptionIterator(): Iterator<Throwable> = listOf(exception).iterator()
}

data class Success<out T>(val results: List<T>) :
    RequestState<T>(),
    Iterable<T> {
    override fun iterator(): Iterator<T> = results.iterator()
    fun exceptionIterator(): Iterator<Throwable> = emptyList<Throwable>().iterator()
}
