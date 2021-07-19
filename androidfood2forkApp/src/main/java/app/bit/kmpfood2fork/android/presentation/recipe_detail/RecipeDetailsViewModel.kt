package app.bit.kmpfood2fork.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bit.kmpfood2fork.datasource.network.RecipeService
import app.bit.kmpfood2fork.domain.model.Recipe
import app.bit.kmpfood2fork.domain.util.DataState
import app.bit.kmpfood2fork.interactors.recipe_detail.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailsViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle, // don't need for this VM
    private val getRecipe: GetRecipeUseCase
) : ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            getRecipe(recipeId)
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> println("RecipeDetailVM: loading: ${true}")
                is DataState.Data -> {
                    this.recipe.value = dataState.data
                }
                is DataState.ErrorMessage -> println("RecipeDetailVM: error: ${dataState.errorMessage}")
            }
        }.launchIn(viewModelScope)
    }

}
