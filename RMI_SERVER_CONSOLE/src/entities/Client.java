package entities;

import service.IClientService;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client implements Serializable, Comparable<Client> {

    private Details details;
    private IClientService remoteClient;

    public Client(String name,String address, int port, String code) throws MalformedURLException, NotBoundException, RemoteException {
        this.details = new Details();
        this.details.setName(name);
        this.details.setAddress(address);
        this.details.setCode(code);
        this.details.setPort(port);

        String link = "rmi://" + address + ":" + port + "/" + name;
        remoteClient = (IClientService) Naming.lookup(link);
    }

    public void sendMsg(Details source, String msg) throws RemoteException {
        remoteClient.sendMsg(source, msg);
    }

    public void updateClients(ArrayList<Client> clients) throws RemoteException {
        ArrayList<Details> clientsDetails = new ArrayList<>();
        for (Client item : clients) {
            clientsDetails.add(item.getDetails());
        }
        remoteClient.setClients(clientsDetails);
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    @Override
    public int compareTo(Client client) {
        return this.details.getCode().compareTo(client.getDetails().getCode());
    }
}
