package com.github.atamanroman.hearts.domain.deck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckTest {

  public static final Deck PRISTINE = Deck.pristine();
  public static final Deck SHUFFLED = Deck.shuffled();

  @Test
  void pristine() {
    assertEquals(52, PRISTINE.size());
    var iterator = PRISTINE.iterator();
    var first = iterator.next();
    var second = iterator.next();
    var third = iterator.next();
    assertEquals(Rank.TWO, first.rank());
    assertEquals(Rank.THREE, second.rank());
    assertEquals(Rank.FOUR, third.rank());
    // ..
  }

  @Test
  void shuffled() {
    assertEquals(52, SHUFFLED.size());
  }

  @Test
  public void draw() {
    var stacks = SHUFFLED.draw(10);
    assertEquals(10, stacks.drawn().size());
    assertEquals(42, stacks.remaining().size());
  }
}
