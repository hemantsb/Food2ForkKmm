package bit.hemant.kmmfood2fork.presentation.recipe_details

import bit.hemant.kmmfood2fork.domain.model.GenericMessageInfo
import bit.hemant.kmmfood2fork.domain.model.Recipe
import bit.hemant.kmmfood2fork.domain.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
)