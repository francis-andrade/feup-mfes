package Uber;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Client extends User {
  public Number rating = 5L;
  public Object status = Uber.quotes.ReadyQuote.getInstance();
  public UberUtils.Location location;
  public VDMSet reports = SetUtil.set();

  public void cg_init_Client_1(final String nm, final Number lat, final Number lon) {

    name = nm;
    location = new UberUtils.Location(lat, lon, "Portugal");
    return;
  }

  public Client(final String nm, final Number lat, final Number lon) {

    cg_init_Client_1(nm, lat, lon);
  }

  public void setReady() {

    status = Uber.quotes.ReadyQuote.getInstance();
  }

  public void setWaiting() {

    status = Uber.quotes.WaitingQuote.getInstance();
  }

  public void setInTransit() {

    status = Uber.quotes.InTransitQuote.getInstance();
  }

  public void addReport(final Uber.Report rep) {

    reports = SetUtil.union(Utils.copy(reports), SetUtil.set(Utils.copy(rep)));
  }

  public Client() {}

  public String toString() {

    return "Client{"
        + "rating := "
        + Utils.toString(rating)
        + ", status := "
        + Utils.toString(status)
        + ", location := "
        + Utils.toString(location)
        + ", reports := "
        + Utils.toString(reports)
        + "}";
  }
}
