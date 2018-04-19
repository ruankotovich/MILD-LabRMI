/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q1;

import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lathe
 */
public class MSServerManager implements MSInterface {

    public static final int NOP = 0;
    public static final int REPLACE = 1;
    public static final int DELETE = 2;

    private final HashMap<Integer, Queue<JSONObject>> pendingOperations;
    private final JTextArea jTextArea = new JTextArea();

    DocumentFilter documentFilter = new DocumentFilter() {

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            super.replace(fb, offset, length, text, attrs);
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            super.insertString(fb, offset, string, attr);
        }

    };

    public MSServerManager() {
        this.pendingOperations = new HashMap<>();
        ((AbstractDocument) jTextArea.getDocument()).setDocumentFilter(documentFilter);
    }

    @Override
    public String startInteraction(int id) throws RemoteException {
        this.pendingOperations.put(id, new ArrayDeque<>());
        return jTextArea.getText();
    }

    @Override
    public String pullAndPushOperations(String jsonInput) throws RemoteException {
        return performOperations(new JSONObject(jsonInput));
    }

    public void emplaceOperation(JSONObject op, int id) throws Exception {
        pendingOperations.values().forEach((currentQueue) -> {
            currentQueue.add(op);
        });
        System.out.println("Text:" + jTextArea.getText());
        Utils.performActions(op, jTextArea);
    }

    public synchronized String performOperations(JSONObject object) {
        JSONObject operation = object.getJSONObject("operation");
        int userId = object.getInt("userId");

        if (operation.getInt("code") != NOP) {
            try {
                emplaceOperation(operation, userId);
                System.out.println("Performing operation from client " + userId);
            } catch (Exception ex) {
                return "[]";
            }
        }

        Queue<JSONObject> userPendingOperations = pendingOperations.get(userId);

        if (userPendingOperations != null) {
            JSONArray jsonArray = new JSONArray();
            userPendingOperations.forEach((curCommand) -> {
                jsonArray.put(curCommand);
            });

            userPendingOperations.clear();

            return jsonArray.toString();
        }

        return "[]";
    }

}
