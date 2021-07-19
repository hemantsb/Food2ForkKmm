package app.bit.kmpfood2fork.android.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bit.kmpfood2fork.domain.util.DataState
import app.bit.kmpfood2fork.interactors.recipe_list.SearchRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
private val searchRecipeUseCase :  SearchRecipeUseCase
) : ViewModel() {

    init {
        loadRecipe()
    }

    private fun loadRecipe() {
        searchRecipeUseCase.execute(1,"nice").onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> println("RecipeDetailVM: loading: ${true}")
                is DataState.Data -> {
                    println("RecipeListVM: recipes: ${dataState.data}")
                }
                is DataState.ErrorMessage -> println("RecipeDetailVM: error: ${dataState.errorMessage}")
            }
        }.launchIn(viewModelScope)
    }

}
