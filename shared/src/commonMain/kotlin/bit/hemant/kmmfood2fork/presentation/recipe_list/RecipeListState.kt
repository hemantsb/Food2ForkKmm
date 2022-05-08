package bit.hemant.kmmfood2fork.presentation.recipe_list

import bit.hemant.kmmfood2fork.domain.model.GenericMessageInfo
import bit.hemant.kmmfood2fork.domain.model.Recipe
import bit.hemant.kmmfood2fork.domain.util.Queue


data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectCategoty: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)