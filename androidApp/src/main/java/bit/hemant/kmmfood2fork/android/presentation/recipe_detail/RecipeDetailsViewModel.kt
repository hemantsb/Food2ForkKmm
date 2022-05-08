package bit.hemant.kmmfood2fork.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bit.hemant.kmmfood2fork.domain.model.GenericMessageInfo
import bit.hemant.kmmfood2fork.domain.model.UiComponentType
import bit.hemant.kmmfood2fork.domain.util.GenericMessageInfoQueueUtil
import bit.hemant.kmmfood2fork.domain.util.Queue
import bit.hemant.kmmfood2fork.interactors.recipe_detail.GetRecipe
import bit.hemant.kmmfood2fork.presentation.recipe_details.RecipeDetailEvents
import bit.hemant.kmmfood2fork.presentation.recipe_details.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception
import java.util.*
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailsViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle, // don't need for this VM
    private val getRecipe: GetRecipe
) : ViewModel() {

    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())
//    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId = recipeId))
//            getRecipe(recipeId)
        }
    }

    fun onTriggerEvent(event: RecipeDetailEvents) {
        when (event) {
            is RecipeDetailEvents.GetRecipe -> {
                getRecipe(recipeId = event.recipeId)
            }
            RecipeDetailEvents.OnRemoveHeadMessageFromQueue->{
                removeHeadMessage()
            }
            else -> {
                appendToMessageQueue(GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Error")
                    .uiComponentType(UiComponentType.Dialog)
                    .description("Invalid Recipe"))
            }
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipe ->
                state.value = state.value.copy(recipe = recipe)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }.launchIn(viewModelScope)
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
