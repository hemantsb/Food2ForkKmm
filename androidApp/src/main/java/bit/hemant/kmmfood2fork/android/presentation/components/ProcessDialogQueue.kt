package bit.hemant.kmmfood2fork.android.presentation.components

import androidx.compose.runtime.Composable
import bit.hemant.kmmfood2fork.domain.model.GenericMessageInfo
import bit.hemant.kmmfood2fork.domain.util.Queue

@Composable
fun ProcessDialogQueue(dialogQueue: Queue<GenericMessageInfo>?, onRemoveHeadMessageFromQueue: ()->Unit) {
    dialogQueue?.peek()?.let { message ->
        GenericDialog(title = message.title, description = message.description,onRemoveHeadMessageFromQueue)
    }
}