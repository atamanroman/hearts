package com.github.atamanroman.hearts.domain.table;

import com.github.atamanroman.hearts.domain.deck.Card;
import com.github.atamanroman.hearts.domain.deck.Deck;
import com.github.atamanroman.hearts.domain.deck.Rank;
import com.github.atamanroman.hearts.domain.deck.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>The table all players are sitting at. It serves as coordinator and referee.</p>
 *
 * <p>Rules used: https://bicyclecards.com/how-to-play/hearts/</p>
 *
 * <p>This class is mutable!</p>
 */
public class Table {

  // TODO the start card actually depends on the number of players!
  private static final Card START_CARD = new Card(Rank.TWO, Suit.SPADES);

  private final List<Player> players;
  private final Deck deck;
  // TODO better model for null -> not started
  private Player nowPlaying;
  private Stack stack = Stack.EMPTY;

  /**
   * @param players
   * @return a rigged table (because the deck is not shuffled)
   */
  public static Table rigged(List<Player> players) {
    return new Table(players, Deck.pristine());
  }

  /**
   * @param players
   * @return a rigged table (because the deck might have been seen)
   */
  public static Table rigged(List<Player> players, Deck deck) {
    return new Table(players, Deck.pristine());
  }

  /**
   * @param players
   * @return a fair table with a secret, shuffled deck
   */
  public static Table fair(List<Player> players) {
    return new Table(players, Deck.shuffled());
  }

  Table(List<Player> players, Deck deck) {
    this.players = new ArrayList<>(Objects.requireNonNull(players));
    if (players.size() != 4) {
      throw new IllegalArgumentException("Only games of four are supported now");
    }
    this.deck = Objects.requireNonNull(deck);
  }

  public TableState play(Card card, Player player) {
    if (player != nowPlaying) {
      var msg = String.format("Player %s tried to play %s but %s is on the move", player, card, this.nowPlaying);
      throw new CheatingException(msg);
    }
    this.stack = stack.add(card, player);
    return state();
  }

  /**
   * Deal the cards, find the first player and start the game
   *
   * <p>Rules: Deal the cards one at a time, face down, clockwise. In a four-player game, each is
   * dealt 13 cards; in a three-player game, the 2 of diamonds should be removed, and each player
   * gets 17 cards; in a five-player game, the 2 of diamonds and 2 of clubs should be removed so
   * that each player will get 10 cards.
   */
  public void deal() {
    Deck remaining = deck;
    for (Player p : players) {
      // TODO depending on the number of players
      var stacks = remaining.draw(13);
      remaining = stacks.remaining();
      p.takeHand(stacks.drawn());
      if (p.holds(START_CARD)) {
        nowPlaying = p;
      }
    }

    if (nowPlaying == null) {
      var msg = String.format("Can't start the game because no player holds the starting card=%s", START_CARD);
      throw new IllegalStateException(msg);
    }
  }

  public TableState state() {
    return new TableState(
      nowPlaying != null ? nowPlaying.getId() : Id.NULL, stack, players.stream().collect(Collectors.toMap(Player::getId, Player::getHand)), players.stream().collect(Collectors.toMap(Player::getId, Player::getStack))
    );
  }

  public static record TableState(Id currentPlayer, Stack stack, Map<Id, Hand>hands, Map<Id, Stack>score) {
    public TableState {
      Objects.requireNonNull(score);
      Objects.requireNonNull(hands);
      Objects.requireNonNull(currentPlayer);
      Objects.requireNonNull(stack);
    }
  }
}
