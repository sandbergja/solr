package org.apache.solr.ui.components.indexAndQuery.store

import com.arkivanov.mvikotlin.core.store.Store
import org.apache.solr.ui.components.environment.store.EnvironmentStore.Intent
import org.apache.solr.ui.components.environment.store.EnvironmentStore.State
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestHandler

internal interface IndexAndQueryStore : Store<Intent, State, Nothing> {
    /**
     * Intent that represents actions the user wants to take on the IndexAndQuery screen
     */
    sealed interface Intent {

        /**
         * Intent for requesting system data.
         */
        data object FetchCollectionsData : Intent
    }

    data class State(
        val selectedCollection: CollectionName,
        val selectedRequestHandler: RequestHandler,
    )
}
