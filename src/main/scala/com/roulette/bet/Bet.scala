package com.roulette.bet

import com.roulette.player.Player

/**
  * Created by harshitha.suresh on 31/01/2018.
  */
class Bet(val player: Player, val amount: BigDecimal, val betType: BetType ) {

  override def toString = s"Bet($player, $amount, $betType)"
}
