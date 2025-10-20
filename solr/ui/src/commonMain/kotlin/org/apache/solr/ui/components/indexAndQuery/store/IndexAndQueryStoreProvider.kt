package org.apache.solr.ui.components.indexAndQuery.store

import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestState

internal class IndexAndQueryStoreProvider {
    interface Client {
        suspend fun fetchCollections(): RequestState<CollectionName>
    }
}
