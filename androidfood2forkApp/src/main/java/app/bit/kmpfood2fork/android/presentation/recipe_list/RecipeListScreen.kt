package app.bit.kmpfood2fork.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import app.bit.kmpfood2fork.android.presentation.recipe_list.component.RecipeList
import app.bit.kmpfood2fork.android.presentation.recipe_list.component.SearchAppBar
import app.bit.kmpfood2fork.android.presentation.theme.AppTheme
import app.bit.kmpfood2fork.presentation.recipe_list.RecipeListEvent
import app.bit.kmpfood2fork.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    recipeState: RecipeListState,
    onTriggerEvent: (RecipeListEvent) -> Unit,
    onSelectRecipe: (id: Int) -> Unit
) {
    AppTheme(displayProgressBar = recipeState.isLoading) {
        Scaffold(topBar = {
            SearchAppBar(
                query = recipeState.query,
                onQueryChanged = { onTriggerEvent(RecipeListEvent.UpdateQuery(it)) }) {
                onTriggerEvent(RecipeListEvent.NewSearch)
            }
        }) {
            RecipeList(
                loading = recipeState.isLoading,
                recipes = recipeState.recipes,
                page = recipeState.page,
                onTriggerNextPage = { onTriggerEvent(RecipeListEvent.NextPage) },
                onClickRecipeListItem = onSelectRecipe
            )
        }

    }
}