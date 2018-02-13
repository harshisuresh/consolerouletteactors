package com.roulette.exceptions

/**
  * Created by harshitha.suresh on 31/01/2018.
  */
case class BetInputException(private val message: String = "", private val cause: Throwable = None.orNull
                            ) extends Exception(message, cause)
