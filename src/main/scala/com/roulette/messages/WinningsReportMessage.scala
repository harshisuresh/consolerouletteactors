package com.roulette.messages

import com.roulette.bet.BetOutcome

/**
  * Created by harshitha.suresh on 31/01/2018.
  */
case class WinningsReportMessage(rouletteOutcome : Int, outcomes : List[BetOutcome])
