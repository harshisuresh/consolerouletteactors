package com.roulette.actors

import akka.actor.Actor
import com.roulette.messages.{GeneratBetInputMessage, ParseBetInputMessage}

import scala.util.Random

/**
  * Created by harshitha.suresh on 06/02/2018.
  */
class BetGeneratorActor extends Actor {
  private val rand: Random = new Random()
  val names = List[String]("a", "b", "c", "d", "e")
  val possibleBets = List[String]("ODD", "EVEN", "1", "2", "3")
  val possibleAmounts = List.range(1, 10)
  val betParserActor = context.system.actorSelection("akka://ConsoleRouletteSystem/user/betparseractor")

  override def receive: Receive = {

    case GeneratBetInputMessage => {
      val name = names(rand.nextInt(names.size))
      val bet = possibleBets(rand.nextInt(possibleBets.size))
      val amount = possibleAmounts(rand.nextInt(possibleAmounts.size))
      val betInput: String = name + "," + bet + "," + amount
      betParserActor ! ParseBetInputMessage(betInput)
    }
  }
}
