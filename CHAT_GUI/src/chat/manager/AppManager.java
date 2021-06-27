package chat.manager;

import chat.Controller;
import entities.Details;
import service.IClientService;
import service.IServerService;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AppManager  extends UnicastRemoteObject implements IClientService, Serializable {

    private ArrayList<Details> clients;
    private final Details current;
    private IServerService server;
    private final Controller mainController;

    public AppManager(Controller controller, String name, String serverIP) throws RemoteException, MalformedURLException, UnknownHostException, ServerNotActiveException, NotBoundException {
        super();

        clients = new ArrayList<>();
        current = new Details();
        mainController = controller;

        init(name, serverIP);
    }

    private void init(String name, String serverIp) throws MalformedURLException, NotBoundException, RemoteException, ServerNotActiveException, UnknownHostException {
        server = (IServerService) Naming.lookup("rmi://" + serverIp + ":1099/Chat");
        String code = server.getUniqueCode();
        int port = server.getNumPort();
        current.setName(name);
        current.setCode(code);
        current.setPort(port);
        java.rmi.registry.LocateRegistry.createRegistry(port);

        String bindingLink = "//" +  InetAddress.getLocalHost().getHostName() + ":" + port + "/" + current.getName();
        Naming.rebind(bindingLink, this);
        server.connect(current);
    }

    @Override
    public void sendMsg(Details source, String msg) throws RemoteException {
        mainController.loadMsgBox(source.getName(), msg);
    }

    public void sendMessageOut(String msg){
        try {
            server.sendMsg(msg, current, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setClients(ArrayList<Details> clients) throws RemoteException {
        this.clients = clients;
        mainController.clearUsersList();
        for(Details client:  clients){
            mainController.addUser(client.getName());
        }
    }

    public IServerService getServer(){
        return server;
    }

    public void disconnect() {
        try {
            server.disconnect(current);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
