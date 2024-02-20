package com.example.cfcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cfcompose.ui.screen.CityScreen
import com.example.cfcompose.ui.screen.DateScreen
import com.example.cfcompose.ui.screen.NameScreen
import com.example.cfcompose.ui.screen.RecapScreen
import com.example.cfcompose.ui.screen.SexScreen
import com.example.cfcompose.ui.screen.StartScreen
import com.example.cfcompose.ui.screen.StepsScreen
import com.example.cfcompose.ui.screen.SurnameScreen
import com.example.cfcompose.ui.theme.CFcomposeTheme
import com.example.cfcompose.ui.utils.CfScreen
import com.example.cfcompose.ui.utils.Windows
import com.example.cfcompose.ui.viewmodel.CfViewModel


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CFcomposeTheme {
                val windowSize = calculateWindowSizeClass(this)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CityApp(windowSize.widthSizeClass)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CfAppBar(
    currentScreen: CfScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(currentScreen.title),
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController(),

    ) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentScreen = CfScreen.valueOf(
        backStackEntry?.destination?.route ?: CfScreen.Start.name
    )

    var contentType: Windows

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            contentType = Windows.Screen
        }

        WindowWidthSizeClass.Medium -> {
            contentType = Windows.Screen
        }

        WindowWidthSizeClass.Expanded -> {
            contentType = Windows.ScreenAndSteps
        }

        else -> {
            contentType = Windows.Screen
        }
    }

    if (contentType == Windows.ScreenAndSteps) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                StepsScreen(currentScreen)
            }

            Column(
                modifier = Modifier
                    .weight(3f)
            ) {
                Screens(windowSize, contentType)
            }

        }

    }

    if (contentType != Windows.ScreenAndSteps) {
        Screens(windowSize, contentType)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screens(
    windowSize: WindowWidthSizeClass,
    contentType: Windows,
    viewModel: CfViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    var canNavigateBack = false
    var currentScreen = CfScreen.valueOf(
        backStackEntry?.destination?.route ?: CfScreen.Start.name
    )

    if (currentScreen != CfScreen.Start) {
        canNavigateBack = true
    }

    val uiState by viewModel.uiState.collectAsState()


    Scaffold(
        topBar = {
            CfAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                navigateUp = {

                    when (currentScreen) {

                        CfScreen.Surname -> {

                            navController.navigateUp()
                        }

                        CfScreen.Name -> {
                            viewModel.updateCF(0..2)
                            navController.navigateUp()
                        }

                        CfScreen.Date -> {
                            viewModel.updateCF(3..5)
                            navController.navigateUp()
                        }

                        CfScreen.Sex -> {
                            viewModel.updateCF(6..10)
                            navController.navigateUp()
                        }

                        CfScreen.City -> {
                            navController.navigateUp()
                        }

                        CfScreen.Recap -> {
                            viewModel.updateCF(11..14)
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
                        if (contentType == Windows.ScreenAndSteps) {
                            viewModel.setStateSteps(buttonEnabled , currentScreen)
                        }
                        viewModel.setCF(part.takeLast(3))
                        viewModel.checkDestinations(navController, currentScreen)
                    },
                    onValueChanged = { newValue ->
                        part = viewModel.calcSurname(newValue)
                        buttonEnabled = part.isNotBlank()
                    },
                    CF = part,
                    enabled = buttonEnabled
                )
                Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
                Log.d(
                    "MainScreen",
                    "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city}"
                )
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
                    },
                    CF = uiState.liveCf + part,
                    enabled = buttonEnabled
                )
                Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
                Log.d(
                    "MainScreen",
                    "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city} "
                )

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
                    },
                    CF = uiState.liveCf + part,
                    enabled = buttonEnabled
                )

                Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
                Log.d(
                    "MainScreen",
                    "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city} "
                )

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
                    },
                    CF = uiState.liveCf + part,
                    enabled = buttonEnabled
                )

                Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
                Log.d(
                    "MainScreen",
                    "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city} "
                )

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
                        viewModel.setCity(city)
                    },
                    CF = uiState.liveCf + part,
                    enabled = buttonEnabled
                )

                Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
                Log.d(
                    "MainScreen",
                    "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city}"
                )
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


    } //


}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    CFcomposeTheme {
//        CityApp(windowSize.widthSizeClass)
    }
}



