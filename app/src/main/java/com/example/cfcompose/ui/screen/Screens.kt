
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cfcompose.data.CfUiState
import com.example.cfcompose.ui.screen.commonElements.NavHostScreens
import com.example.cfcompose.ui.utils.CfScreenUtils
import com.example.cfcompose.ui.utils.WindowsUtils
import com.example.cfcompose.ui.viewmodel.CfViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screens(
    contentType: WindowsUtils,
    viewModel: CfViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    uiState: CfUiState,
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentScreen = CfScreenUtils.valueOf(
        backStackEntry?.destination?.route ?: CfScreenUtils.Start.name
    )

    Scaffold(
        topBar = {
            CfAppBar(
                currentScreen = currentScreen,
                canNavigateBack = currentScreen != CfScreenUtils.Start,
                navigateUp = {viewModel.checkDestinations(navController , currentScreen , true , contentType)}
            )
        }

    ) { innerPadding ->
        NavHostScreens(navController , innerPadding, viewModel , uiState , currentScreen , contentType )
    }


}