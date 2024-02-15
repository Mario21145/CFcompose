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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.cfcompose.ui.screen.SexScreen
import com.example.cfcompose.ui.screen.StartScreen
import com.example.cfcompose.ui.screen.SurnameScreen


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
    if(currentScreen != CfScreen.Start ){
        canNavigateBack = true
    }

    Scaffold(

        topBar = {
            CfAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                navigateUp = { navController.navigateUp() }
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

                SurnameScreen(
                    onClick = {
                        checkDestinations(navController , currentScreen)
                              },
                    onValueChanged = {newValue ->
//                        Log.d("SurnameScreen" , "Result surname: $part")
//                        Log.d("SurnameScreen" , "NewValue: $newValue - part: ${part}")
                        if(newValue.isNotEmpty()){
                            viewModel.setCF(viewModel.calcConsonants(newValue.toList()))
                        }
                    },
                    CF =  uiState.liveCf.takeIf { it.isNotEmpty() } ?: "default"

                )

                Log.d("SurnameScreen" , "Result surname screen in the main: ${uiState.liveCf}")

            }

            composable(route = CfScreen.Name.name) {
                NameScreen(
                    onClick = {
                        checkDestinations(navController , currentScreen)
                    },
                    onValueChanged = {
                        viewModel.calcConsonants(it.toString().toList())
                    },
                    CF = uiState.liveCf
                )
            }


            composable(route = CfScreen.Date.name) {
                DateScreen(
                    onClick = {checkDestinations(navController , currentScreen)}
                )
            }

            composable(route =  CfScreen.Sex.name) {
                SexScreen(
                    onClick = {checkDestinations(navController , currentScreen)}
                )
            }

            composable(route =  CfScreen.City.name) {
                CityScreen(
                    onClickPositive = {checkDestinations(navController , currentScreen)}
                )
            }

            composable(route =  CfScreen.Recap.name) {

            }

        }
    }

}

/*

State = true on navigation
State = flase on back navigation

 */
fun checkDestinations(navController: NavHostController , currentScreen: CfScreen){
    when(currentScreen){
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
fun GreetingPreview() {
    CFcomposeTheme {
        CityApp()
    }
}


