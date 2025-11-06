package org.apache.solr.ui.views.indexAndQuery

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestState

@OptIn(ExperimentalTestApi::class)
class CollectionsDropdownTest {
    @Test
    fun `when data has not loaded yet, it is disabled`() = runComposeUiTest {
        setContent {
            CollectionsDropdown(selectedCollection = "my-collection", selectCollection = {}, collectionData = RequestState.Loading<CollectionName>())
        }
        onNodeWithTag("collections_dropdown_textfield").assertIsNotEnabled()
    }
}
