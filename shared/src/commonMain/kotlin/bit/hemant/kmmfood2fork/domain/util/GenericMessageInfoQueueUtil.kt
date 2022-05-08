package bit.hemant.kmmfood2fork.domain.util

import bit.hemant.kmmfood2fork.domain.model.GenericMessageInfo

class GenericMessageInfoQueueUtil {

    fun doesMessageAlreadyExistInQueue(
        queue: Queue<GenericMessageInfo>,
        messageInfo: GenericMessageInfo
    ): Boolean {

        for (item in queue.items) {
            if (item.id == messageInfo.id) {
                return true
            }
        }
        return false
    }
}