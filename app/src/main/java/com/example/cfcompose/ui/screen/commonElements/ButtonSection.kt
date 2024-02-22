package com.example.cfcompose.ui.screen.commonElements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cfcompose.R

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled : Boolean
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
        ,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = modifier
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = modifier
                    .weight(1f)
                    .padding(10.dp)
                ,
                onClick = onClick,
                enabled = enabled,
            ) {
                Text(
                    text = stringResource(R.string.go),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }


    }

}


@Preview(showSystemUi = true)
@Composable
fun ButtonSectionPreview() {
    ButtonSection(Modifier, {} , true)
}