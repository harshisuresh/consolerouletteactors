package com.roulette.bet

/**
  * Created by harshitha.suresh on 31/01/2018.
  */
trait BetType {
  def isMatched(rouletteNumber: Int): Boolean

  def getWinningsForBetAmount(amount: BigDecimal): BigDecimal

}

object BetType {
  val ROULETTE_SLOTS = "36"
}