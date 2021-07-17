package app.bit.kmpfood2fork.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import app.bit.kmpfood2fork.android.presentation.navigation.Screen
import app.bit.kmpfood2fork.android.presentation.recipe_list.RecipeListScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
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
            app.bit.kmpfood2fork.android.presentation.recipe_detail.RecipeDetailScreen(
                recipeId = navBackStackEntry.arguments?.getInt("recipeId")
            )
        }
    }

}