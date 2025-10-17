package org.apache.solr.ui.components.indexAndQuery.store

import org.apache.solr.ui.components.indexAndQuery.data.ListCollections

internal class IndexAndQueryStoreProvider {
    interface Client {
        suspend fun fetchCollections(): Result<ListCollections>
    }
}
