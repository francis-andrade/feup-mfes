package uber;

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
        uber.registerClient(c);
        cli.printClients(uber.clients);

    }

    public static void addDriver(Interface cli){
        Driver d = cli.printAddDriver();
        uber.registerDriver(new Admin("admin"), d);
        cli.printDrivers(uber.drivers);
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

    }

    public static void takeOnRide(Interface cli){

    }

    public Uber getUber(){
        return uber;
    }



}
