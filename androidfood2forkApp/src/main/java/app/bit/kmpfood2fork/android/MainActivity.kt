package app.bit.kmpfood2fork.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.ExperimentalComposeUiApi
import app.bit.kmpfood2fork.android.presentation.navigation.Navigation
import app.bit.kmpfood2fork.datasource.network.KtorClientFactory
import app.bit.kmpfood2fork.datasource.network.model.RecipeDto
import app.bit.kmpfood2fork.datasource.network.toRecipe
import app.bit.kmpfood2fork.domain.util.DatetimeUtil
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
const val BASE_URL = "https://food2fork.ca/api/recipe"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ktorClient = KtorClientFactory().build()
        CoroutineScope(IO).launch {
            val recipeId = 1551
            val recipe = ktorClient.get<RecipeDto>{
                url("$BASE_URL/get?id=$recipeId")
                header("Authorization", TOKEN)
            }.toRecipe()
            println("KtorTest: ${recipe.title}")
            println("KtorTest: ${recipe.ingredients}")
            println("KtorTest: ${DatetimeUtil().humanizeDatetime(recipe.dateUpdated)}")
        }
        setContent {
            Navigation()
        }
    }
}
