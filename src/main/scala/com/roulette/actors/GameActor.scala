package com.roulette.actors

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, Props}
import com.roulette.bet.BetType
import com.roulette.messages.{RouletteLandedMessage, StartGameMessage}

import scala.concurrent.duration.Duration
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by harshitha.suresh on 30/01/2018.
  */
class GameActor extends Actor {

  private val rand: Random = new Random()
  override def receive: Receive = {
    case StartGameMessage => {
      println("Game started")
      val betMatcherActor = context.system.actorOf(Props[BetMatcherActor], name = "betMatcherActor")
      val betParserActor = context.system.actorOf(Props[BetParserActor], name = "betparseractor")
      val betInputActor = context.system.actorOf(Props[BetInputReaderActor], name = "betinputactor")
      val winningsReporterActor = context.system.actorOf(Props[WinningsReporterActor], name = "winningsReporterActor")

      println("Schedule roulette generator")
      context.system.scheduler.schedule(
        Duration(30, TimeUnit.SECONDS),
        Duration(30, TimeUnit.SECONDS)) {
        betMatcherActor ! new RouletteLandedMessage(rand.nextInt(BetType.ROULETTE_SLOTS.toInt + 1))
      }
      //betInputActor ! ReadBetInputMessage
    }
    case _ => println("Unknown message in %s", self)
  }
}
