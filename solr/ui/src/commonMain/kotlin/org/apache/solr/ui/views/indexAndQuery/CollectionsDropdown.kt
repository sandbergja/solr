package org.apache.solr.ui.views.indexAndQuery

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.apache.solr.ui.generated.resources.Res
import org.apache.solr.ui.generated.resources.cd_clear_field
import org.apache.solr.ui.generated.resources.close
import org.apache.solr.ui.generated.resources.nav_configsets
import org.apache.solr.ui.generated.resources.no_configsets
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionsDropdown(
    selectCollection: (String) -> Unit,
    collections: List<String>,
    selectedCollection: String?,
    modifier: Modifier = Modifier,
    enableReset: Boolean = false,
) {
    var expanded by remember { mutableStateOf(false) }
    val enabled = collections.isNotEmpty()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = selectedCollection ?: "",
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            singleLine = true,
            label = { Text(stringResource(Res.string.nav_configsets)) },
            placeholder = {
                if (collections.isEmpty()) {
                    Text(
                        modifier = Modifier.testTag("no_collections_placeholder"),
                        text = stringResource(Res.string.no_configsets),
                    )
                }
            },
            trailingIcon = {
                if (enableReset && !selectedCollection.isNullOrEmpty()) {
                    IconButton(onClick = { selectCollection("") }) {
                        Icon(
                            painter = painterResource(Res.drawable.close),
                            contentDescription = stringResource(Res.string.cd_clear_field),
                        )
                    }
                } else {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                }
            },
            modifier = Modifier
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    enabled = enabled,
                )
                .testTag("collections_dropdown"),
        )
    }
}
