package com.platzi.randomcats.core.common

sealed class Result<out T, out E> {

    companion object {
        /** Returns the given [value] as successful value. */
        fun <T, E> success(value: T): Result<T, E> = Success(value)

        /** Returns the given [error] as failure. */
        fun <T, E> failure(error: E): Result<T, E> = Failure(error)
    }

    /**
     * Returns `true` if this instance represents a successful result.
     */
    abstract val isSuccess: Boolean

    /**
     * Returns `true` if this instance represents a failed result.
     */
    abstract val isFailure: Boolean

    /**
     * Returns the value if this instance is success [Result.isSuccess] or `null` if it is failure
     * [Result.isFailure]
     */
    abstract fun getOrNull(): T?

    /**
     * Returns the error if this instance is failure [Result.isFailure] or `null` if it is success
     * [Result.isSuccess]
     */
    abstract fun errorOrNull(): E?

    private class Success<T, E>(private val value: T) : Result<T, E>() {
        override val isSuccess: Boolean get() = true
        override val isFailure: Boolean get() = false

        override fun getOrNull(): T? = value
        override fun errorOrNull(): E? = null

        override fun toString(): String = "Success($value)"
        override fun hashCode(): Int = 7 + 31 * value.hashCode()

        override fun equals(other: Any?): Boolean =
            this === other || other is Success<*, *> && this.value == other.value
    }

    private class Failure<T, E>(private val error: E) : Result<T, E>() {
        override val isSuccess: Boolean get() = false
        override val isFailure: Boolean get() = true

        override fun getOrNull(): T? = null
        override fun errorOrNull(): E? = error

        override fun toString(): String = "Failure($error)"
        override fun hashCode(): Int = 7 + 31 * error.hashCode()

        override fun equals(other: Any?): Boolean =
            this === other || other is Failure<*, *> && this.error == other.error
    }
}

/**
 * Perform the given [action] on the encapsulated value if this instance is success [Result.isSuccess].
 * Returns the original [Result] unchanged.
 */
inline fun <T, E> Result<T, E>.onSuccess(action: (data: T) -> Unit): Result<T, E> {
    @Suppress("UNCHECKED_CAST")
    if (isSuccess) action(getOrNull() as T)
    return this
}

/**
 * Perform the given [action] on the encapsulated error if this instance is failure [Result.isFailure].
 * Returns the original [Result] unchanged.
 */
inline fun <T, E> Result<T, E>.onFailure(action: (error: E) -> Unit): Result<T, E> {
    @Suppress("UNCHECKED_CAST")
    if (isFailure) action(errorOrNull() as E)
    return this
}

/**
 * Returns the result of [onSuccess] for the encapsulated value if this instance is success [Result.isSuccess].
 * Returns the result of [onFailure] for the encapsulated error if this instance is failure [Result.isFailure].
 */
inline fun <R, T, E> Result<T, E>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (error: E) -> R
): R {
    @Suppress("UNCHECKED_CAST")
    return if (isSuccess) onSuccess(getOrNull() as T) else onFailure(errorOrNull() as E)
}
