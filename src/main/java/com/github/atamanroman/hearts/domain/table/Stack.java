package com.github.atamanroman.hearts.domain.table;

import com.github.atamanroman.hearts.domain.deck.Card;
import com.github.atamanroman.hearts.infra.Pair;

import java.util.ArrayList;
import java.util.List;

public record Stack(List<Pair<Id, Card>>played) {

  public static final Stack EMPTY = new Stack(new ArrayList<>());

  Stack add(Card card, Player player) {
    var played = new ArrayList<>(this.played);
    played.add(new Pair<>(player.getId(), card));
    return new Stack(played);
  }

  Stack add(Stack stack) {
    var played = new ArrayList<>(this.played);
    played.addAll(stack.played);
    return new Stack(played);
  }

  @Override
  public String toString() {
    return "S" + played.toString();
  }
}
