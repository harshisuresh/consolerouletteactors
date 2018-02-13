package com.roulette.actors

import akka.actor.Actor
import com.roulette.bet.{Bet, BetType, NumberBetType, OddEvenBetType}
import com.roulette.exceptions.BetInputException
import com.roulette.messages.{BetPlacedMessage, ParseBetInputMessage}
import com.roulette.player.Player

/**
  * Created by harshitha.suresh on 30/01/2018.
  */
class BetParserActor extends Actor{
  override def receive: Receive = {
    case ParseBetInputMessage(betInput) => {
      val betMatcherActor = context.system.actorSelection("akka://ConsoleRouletteSystem/user/betMatcherActor")
      val bet = parseBetInput(betInput)

      betMatcherActor ! BetPlacedMessage(bet)
    }
  }

  def parseBetInput(betInput : String): Bet = {
    val inputTokens = betInput.split(",").map(_.trim)
    val playerName = inputTokens(0)
    val betString = inputTokens(1)
    val betAmount = BigDecimal(inputTokens(2))

    val betType = parseBet(betString)
    if(betType.isEmpty) {
      throw new BetInputException("Invalid Bet Type: " + betType)
    }
    val bet: Bet = new Bet(new Player(playerName), betAmount, betType.get)
    return bet
  }

  def parseBet(bet : String) : Option[BetType] = {
    bet match {
      case "ODD" => Option.apply(new OddEvenBetType(1))
      case "EVEN" => Option.apply(new OddEvenBetType(0))
      case _ =>
        val betNumber = bet.toInt
        if (betNumber >0 && betNumber <=36) Option.apply(new NumberBetType(bet.toInt)) else Option.apply(null)
    }
  }
}
