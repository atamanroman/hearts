package com.github.atamanroman.hearts.domain.table;

import com.github.atamanroman.hearts.domain.deck.Card;

import java.util.List;
import java.util.Objects;

/**
 * Player in the game, holding his played
 *
 * <p>This class is mutable!
 */
public class Player {

  // might be a uuid or whatever, for the sake of simplicity we use the name
  private final Id id;
  private Hand hand = Hand.EMPTY;
  // TODO is this really a stack?
  private Stack stack = Stack.EMPTY;

  public Player(String id) {
    this.id = new Id(Objects.requireNonNull(id));
  }

  public void play(Card card, Table table) {
    this.hand = hand.draw(card);
    table.play(card, this);
  }

  public void takeHand(List<Card> cards) {
    this.hand = new Hand(cards);
  }

  public void takeStack(Stack stack) {
    this.stack = stack;
  }

  public Id getId() {
    return id;
  }

  public Stack getStack() {
    return stack;
  }

  public Hand getHand() {
    return hand;
  }

  public boolean holds(Card card) {
    return hand.contains(card);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return id.equals(player.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Player{" + "id='" + id + '\'' + ", hand=" + hand + ", stack=" + stack + '}';
  }
}
