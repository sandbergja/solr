package org.apache.solr.ui.components.indexAndQuery.store

import com.arkivanov.mvikotlin.core.store.Store
import org.apache.solr.ui.components.environment.store.EnvironmentStore.Intent
import org.apache.solr.ui.components.environment.store.EnvironmentStore.State
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestHandler
import org.apache.solr.ui.components.indexAndQuery.data.RequestState

/**
 * The business logic and state of the Index and Query screen
 */
internal interface IndexAndQueryStore : Store<Intent, State, Nothing> {
    /**
     * Intent that represents actions the user wants to take on the IndexAndQuery screen
     */
    sealed interface Intent {

        /**
         * Intent for requesting the list of collections
         */
        data object FetchCollectionsData : Intent

        /**
         * Intent for requesting the list of request handlers for the selected collection
         */
        data object FetchRequestHandlersData : Intent
    }

    data class State(
        val selectedCollection: CollectionName?,
        val selectedRequestHandler: RequestHandler?,
        val collectionListState: RequestState<CollectionName> = RequestState.Loading<CollectionName>(),
    )
}
