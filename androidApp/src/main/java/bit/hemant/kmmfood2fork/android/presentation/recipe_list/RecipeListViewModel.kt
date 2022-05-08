package bit.hemant.kmmfood2fork.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bit.hemant.kmmfood2fork.domain.model.GenericMessageInfo
import bit.hemant.kmmfood2fork.domain.model.Recipe
import bit.hemant.kmmfood2fork.domain.model.UiComponentType
import bit.hemant.kmmfood2fork.domain.util.GenericMessageInfoQueueUtil
import bit.hemant.kmmfood2fork.domain.util.Queue
import bit.hemant.kmmfood2fork.interactors.recipe_list.SearchRecipeUseCase
import bit.hemant.kmmfood2fork.presentation.recipe_list.FoodCategory
import bit.hemant.kmmfood2fork.presentation.recipe_list.RecipeListEvent
import bit.hemant.kmmfood2fork.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception
import java.util.*
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
                loadRecipes()
            }
            RecipeListEvent.NextPage -> {
                nextPage()
            }
            RecipeListEvent.NewSearch -> {
                newSearch()
            }
            RecipeListEvent.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            is RecipeListEvent.UpdateQuery -> {
                state.value = state.value.copy(query = event.query, selectCategoty = null)
            }
            is RecipeListEvent.SelectCategory -> {
                selectCategory(event.category)
            }


            else -> {
                appendToMessageQueue(
                    GenericMessageInfo.Builder()
                        .id(UUID.randomUUID().toString())
                        .title("Error")
                        .uiComponentType(UiComponentType.Dialog)
                        .description("Invalid Recipe")
                )
            }
        }
    }


    /**
     * Get the next page of recipes
     */
    private fun selectCategory(category: FoodCategory) {
        state.value = state.value.copy(selectCategoty = category, query = category.value)
        newSearch()
    }


    /**
     * Get the next page of recipes
     */
    private fun nextPage() {
        state.value = state.value.copy(isLoading = true, page = state.value.page + 1)
        loadRecipes()
    }

    /**
     * Perform a new search:
     * 1. page = 1
     * 2. list position needs to be reset
     */
    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipeUseCase.execute(
            page = state.value.page,
            query = state.value.query,
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(isLoading = false, recipes = curr)
    }

    private fun appendToMessageQueue(errorMessage: GenericMessageInfo.Builder) {
        if (GenericMessageInfoQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue,
                messageInfo = errorMessage.build()
            )
        ) {
            return
        }
        val queue = state.value.queue
        queue.add(errorMessage.build())
        state.value = state.value.copy(queue = queue)
    }

    private fun removeHeadMessage() {
        val queue = state.value.queue
        try {
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf()))
            state.value=state.value.copy(queue = queue)
        }catch (e: Exception){
            //Nothing to remove
        }
    }

}
