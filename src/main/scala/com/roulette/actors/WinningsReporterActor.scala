package com.roulette.actors

import akka.actor.Actor
import com.roulette.messages.WinningsReportMessage

/**
  * Created by harshitha.suresh on 31/01/2018.
  */
class WinningsReporterActor extends Actor{
  override def receive: Receive = {
    case WinningsReportMessage(rouletteOutcome, betOutcomes) => {
      println("received winnings message" + rouletteOutcome, betOutcomes)
      println(s"Roulette outcome is $rouletteOutcome")
      println("Player       Bet     BetAmount     Outcome    Winnings ")
      println("-------------------------------------------")
      for(outcome <- betOutcomes){
        val player = outcome.bet.player
        val bet = outcome.bet.betType
        val betAmount = outcome.bet.amount
        val outcomeResult = outcome.betOutcomeType
        val winnings = outcome.winningsAmount
        println(s"$player       $bet        $betAmount        $outcomeResult        $winnings")
      }
    }
  }
}
