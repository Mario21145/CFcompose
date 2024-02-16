package com.example.cfcompose.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cfcompose.R
import com.example.cfcompose.ui.screen.commonElements.ButtonSection
import com.example.cfcompose.ui.screen.commonElements.LiveCfSection
import com.example.cfcompose.ui.theme.CFcomposeTheme
import com.example.cfcompose.ui.utils.CfScreen
import com.example.cfcompose.ui.viewmodel.CfViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun NameScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onValueChanged: (String) -> Unit,
    CF: String,
) {

    LiveCfSection(modifier, CF)

    InsertNameSection(
        modifier = Modifier,
        onValueChanged = onValueChanged
    )

    ButtonSection(modifier, onClick)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertNameSection(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    viewModel : CfViewModel = viewModel()
) {

    val inputvalue = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TextField(
            value = inputvalue.value,
            onValueChange = {
                inputvalue.value = it
                onValueChanged(it.text)
            },
            placeholder = { Text(text = stringResource(id = R.string.placeholderName)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Account",
                    tint = Color.Black,
                    modifier = Modifier
                )
            },
            shape = RoundedCornerShape(8.dp, 8.dp)

        )

    }
}




@Preview(showSystemUi = true)
@Composable
fun NameScreenPreview() {
    CFcomposeTheme {
        NameScreen(Modifier, {}, {}, "xxx")
    }
}



