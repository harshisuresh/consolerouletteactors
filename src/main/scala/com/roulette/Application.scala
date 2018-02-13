package com.roulette

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import com.roulette.actors.{BetGeneratorActor, GameActor}
import com.roulette.messages.{GeneratBetInputMessage, StartGameMessage}

import scala.concurrent.duration.Duration


import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by harshitha.suresh on 30/01/2018.
  */
object Application extends App {
  // an actor needs an ActorSystem
  val system = ActorSystem("ConsoleRouletteSystem")
  // create and start the actor
  val gameActor = system.actorOf(Props[GameActor], name = "gameactor")
  val betGeneratorActor = system.actorOf(Props[BetGeneratorActor], name = "betGeneratorActor")
  println("Schedule bet generator")
  system.scheduler.schedule(
    Duration(10, TimeUnit.SECONDS),
    Duration(1, TimeUnit.SECONDS)) {
    betGeneratorActor ! GeneratBetInputMessage
  }
  // send the actor two messages
  gameActor ! StartGameMessage
}