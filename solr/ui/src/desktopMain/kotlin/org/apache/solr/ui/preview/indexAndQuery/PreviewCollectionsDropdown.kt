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
        CollectionsDropdown(selectedCollection = "my-collection", selectCollection = {}, collectionData = RequestState.Loading<CollectionName>())
    }

    @Preview
    @Composable
    private fun PreviewSuccessfulCollectionsDropdown() = PreviewContainer {
        CollectionsDropdown(selectedCollection = "my-collection", selectCollection = {}, collectionData = RequestState.Success<CollectionName>(listOf("my-collection", "my-other-collection")))
    }
}
