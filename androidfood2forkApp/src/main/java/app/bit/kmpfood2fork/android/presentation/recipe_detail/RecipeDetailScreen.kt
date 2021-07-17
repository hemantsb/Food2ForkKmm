package app.bit.kmpfood2fork.android.presentation.recipe_detail

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RecipeDetailScreen(recipeId: Int?) {

    if (recipeId == null) {
        Text(text = "Not valid Recipe")
    } else {
        Text(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),text= "RecipeDetailScreen: $recipeId")
    }
}