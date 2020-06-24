package com.github.atamanroman.hearts.domain.deck;

public enum Suit implements Readable {
  CLUBS("♣"),
  DIAMONDS("♦"),
  HEARTS("♥"),
  SPADES("♠");

  private final String abbrv;

  Suit(String abbrv) {
    this.abbrv = abbrv;
  }

  public String readable() {
    return abbrv;
  }
}
