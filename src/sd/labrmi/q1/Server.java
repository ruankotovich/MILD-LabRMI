/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q1;

import java.rmi.*;
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
        MSInterface msInterface = new MSServerManager();
        MSInterface stub = (MSInterface) UnicastRemoteObject.exportObject(msInterface, 0);
        Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);
        registry.rebind("MagicScreenManager", stub);
    }
}
