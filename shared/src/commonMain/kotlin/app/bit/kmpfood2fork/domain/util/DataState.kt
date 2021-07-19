package app.bit.kmpfood2fork.domain.util

sealed class DataState<out R> {

    data class Data<out T>(val data: T) : DataState<T>()
    data class Error(val error: ErrorResponse) : DataState<Nothing>()
    data class ErrorMessage(val errorMessage: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()


    override fun toString(): String {
        return when (this) {
            is Data<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            is ErrorMessage -> "Error[exception=$errorMessage]"
            Loading -> "Loading"
        }
    }
}

val DataState<*>.succeeded
    get() = this is DataState.Data && data != null

val DataState<*>.failed
    get() = this is DataState.Error

fun <T> DataState<T>.successOr(fallback: T): T {
    return (this as? DataState.Data<T>)?.data ?: fallback
}

val <T> DataState<T>.data: T?
    get() = (this as? DataState.Data)?.data