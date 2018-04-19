/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q1;

import java.rmi.*;

/**
 *
 * @author lathe
 */
public interface MSInterface extends Remote {

    public String startInteraction(int id) throws RemoteException;

    public String pullAndPushOperations(String jsonInput) throws RemoteException;

}
