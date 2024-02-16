package com.example.cfcompose.ui.screen

import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
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
    onClick : () -> Unit,
    onCalendarClick: (Int , Int , Int) -> Unit,
    CF : String
) {

    LiveCfSection(modifier , CF)
    Spacer(
        modifier = modifier
            .height(50.dp)
    )
    InsertDateSection(
        modifier = Modifier,
        onCalendarClick,
    )
    ButtonSection(modifier, onClick)

}


@Composable
fun InsertDateSection(
    modifier: Modifier = Modifier,
    onCalendarClick: (Int, Int, Int) -> Unit,
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

            Card (
                modifier = modifier
                    .padding(20.dp)
                    .width(300.dp),
                colors = CardDefaults.cardColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ){

                AndroidView(
                    factory = { CalendarView(it) } ,
                    update = {
                        it.setOnDateChangeListener { calendarView, year, month, day ->
                            Log.d("DateScreen" , "year : [${year}] // month : [${month}] // day : [${day}]  ")
                            onCalendarClick(year , month , day)
                        }
                    }
                )

            }


        }

    }
}
@Preview(showSystemUi = true)
@Composable
fun DateScreenPreview() {
    CFcomposeTheme {
        DateScreen(Modifier, {} , { _, _, _ -> } , "xxx")
    }
}



