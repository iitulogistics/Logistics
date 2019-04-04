package kz.logistic.pl.utils;

import java.util.concurrent.atomic.AtomicLong;

public class UniqueSeq {
  private static final AtomicLong sequence = new AtomicLong(System.currentTimeMillis() / 1000);

  public static long getNext() {
    return sequence.incrementAndGet();
  }
}
