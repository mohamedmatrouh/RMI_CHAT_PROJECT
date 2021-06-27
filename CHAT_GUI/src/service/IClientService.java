package service;

import entities.Details;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClientService extends Remote{
    public void sendMsg(Details source, String msg) throws RemoteException;
    public void setClients(ArrayList<Details> clients) throws RemoteException;
}
