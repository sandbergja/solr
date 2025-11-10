package org.apache.solr.ui.views.indexAndQuery

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.apache.solr.ui.components.indexAndQuery.IndexAndQueryComponent
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestState

@Composable
fun IndexAndQueryContent(component: IndexAndQueryComponent,
                           modifier: Modifier = Modifier) {
    val model by component.model.collectAsState()
    Row {
        CollectionsDropdown(selectedCollection = "dog", selectCollection = {}, collectionData = model.collectionsRequest)
    }
}
