package com.platzi.randomcats.core.network.common

import com.platzi.randomcats.core.common.*
import com.squareup.moshi.Moshi
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

/**
 * [apiCall] is used to make a safe API call. It avoids boilerplate and if an exception happens, it
 * is encapsulated here.
 *
 * @param In Represents the response from server-side as an object.
 * @param Out Represents the result for the call.
 * @param api Endpoint that will be called.
 * @param onSuccess Called when the response is successful.
 * @param onFailure Called when the response is failed.
 * @return [Result] that can be success [Result.isSuccess] or failure [Result.isFailure]
 */
inline fun <In, Out> apiCall(
    api: () -> Response<In>,
    onSuccess: (Response<In>) -> Result<Out, Failure>,
    onFailure: (Response<In>) -> Failure
): Result<Out, Failure> {
    return try {
        val response = api()

        when {
            response.isSuccessful -> onSuccess(response)
            else -> Result.failure(onFailure(response))
        }
    } catch (exception: Exception) {
        Result.failure(
            when (exception) {
                is TimeoutException, is SocketTimeoutException, is ConnectException -> NetworkConnectionFailure(exception)
                else -> ExceptionFailure(exception)
            }
        )
    }
}

/**
 * [parseErrorResponse] is used to map the error json response to [ErrorResponse].
 *
 * @return [ErrorResponse] if [retrofit2.Response.errorBody] is not null, otherwise null.
 */
internal fun <T> Response<T>.parseErrorResponse(): ErrorResponse? = errorBody()?.string()?.let { it ->
    Moshi.Builder().build().adapter(ErrorResponse::class.java).fromJson(it)
}

/**
 * [unauthorizedError] is used to map an Unauthorized error.
 *
 * @return [AuthFailure] if HTTP status code is 401, otherwise null.
 */
internal fun unauthorizedError(status: Int): AuthFailure? =
    if (status == 401) {
        AuthFailure.Unauthorized
    } else {
        null
    }

/**
 * [getServerError] is used when an HTTP 5xx status code is obtained.
 *
 * @return [ServerFailure] if [retrofit2.Response.errorBody] is not null, otherwise null.
 */
internal fun getServerError(status: Int, body: ErrorResponse?): ServerFailure? {
    return body?.let {
        if (status >= 500) {
            ServerFailure(it.message)
        } else null
    }
}