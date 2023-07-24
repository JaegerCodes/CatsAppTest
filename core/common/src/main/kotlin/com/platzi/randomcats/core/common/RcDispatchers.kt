package com.platzi.randomcats.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val rcDispatcher: RcDispatchers)

enum class RcDispatchers {
    Default,
    IO,
}