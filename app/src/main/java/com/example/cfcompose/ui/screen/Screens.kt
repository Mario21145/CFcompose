
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cfcompose.data.CfUiState
import com.example.cfcompose.ui.screen.CityScreen
import com.example.cfcompose.ui.screen.DateScreen
import com.example.cfcompose.ui.screen.NameScreen
import com.example.cfcompose.ui.screen.RecapScreen
import com.example.cfcompose.ui.screen.SexScreen
import com.example.cfcompose.ui.screen.StartScreen
import com.example.cfcompose.ui.screen.SurnameScreen
import com.example.cfcompose.ui.utils.CfScreen
import com.example.cfcompose.ui.utils.Windows
import com.example.cfcompose.ui.viewmodel.CfViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screens(
    contentType: Windows,
    viewModel: CfViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    uiState: CfUiState,
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    var canNavigateBack = false
    var currentScreen = CfScreen.valueOf(
        backStackEntry?.destination?.route ?: CfScreen.Start.name
    )

    if (currentScreen != CfScreen.Start) {
        canNavigateBack = true
    }

    Scaffold(
        topBar = {
            CfAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                navigateUp = {

                    Log.d("CurrentScreen" , "$currentScreen")
                    when (currentScreen) {

                        CfScreen.Surname -> {
                            if(contentType == Windows.ScreenAndSteps){
                                viewModel.setStateSteps(false , currentScreen , true)
                            }
                            navController.navigateUp()
                        }

                        CfScreen.Name -> {
                            viewModel.updateCF(0..2)
                            viewModel.setSurname("")
                            if(contentType == Windows.ScreenAndSteps){
                                viewModel.setStateSteps(false , currentScreen , true)
                            }
                            navController.navigateUp()
                        }

                        CfScreen.Date -> {
                            viewModel.updateCF(3..5)
                            viewModel.setName("")
                            if(contentType == Windows.ScreenAndSteps){
                                viewModel.setStateSteps(false , currentScreen , true)
                            }
                            navController.navigateUp()
                        }

                        CfScreen.Sex -> {
                            viewModel.updateCF(6..10)
                            viewModel.setMonth("")
                            viewModel.setYear("")
                            viewModel.setDay("")
                            if(contentType == Windows.ScreenAndSteps){
                                viewModel.setStateSteps(false , currentScreen , true)
                            }
                            navController.navigateUp()
                        }

                        CfScreen.City -> {
                            viewModel.setSex("")
                            if(contentType == Windows.ScreenAndSteps){
                                viewModel.setStateSteps(false , currentScreen , true)
                            }
                            navController.navigateUp()
                        }

                        CfScreen.Recap -> {
                            viewModel.setCity("")
                            viewModel.updateCF(11..14)
                            if(contentType == Windows.ScreenAndSteps){
                                viewModel.setStateSteps(false , currentScreen , true)
                            }
                            navController.navigateUp()
                        }

                        else -> {}
                    }
                }
            )
        }

    ) { innerPadding ->






        NavHost(
            navController = navController,
            startDestination = CfScreen.Start.name,
            modifier = Modifier
                .padding(innerPadding)
        ) {

            composable(route = CfScreen.Start.name) {
                StartScreen(
                    onClick = {
                        navController.navigate(CfScreen.Surname.name)
                    }
                )
            }

            composable(route = CfScreen.Surname.name) {

                var part by remember { mutableStateOf("") }
                var buttonEnabled by remember { mutableStateOf(false) }

                SurnameScreen(
                    onClick = {
                        viewModel.setCF(part.takeLast(3))
                        viewModel.checkDestinations(navController, currentScreen)
                    },
                    onValueChanged = { newValue ->
                        part = viewModel.calcSurname(newValue)
                        buttonEnabled = part.isNotBlank()
                        if (contentType == Windows.ScreenAndSteps) {
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


            composable(route = CfScreen.Name.name) {

                var part by remember { mutableStateOf("") }
                var buttonEnabled by remember { mutableStateOf(false) }


                NameScreen(
                    onClick = {
                        viewModel.checkDestinations(navController, currentScreen)
                        viewModel.setCF(part)
                    },
                    onValueChanged = { newValue ->
                        buttonEnabled = newValue.isNotBlank()
                        part = viewModel.calcName(newValue)
                        if (contentType == Windows.ScreenAndSteps) {
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

            composable(route = CfScreen.Date.name) {

                var part by remember { mutableStateOf("") }
                var buttonEnabled by remember { mutableStateOf(false) }

                DateScreen(
                    onClick = {
                        viewModel.setCF(part)
                        viewModel.checkDestinations(navController, currentScreen)
                    },
                    onCalendarClick = { year, month, day ->
                        part = viewModel.calcDate(year, month, day)
                        buttonEnabled = part.isNotEmpty()
                        if (contentType == Windows.ScreenAndSteps) {
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

            composable(route = CfScreen.Sex.name) {

                var part by remember { mutableStateOf("") }
                var buttonEnabled by remember { mutableStateOf(false) }

                SexScreen(
                    onClick = {
                        viewModel.setCF(part)
                        viewModel.checkDestinations(navController, currentScreen)
                    },
                    onRadioClicked = { isMenSelected, isWomenSelected ->
                        buttonEnabled = isMenSelected || isWomenSelected
                        part = viewModel.calcSex(isMenSelected, isWomenSelected)
                        if (contentType == Windows.ScreenAndSteps) {
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

            composable(route = CfScreen.City.name) {

                var part by remember { mutableStateOf("") }
                var buttonEnabled by remember { mutableStateOf(false) }

                CityScreen(
                    onClick = {
                        viewModel.setCF(part)
                        viewModel.checkDestinations(navController, currentScreen)
                    },
                    onDropDownClicked = { city ->
                        part = viewModel.calcCity(city)
                        buttonEnabled = part.isNotBlank()
                        if (contentType == Windows.ScreenAndSteps) {
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

            composable(route = CfScreen.Recap.name) {

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
                        viewModel.checkDestinations(navController, currentScreen)
                    }
                )

            }
        }

    }


}