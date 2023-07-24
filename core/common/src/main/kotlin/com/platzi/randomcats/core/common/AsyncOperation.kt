package com.platzi.randomcats.core.common

sealed class AsyncOperation<out T> {

    /**
     * True if this is a loading state.
     */
    val isLoading by lazy { this is Loading }

    /**
     * True if this is a completed success state.
     */
    val isSuccess by lazy { this is Completed && this.result.isSuccess }

    /**
     * True if this is a completed failure state.
     */
    val isFailure by lazy { this is Completed && this.result.isFailure }

    /**
     * Perform [action] on the result data if any, then return this operation.
     */
    inline fun onSuccess(action: (data: T) -> Unit): AsyncOperation<T> = apply {
        if (this is Completed) {
            result.onSuccess(action)
        }
    }

    /**
     * Perform [action] on the failure if any, then return this operation.
     */
    inline fun onFailure(action: (failure: Failure) -> Unit): AsyncOperation<T> = apply {
        if (this is Completed) {
            result.onFailure(action)
        }
    }

    /**
     * Return result data, if any.
     */
    val data: T?
        get() = when (this) {
            is Loading -> null
            is Completed -> result.getOrNull()
        }

    /**
     * Return error, if any
     */
    val error: Failure?
        get() = when (this) {
            is Loading -> null
            is Completed -> result.errorOrNull()
        }
}

/**
 * [Loading] represents the loading status of an operation. It does not contain any data because
 * the operation is incomplete.
 */
object Loading : AsyncOperation<Nothing>()

/**
 * [Completed] represents the completed status of an operation. It contains a [Result] object and it
 * could be a success or failure operation.
 *
 * @param T type of the operation
 * @property result contains the success or failure of the operation
 */
class Completed<T>(val result: Result<T, Failure>) : AsyncOperation<T>()

fun <T> loading(): AsyncOperation<T> = Loading
fun <T> success(data: T) = Completed(Result.success(data))
fun <T> failure(failure: Failure) = Completed<T>(Result.failure(failure))
