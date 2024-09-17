import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ui.theme.ColorAccent
import ui.theme.DarkGray
import ui.theme.MediumGray
import ui.theme.TextPrimary


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
            .background(DarkGray)
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
                onSearchTextChange.invoke(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(0.5.dp, ColorAccent, RoundedCornerShape(8.dp)),
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
            placeholder = { Text("Search...") },
            colors = textFieldColors(
                textColor = TextPrimary,
                backgroundColor = MediumGray,
                cursorColor = TextPrimary,
                placeholderColor = TextPrimary.copy(alpha = 0.5F),
                leadingIconColor = TextPrimary,
                trailingIconColor = TextPrimary
            ),
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                autoCorrect = false,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    onSearchTextChange.invoke(text.value)
                }
            )
        )
    }
}