package com.alkemy.ongsomosmas.utils

import java.lang.Exception


/**
 * Auxiliary class
 */
sealed class ResultAux<out T> {
    class Loading<out T>: ResultAux<T>()
    data class Success<out T>(val data: T): ResultAux<T>()
    data class Failure(val exception: Exception): ResultAux<Nothing>()

}