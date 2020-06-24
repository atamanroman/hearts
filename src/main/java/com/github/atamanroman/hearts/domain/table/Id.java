package com.github.atamanroman.hearts.domain.table;

import java.util.Objects;

public record Id(String id) {

  public static final Id NULL = new Id("--");

  public Id {
    Objects.requireNonNull(id);
    if(id.isBlank()) {
      throw new IllegalArgumentException("Id must not be blank");
    }
    if(id.equals("--") && NULL != null) { // TODO is this safe? if NULL == null we're initializing it
      throw new IllegalArgumentException("Player name '--' is not valid.");
    }
  }

  @Override
  public String toString() {
    return id;
  }
}
