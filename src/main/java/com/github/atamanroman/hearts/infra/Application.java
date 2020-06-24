package com.github.atamanroman.hearts.infra;

import com.github.atamanroman.hearts.domain.deck.Card;
import com.github.atamanroman.hearts.domain.deck.CardUnknownException;
import com.github.atamanroman.hearts.domain.table.Player;
import com.github.atamanroman.hearts.domain.table.Table;

import java.util.ArrayList;
import java.util.List;

public class Application {

  public static void main(String[] args) throws CardUnknownException {
    List<Player> players = new ArrayList<>();
    var gunnar = new Player("Gunnar");
    players.add(gunnar);
    var xaver = new Player("Xaver");
    players.add(xaver);
    var basti = new Player("Basti");
    players.add(basti);
    var roman = new Player("Roman");
    players.add(roman);

    var rigged = Table.rigged(players);
    System.out.println(rigged.state());
    rigged.deal();
    System.out.println(rigged.state());
    // TODO table should orchestrate and make sure cards are moved "transactionally"?
    roman.play(Card.parse("2S"), rigged);
    System.out.println(rigged.state());
  }
}
