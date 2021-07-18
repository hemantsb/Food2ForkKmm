package app.bit.kmpfood2fork.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import app.bit.kmpfood2fork.android.presentation.navigation.Navigation
import app.bit.kmpfood2fork.datasource.network.KtorClientFactory
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.text.get

const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
const val BASE_URL = "https://food2fork.ca/api/recipe"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ktorClient = KtorClientFactory().build()
        CoroutineScope(IO).launch {
            val recipeId = 1551
            val recipe = ktorClient.get<String> {
                url("$BASE_URL/get?id=$recipeId")
                header("Authorization", TOKEN)
            }
            println("KtorTest: ${recipe}")
        }
        setContent {
            Navigation()
        }
    }
}
