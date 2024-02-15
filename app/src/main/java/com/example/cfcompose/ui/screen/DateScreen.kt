package com.example.cfcompose.ui.screen

import android.util.Log
import android.widget.CalendarView
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
import androidx.compose.ui.viewinterop.AndroidView
import com.example.cfcompose.R
import com.example.cfcompose.ui.screen.commonElements.ButtonSection
import com.example.cfcompose.ui.screen.commonElements.LiveCfSection
import com.example.cfcompose.ui.theme.CFcomposeTheme


@Composable
fun DateScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

//    LiveCfSection()
    InsertDateSection(
        modifier = Modifier,
    )
    ButtonSection(modifier, onClick)

}


@Composable
fun InsertDateSection(
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


            AndroidView(
                factory = {CalendarView(it)} ,
                update = {
                    it.setOnDateChangeListener { calendarView, year, month, day ->
                        Log.d("DateScreen" , "year : [${year}] // month : [${month}] // day : [${day}]  ")
                    }
                }
            )

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun DateScreenPreview() {
    CFcomposeTheme {
        DateScreen(Modifier, {})
    }
}



