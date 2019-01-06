package Uber;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Uber {
  public VDMSet drivers = SetUtil.set();
  public VDMSet clients = SetUtil.set();
  public VDMSet admins = SetUtil.set();

  public void cg_init_Uber_1() {

    return;
  }

  public Uber() {

    cg_init_Uber_1();
  }

  public VDMSet registerDriver(final Driver d) {

    drivers = SetUtil.union(SetUtil.set(d), Utils.copy(drivers));
    return Utils.copy(drivers);
  }

  public VDMSet removeDriver(final Driver d) {

    drivers = SetUtil.diff(SetUtil.set(d), Utils.copy(drivers));
    return Utils.copy(drivers);
  }

  public VDMSet removeClient(final Client c) {

    clients = SetUtil.diff(SetUtil.set(c), Utils.copy(clients));
    return Utils.copy(clients);
  }

  public VDMSet registerClient(final Client c) {

    clients = SetUtil.union(SetUtil.set(c), Utils.copy(clients));
    return Utils.copy(clients);
  }

  public void requestRide(final Client c) {

    c.setWaiting();
  }

  public void goOnRide(
      final Client c,
      final UberUtils.Location destination,
      final UberUtils.Timestamp initTime,
      final Driver d) {

    Number distance = null;
    Number cost = null;
    UberUtils.Timestamp endTime = null;
    Number time = 0L;
    distance = UberUtils.calculateDistance(c.location, Utils.copy(destination));
    time = UberUtils.calculateTravelTime(d.speed, distance);
    cost = UberUtils.calculateCost(distance, time);
    endTime = UberUtils.calculateTimestamp(UberUtils.calculateEndTime(Utils.copy(initTime), time));
    c.addReport(new Report(initTime, endTime, c.location, destination, cost));
  }

  public void acceptRide(final Driver d, final Client c) {

    d.setReachingClient();
  }

  public void takeOnRide(final Driver d, final UberUtils.Location destination) {

    return;
  }

  public String toString() {

    return "Uber{"
        + "drivers := "
        + Utils.toString(drivers)
        + ", clients := "
        + Utils.toString(clients)
        + ", admins := "
        + Utils.toString(admins)
        + "}";
  }

  public static class Report implements Record {
    public UberUtils.Timestamp timeStart;
    public UberUtils.Timestamp timeEnd;
    public UberUtils.Location locationStart;
    public UberUtils.Location locationEnd;
    public Number cost;

    public Report(
        final UberUtils.Timestamp _timeStart,
        final UberUtils.Timestamp _timeEnd,
        final UberUtils.Location _locationStart,
        final UberUtils.Location _locationEnd,
        final Number _cost) {

      timeStart = _timeStart != null ? Utils.copy(_timeStart) : null;
      timeEnd = _timeEnd != null ? Utils.copy(_timeEnd) : null;
      locationStart = _locationStart != null ? Utils.copy(_locationStart) : null;
      locationEnd = _locationEnd != null ? Utils.copy(_locationEnd) : null;
      cost = _cost;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Report)) {
        return false;
      }

      Report other = ((Report) obj);

      return (Utils.equals(timeStart, other.timeStart))
          && (Utils.equals(timeEnd, other.timeEnd))
          && (Utils.equals(locationStart, other.locationStart))
          && (Utils.equals(locationEnd, other.locationEnd))
          && (Utils.equals(cost, other.cost));
    }

    public int hashCode() {

      return Utils.hashCode(timeStart, timeEnd, locationStart, locationEnd, cost);
    }

    public Report copy() {

      return new Report(timeStart, timeEnd, locationStart, locationEnd, cost);
    }

    public String toString() {

      return "mk_Uber`Report"
          + Utils.formatFields(timeStart, timeEnd, locationStart, locationEnd, cost);
    }
  }
}
