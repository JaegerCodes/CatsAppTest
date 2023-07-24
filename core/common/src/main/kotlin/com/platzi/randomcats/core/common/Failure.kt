package com.platzi.randomcats.core.common

/**
 * [Failure] is a base type representing failed operations. Subtypes are created to add context to
 * particular kinds of failure in an operation.
 */
interface Failure {

    /**
     * Human readable message that describes what the failure is.
     */
    val message: String
        get() = ""
}

/**
 * [NetworkConnectionFailure] is used to represent failures when using the network
 *
 * @param errorData any error data in the response
 */
class NetworkConnectionFailure<ErrorData>(
    val errorData: ErrorData? = null
) : Failure

/**
 * [ServerFailure] is used to represent an error on the server-side of a network request. Typical
 * usage is when the request gets an HTTP 5xx status code.
 *
 * @param message It is from the server-side
 */
data class ServerFailure(
    override val message: String
) : Failure

/**
 * [ExceptionFailure] wraps an exception that resulted a [Failure]
 *
 * @param e The exception
 */
class ExceptionFailure(private val e: Throwable) : Failure {
    override val message: String
        get() = "ExceptionFailure: ${toString()}"

    override fun toString(): String = e.toString()
}

/**
 * [Error] is used to represent an HTTP 4xx status code.
 *
 * @param code Status code
 * @param message It is from the server-side
 */
data class Error(
    val code: Int = 0,
    override val message: String = ""
): Failure