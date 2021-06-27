import service.IServerService;
import serviceImpl.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            Server server = new Server();
            Naming.rebind("Chat", server);
            System.out.println("server started");
            while (true){
                Thread.sleep(10000);
            }
        } catch (RemoteException | MalformedURLException | InterruptedException e) {
            System.out.println(e);
        }
    }
}
