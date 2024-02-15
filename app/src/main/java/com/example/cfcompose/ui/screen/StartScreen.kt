package com.example.cfcompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cfcompose.R
import com.example.cfcompose.ui.theme.CFcomposeTheme

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    MainSection { onClick() }
}


@Composable
fun MainSection(
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
            ,
            onClick = onCardClick
        ) {

               Text(
                   text = stringResource(R.string.go),
                   fontSize = 40.sp,
                   textAlign = TextAlign.Center,
                   modifier = Modifier.fillMaxWidth()
               )

        }

    }


}


@Preview(showSystemUi = true)
@Composable
fun MainSectionPreview(){
    CFcomposeTheme {
        MainSection(modifier = Modifier , {})
    }
}







