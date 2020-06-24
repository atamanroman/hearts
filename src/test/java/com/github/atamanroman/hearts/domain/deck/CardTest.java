package com.github.atamanroman.hearts.domain.deck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

  public static final Card ACE_OF_SPADES = new Card(Rank.ACE, Suit.SPADES);

  @Test
  public void stringRepresentation() {
    assertEquals("A♠", ACE_OF_SPADES.toString());
  }

  @Test
  public void equals() {
    var aceOfSpades2 = new Card(Rank.ACE, Suit.SPADES);
    assertEquals(ACE_OF_SPADES, aceOfSpades2);
    assertEquals(aceOfSpades2, ACE_OF_SPADES);
    assertEquals(ACE_OF_SPADES, ACE_OF_SPADES);
    assertNotEquals(ACE_OF_SPADES, new Card(Rank.KING, Suit.SPADES));
    assertNotEquals(ACE_OF_SPADES, new Card(Rank.ACE, Suit.HEARTS));
  }

  @Test
  public void parse() throws CardUnknownException {
    assertEquals(new Card(Rank.ACE, Suit.SPADES), Card.parse("AS"));
    assertEquals(new Card(Rank.ACE, Suit.HEARTS), Card.parse("AH"));
    assertEquals(new Card(Rank.ACE, Suit.DIAMONDS), Card.parse("AD"));
    assertEquals(new Card(Rank.ACE, Suit.CLUBS), Card.parse("AC"));
    assertEquals(new Card(Rank.ACE, Suit.SPADES), Card.parse("A♠"));
    assertEquals(new Card(Rank.ACE, Suit.HEARTS), Card.parse("A♥"));
    assertEquals(new Card(Rank.ACE, Suit.DIAMONDS), Card.parse("A♦"));
    assertEquals(new Card(Rank.ACE, Suit.CLUBS), Card.parse("A♣"));
    assertEquals(new Card(Rank.TWO, Suit.CLUBS), Card.parse("2♣"));

    assertThrows(CardUnknownException.class,
      () -> Card.parse("bla"),
      "bla must not yield a valid card (gibberish)");
    assertThrows(CardUnknownException.class,
      () -> Card.parse("1H"),
      "1H must not yield a valid card (1 is not a valid rank)");
    assertThrows(CardUnknownException.class,
      () -> Card.parse(""),
      "empty string must not yield a valid card");
  }
}
