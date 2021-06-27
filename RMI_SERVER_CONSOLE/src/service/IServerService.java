package service;

import entities.Details;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface IServerService extends Remote, Serializable {

    public void sendMsg(String msg, Details source, Details dist) throws RemoteException;
    public void connect(Details details) throws RemoteException, MalformedURLException, NotBoundException, ServerNotActiveException;
    public void disconnect(Details details) throws RemoteException;
    public String getUniqueCode() throws RemoteException;
    public int getNumPort() throws RemoteException;
}
