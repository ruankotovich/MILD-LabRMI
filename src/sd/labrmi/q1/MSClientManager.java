/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q1;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lathe
 */
public class MSClientManager extends DocumentFilter {

    private final MSInterface server;
    private final JTextArea consumer;
    private final int id;
    private final String NOP_OPERATION;

    public MSClientManager(MSInterface server, int id, JTextArea consumer) {
        super();
        this.server = server;
        this.id = id;
        this.consumer = consumer;
        this.NOP_OPERATION = new JSONObject()
                .put("userId", id)
                .put("operation", new JSONObject()
                        .put("code", MSServerManager.NOP)
                )
                .toString();
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
            String text, AttributeSet attrs) throws BadLocationException {

        Object prop = fb.getDocument().getProperty("eroded");

        if (prop != null && ((Boolean) prop)) {
            System.out.println("Operation REPLACE already eroded");
            return;
        }

        fb.getDocument().putProperty("eroded", true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", id);

        JSONObject operation = new JSONObject();
        operation.put("code", MSServerManager.REPLACE);

        JSONArray pars = new JSONArray();
        pars.put(offset);
        pars.put(length);
        pars.put(text);

        operation.put("pars", pars);
        jsonObject.put("operation", operation);

        try {
            JSONArray response = new JSONArray(server.pullAndPushOperations(jsonObject.toString()));

            for (int i = 0; i < response.length(); ++i) {
                Utils.performActions(response.getJSONObject(i), consumer);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(MSClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        fb.getDocument().putProperty("eroded", false);
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset,
            String text, AttributeSet attr) throws BadLocationException {
        if (attr != null && attr.isDefined("polling")) {

            Object prop = fb.getDocument().getProperty("eroded");

            if (prop != null && ((Boolean) prop)) {
                System.out.println("Operation INSERT already eroded");
                super.insertString(fb, offset, text, attr);
                return;
            }

            fb.getDocument().putProperty("eroded", true);

            try {
                JSONArray response = new JSONArray(server.pullAndPushOperations(NOP_OPERATION));
                for (int i = 0; i < response.length(); ++i) {
                    Utils.performActions(response.getJSONObject(i), consumer);
                }

            } catch (RemoteException ex) {
                Logger.getLogger(MSClientManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            fb.getDocument().putProperty("eroded", false);

        } else {
            super.insertString(fb, offset, text, attr);
        }
    }

    @Override
    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Object prop = fb.getDocument().getProperty("eroded");

        if (prop != null && ((Boolean) prop)) {
            System.out.println("Operation REMOVE already eroded");
            super.remove(fb, offset, length);
            return;
        }

        fb.getDocument().putProperty("eroded", true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", id);

        JSONObject operation = new JSONObject();
        operation.put("code", MSServerManager.DELETE);

        JSONArray pars = new JSONArray();
        pars.put(offset);
        pars.put(length);

        operation.put("pars", pars);
        jsonObject.put("operation", operation);

        try {
            JSONArray response = new JSONArray(server.pullAndPushOperations(jsonObject.toString()));

            for (int i = 0; i < response.length(); ++i) {
                Utils.performActions(response.getJSONObject(i), consumer);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(MSClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        fb.getDocument().putProperty("eroded", false);
    }
}
