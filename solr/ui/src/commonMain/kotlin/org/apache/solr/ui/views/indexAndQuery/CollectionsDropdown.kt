package org.apache.solr.ui.views.indexAndQuery

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.apache.solr.ui.components.indexAndQuery.data.CollectionName
import org.apache.solr.ui.components.indexAndQuery.data.RequestState
import org.apache.solr.ui.generated.resources.Res
import org.apache.solr.ui.generated.resources.label_collection
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionsDropdown(
    selectedCollection: CollectionName,
    selectCollection: (CollectionName) -> Unit,
    collectionData: RequestState<CollectionName>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it}
    ) {
        OutlinedTextField(
            value = selectedCollection,
            onValueChange = {},
            enabled = when(collectionData) {
                is RequestState.Success -> true
                else -> false
            },
            modifier = Modifier.testTag("collections_dropdown_textfield"),
            label = { Text(stringResource(Res.string.label_collection)) },
        )
        ExposedDropdownMenu(
            modifier = Modifier.testTag("configsets_exposed_dropdown_menu"),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {

                DropdownMenuItem(
                    text = { "Dogs" },
                    onClick = {},
                )
        }
    }

}
