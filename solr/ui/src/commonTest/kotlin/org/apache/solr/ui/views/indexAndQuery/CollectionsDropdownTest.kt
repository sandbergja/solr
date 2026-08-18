package org.apache.solr.ui.views.indexAndQuery

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.collections.emptyList
import kotlin.test.Test
import org.apache.solr.ui.generated.resources.Res
import org.apache.solr.ui.generated.resources.error_no_collections_available
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalTestApi::class)
class CollectionsDropdownTest {
    @Test
    fun `GIVEN empty available collections WHEN dropdown clicked THEN not expanded`() = runComposeUiTest {
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

    @Test
    fun `GIVEN several available collections WHEN dropdown clicked THEN expanded`() = runComposeUiTest {
        setContent {
            CollectionsDropdown(
                selectCollection = {},
                collections = listOf("collection1", "collection2"),
                selectedCollection = "",
            )
        }
        onNodeWithTag(testTag = "collections_dropdown").performClick()
        onNodeWithTag(testTag = "collections_exposed_dropdown_menu").assertIsDisplayed()
        onNodeWithText("collection1").assertIsDisplayed()
        onNodeWithText("collection2").assertIsDisplayed()
    }
}
