package com.roulette.bet

/**
  * Created by harshitha.suresh on 05/02/2018.
  */
class BetOutcome(val bet: Bet, val betOutcomeType: BetOutcomeType, val winningsAmount: BigDecimal)

sealed abstract class BetOutcomeType() {
  def outcome: String
  override def toString = s"$outcome"
}
case object WinBetOutcomeType extends BetOutcomeType { val outcome = "WIN" }
case object LossBetOutcomeType extends BetOutcomeType { val outcome = "LOSS" }
