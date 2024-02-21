package com.example.cfcompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cfcompose.R
import com.example.cfcompose.ui.screen.commonElements.ButtonSection
import com.example.cfcompose.ui.theme.CFcomposeTheme

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    MainSection()
    ButtonSection(onClick = onClick, enabled = true)
}


@Composable
fun MainSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .width(500.dp)
                .height(570.dp)
                .padding(40.dp),
            colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.what_u_will_need),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier
                        .padding(10.dp)
                ) {
                    Text(text = stringResource(R.string.nameHome), fontSize = 25.sp)
                    Text(text = stringResource(R.string.surnameHome), fontSize = 25.sp)
                    Text(text = stringResource(R.string.your_birthday), fontSize = 25.sp)
                    Text(text = stringResource(R.string.your_sex), fontSize = 25.sp)
                    Text(text = stringResource(R.string.place_where_u_born), fontSize = 25.sp)
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun MainSectionPreview() {
    CFcomposeTheme {
        MainSection(modifier = Modifier)
    }
}







