package app.bit.kmpfood2fork.android.presentation.recipe_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.bit.kmpfood2fork.domain.model.Recipe

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onClickRecipeListItem: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && recipes.isEmpty()) {
            // Loading
        } else if (recipes.isEmpty()) {
            // There's nothing here
        } else {
            LazyColumn {
                itemsIndexed(
                    items = recipes
                ) { index, recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            onClickRecipeListItem(recipe.id)
                        }
                    )
                }
            }
        }
    }
}