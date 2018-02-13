package com.roulette.actors

/**
  * Created by harshitha.suresh on 30/01/2018.
  */

import akka.actor.Actor
import com.roulette.messages.{EndProcessingMessage, ParseBetInputMessage, ReadBetInputMessage}

class BetInputReaderActor extends Actor {
  def receive = {
    case ReadBetInputMessage => {
      val betParserActor = context.system.actorSelection("akka://ConsoleRouletteSystem/user/betparseractor")
      println("Reading bet input")
      val filename = "betinput.txt"
      for (line <- io.Source.fromResource(filename).getLines) {
        println("Processing: " + line)
        line match {
          case "END" => self ! EndProcessingMessage
          case _ =>
            betParserActor ! ParseBetInputMessage(line)
        }
      }

    }
    case EndProcessingMessage => {
      println("Done processing file")
    }
  }


}
