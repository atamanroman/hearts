package com.github.atamanroman.hearts.domain.table;

import com.github.atamanroman.hearts.domain.deck.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Hand(List<Card>cards) {
  public static final Hand EMPTY = new Hand(List.of());

  public Hand {
    Objects.nonNull(cards);
    this.cards = new ArrayList<>(cards);
  }

  public Hand draw(Card card) {
    if (!cards.contains(card)) {
      var msg = String.format("Can't play card=%s because it is not in hand=%s", card, this);
      throw new IllegalStateException(msg);
    }
    var without = new ArrayList<>(this.cards);
    without.remove(card);
    return new Hand(without);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Hand hand = (Hand) o;
    return cards.equals(hand.cards);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cards);
  }

  @Override
  public String toString() {
    return "H" + cards.toString();
  }

  public boolean contains(Card card) {
    return cards.contains(card);
  }
}
