import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cfcompose.ui.screen.StepsScreen
import com.example.cfcompose.ui.utils.Windows
import com.example.cfcompose.ui.viewmodel.CfViewModel

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: CfViewModel = viewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()

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
        Row() {
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                StepsScreen(uiState)
            }

            Column(
                modifier = Modifier
                    .weight(4f)
            ) {
                Screens( contentType , uiState = uiState)
            }

        }
    }

    if (contentType != Windows.ScreenAndSteps) {
        Screens( contentType , uiState = uiState)
    }
}