package Uber.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class InTransitQuote {
  private static int hc = 0;
  private static InTransitQuote instance = null;

  public InTransitQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static InTransitQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new InTransitQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof InTransitQuote;
  }

  public String toString() {

    return "<InTransit>";
  }
}
