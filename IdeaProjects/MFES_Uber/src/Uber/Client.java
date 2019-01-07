package Uber;

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

  public Object getStatus() {

    return status;
  }

  public UberUtils.Location getLocation() {

    return Utils.copy(location);
  }

  public VDMSet getReports() {

    return Utils.copy(reports);
  }

  public void setReady() {

    status = Uber.quotes.ReadyQuote.getInstance();
  }

  public void setWaiting() {

    status = Uber.quotes.WaitingQuote.getInstance();
  }

  public void endTrip(final Uber.Report rep) {

    reports = SetUtil.union(Utils.copy(reports), SetUtil.set(Utils.copy(rep)));
    location = Utils.copy(rep.locationEnd);
    status = Uber.quotes.ReadyQuote.getInstance();
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
