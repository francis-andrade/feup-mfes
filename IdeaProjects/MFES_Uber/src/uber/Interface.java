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
        name = s.next(new String());
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
        name = s.next(new String());
        Iterator it = clients.iterator();
        Client c = null;
        while(it.hasNext()){
            c = (Client) it.next();
            if (c.name.equals(name)){
                clients.remove(c);
            }
        }

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
        name = s.next(new String());
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
        Iterator it = drivers.iterator();
        Driver d = null;

        System.out.print("Write the driver's name: ");
        String name = s.next(new String());
        while(it.hasNext()){
            d = (Driver) it.next();
            if (d.name.equals(name)){
                drivers.remove(d);
            }
        }

        if (d != null)
            System.out.println("Driver " + d.name + " removed successfully.");
        else
            System.out.println("Driver " + d.name + " removed unsuccessfully.");

        return d;
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
}
