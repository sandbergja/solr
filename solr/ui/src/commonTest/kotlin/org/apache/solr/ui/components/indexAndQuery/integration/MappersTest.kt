package org.apache.solr.ui.components.indexAndQuery.integration

import kotlin.test.Test
import kotlin.test.assertEquals
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestState
import org.apache.solr.ui.components.indexAndQuery.store.IndexAndQueryStore

class MappersTest {
    @Test
    fun `it can map a state into a model`() {
        val state = IndexAndQueryStore.State(selectedCollection = "my-collection", collectionListState = RequestState.Loading<CollectionName>(), selectedRequestHandler = null)
        val model = indexAndQueryStateToModel(state)

        assertEquals("my-collection", model.selectedCollection)
        assertEquals(null, model.selectedRequestHandler)
    }
}
