package uber;

import org.overture.codegen.runtime.VDMSet;
import uber.quotes.ReachingClientQuote;
import uber.quotes.WaitingQuote;

import java.util.Iterator;

public class Main {

    private static Uber uber = new Uber();

    public static void main(String[] args) {
        uber.addAdmin(new Admin("admin"));
        Interface cli = new Interface();
        runProgram(cli);
        cli.closeScanner();
    }

    private static void runProgram(Interface cli) {
        int mainMenu = cli.runMainMenu();
        while (mainMenu != 0){
            switch(mainMenu){
                case 1: addClient(cli);
                    break;
                case 2: addDriver(cli);
                    break;
                case 3: removeClient(cli);
                    break;
                case 4: removeDriver(cli);
                    break;
                case 5: goOnRide(cli);
                    break;
                case 6: takeOnRide(cli);
                    break;
                default: break;
            }

            mainMenu = cli.runMainMenu();
        }
    }

    public static void addClient(Interface cli){
        Client c = cli.printAddClient();
        VDMSet clients = uber.registerClient(c);
        cli.printClients(clients);
    }

    public static void addDriver(Interface cli){
        Driver d = cli.printAddDriver();
        VDMSet drivers = uber.registerDriver(new Admin("admin"), d);
        cli.printDrivers(drivers);
    }

    public static void removeClient(Interface cli){
        Client c = cli.printRemoveClient(uber.clients);
        cli.printClients(uber.clients);
    }

    public static void removeDriver(Interface cli){
        Driver d = cli.printRemoveDriver(uber.drivers);
        cli.printDrivers(uber.drivers);
    }

    public static void goOnRide(Interface cli){
        Client c = cli.requestRide(uber.clients);
        uber.requestRide(c);

        int lat = cli.requestLatitude();
        int lon = cli.requestLongitude();
        UberUtils.Location loc = new UberUtils.Location(lat, lon, "Portugal");
        UberUtils.Timestamp t = cli.requestTime();
        Driver d = getReachingDriver();

        uber.goOnRide(c, loc, t, d);
    }

    private static Driver getReachingDriver() {
        Iterator it = uber.drivers.iterator();
        Driver d = null;
        while(it.hasNext()){
            d = (Driver) it.next();
            if (d.status instanceof ReachingClientQuote){
                return d;
            }
        }
        return null;
    }

    private static Client getAvailableClient(){
        Iterator it = uber.clients.iterator();
        Client c = null;
        while(it.hasNext()){
            c = (Client) it.next();
            if (c.status instanceof WaitingQuote){
                return c;
            }
        }
        return null;
    }

    public static void takeOnRide(Interface cli){
        Driver d = cli.takeOnRide(uber.drivers);
        Client c = getAvailableClient();
        uber.acceptRide(d, c);
        uber.takeOnRide(d, c.location);
    }

    public Uber getUber(){
        return uber;
    }



}
