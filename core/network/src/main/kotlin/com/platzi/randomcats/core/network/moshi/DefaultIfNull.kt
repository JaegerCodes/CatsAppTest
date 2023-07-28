package com.platzi.randomcats.core.network.moshi

/**
 * [DefaultIfNull] is an annotation that it will be used to indicate that a [com.squareup.moshi.JsonClass]
 * will use the default values for its properties when any of them comes `null` from the server-side.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class DefaultIfNull