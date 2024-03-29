package Uber.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class WaitingQuote {
  private static int hc = 0;
  private static WaitingQuote instance = null;

  public WaitingQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static WaitingQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new WaitingQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof WaitingQuote;
  }

  public String toString() {

    return "<Waiting>";
  }
}
