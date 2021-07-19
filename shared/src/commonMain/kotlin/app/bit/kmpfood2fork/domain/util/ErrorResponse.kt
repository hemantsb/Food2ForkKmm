package app.bit.kmpfood2fork.domain.util

import kotlinx.serialization.json.JsonElement


data class ErrorResponse(
    val statusCode: Int = 0,
    val message: String = "",
    val status: String = "",
    val detail: String = "",
    val errorData: JsonElement
)
