package org.apache.solr.ui.preview.indexAndQuery

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestState
import org.apache.solr.ui.preview.PreviewContainer
import org.apache.solr.ui.views.indexAndQuery.CollectionsDropdown

class PreviewCollectionsDropdown {
    @Preview
    @Composable
    private fun PreviewLoadingCollectionsDropdown() = PreviewContainer {
        CollectionsDropdown(selectedCollection = "my-collection", selectCollection = {}, collectionData = RequestState.Loading<CollectionName>(), )
    }

    @Preview
    @Composable
    private fun PreviewSuccessfulCollectionsDropdown() = PreviewContainer {
        CollectionsDropdown(selectedCollection = "my-collection", selectCollection = {}, collectionData = RequestState.Success<CollectionName>(listOf("my-collection", "my-other-collection")), )
    }

}
