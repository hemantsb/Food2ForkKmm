package bit.hemant.kmmfood2fork.android.presentation.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import bit.hemant.kmmfood2fork.android.presentation.components.ProcessDialogQueue
import bit.hemant.kmmfood2fork.android.presentation.recipe_list.component.CircularIndeterminateProgressBar
import bit.hemant.kmmfood2fork.domain.model.GenericMessageInfo
import bit.hemant.kmmfood2fork.domain.util.Queue
import java.util.*

@SuppressLint("ConflictingOnColor")
private val LightThemeColors = lightColors(
    primary = Blue600,
    primaryVariant = Blue400,
    onPrimary = Black2,
    secondary = Color.White,
    secondaryVariant = Teal300,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Black2,
)

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppTheme(
    displayProgressBar: Boolean,
    dialogQueue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
    onRemoveHeadMessageFromQueue:()->Unit,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = QuickSandTypography,
        shapes = AppShapes
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Grey1)
        ){
            ProcessDialogQueue(dialogQueue = dialogQueue,onRemoveHeadMessageFromQueue)
            content()
            if(displayProgressBar){
                CircularIndeterminateProgressBar(isDisplayed = displayProgressBar, 0.3f)
            }
        }
    }
}