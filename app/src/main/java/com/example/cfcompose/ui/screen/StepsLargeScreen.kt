package com.example.cfcompose.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cfcompose.data.Data
import com.example.cfcompose.ui.theme.CFcomposeTheme
import com.example.cfcompose.ui.utils.CfScreen
import com.example.cfcompose.ui.viewmodel.CfViewModel

@Composable
fun StepsScreen(currentScreen: CfScreen) {

    val listScreens = Data.getScreensData()

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier,
        ) {
            items(listScreens) { screen ->
                CardStepScreen(stringResource(id = screen.title) , currentScreen)
            }
        }
    }



}


@Composable
fun CardStepScreen(text: String, currentScreen: CfScreen , viewModel: CfViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    var color = Color.Gray
    var icon = Icons.Filled.Clear

    if(uiState.stateStep){
        color = Color.Green
        icon = Icons.Filled.Check
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp),
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(1.dp, color),

        ) {

        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )

            Spacer(
                modifier = Modifier
                    .weight(2f),
            )


            Icon(
                imageVector = icon,
                contentDescription = "Account",
                tint = Color.Black,
                modifier = Modifier
            )

        }

    }


}


@Preview(showSystemUi = true)
@Composable
fun StepsScreenPreview() {
    CFcomposeTheme {
//        StepsScreen()
    }
}



