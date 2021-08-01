package app.bit.kmpfood2fork.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bit.kmpfood2fork.domain.model.Recipe
import app.bit.kmpfood2fork.domain.util.DataState
import app.bit.kmpfood2fork.interactors.recipe_list.SearchRecipeUseCase
import app.bit.kmpfood2fork.presentation.recipe_list.RecipeListEvent
import app.bit.kmpfood2fork.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val searchRecipeUseCase: SearchRecipeUseCase
) : ViewModel() {
    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvent.LoadRecipes)
    }

    fun onTriggerEvent(event: RecipeListEvent){
        when (event){
            RecipeListEvent.LoadRecipes -> {
                loadRecipe()
            }
            RecipeListEvent.NextPage -> {
                nextPage()
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }
    /**
     * Get the next page of recipes
     */
    private fun nextPage(){
        state.value = state.value.copy(isLoading = true, page = state.value.page + 1)
        loadRecipe()
    }

    private fun loadRecipe() {
        searchRecipeUseCase.execute(state.value.page, state.value.query).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> println("RecipeDetailVM: loading: ${true}")
                is DataState.Data -> {
                    appendRecipes(dataState.data)
                }
                is DataState.ErrorMessage -> println("RecipeDetailVM: error: ${dataState.errorMessage}")
            }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(isLoading = false,recipes = curr)
    }

    private fun handleError(errorMessage: String){
        // TODO("Handle errors")
    }
}
