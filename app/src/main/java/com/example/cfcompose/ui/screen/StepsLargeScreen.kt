package com.example.cfcompose.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cfcompose.R
import com.example.cfcompose.data.CfUiState
import com.example.cfcompose.model.StepModel
import com.example.cfcompose.ui.theme.CFcomposeTheme

@Composable
fun StepsScreen(
    uiState: CfUiState,
) {

    val listScreens = listOf(
        StepModel(R.string.surname, uiState.stateStepSurname),
        StepModel(R.string.name, uiState.stateStepName),
        StepModel(R.string.date, uiState.stateStepDate),
        StepModel(R.string.sex, uiState.stateStepSex),
        StepModel(R.string.city, uiState.stateStepCity),
        StepModel(R.string.recap, uiState.stateStepRecap),
    )



    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxHeight()
            ,
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier,
            ) {
                itemsIndexed(listScreens) { index, screen ->
                    CardStepScreen(
                        (index + 1).toString(),
                        stringResource(id = screen.title),
                        screen.completed
                    )
                }


            }
        }
    }


}


@Composable
fun CardStepScreen(text: String, counter: String, isCompleted: Boolean) {

    var color = Color.Gray
    var icon = Icons.Filled.Clear


    if (isCompleted) {
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
        border = BorderStroke(2.dp, color),

        ) {

        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Text(
                text = "$text.",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
            )

            Spacer(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
            )

            Text(
                text = counter,
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
        StepsScreen(uiState = CfUiState())
    }
}



