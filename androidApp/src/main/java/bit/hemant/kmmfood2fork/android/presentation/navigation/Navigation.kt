package bit.hemant.kmmfood2fork.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import bit.hemant.kmmfood2fork.android.presentation.recipe_detail.RecipeDetailsViewModel
import bit.hemant.kmmfood2fork.android.presentation.recipe_list.RecipeListScreen
import bit.hemant.kmmfood2fork.android.presentation.recipe_list.RecipeListViewModel


@ExperimentalComposeUiApi
@ExperimentalStdlibApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel =
                viewModel(modelClass = RecipeListViewModel::class.java, factory = factory)
            RecipeListScreen(
                recipeState = viewModel.state.value,
                onTriggerEvent = viewModel::onTriggerEvent
            ) {
                navController.navigate(Screen.RecipeDetail.route + "/$it")
            }
        }

        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeDetailsViewModel = viewModel<RecipeDetailsViewModel>(
                modelClass = RecipeDetailsViewModel::class.java,
                factory = factory
            )
            bit.hemant.kmmfood2fork.android.presentation.recipe_detail.RecipeDetailScreen(
                recipe = viewModel.recipe.value
            )
        }
    }

}