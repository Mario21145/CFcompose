package com.example.cfcompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cfcompose.R
import com.example.cfcompose.ui.screen.commonElements.ButtonSection
import com.example.cfcompose.ui.screen.commonElements.LiveCfSection
import com.example.cfcompose.ui.theme.CFcomposeTheme


@Composable
fun SexScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

//    LiveCfSection()
    InsertSexSection(
        modifier = Modifier,
    )
    ButtonSection(modifier, onClick)

}


@Composable
fun InsertSexSection(
    modifier: Modifier = Modifier,
) {

    val isSelected = remember { false }

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

                RadioButton(
                    selected = isSelected,
                    onClick = { /*TODO*/ }
                )


                Text(
                    text = "men",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

            }

            Spacer(
                modifier = modifier
                    .padding(10.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = isSelected,
                    onClick = { /*TODO*/ }
                )

                Text(
                    text = "women",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,

                    )

            }




    }
}

@Preview(showSystemUi = true)
@Composable
fun SexScreenPreview() {
    CFcomposeTheme {
        SexScreen(Modifier, {})
    }
}



