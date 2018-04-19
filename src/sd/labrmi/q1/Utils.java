/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q1;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import org.json.JSONArray;
import org.json.JSONObject;
import static sd.labrmi.q1.MSServerManager.DELETE;
import static sd.labrmi.q1.MSServerManager.REPLACE;

/**
 *
 * @author dmitry
 */
public class Utils {

    public static void performActions(JSONObject op, JTextArea jTextArea) {
        JSONArray parameters = op.getJSONArray("pars");
        int code = op.getInt("code");
        
        switch (code) {

            case DELETE: {
                try {
                    jTextArea.getDocument().remove(parameters.getInt(0), parameters.getInt(1));
                } catch (BadLocationException ex) {
                    Logger.getLogger(MSServerManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case REPLACE: {
                try {
                    int length = parameters.getInt(1);
                    int offset = parameters.getInt(0);

                    if (length > 0) {
                        jTextArea.getDocument().remove(offset, length);
                    }

                    jTextArea.getDocument().insertString(offset, parameters.getString(2), null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(MSServerManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
        }
    }
}
