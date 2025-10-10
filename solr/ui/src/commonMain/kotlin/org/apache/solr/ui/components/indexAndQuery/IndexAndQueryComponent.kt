package org.apache.solr.ui.components.indexAndQuery

import kotlinx.coroutines.flow.StateFlow

/* This component interface represents the Index and Query section of the UI.
 */
interface IndexAndQueryComponent {
    val model: StateFlow<Model>

    /* The state of the Index and Query screen */
    data class Model(
        val selectedCollection: String,
        val selectedEndpoint: String,
    )
}
