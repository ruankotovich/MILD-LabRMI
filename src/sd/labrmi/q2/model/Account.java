/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q2.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author dmitry
 */
public class Account {

    public static final class AccountOperations {

        public static final int WITHDRAW = 1;
        public static final int PUT = 2;
        public static final int GET = 3;

    };

    float value;

    public Account() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("database/account.json"));
            StringBuilder stringBuffer = new StringBuilder();
            while (bufferedReader.ready()) {
                stringBuffer.append(bufferedReader.readLine());
            }
            bufferedReader.close();

            JSONObject jsonAccount = new JSONObject(stringBuffer.toString());
            this.value = jsonAccount.getFloat("value");

            new Thread(() -> {
                File file = new File("database/account.json");
                JSONObject innerAccount = new JSONObject(jsonAccount);

                while (true) {
                    try {
                        file.delete();
                        PrintWriter printWriter = new PrintWriter(file);
                        printWriter.write(innerAccount.put("value", value).toString());
                        printWriter.close();
                        System.out.println("Updated account file");
                        Thread.sleep(10000);

                    } catch (InterruptedException | FileNotFoundException ex) {
                        Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean withdraw(float value) {
        if (this.value >= value && value > 5) {
            this.value -= value;
            return true;
        }
        return false;
    }

    public float getValue() {
        return value;
    }

    public boolean put(float value) {
        if (value > 0) {
            this.value += value;
            return true;
        }
        return false;
    }
}
