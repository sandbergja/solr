package org.apache.solr.ui.views.indexAndQuery

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.collections.emptyList
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class CollectionsDropdownTest {
    @Test
    fun `GIVEN empty availableConfigsets WHEN dropdown clicked THEN not expanded`() = runComposeUiTest {
        setContent {
            CollectionsDropdown(
                selectCollection = {},
                collections = emptyList(),
                selectedCollection = "",
            )
        }

        onNodeWithTag(testTag = "collections_dropdown").performClick()
        onNodeWithTag(testTag = "collections_exposed_dropdown_menu").assertDoesNotExist()
    }
}
