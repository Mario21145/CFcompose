package com.example.cfcompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cfcompose.R
import com.example.cfcompose.data.Data
import com.example.cfcompose.ui.screen.commonElements.ButtonSection
import com.example.cfcompose.ui.screen.commonElements.LiveCfSection
import com.example.cfcompose.ui.theme.CFcomposeTheme


@Composable
fun CityScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDropDownClicked: (String) -> Unit,
    CF: String,
    enabled: Boolean,
) {

    LiveCfSection(modifier, CF)
    InsertCitySection(
        modifier = Modifier,
        onDropDownClicked = onDropDownClicked
    )
    ButtonSection(modifier, onClick , enabled)

}


@Composable
fun InsertCitySection(
    modifier: Modifier = Modifier,
    onDropDownClicked: (String) -> Unit,
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedCity = remember { mutableStateOf(Data.cities.first()) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.placeholderCity),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    expanded.value = !expanded.value
                }
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown Arrow",
                tint = Color.Black,
                modifier = Modifier.clickable {
                    expanded.value = !expanded.value
                }
            )

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                Data.cities.forEach { entry ->

                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            selectedCity.value = entry
                            onDropDownClicked(selectedCity.value)
                            expanded.value = false
                        },
                        text = {
                            Text(
                                text = (entry),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.Start)
                            )
                        }
                    )
                }

            }
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = selectedCity.value,
        )

    }
}

@Preview(showSystemUi = true)
@Composable
fun CityScreenPreview() {
    CFcomposeTheme {
        CityScreen(Modifier, {}, {}, "xxx" , true)
    }
}



