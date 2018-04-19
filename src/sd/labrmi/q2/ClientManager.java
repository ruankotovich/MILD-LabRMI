/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q2;

import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import sd.labrmi.q2.model.Account;
import sd.labrmi.q2.view.HubWindow;

/**
 *
 * @author dmitry
 */
public class ClientManager extends UnicastRemoteObject implements ClientInterface {

    private final HubWindow caller;

    public ClientManager(HubWindow calling) throws RemoteException {
        super();
        this.caller = calling;
    }

    @Override
    public void notifyServerCallback(String message) throws RemoteException {
        JSONObject jsonObject = new JSONObject(message);
        switch (jsonObject.getInt("operation")) {
            case Account.AccountOperations.WITHDRAW: {
                caller.jTsaqueValor.setText("R$ ");
                caller.jTmensagemSaque.setText("");
                caller.jBsacar.setEnabled(false);

                if (jsonObject.getBoolean("success")) {
                    caller.jTmensagemSaque.setForeground(Color.BLUE);
                } else {
                    caller.jTmensagemSaque.setForeground(Color.RED);
                }

                caller.jTmensagemSaque.setText(jsonObject.getString("returnString"));
            }
            break;
            case Account.AccountOperations.PUT: {
                caller.jTdepositoValor.setText("R$ ");
                caller.jTmensagemDeposito.setText("");
                caller.jBdepositar.setEnabled(false);

                if (jsonObject.getBoolean("success")) {
                    caller.jTmensagemDeposito.setForeground(Color.BLUE);
                } else {
                    caller.jTmensagemDeposito.setForeground(Color.RED);
                }
                caller.jTmensagemDeposito.setText(jsonObject.getString("returnString"));

            }
            break;
            case Account.AccountOperations.GET: {
                caller.jTsaldo.setText(jsonObject.getString("returnString"));
            }
            break;
        }

        caller.jTabbedPane1.setEnabled(true);
    }

}
