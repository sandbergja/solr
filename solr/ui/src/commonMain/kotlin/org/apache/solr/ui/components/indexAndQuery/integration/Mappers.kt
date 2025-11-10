package org.apache.solr.ui.components.indexAndQuery.integration

import org.apache.solr.ui.components.indexAndQuery.IndexAndQueryComponent
import org.apache.solr.ui.components.indexAndQuery.store.IndexAndQueryStore

internal val indexAndQueryStateToModel: (IndexAndQueryStore.State) -> IndexAndQueryComponent.Model = {
    IndexAndQueryComponent.Model(
        selectedCollection = it.selectedCollection,
        selectedRequestHandler = it.selectedRequestHandler,
        collectionsRequest = it.collectionListState
    )
}
