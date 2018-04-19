/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author lathe
 */
public class Server {
    
    public static int PORT = 8703;
    public static int REGISTRY_PORT = 1099;
    public static String HOST_ADDRESS = "10.42.0.1";
    
    public static void main(String[] args) throws RemoteException  {
        System.setProperty("java.rmi.server.hostname", HOST_ADDRESS);
        BankInterface bInterface = new BankManager();
        BankInterface stub = (BankInterface) UnicastRemoteObject.exportObject(bInterface, 0);
        Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);
        registry.rebind("BankManager", stub);
    }
}
