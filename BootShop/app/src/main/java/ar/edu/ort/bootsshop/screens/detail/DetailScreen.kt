package ar.edu.ort.bootsshop.screens.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.bootsshop.R
import ar.edu.ort.bootsshop.data.Boot
import ar.edu.ort.bootsshop.data.BootListItems
import ar.edu.ort.bootsshop.sizes
import ar.edu.ort.bootsshop.ui.components.BuyButton
import ar.edu.ort.bootsshop.ui.theme.Brown40
import ar.edu.ort.bootsshop.ui.theme.Pink80

@Composable
fun DetailScreen(navController: NavHostController, bootItem: Boot){

    val buttonColor = if( !isSystemInDarkTheme() )  Brown40 else Pink80

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // top row
        Row(
            modifier = Modifier.fillMaxWidth().weight(0.5F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(32.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.detail_text_field_title_size),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    DropdownMenu()
                    Text(
                        text = stringResource(R.string.detail_text_field_title_count),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    NumericField("1")

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth().height(70.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                border = BorderStroke(
                    width = 1.dp,
                    color = buttonColor
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = buttonColor
                ),
                onClick = { navController.popBackStack() },
            ) {
                Text(stringResource(id = R.string.button_back))
            }
            Spacer(modifier = Modifier.size(70.dp))
            BuyButton()
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(sizes[0]) }

    Box(
        modifier = Modifier.fillMaxWidth().padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText.toString(),
                label = { Text(stringResource(R.string.detail_text_field_label)) },
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                sizes.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.toString()) },
                        onClick = {
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NumericField(text: String) {
    var stock by remember { mutableStateOf(TextFieldValue(text)) }
    Box(
        modifier = Modifier.fillMaxWidth().padding(32.dp)
    ) {
        OutlinedTextField(
            label = { Text(stringResource(R.string.detail_text_field_label)) },
            value = stock,
            onValueChange = {
                if (it.text.all(Char::isDigit)) {
                    stock = it
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()

    DetailScreen(navController, BootListItems.get(0))
}