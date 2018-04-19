/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q2;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author dmitry
 */
public interface ClientInterface extends Remote {

    void notifyServerCallback(String message) throws RemoteException;
}
