package com.github.atamanroman.hearts.infra;

public record Pair<FST, SND>(FST fst, SND snd) {

  @Override
  public String toString() {
    return String.format("(%s, %s)", fst, snd);
  }
}
