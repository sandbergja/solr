package org.apache.solr.ui.components.indexAndQuery.store

import org.apache.solr.ui.components.indexAndQuery.data.ListCollections

class MockedIndexAndQueryStoreClient(
    private val onFetchCollections: () -> Result<ListCollections>
): IndexAndQueryStoreProvider.Client {
    override suspend fun fetchCollections(): Result<ListCollections> = onFetchCollections()
}
