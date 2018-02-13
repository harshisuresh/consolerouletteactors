package com.roulette.bet

/**
  * Created by harshitha.suresh on 31/01/2018.
  */
class OddEvenBetType(remainder: Int) extends BetType {
  private val MULTIPLICAND: BigDecimal = BigDecimal.valueOf(2)

  override def isMatched(rouletteNumber: Int): Boolean = rouletteNumber % 2 == remainder

  override def getWinningsForBetAmount(amount: BigDecimal): BigDecimal = amount * MULTIPLICAND

  override def toString = remainder match {
    case 0 => "EVEN"
    case 1 => "ODD"
  }
}
