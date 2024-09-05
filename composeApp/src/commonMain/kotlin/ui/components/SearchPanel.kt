import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp


@Composable
fun SearchPanel(
    onSearchTextChange: (String) -> Unit,
    onSearchClear: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val text = rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
                onSearchTextChange.invoke(it)
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                Icon(Icons.Default.Clear,
                    contentDescription = "Clear text",
                    modifier = Modifier.clickable {
                        text.value = ""
                        keyboardController?.hide()
                        onSearchClear.invoke()
                    }
                )
            },
            placeholder = { Text("Search...") }
        )
    }
}