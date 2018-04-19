/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import sd.labrmi.q2.model.Account;

/**
 *
 * @author lathe
 */
public class BankManager implements BankInterface {

    Account account = new Account();

    public enum RegisterResult {
        REGISTERED_SUCCESSFULY, ALREADY_REGISTERED, DATABASE_ERROR
    }

    public enum LoginResult {
        LOGGED_SUCCESSFULY, USER_NOT_FOUND, DATABASE_ERROR
    }

    @Override
    public LoginResult loginUser(String username, String password) throws RemoteException {
        // Código obtido de: https://stackoverflow.com/questions/4852531/find-files-in-a-folder-using-java
        File f = new File("./database/users/");
        File[] matchingFiles = f.listFiles((File dir, String name) -> name.startsWith(username) && name.endsWith("json"));

        if (matchingFiles.length != 0) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("database/users/" + username + ".json"));
                StringBuilder buffer = new StringBuilder();

                while (bufferedReader.ready()) {
                    buffer.append(bufferedReader.readLine());
                }

                bufferedReader.close();

                JSONObject jsonObject = new JSONObject(buffer.toString());

                if (jsonObject.getString("password").equals(password) && jsonObject.getString("username").equals(username)) {
                    return LoginResult.LOGGED_SUCCESSFULY;
                }

            } catch (IOException ex) {
                return LoginResult.DATABASE_ERROR;
            }
        }
        return LoginResult.USER_NOT_FOUND;
    }

    @Override
    public RegisterResult registerUser(String username, String password) throws RemoteException {
        // Código obtido de: https://stackoverflow.com/questions/4852531/find-files-in-a-folder-using-java
        File f = new File("./database");
        File[] matchingFiles = f.listFiles((File dir, String name) -> name.startsWith(username) && name.endsWith("json"));

        if (matchingFiles.length == 0) {
            try {
                FileWriter fileWriter = new FileWriter("database/users/" + username + ".json");
                JSONObject userJson = new JSONObject();
                userJson.put("username", username);
                userJson.put("password", password);
                fileWriter.write(userJson.toString());
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
                return RegisterResult.DATABASE_ERROR;
            }
        } else {
            return RegisterResult.ALREADY_REGISTERED;
        }

        return RegisterResult.REGISTERED_SUCCESSFULY;
    }

    @Override
    public void getSuccessMessage(ClientInterface callback) throws RemoteException {
        callback.notifyServerCallback("Successfully performed that shit");
    }

    @Override
    public String performOperation(String command) throws RemoteException {

        return proccessOperation(new JSONObject(command));

    }

    public synchronized String proccessOperation(JSONObject process) {
        switch (process.getInt("operation")) {
            case Account.AccountOperations.WITHDRAW: {
                float amount = process.getFloat("amount");
                if (account.withdraw(amount)) {

                    return (new JSONObject()
                            .put("operation", Account.AccountOperations.WITHDRAW)
                            .put("success", true)
                            .put("returnString", "Foram debitados R$ " + String.format("%.2f", amount) + " da conta.")
                            .toString());

                } else {

                    return (new JSONObject()
                            .put("operation", Account.AccountOperations.WITHDRAW)
                            .put("success", false)
                            .put("returnString", "Não foi possível debitar esse valor da conta!")
                            .toString());

                }
            }

            case Account.AccountOperations.PUT: {
                float amount = process.getFloat("amount");
                if (account.put(amount)) {
                    return (new JSONObject()
                            .put("operation", Account.AccountOperations.PUT)
                            .put("success", true)
                            .put("returnString", "Foram adicionados R$ " + String.format("%.2f", amount) + " à conta.")
                            .toString());

                } else {
                    return (new JSONObject()
                            .put("operation", Account.AccountOperations.PUT)
                            .put("success", false)
                            .put("returnString", "Não foi possível adicionar esse valor à conta!")
                            .toString());

                }

            }

            case Account.AccountOperations.GET: {

                return (new JSONObject()
                        .put("operation", Account.AccountOperations.GET)
                        .put("success", true)
                        .put("returnString", "O valor disponível na conta é R$ " + String.format("%.2f", account.getValue()))
                        .toString());

            }
        }

        return "";
    }
}
