package com.platzi.randomcats.core.network.common

import com.platzi.randomcats.core.common.*
import com.platzi.randomcats.core.network.moshi.DefaultIfNull
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Response

/**
 * [ErrorResponse] is used to map the error body from server-side.
 */
@DefaultIfNull
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "status_code") val code: Int = 0,
    @Json(name = "status_message") val message: String = "",
    @Json(name = "errors") val errors: List<String> = emptyList()
)

/**
 * [parseFailure] is used to map the [retrofit2.Response.errorBody] to [ErrorResponse] and get a
 * [Failure]
 */
fun <T> Response<T>.parseFailure(): Failure {
    val errorResponse = parseErrorResponse()
    return unauthorizedError(code())
        ?: getServerError(code(), errorResponse)
        ?: getError(code(), errorResponse)
}

/**
 * [getError] is used when an HTTP 4xx is obtained.
 * *Note* an HTTP 401 is not obtained here.
 */
private fun getError(status: Int, body: ErrorResponse?): Error {
    return body?.let {
        Error(
            it.code,
            it.message
        )
    } ?: Error(status)
}