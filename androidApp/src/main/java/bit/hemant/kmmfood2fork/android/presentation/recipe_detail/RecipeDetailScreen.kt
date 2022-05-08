package bit.hemant.kmmfood2fork.android.presentation.recipe_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bit.hemant.kmmfood2fork.android.presentation.recipe_detail.components.RecipeView
import bit.hemant.kmmfood2fork.android.presentation.theme.AppTheme
import bit.hemant.kmmfood2fork.presentation.recipe_details.RecipeDetailEvents
import bit.hemant.kmmfood2fork.presentation.recipe_details.RecipeDetailState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalStdlibApi
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit
) {

    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = { onTriggerEvent(RecipeDetailEvents.OnRemoveHeadMessageFromQueue) }
    ) {
        if (state.recipe == null) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "We were unable to retrieve the details for this recipe.\nTry resetting the app.",
                style = MaterialTheme.typography.body1
            )
        } else {
            RecipeView(recipe = state.recipe!!)
        }
//        else if(state.recipe == null){
//            Text(
//                modifier = Modifier.padding(16.dp),
//                text = "We were unable to retrieve the details for this recipe.\nTry resetting the app.",
//                style = MaterialTheme.typography.body1
//            )
//        }
//        else{

//        }
    }
}