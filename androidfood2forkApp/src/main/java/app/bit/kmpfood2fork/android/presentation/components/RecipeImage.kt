package app.bit.kmpfood2fork.android.presentation.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

const val RECIPE_IMAGE_HEIGHT = 260

@ExperimentalCoilApi
@Composable
fun RecipeImage(
    url: String,
    contentDescription: String,
){
    val painter = rememberImagePainter(url)
    Box {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp),
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )
        when(painter.state){
            is Error -> {
                // if you want to display some kind of an error
            }
            is ImagePainter.State.Success -> {
                // if you want to do something when displaying the image is successful
            }
            is ImagePainter.State.Loading -> {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(RECIPE_IMAGE_HEIGHT.dp)
                ) {
                    // empty for white background
                }
            }
        }
    }
}