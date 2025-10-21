package org.apache.solr.ui.components.indexAndQuery.store

import com.arkivanov.mvikotlin.core.rx.Disposable
import com.arkivanov.mvikotlin.core.rx.Observer
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.launch
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestState
import org.apache.solr.ui.components.indexAndQuery.store.IndexAndQueryStore.State

internal class IndexAndQueryStoreProvider(
    private val storeFactory: StoreFactory,
    private val client: Client,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {
    fun provide(): IndexAndQueryStore = object :
        IndexAndQueryStore,
        Store<Intent, State, Nothing> by storeFactory.create(
            name = "IndexAndQueryStore",
            initialState = State(null, null),
            bootstrapper = SimpleBootstrapper(Action.FetchCollections),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {
        override fun states(observer: Observer<IndexAndQueryStore.State>): Disposable {
            TODO("Not yet implemented")
        }

        override fun accept(intent: IndexAndQueryStore.Intent) {
            TODO("Not yet implemented")
        }
    }

    private sealed interface Action {

        /**
         * Action used for initiating the initial fetch of collections.
         */
        data object FetchCollections : Action
    }

    private sealed interface Intent {
        /**
         * Fetch data about collections
         */
        data object FetchCollections : Intent
    }

    private sealed interface Message {
        data class CollectionsUpdated(val data: RequestState<CollectionName>) : Message
    }

    interface Client {
        suspend fun fetchCollections(): RequestState<CollectionName>
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Nothing>(mainContext) {

        override fun executeAction(action: Action) = when (action) {
            Action.FetchCollections -> {
                scope.launch { client.fetchCollections() }
            }
        }

        override fun executeIntent(intent: Intent) {
            when (intent) {
                Intent.FetchCollections -> {
                    scope.launch { client.fetchCollections() }
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State = when (msg) {
            is Message.CollectionsUpdated -> copy(
                selectedCollection = null,
                selectedRequestHandler = null,
                collectionListState = msg.data,
            )
        }
    }
}
