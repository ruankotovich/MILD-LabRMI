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
 * @author lathe
 */
public interface BankInterface extends Remote {

    public BankManager.RegisterResult registerUser(String username, String password) throws RemoteException;

    public BankManager.LoginResult loginUser(String username, String password) throws RemoteException;

    public void getSuccessMessage(ClientInterface callback) throws RemoteException;

    public String performOperation(String command) throws RemoteException;

}
