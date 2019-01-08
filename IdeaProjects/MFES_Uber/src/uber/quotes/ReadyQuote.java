package uber.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ReadyQuote {
  private static int hc = 0;
  private static ReadyQuote instance = null;

  public ReadyQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ReadyQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ReadyQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ReadyQuote;
  }

  public String toString() {

    return "<Ready>";
  }
}
