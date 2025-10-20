package org.apache.solr.ui.components.indexAndQuery.store

import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestState

class MockedIndexAndQueryStoreClient(
    private val onFetchCollections: () -> RequestState<CollectionName>,
) : IndexAndQueryStoreProvider.Client {
    override suspend fun fetchCollections(): RequestState<CollectionName> = onFetchCollections()
}
