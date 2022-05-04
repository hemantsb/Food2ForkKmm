package bit.hemant.kmmfood2fork.android.presentation.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bit.hemant.kmmfood2fork.android.presentation.components.RecipeImage
import bit.hemant.kmmfood2fork.android.presentation.recipe_detail.components.RecipeView
import bit.hemant.kmmfood2fork.domain.model.Recipe

@Composable
fun RecipeDetailScreen(recipe: Recipe?) {

    if (recipe == null) {
        Text(text = "Not valid Recipe")
    } else {
        RecipeView(recipe = recipe)
//        Column(modifier = Modifier.padding(16.dp)) {
//            RecipeImage(url = recipe.featuredImage, contentDescription = recipe.title)
//            Text(
//                modifier = Modifier
//                    .fillMaxWidth(), text = recipe.title, style = MaterialTheme.typography.h4
//            )
//            LazyColumn() {
//                items(recipe.ingredients) {
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth(), text = it, style = MaterialTheme.typography.body2
//                    )
//
//                }
//            }
//        }

    }
}