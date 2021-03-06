package com.github.atamanroman.hearts.domain.deck;

public enum Rank implements Readable {
  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8"),
  NINE("9"),
  TEN("10"),
  JACK("J"),
  QUEEN("Q"),
  KING("K"),
  ACE("A");

  private final String abbrv;

  Rank(String abbrv) {
    this.abbrv = abbrv;
  }

  @Override
  public String readable() {
    return abbrv;
  }
}
