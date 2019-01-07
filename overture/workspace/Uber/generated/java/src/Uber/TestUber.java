package Uber;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestUber extends MyTestCase {
  public void testCreateDriver() {

    Uber u = new Uber();
    Driver d = new Driver("Antonio", 40L, 0L, 0L);
    Admin a = new Admin("Admin");
    assertEqual(SetUtil.set(a), u.addAdmin(a));
    assertEqual(SetUtil.set(d), u.registerDriver(a, d));
  }

  public void testRemoveDriver() {

    Uber u = new Uber();
    Driver d = new Driver("Antonio", 40L, 0L, 0L);
    Admin a = new Admin("Admin");
    assertEqual(SetUtil.set(a), u.addAdmin(a));
    assertEqual(SetUtil.set(d), u.registerDriver(a, d));
    assertEqual(0L, u.removeDriver(a, d).size());
  }

  public void testCreateAccount() {

    Uber u = new Uber();
    Client c = new Client("Barbara", 0L, 0L);
    assertEqual(SetUtil.set(c), u.registerClient(c));
  }

  public void testRemoveClient() {

    Uber u = new Uber();
    Client c = new Client("Barbara", 0L, 0L);
    Admin a = new Admin("Admin");
    assertEqual(SetUtil.set(a), u.addAdmin(a));
    assertEqual(SetUtil.set(c), u.registerClient(c));
    assertEqual(SetUtil.set(), u.removeClient(a, c));
  }

  public void testGoOnRide() {

    Uber u = new Uber();
    Client c = new Client("Barbara", 0L, 0L);
    Driver d = new Driver("Antonio", 40L, 0L, 0L);
    Admin a = new Admin("Admin");
    assertEqual(SetUtil.set(a), u.addAdmin(a));
    assertEqual(SetUtil.set(c), u.registerClient(c));
    u.registerDrivers(a, MapUtil.map(new Maplet("Antonio", 40L)));
    assertEqual(2L, u.registerDriver(a, d).size());
    u.requestRide(c);
    assertEqual(Uber.quotes.WaitingQuote.getInstance(), ((Object) c.getStatus()));
    u.acceptRide(d, c);
    assertEqual(Uber.quotes.ReachingClientQuote.getInstance(), ((Object) d.getStatus()));
    u.goOnRide(
        c, new UberUtils.Location(1L, 1L, "Portugal"), new UberUtils.Timestamp(0L, 0L, 0L), d);
    assertEqual(Uber.quotes.ReadyQuote.getInstance(), ((Object) c.getStatus()));
    assertEqual(new UberUtils.Location(1L, 1L, "Portugal"), c.getLocation());
    for (Iterator iterator_3 = c.getReports().iterator(); iterator_3.hasNext(); ) {
      Uber.Report report = (Uber.Report) iterator_3.next();
      assertTrue(report.timeStart.hour.longValue() <= report.timeEnd.hour.longValue());
    }
  }

  public void testTakeOnRide() {

    Uber u = new Uber();
    Client c = new Client("Barbara", 0L, 0L);
    Driver d = new Driver("Antonio", 40L, 0L, 0L);
    Admin a = new Admin("Admin");
    assertEqual(SetUtil.set(a), u.addAdmin(a));
    assertEqual(SetUtil.set(c), u.registerClient(c));
    assertEqual(SetUtil.set(d), u.registerDriver(a, d));
    u.requestRide(c);
    assertEqual(Uber.quotes.WaitingQuote.getInstance(), ((Object) c.getStatus()));
    u.acceptRide(d, c);
    assertEqual(Uber.quotes.ReachingClientQuote.getInstance(), ((Object) d.getStatus()));
    u.takeOnRide(d, new UberUtils.Location(1L, 1L, "Portugal"));
    assertEqual(Uber.quotes.ReadyQuote.getInstance(), ((Object) d.getStatus()));
    assertEqual(new UberUtils.Location(1L, 1L, "Portugal"), d.getLocation());
  }

  public void testCancelRide() {

    Uber u = new Uber();
    Client c = new Client("Barbara", 0L, 0L);
    Driver d = new Driver("Antonio", 40L, 0L, 0L);
    Admin a = new Admin("Admin");
    assertEqual(SetUtil.set(a), u.addAdmin(a));
    assertEqual(SetUtil.set(c), u.registerClient(c));
    assertEqual(SetUtil.set(d), u.registerDriver(a, d));
    u.requestRide(c);
    u.cancelRide(c);
    assertEqual(Uber.quotes.ReadyQuote.getInstance(), ((Object) c.getStatus()));
  }

  public void testAll() {

    testCreateDriver();
    testRemoveDriver();
    testCreateAccount();
    testRemoveClient();
    testGoOnRide();
    testTakeOnRide();
    testCancelRide();
  }

  public TestUber() {}

  public String toString() {

    return "TestUber{}";
  }
}
