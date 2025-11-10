package org.apache.solr.ui.components.indexAndQuery

import kotlinx.coroutines.flow.StateFlow
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestHandler
import org.apache.solr.ui.components.indexAndQuery.data.RequestState

/* This component interface represents the Index and Query section of the UI.
 */
interface IndexAndQueryComponent {
    val model: StateFlow<Model>

    /* The state of the Index and Query screen */
    data class Model(
        val selectedCollection: CollectionName?,
        val selectedRequestHandler: RequestHandler?,
        val collectionsRequest: RequestState<CollectionName>
    )
}
