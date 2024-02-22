package com.example.cfcompose.ui.screen.commonElements

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cfcompose.data.CfUiState
import com.example.cfcompose.ui.screen.CityScreen
import com.example.cfcompose.ui.screen.DateScreen
import com.example.cfcompose.ui.screen.NameScreen
import com.example.cfcompose.ui.screen.RecapScreen
import com.example.cfcompose.ui.screen.SexScreen
import com.example.cfcompose.ui.screen.StartScreen
import com.example.cfcompose.ui.screen.SurnameScreen
import com.example.cfcompose.ui.utils.CfScreenUtils
import com.example.cfcompose.ui.utils.WindowsUtils
import com.example.cfcompose.ui.viewmodel.CfViewModel


@Composable
fun NavHostScreens(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: CfViewModel,
    uiState: CfUiState,
    currentScreen : CfScreenUtils,
    contentType : WindowsUtils
){

    NavHost(
        navController = navController,
        startDestination = CfScreenUtils.Start.name,
        modifier = Modifier
            .padding(innerPadding)
    ) {

        composable(route = CfScreenUtils.Start.name) {
            StartScreen(
                onClick = {
                    navController.navigate(CfScreenUtils.Surname.name)
                }
            )
        }

        composable(route = CfScreenUtils.Surname.name) {

            var part by remember { mutableStateOf("") }
            var buttonEnabled by remember { mutableStateOf(false) }

            SurnameScreen(
                onClick = {
                    viewModel.setCF(part.takeLast(3))
                    viewModel.checkDestinations(navController, currentScreen , false , contentType)
                },
                onValueChanged = { newValue ->
                    part = viewModel.calcSurnameName(newValue , false)
                    buttonEnabled = part.isNotBlank()
                    if (contentType == WindowsUtils.ScreenAndSteps) {
                        viewModel.setStateSteps(buttonEnabled , currentScreen , false)
                    }
                },
                CF = part,
                enabled = buttonEnabled
            )
            Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
            Log.d("MainScreen", "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city}")
            Log.d("MainScreen", "stateStepSurname: ${uiState.stateStepSurname}")
            Log.d("MainScreen", "stateStepName: ${uiState.stateStepName}")
            Log.d("MainScreen", "stateStepDate: ${uiState.stateStepDate}")
            Log.d("MainScreen", "stateStepSex: ${uiState.stateStepSex}")
            Log.d("MainScreen", "stateStepCity: ${uiState.stateStepCity}")
            Log.d("MainScreen", "stateStepRecap: ${uiState.stateStepRecap}")
        }


        composable(route = CfScreenUtils.Name.name) {

            var part by remember { mutableStateOf("") }
            var buttonEnabled by remember { mutableStateOf(false) }


            NameScreen(
                onClick = {
                    viewModel.checkDestinations(navController, currentScreen , false , contentType)
                    viewModel.setCF(part)
                },
                onValueChanged = { newValue ->
                    buttonEnabled = newValue.isNotBlank()
                    part = viewModel.calcSurnameName(newValue , true)
                    if (contentType == WindowsUtils.ScreenAndSteps) {
                        viewModel.setStateSteps(buttonEnabled , currentScreen , false)
                    }
                },
                CF = uiState.liveCf + part,
                enabled = buttonEnabled
            )
            Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
            Log.d("MainScreen", "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city} ")
            Log.d("MainScreen", "stateStepSurname: ${uiState.stateStepSurname}")
            Log.d("MainScreen", "stateStepName: ${uiState.stateStepName}")
            Log.d("MainScreen", "stateStepDate: ${uiState.stateStepDate}")
            Log.d("MainScreen", "stateStepSex: ${uiState.stateStepSex}")
            Log.d("MainScreen", "stateStepCity: ${uiState.stateStepCity}")
            Log.d("MainScreen", "stateStepRecap: ${uiState.stateStepRecap}")

        }

        composable(route = CfScreenUtils.Date.name) {

            var part by remember { mutableStateOf("") }
            var buttonEnabled by remember { mutableStateOf(false) }

            DateScreen(
                onClick = {
                    viewModel.setCF(part)
                    viewModel.checkDestinations(navController, currentScreen , false , contentType)
                },
                onCalendarClick = { year, month, day ->
                    part = viewModel.calcDate(year, month, day)
                    buttonEnabled = part.isNotEmpty()
                    if (contentType == WindowsUtils.ScreenAndSteps) {
                        viewModel.setStateSteps(buttonEnabled , currentScreen , false)
                    }
                },
                CF = uiState.liveCf + part,
                enabled = buttonEnabled
            )

            Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
            Log.d(
                "MainScreen",
                "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city} "
            )
            Log.d("MainScreen", "stateStepSurname: ${uiState.stateStepSurname}")
            Log.d("MainScreen", "stateStepName: ${uiState.stateStepName}")
            Log.d("MainScreen", "stateStepDate: ${uiState.stateStepDate}")
            Log.d("MainScreen", "stateStepSex: ${uiState.stateStepSex}")
            Log.d("MainScreen", "stateStepCity: ${uiState.stateStepCity}")
            Log.d("MainScreen", "stateStepRecap: ${uiState.stateStepRecap}")

        }

        composable(route = CfScreenUtils.Sex.name) {

            var part by remember { mutableStateOf("") }
            var buttonEnabled by remember { mutableStateOf(false) }

            SexScreen(
                onClick = {
                    viewModel.setCF(part)
                    viewModel.checkDestinations(navController, currentScreen , false , contentType)
                },
                onRadioClicked = { isMenSelected, isWomenSelected ->
                    buttonEnabled = isMenSelected || isWomenSelected
                    part = viewModel.calcSex(isMenSelected, isWomenSelected)
                    if (contentType == WindowsUtils.ScreenAndSteps) {
                        viewModel.setStateSteps(buttonEnabled , currentScreen , false)
                    }
                },
                CF = uiState.liveCf + part,
                enabled = buttonEnabled
            )

            Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
            Log.d("MainScreen", "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city} ")
            Log.d("MainScreen", "stateStepSurname: ${uiState.stateStepSurname}")
            Log.d("MainScreen", "stateStepName: ${uiState.stateStepName}")
            Log.d("MainScreen", "stateStepDate: ${uiState.stateStepDate}")
            Log.d("MainScreen", "stateStepSex: ${uiState.stateStepSex}")
            Log.d("MainScreen", "stateStepCity: ${uiState.stateStepCity}")
            Log.d("MainScreen", "stateStepRecap: ${uiState.stateStepRecap}")

        }

        composable(route = CfScreenUtils.City.name) {

            var part by remember { mutableStateOf("") }
            var buttonEnabled by remember { mutableStateOf(false) }

            CityScreen(
                onClick = {
                    viewModel.setCF(part)
                    viewModel.checkDestinations(navController, currentScreen , false , contentType)
                },
                onDropDownClicked = { city ->
                    part = viewModel.calcCity(city)
                    buttonEnabled = part.isNotBlank()
                    if (contentType == WindowsUtils.ScreenAndSteps) {
                        viewModel.setStateSteps(buttonEnabled , currentScreen , false)
                    }
                    viewModel.setCity(city)
                },
                CF = uiState.liveCf + part,
                enabled = buttonEnabled
            )

            Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
            Log.d("MainScreen", "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city}")
            Log.d("MainScreen", "stateStepSurname: ${uiState.stateStepSurname}")
            Log.d("MainScreen", "stateStepName: ${uiState.stateStepName}")
            Log.d("MainScreen", "stateStepDate: ${uiState.stateStepDate}")
            Log.d("MainScreen", "stateStepSex: ${uiState.stateStepSex}")
            Log.d("MainScreen", "stateStepCity: ${uiState.stateStepCity}")
            Log.d("MainScreen", "stateStepRecap: ${uiState.stateStepRecap}")
        }

        composable(route = CfScreenUtils.Recap.name) {

            val lastLetter = viewModel.calcLastLetter(uiState.liveCf)
            val checkedDay = viewModel.checkDayFinal().toString()

            RecapScreen(
                name = uiState.name,
                surname = uiState.surname,
                sex = uiState.sex,
                year = uiState.year,
                city = uiState.city,
                month = uiState.month,
                liveCf = uiState.liveCf + lastLetter,
                day = checkedDay,
                onClick = {
                    viewModel.clearAll()
                    viewModel.checkDestinations(navController, currentScreen , false , contentType)
                }
            )

        }
    }


}

