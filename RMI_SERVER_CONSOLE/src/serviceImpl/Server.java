package serviceImpl;

import entities.Client;
import entities.Details;
import service.IServerService;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;


public class Server extends UnicastRemoteObject implements IServerService {

    private final ArrayList<Client> clients;
    private int clientPortNumber = 1050;

    public Server() throws RemoteException {
        super();
        clients = new ArrayList<>();
    }

    @Override
    public void sendMsg(String msg, Details source, Details dist) throws RemoteException{

        for (Client client: clients) {
            client.sendMsg(source, msg);
        }
    }

    @Override
    public void connect(Details details) throws RemoteException, ServerNotActiveException, MalformedURLException, NotBoundException {
        Client client = new Client(details.getName(), getClientHost(), details.getPort(), details.getCode());
        clients.add(client);
        System.out.println("Client " + details.getName() + " joined the chat");

        // update clients list of remote clients
        for (Client item : clients) {
            item.updateClients(clients);
        }
    }

    @Override
    public void disconnect(Details details) throws RemoteException {
        clients.removeIf(client -> client.getDetails().getCode().equals(details.getCode()));
        System.out.println("Client " + details.getName() + " leaved the chat");
        // update clients list of remote clients
        for (Client item : clients) {
            item.updateClients(clients);
        }
    }

    @Override
    public String getUniqueCode() throws RemoteException {
        String code;
        code = UUID.randomUUID().toString();

        return code;
    }

    @Override
    public int getNumPort()  throws RemoteException{
        return clientPortNumber++;
    }

    private Client getClientFromList(String code){
        for (Client client : clients){
            if(client.getDetails().getCode().equals(code)) return client;
        }
        return null;
    }
}
