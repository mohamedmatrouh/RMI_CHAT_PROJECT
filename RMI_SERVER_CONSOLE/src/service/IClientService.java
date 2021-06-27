package service;

import entities.Details;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClientService extends Remote, Serializable {
    void sendMsg(Details source, String msg) throws RemoteException;
    void setClients(ArrayList<Details> clients) throws RemoteException;
}
