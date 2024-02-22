import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cfcompose.ui.screen.StepsScreen
import com.example.cfcompose.ui.utils.WindowsUtils
import com.example.cfcompose.ui.viewmodel.CfViewModel

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    viewModel: CfViewModel = viewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()
    val contentType = viewModel.checkWindowsSize(windowSize)

    if (contentType == WindowsUtils.ScreenAndSteps) {
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
                Screens(contentType, uiState = uiState)
            }

        }
    }

    if (contentType != WindowsUtils.ScreenAndSteps) {
        Screens(contentType, uiState = uiState)
    }
}