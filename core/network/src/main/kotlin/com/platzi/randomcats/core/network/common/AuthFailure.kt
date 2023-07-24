package com.platzi.randomcats.core.network.common

import com.platzi.randomcats.core.common.Failure


/**
 * [AuthFailure] represents an authorization failure
 */
sealed class AuthFailure : Failure {

    /**
     * [Unauthorized] is used to represent an HTTP 401 status code.
     */
    object Unauthorized: AuthFailure()
}
