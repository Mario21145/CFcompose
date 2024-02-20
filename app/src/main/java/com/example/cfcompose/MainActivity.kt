package com.example.cfcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cfcompose.ui.theme.CFcomposeTheme
import com.example.cfcompose.ui.utils.CfScreen
import com.example.cfcompose.ui.viewmodel.CfViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cfcompose.ui.screen.CityScreen
import com.example.cfcompose.ui.screen.DateScreen
import com.example.cfcompose.ui.screen.NameScreen
import com.example.cfcompose.ui.screen.RecapScreen
import com.example.cfcompose.ui.screen.SexScreen
import com.example.cfcompose.ui.screen.StartScreen
import com.example.cfcompose.ui.screen.SurnameScreen
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CFcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CityApp()
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
    viewModel: CfViewModel = viewModel(),
) {

//    val uiState by viewModel.uiState.collectAsState()

    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(currentScreen.title))
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
                        //stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityApp(
    viewModel: CfViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentScreen = CfScreen.valueOf(
        backStackEntry?.destination?.route ?: CfScreen.Start.name
    )

    val uiState by viewModel.uiState.collectAsState()

    var canNavigateBack = false
    if (currentScreen != CfScreen.Start) {
        canNavigateBack = true
    }

    Scaffold(

        topBar = {
            CfAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                navigateUp = {

                    when (currentScreen) {

                        CfScreen.Surname -> {
                            viewModel.updateCF(0..2)
                            navController.navigateUp()
                        }

                        CfScreen.Name -> {
                            viewModel.updateCF(3..5)
                            navController.navigateUp()
                        }

                        CfScreen.Date -> {
                            viewModel.updateCF(6..10)
                            navController.navigateUp()
                        }

                        CfScreen.Sex -> {
//                            viewModel.updateCF(0..2)
                            navController.navigateUp()
                        }

                        CfScreen.City -> {
//                            viewModel.updateCF(0..2)
                            navController.navigateUp()
                        }

                        CfScreen.Recap -> {
//                            viewModel.updateCF(0..2)
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
                        checkDestinations(navController, currentScreen)
                        viewModel.setCF(part.takeLast(3))
                    },
                    onValueChanged = { newValue ->
                        buttonEnabled = newValue.isNotBlank()
                        part = viewModel.calcSurname(newValue)
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
                        checkDestinations(navController, currentScreen)
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
                    "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city}"
                )

            }



            composable(route = CfScreen.Date.name) {

                var part by remember { mutableStateOf("") }
                var buttonEnabled by remember { mutableStateOf(false) }

                DateScreen(
                    onClick = {
                        viewModel.setCF(part)
                        checkDestinations(navController, currentScreen)
                    },
                    onCalendarClick = { year, month, day ->
                        buttonEnabled = part.isNotEmpty()
                        part = viewModel.calcDate(year, month, day)
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

            composable(route = CfScreen.Sex.name) {

                var part by remember { mutableStateOf("") }
                var buttonEnabled by remember { mutableStateOf(false) }


                SexScreen(
                    onClick = {
                        viewModel.setCF(part)
                        checkDestinations(navController, currentScreen)
                    },
                    onRadioClicked = { isMenSelected, isWomenSelected ->
                        if(uiState.stateSex == 1){
                            buttonEnabled = false
                        }
                        part = viewModel.calcSex(isMenSelected, isWomenSelected)
                    },
                    CF = uiState.liveCf + part
                )

                Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
                Log.d(
                    "MainScreen",
                    "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city}"
                )

            }

            composable(route = CfScreen.City.name) {

                var part by remember { mutableStateOf("") }

                CityScreen(
                    onClick = {
                        viewModel.setCF(part)
                        checkDestinations(navController, currentScreen)
                    },
                    onDropDownClicked = { city ->
                        part = viewModel.calcCity(city)
                        viewModel.setCity(city)
                    },
                    CF = uiState.liveCf + part
                )

                Log.d("MainScreen", "Livecf: ${uiState.liveCf}")
                Log.d(
                    "MainScreen",
                    "Data stored in ui state: surname: ${uiState.surname}, name: ${uiState.name}, year: ${uiState.year}, month: ${uiState.month}, day: ${uiState.day}, sex: ${uiState.sex}, city: ${uiState.city}"
                )
            }

            composable(route = CfScreen.Recap.name) {

                val lastLetter = viewModel.calcLastLetter(uiState.liveCf)

                RecapScreen(
                    name = uiState.name,
                    surname = uiState.surname,
                    sex = uiState.sex,
                    year = uiState.year,
                    city = uiState.city,
                    month = uiState.month,
                    liveCf = uiState.liveCf + lastLetter,
                    day = uiState.day,
                    onClick = {
                        checkDestinations(navController, currentScreen)
                        viewModel.clearAll()
                    }
                )

            }
        }
    }

}

fun checkDestinations(navController: NavHostController, currentScreen: CfScreen) {
    when (currentScreen) {
        CfScreen.Start -> {
            navController.navigate(CfScreen.Surname.name)
        }

        CfScreen.Surname -> {
            navController.navigate(CfScreen.Name.name)
        }

        CfScreen.Name -> {
            navController.navigate(CfScreen.Date.name)
        }

        CfScreen.Date -> {
            navController.navigate(CfScreen.Sex.name)
        }

        CfScreen.Sex -> {
            navController.navigate(CfScreen.City.name)
        }

        CfScreen.City -> {
            navController.navigate(CfScreen.Recap.name)
        }

        CfScreen.Recap -> {
            navController.navigate(CfScreen.Start.name)
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    CFcomposeTheme {
        CityApp()
    }
}



