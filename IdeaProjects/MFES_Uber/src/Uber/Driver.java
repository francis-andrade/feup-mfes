package Uber;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Driver extends User {
  public Number rating = 5L;
  public Object status = Uber.quotes.ReadyQuote.getInstance();
  public Number speed;
  public UberUtils.Location location;

  public void cg_init_Driver_1(
      final String nm, final Number spd, final Number lat, final Number lon) {

    name = nm;
    speed = spd;
    location = new UberUtils.Location(lat, lon, "Portugal");
    return;
  }

  public Driver(final String nm, final Number spd, final Number lat, final Number lon) {

    cg_init_Driver_1(nm, spd, lat, lon);
  }

  public Object getStatus() {

    return status;
  }

  public UberUtils.Location getLocation() {

    return Utils.copy(location);
  }

  public void setReachingClient() {

    status = Uber.quotes.ReachingClientQuote.getInstance();
  }

  public void endTrip(final UberUtils.Location destination) {

    location = Utils.copy(destination);
    status = Uber.quotes.ReadyQuote.getInstance();
  }

  public Driver() {}

  public String toString() {

    return "Driver{"
        + "rating := "
        + Utils.toString(rating)
        + ", status := "
        + Utils.toString(status)
        + ", speed := "
        + Utils.toString(speed)
        + ", location := "
        + Utils.toString(location)
        + "}";
  }
}
