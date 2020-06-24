package com.github.atamanroman.hearts.domain.table;

import java.util.Objects;

// TODO don't model with exceptions... :(
/** Signals that someone broke the rules! */
public class CheatingException extends RuntimeException {
  public CheatingException(String msg) {
    super(Objects.requireNonNull(msg));
  }
}
