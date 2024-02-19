package com.example.cfcompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cfcompose.R
import com.example.cfcompose.ui.screen.commonElements.ButtonSection
import com.example.cfcompose.ui.theme.CFcomposeTheme


@Composable
fun RecapScreen(
    modifier: Modifier = Modifier,
    name: String,
    surname: String,
    sex: String,
    year: String,
    day: String,
    city: String,
    month: String,
    liveCf: String,
    onClick: () -> Unit,
) {

    Spacer(
        modifier = modifier
            .height(50.dp)
    )
    ShowDataSection(
        modifier = modifier,
        name,
        surname,
        sex,
        year,
        day,
        city,
        month,
        liveCf,
    )
    ButtonSection(modifier, onClick)

}


@Composable
fun ShowDataSection(
    modifier: Modifier = Modifier,
    name: String,
    surname: String,
    sex: String,
    year: String,
    day: String,
    city: String,
    month: String,
    liveCf: String,
) {

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = modifier
                    .padding(20.dp)
            ) {

                Column(
                    modifier = modifier
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Account",
                        tint = Color.Black,
                    )

                    Text(
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(5.dp)
                    )

                    Text(
                        text = surname,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(5.dp)
                    )

                }


            }

            Card(
                modifier = modifier
                    .padding(20.dp)
            ) {

                Row(
                    modifier = modifier
                        .padding(20.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sex_icon),
                        contentDescription = "Account",
                        tint = Color.Black,
                        modifier = modifier
                            .padding(end = 20.dp, start = 10.dp, top = 10.dp, bottom = 10.dp)
                    )

                    Text(
                        text = sex,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(10.dp)
                    )

                }


            }


        }


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = modifier
                    .padding(10.dp)
            ) {

                Column(
                    modifier = modifier
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.year_icon),
                        contentDescription = "Account",
                        tint = Color.Black,
                    )

                    Text(
                        text = year,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(10.dp)
                    )

                    Text(
                    text = "$day $month",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(10.dp)
                )

                }


            }
        }

        Card(
            modifier = modifier
                .padding(20.dp)
        ) {

            Row(
                modifier = modifier
                    .padding(20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.place_icon),
                    contentDescription = "Account",
                    tint = Color.Black,
                    modifier = modifier
                        .padding(end = 20.dp, start = 10.dp, top = 10.dp, bottom = 10.dp)
                )

                Text(
                    text = city,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(10.dp)
                )

            }


        }

        Card(
            modifier = modifier
                .padding(20.dp)
        ) {

                Text(
                    text = liveCf,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(20.dp)
                )

            }


        }


    }


@Preview(showSystemUi = true)
@Composable
fun RecapScreenPreview() {
    CFcomposeTheme {
        ShowDataSection(
            modifier = Modifier,
            "mario",
            "mazziotti",
            "uomo",
            "2005",
            "30",
            "napoli",
            "gennaio",
            "xxxxxxxx"
        )
    }
}



