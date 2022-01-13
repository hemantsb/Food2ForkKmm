package bit.hemant.kmmfood2fork.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bit.hemant.kmmfood2fork.domain.model.Recipe
import bit.hemant.kmmfood2fork.domain.util.DataState
import bit.hemant.kmmfood2fork.interactors.recipe_list.SearchRecipeUseCase
import bit.hemant.kmmfood2fork.presentation.recipe_list.RecipeListEvent
import bit.hemant.kmmfood2fork.presentation.recipe_list.RecipeListState
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

    fun onTriggerEvent(event: RecipeListEvent) {
        when (event) {
            RecipeListEvent.LoadRecipes -> {
                loadRecipe()
            }
            RecipeListEvent.NextPage -> {
                nextPage()
            }
            RecipeListEvent.NewSearch -> {
                newSearch()
            }
            is RecipeListEvent.UpdateQuery -> {
                state.value = state.value.copy( query = event.query)
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }

    /**
     * Get the next page of recipes
     */
    private fun nextPage() {
        state.value = state.value.copy(isLoading = true, page = state.value.page + 1)
        loadRecipe()
    }

    /**
     * Perform a new search:
     * 1. page = 1
     * 2. list position needs to be reset
     */
    private fun newSearch(){
        state.value = state.value.copy(page = 1, recipes = listOf())
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
        state.value = state.value.copy(isLoading = false, recipes = curr)
    }

    private fun handleError(errorMessage: String) {
        // TODO("Handle errors")
    }
}
