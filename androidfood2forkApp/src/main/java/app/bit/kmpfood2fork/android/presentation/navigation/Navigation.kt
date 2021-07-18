package app.bit.kmpfood2fork.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import app.bit.kmpfood2fork.android.presentation.navigation.Screen
import app.bit.kmpfood2fork.android.presentation.recipe_detail.RecipeDetailsViewModel
import app.bit.kmpfood2fork.android.presentation.recipe_list.RecipeListScreen
import app.bit.kmpfood2fork.android.presentation.recipe_list.RecipeListViewModel
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory


@ExperimentalStdlibApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            val viewModel: RecipeListViewModel = viewModel()
            RecipeListScreen {
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
            app.bit.kmpfood2fork.android.presentation.recipe_detail.RecipeDetailScreen(
                recipeId = viewModel.recipe.value
            )
        }
    }

}