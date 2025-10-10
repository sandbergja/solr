package org.apache.solr.ui.components.indexAndQuery.data
import kotlinx.serialization.Serializable

typealias CollectionName = String

@Serializable
data class ListCollections(
    val collections: List<CollectionName> = emptyList(),
)
