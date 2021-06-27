package manager;

import entities.Client;
import serviceImpl.Server;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientManager {

    private ArrayList<Client> clients;
    private ArrayList<String> usedCodes;
    private Server server;
    public ClientManager() throws RemoteException {
        super();
        this.server = new Server();
        clients = new ArrayList<>();
        usedCodes = new ArrayList<>();
    }

}
