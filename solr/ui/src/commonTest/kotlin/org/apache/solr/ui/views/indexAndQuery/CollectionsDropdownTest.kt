/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.solr.ui.views.indexAndQuery

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertValueEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.text.AnnotatedString
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

    @Test
    fun `when data has loaded, it is enabled`() = runComposeUiTest {
        setContent {
            CollectionsDropdown(selectedCollection = "my-collection", selectCollection = {}, collectionData = RequestState.Success<CollectionName>(listOf("my-collection", "my-other-collection")))
        }
        onNodeWithTag("collections_dropdown_textfield").assertIsEnabled()
    }

    @Test
    fun `it displays the name of the selectedCollection as its value`() = runComposeUiTest {
        setContent {
            CollectionsDropdown(selectedCollection = "my-collection", selectCollection = {}, collectionData = RequestState.Loading<CollectionName>())
        }
        onNodeWithTag("collections_dropdown_textfield").assert(SemanticsMatcher.expectValue(SemanticsProperties.EditableText,
            AnnotatedString("my-collection")))
    }
}
