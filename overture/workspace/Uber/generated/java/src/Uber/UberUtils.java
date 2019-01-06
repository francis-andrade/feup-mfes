package Uber;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UberUtils {
  public UberUtils() {}

  public static Number timeBetween(final Timestamp timeS, final Timestamp timeE) {

    return timeE.hour.longValue() * 1440L
        + timeE.minute.longValue() * 60L
        + timeE.second.longValue()
        - (timeS.hour.longValue() * 1440L
            + timeS.minute.longValue() * 60L
            + timeS.second.longValue());
  }

  public static Number calculateCost(final Number distance, final Number time) {

    return 2.5 + distance.doubleValue() * 0.3 + 0.1 * Utils.divide((1.0 * time.longValue()), 60L);
  }

  public static Number calculateDistance(final Location localS, final Location localD) {

    return Utils.pow(
        Utils.pow(localS.latitude.longValue() - localD.latitude.longValue(), 2L)
            + Utils.pow(localS.longitude.longValue() - localD.longitude.longValue(), 2L),
        0.5);
  }

  public static Number calculateEndTime(final Timestamp timeS, final Number elapsed) {

    return timeS.hour.longValue() * 1440L
        + timeS.minute.longValue() * 60L
        + timeS.second.longValue()
        + elapsed.longValue();
  }

  public static Number calculateTravelTime(final Number speed, final Number dist) {

    return Math.round(Utils.floor(Utils.divide(dist.doubleValue(), speed.longValue()))) + 1L;
  }

  public static Timestamp calculateTimestamp(final Number seconds) {

    return new Timestamp(
        Utils.divide((1.0 * seconds.longValue()), 3600L),
        Utils.rem(Utils.divide((1.0 * seconds.longValue()), 60L), 60L),
        Utils.rem(seconds.longValue(), 60L));
  }

  public String toString() {

    return "UberUtils{}";
  }

  public static class Timestamp implements Record {
    public Number hour;
    public Number minute;
    public Number second;

    public Timestamp(final Number _hour, final Number _minute, final Number _second) {

      hour = _hour;
      minute = _minute;
      second = _second;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Timestamp)) {
        return false;
      }

      Timestamp other = ((Timestamp) obj);

      return (Utils.equals(hour, other.hour))
          && (Utils.equals(minute, other.minute))
          && (Utils.equals(second, other.second));
    }

    public int hashCode() {

      return Utils.hashCode(hour, minute, second);
    }

    public Timestamp copy() {

      return new Timestamp(hour, minute, second);
    }

    public String toString() {

      return "mk_UberUtils`Timestamp" + Utils.formatFields(hour, minute, second);
    }
  }

  public static class Location implements Record {
    public Number latitude;
    public Number longitude;
    public String country;

    public Location(final Number _latitude, final Number _longitude, final String _country) {

      latitude = _latitude;
      longitude = _longitude;
      country = _country != null ? _country : null;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Location)) {
        return false;
      }

      Location other = ((Location) obj);

      return (Utils.equals(latitude, other.latitude))
          && (Utils.equals(longitude, other.longitude))
          && (Utils.equals(country, other.country));
    }

    public int hashCode() {

      return Utils.hashCode(latitude, longitude, country);
    }

    public Location copy() {

      return new Location(latitude, longitude, country);
    }

    public String toString() {

      return "mk_UberUtils`Location" + Utils.formatFields(latitude, longitude, country);
    }
  }
}
