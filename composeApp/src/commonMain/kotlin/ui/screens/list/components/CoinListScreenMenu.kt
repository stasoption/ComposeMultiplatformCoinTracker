package ui.screens.list.components

import Platform
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import cointracker.composeapp.generated.resources.Res
import cointracker.composeapp.generated.resources.about
import cointracker.composeapp.generated.resources.help_center
import cointracker.composeapp.generated.resources.settings
import MediumIcon
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CoinListScreenMenu(
    menuExpanded: Boolean,
    onDismiss: () -> Unit
) {
    Box {
        DropdownMenu(
            expanded = menuExpanded,
            offset = DpOffset(Platform().screenWidth.dp, 0.dp),
            onDismissRequest = { onDismiss.invoke() }
        ) {
            DropdownMenuItem(
                onClick = { onDismiss.invoke() /* TODO Settings */ }
            ) {
                Row {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = stringResource(Res.string.settings),
                        modifier = Modifier.size(MediumIcon)
                    )
                    Text(
                        text = stringResource(Res.string.settings),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            DropdownMenuItem(onClick = { onDismiss.invoke() /* TODO About */ }) {
                Row {
                    Icon(
                        modifier = Modifier.size(MediumIcon),
                        painter = painterResource(Res.drawable.help_center),
                        contentDescription = stringResource(Res.string.settings)
                    )
                    Text(
                        text = stringResource(Res.string.about),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}