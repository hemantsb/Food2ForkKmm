package bit.hemant.kmmfood2fork.android.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FoodCategoryChip(
    category: String,
    isSelected: Boolean = false,
    onSelectedCategoryChanged: (String) -> Unit
) {

    androidx.compose.material.Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 9.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.Gray else MaterialTheme.colors.primary,
    ) {
        Row(modifier = Modifier.clickable {
            onSelectedCategoryChanged(category)
        }.padding(vertical = 4.dp, horizontal = 8.dp)) {
            Text(text = category, style = MaterialTheme.typography.body2, color = Color.White)
        }
    }
}