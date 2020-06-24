package com.github.atamanroman.hearts.domain.deck;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Deck implements Iterable<Card> {

  private static Deck INSTANCE = new Deck();

  /**
   * @return a new full deck, still in packaging
   */
  public static Deck pristine() {
    return INSTANCE;
  }

  /**
   * @return a new full deck, just opened and shuffled
   */
  public static Deck shuffled() {
    var cards = new ArrayList<>(INSTANCE.cards);
    Collections.shuffle(cards);
    return new Deck(cards);
  }

  private List<Card> cards;

  private Deck() {
    this.cards =
        stream(Suit.values())
            .flatMap(s -> stream(Rank.values()).map(r -> new Card(r, s)))
            .collect(Collectors.toList());
  }

  private Deck(List<Card> cards) {
    Objects.requireNonNull(cards);
    this.cards = new ArrayList<>(cards);
  }

  public CardStacks draw(int no) {
    var drawn = this.cards.subList(0, no);
    var remaining = this.cards.subList(no, this.cards.size());
    return new CardStacks(drawn, new Deck(remaining));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Deck cards1 = (Deck) o;
    return cards.equals(cards1.cards);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cards);
  }

  @Override
  public String toString() {
    return "Deck{" + "played=" + cards + '}';
  }

  @Override
  public Iterator<Card> iterator() {
    return cards.iterator();
  }

  @Override
  public void forEach(Consumer<? super Card> action) {
    cards.forEach(action);
  }

  @Override
  public Spliterator<Card> spliterator() {
    return cards.spliterator();
  }

  public int size() {
    return cards.size();
  }
}
