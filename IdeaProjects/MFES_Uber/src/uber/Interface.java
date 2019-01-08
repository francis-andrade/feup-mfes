package uber;

import org.overture.codegen.runtime.VDMSet;

import java.util.Iterator;
import java.util.Scanner;

public class Interface {

    public Scanner s;

    public Interface(){
        s = new Scanner(System.in);
    }

    public int runMainMenu(){
        printMainMenu();
        return s.nextInt();
    }

    private void printMainMenu() {
        System.out.println("Welcome to Uber!");
        System.out.println("----------------------------------");
        System.out.println("  1. Add Clients");
        System.out.println("  2. Add Drivers");
        System.out.println("  3. Remove Clients");
        System.out.println("  4. Remove Drivers");
        System.out.println("  5. Go on a ride");
        System.out.println("  6. Take someone on a ride");
        System.out.println("  0. Exit\n");
        System.out.println("----------------------------------");
        System.out.print  ("Please choose your desired option:");

    }

    public Client printAddClient(){
        int lat, lon;
        String name;
        System.out.print("Write your name: ");
        name = s.next();
        System.out.print("Write your latitude: ");
        lat = s.nextInt();
        System.out.print("Write your longitude: ");
        lon = s.nextInt();
        System.out.println("Client added successfully.");
        return new Client(name, lat, lon);

    }

    public Client printRemoveClient(VDMSet clients){
        System.out.print("Write the client's name: ");
        String name;
        name = s.next();
        Client c = null;
        c = findClient(name, clients);
        clients.remove(c);
        if (c != null)
            System.out.println("Client " + c.name + " removed successfully.");
        else
            System.out.println("Client " + c.name + " removed unsuccessfully.");

        return c;
    }

    public Driver printAddDriver(){
        String name;
        int lat, lon, spd;
        System.out.print("Write the driver's name: ");
        name = s.next();
        System.out.print("Write the driver's latitude: ");
        lat = s.nextInt();
        System.out.print("Write the driver's longitude: ");
        lon = s.nextInt();
        System.out.print("Write the driver's speed: ");
        spd = s.nextInt();
        System.out.println("Driver added successfully.");
        return new Driver(name, spd, lat, lon);
    }

    public Driver printRemoveDriver(VDMSet drivers){


        System.out.print("Write the driver's name: ");
        String name = s.next();

        Driver d = findDriver(name, drivers);
        drivers.remove(d);

        if (d != null)
            System.out.println("Driver " + d.name + " removed successfully.");
        else
            System.out.println("Driver " + d.name + " removed unsuccessfully.");

        return d;
    }

    private Driver findDriver(String name, VDMSet drivers) {
        Driver d;
        Iterator it = drivers.iterator();
        while(it.hasNext()) {
            d = (Driver) it.next();
            if (d.name.equals(name)) {return d;
            }
        }
        return null;
    }


    public void closeScanner(){
        s.close();
    }

    public void printClients(VDMSet clients) {
        System.out.println("Clients set: " + clients.toString());
    }

    public void printDrivers(VDMSet drivers) {
        System.out.println("Drivers set: " + drivers.toString());
    }

    public Client requestRide(VDMSet clients) {
        System.out.print("Username (b to go back): ");
        String name = s.next();
        return findClient(name, clients);
    }

    public Driver takeOnRide(VDMSet drivers) {
        System.out.print("Username (b to go back): ");
        String name = s.next();
        return findDriver(name, drivers);
    }

    public Client findClient(String name, VDMSet clients){
        Iterator it = clients.iterator();
        Client c;
        while(it.hasNext()){
            c = (Client) it.next();
            if (c.name.equals(name)){
                return c;
            }
        }
        return null;
    }

    public int requestLatitude() {
        System.out.print("Enter destination latitude (b to go back): ");
        return s.nextInt();
    }

    public int requestLongitude() {
        System.out.print("Enter destination longitude (b to go back): ");
        int res =  s.nextInt();
        System.out.print("Enter destination country (b to go back): ");
        s.next();
        return res;
    }

    public UberUtils.Timestamp requestTime() {
        int hour, minute, second;
        System.out.print("Enter hour: ");
        hour = s.nextInt();
        System.out.print("Enter minute: ");
        minute = s.nextInt();
        System.out.print("Enter second: ");
        second = s.nextInt();
        return new UberUtils.Timestamp(hour, minute, second);
    }
}
