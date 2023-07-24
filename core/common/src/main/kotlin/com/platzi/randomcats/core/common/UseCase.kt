package com.platzi.randomcats.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * [FlowUseCase] is a UseCase type that returns a cold [Flow] with [AsyncOperation].
 *
 * *Note* [FlowUseCase] returns a cold flow that will not execute until it is collected and will
 * update the flow with the loading state when [FlowUseCase.execute] is called.
 *
 * @param T - Return type when [FlowUseCase] succeeds.
 */
abstract class FlowUseCase<T>(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    /**
     * Public method for executing the business logic of this [FlowUseCase]. Returns a [Flow] that
     * will emit [Loading] and then the result of executing [FlowUseCase.run].
     *
     * @return a flow that will emit [Loading] and [Completed]
     */
    fun execute(): Flow<AsyncOperation<T>> = flow {
        emit(loading())
        emit(withContext(ioDispatcher) { run() })
    }

    /**
     * Protected method to encapsulate the business logic of the [FlowUseCase] subtype.
     *
     * @return the result of the logic ([success] or [failure])
     */
    abstract suspend fun run(): Completed<T>
}

/**
 * [FlowUseCase2] is the same as [FlowUseCase] but it needs a new param.
 *
 * @param I Input needed to make the API call.
 * @param T Return type when [FlowUseCase2] succeeds.
 */
abstract class FlowUseCase2<I, T>(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    fun execute(input: I): Flow<AsyncOperation<T>> = flow {
        emit(loading())
        emit(withContext(ioDispatcher) { run(input) })
    }

    abstract suspend fun run(input: I): Completed<T>
}