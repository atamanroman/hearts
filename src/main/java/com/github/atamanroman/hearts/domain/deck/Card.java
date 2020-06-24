package com.github.atamanroman.hearts.domain.deck;

import java.util.Objects;

/**
 * A single card like ace of spades
 */
public record Card(Rank rank, Suit suit) implements Readable {

  // TODO move to suit/rank?
  /**
   * Parse a card shorthand. Possible variations are: AS, as
   *
   * @param input card shorthand
   * @return Card of right suit and color
   * @throws CardUnknownException if the card can't be parsed
   */
  public static Card parse(String input) throws CardUnknownException {
    Objects.requireNonNull(input);
    if (input.isBlank()) {
      throw new CardUnknownException("Can't create a card from a blank string");
    }

    if (input.length() < 2) {
      throw new CardUnknownException("Card=" + input + "is unknown");
    }

    String suitString = input.trim().substring(input.length() - 1).toUpperCase();

    Suit suit = switch (suitString) {
      case "C", "♣" -> Suit.CLUBS;
      case "H", "♥" -> Suit.HEARTS;
      case "S", "♠" -> Suit.SPADES;
      case "D", "♦" -> Suit.DIAMONDS;
      default -> throw new CardUnknownException("Suit=" + suitString + "is unknown");
    };

    String rankString = input.substring(0, input.length() - 1).trim();
    Rank rank = switch (rankString) {
      case "2" -> Rank.TWO;
      case "3" -> Rank.THREE;
      case "4" -> Rank.FOUR;
      case "5" -> Rank.FIVE;
      case "6" -> Rank.SIX;
      case "7" -> Rank.SEVEN;
      case "8" -> Rank.EIGHT;
      case "9" -> Rank.NINE;
      case "10" -> Rank.TEN;
      case "J" -> Rank.JACK;
      case "Q" -> Rank.QUEEN;
      case "K" -> Rank.KING;
      case "A" -> Rank.ACE;
      default -> throw new CardUnknownException("Rank=" + rankString + "is unknown");
    };
    return new Card(rank, suit);
  }

  public Card {
    Objects.requireNonNull(rank);
    Objects.requireNonNull(suit);
  }

  @Override
  public String toString() {
    return readable();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Card card = (Card) o;
    return rank == card.rank && suit == card.suit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rank, suit);
  }

  @Override
  public String readable() {
    return rank.readable() + suit.readable();
  }
}
