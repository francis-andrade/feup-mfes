package Uber.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ReachingClientQuote {
  private static int hc = 0;
  private static ReachingClientQuote instance = null;

  public ReachingClientQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ReachingClientQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ReachingClientQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ReachingClientQuote;
  }

  public String toString() {

    return "<ReachingClient>";
  }
}
