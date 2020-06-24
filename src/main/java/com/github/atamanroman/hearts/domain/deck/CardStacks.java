package com.github.atamanroman.hearts.domain.deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represent two stacks of cards: some drawn cards and a remainder of the deck
 */
public record CardStacks(List<Card>drawn, Deck remaining) {
  public CardStacks {
    Objects.nonNull(drawn);
    this.drawn = new ArrayList<>(drawn);
  }
}
