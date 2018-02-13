package com.roulette.actors

import akka.actor.Actor
import com.roulette.bet._
import com.roulette.messages.{BetPlacedMessage, RouletteLandedMessage, WinningsReportMessage}

import scala.collection.mutable

/**
  * Created by harshitha.suresh on 04/02/2018.
  */
class BetMatcherActor extends Actor {
  val bets = mutable.Queue[Bet]()
  override def receive: Receive = {
    case RouletteLandedMessage(rouletteNumber) => {
      println("Roulette number" + rouletteNumber)
      rouletteLanded(rouletteNumber)
    }
    case BetPlacedMessage(bet) => {
      println("BetPlacedMessage" + bet)
      bets += bet
    }
  }

  def rouletteLanded(rouletteOutcome: Int) {

    val betOutcomeList: List[BetOutcome] = bets.dequeueAll(b => b.amount > 0).toStream.map(computeBetOutcome(_, rouletteOutcome)).toList
    val winningsReporterActor = context.system.actorSelection("akka://ConsoleRouletteSystem/user/winningsReporterActor")
    println("Sending WinningsReportMessage" + rouletteOutcome + betOutcomeList)
    winningsReporterActor ! WinningsReportMessage(rouletteOutcome, betOutcomeList)
  }

  def computeBetOutcome(bet : Bet, rouletteOutcome: Int): BetOutcome = {
    if (isMatched(bet, rouletteOutcome)) {
      new BetOutcome(bet, WinBetOutcomeType, bet.betType.getWinningsForBetAmount(bet.amount))
    }
    else {
      new BetOutcome(bet, LossBetOutcomeType, BigDecimal.apply(0))
    }
  }

  def isMatched(bet : Bet, rouletteNumber : Int) = bet.betType.isMatched(rouletteNumber)
}
