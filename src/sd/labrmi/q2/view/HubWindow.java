/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.labrmi.q2.view;

import java.awt.Color;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.json.JSONObject;
import sd.labrmi.q2.BankInterface;
import sd.labrmi.q2.ClientManager;
import sd.labrmi.q2.model.Account;
import sd.labrmi.q2.model.User;

/**
 *
 * @author mutoh
 */
public class HubWindow extends javax.swing.JFrame {

    /**
     * Creates new form HubWindow
     */
    public final User owner;
    public Registry registry;
    public BankInterface server;
    private static final Pattern PATTERN = Pattern.compile("^R\\$ \\d+((\\.|,)\\d{1,2})?$");
    
    ClientManager manager;
    
    public HubWindow(User ownerCalling, Registry registry, BankInterface server) {
        try {
            manager = new ClientManager(this);
        } catch (RemoteException ex) {
            Logger.getLogger(HubWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.owner = ownerCalling;
        this.registry = registry;
        this.server = server;
        
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.lblWelcome.setText(this.lblWelcome.getText().replace("$USERNAME", ownerCalling.getUsername()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWelcome = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPsaldo = new javax.swing.JPanel();
        jTsaldo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPdeposito = new javax.swing.JPanel();
        jTdepositoValor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jBdepositar = new javax.swing.JButton();
        jTmensagemDeposito = new javax.swing.JLabel();
        jPsaque = new javax.swing.JPanel();
        jTsaqueValor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jBsacar = new javax.swing.JButton();
        jTmensagemSaque = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblWelcome.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        lblWelcome.setText("Bem vindo ao BanCompartilhado, $USERNAME!");

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });

        jPsaldo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPsaldo.setForeground(new java.awt.Color(254, 254, 254));

        jTsaldo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jTsaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTsaldo.setText("Consultando o saldo...");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sd/labrmi/gf/flag-res.png"))); // NOI18N

        javax.swing.GroupLayout jPsaldoLayout = new javax.swing.GroupLayout(jPsaldo);
        jPsaldo.setLayout(jPsaldoLayout);
        jPsaldoLayout.setHorizontalGroup(
            jPsaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPsaldoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTsaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPsaldoLayout.setVerticalGroup(
            jPsaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPsaldoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPsaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPsaldoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTsaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Saldo", jPsaldo);

        jPdeposito.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPdeposito.setForeground(new java.awt.Color(254, 254, 254));

        jTdepositoValor.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jTdepositoValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTdepositoValor.setText("R$ ");
        jTdepositoValor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTdepositoValorPropertyChange(evt);
            }
        });
        jTdepositoValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTdepositoValorKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Valor do Depósito");

        jBdepositar.setText("Depositar");
        jBdepositar.setEnabled(false);
        jBdepositar.setFocusable(false);
        jBdepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBdepositarActionPerformed(evt);
            }
        });

        jTmensagemDeposito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPdepositoLayout = new javax.swing.GroupLayout(jPdeposito);
        jPdeposito.setLayout(jPdepositoLayout);
        jPdepositoLayout.setHorizontalGroup(
            jPdepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPdepositoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPdepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTmensagemDeposito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTdepositoValor, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addComponent(jBdepositar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPdepositoLayout.setVerticalGroup(
            jPdepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPdepositoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTdepositoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBdepositar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(jTmensagemDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Depósito", jPdeposito);

        jPsaque.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPsaque.setForeground(new java.awt.Color(254, 254, 254));

        jTsaqueValor.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jTsaqueValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTsaqueValor.setText("R$ ");
        jTsaqueValor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTsaqueValorPropertyChange(evt);
            }
        });
        jTsaqueValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTsaqueValorKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Valor do Saque");

        jBsacar.setText("Sacar");
        jBsacar.setEnabled(false);
        jBsacar.setFocusable(false);
        jBsacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsacarActionPerformed(evt);
            }
        });

        jTmensagemSaque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPsaqueLayout = new javax.swing.GroupLayout(jPsaque);
        jPsaque.setLayout(jPsaqueLayout);
        jPsaqueLayout.setHorizontalGroup(
            jPsaqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPsaqueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPsaqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTmensagemSaque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTsaqueValor, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addComponent(jBsacar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPsaqueLayout.setVerticalGroup(
            jPsaqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPsaqueLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTsaqueValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBsacar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(jTmensagemSaque, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Saque", jPsaque);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1FocusGained
    
    boolean validateMoney(String input) {
        return PATTERN.matcher(input).find();
    }
    
    // Código obtido no StackOverflow
    // https://stackoverflow.com/questions/47930177/how-to-round-to-2-decimals-in-java-with-floats
    public static float round(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++) {
            pow *= 10;
        }
        float tmp = number * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }
    
    float recoverMoney(String input) {
        return round(Float.parseFloat(input.replace("R$ ", "")), 2);
    }

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        
        if (jTabbedPane1.getSelectedComponent() == jPsaldo) {
            jTabbedPane1.setEnabled(false);
            jTsaldo.setText("Consultando o saldo...");
            try {
                manager.notifyServerCallback(server.performOperation(new JSONObject()
                        .put("operation", Account.AccountOperations.GET).toString()));
            } catch (RemoteException ex) {
                Logger.getLogger(HubWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (jTabbedPane1.getSelectedComponent() == jPdeposito) {
            jTdepositoValor.setText("R$ ");
            jTmensagemDeposito.setText("");
            jBdepositar.setEnabled(false);
            jTmensagemDeposito.setForeground(Color.BLACK);
        } else if (jTabbedPane1.getSelectedComponent() == jPsaque) {
            jTsaqueValor.setText("R$ ");
            jTmensagemSaque.setText("");
            jBsacar.setEnabled(false);
            jTmensagemSaque.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jTdepositoValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTdepositoValorKeyReleased
        if (validateMoney(jTdepositoValor.getText())) {
            jBdepositar.setEnabled(true);
        } else {
            jBdepositar.setEnabled(false);
        }
    }//GEN-LAST:event_jTdepositoValorKeyReleased

    private void jBdepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdepositarActionPerformed
        new Thread(() -> {
            try {
                manager.notifyServerCallback(server.performOperation(new JSONObject()
                        .put("operation", Account.AccountOperations.PUT)
                        .put("amount", recoverMoney(jTdepositoValor.getText()))
                        .toString()));
            } catch (RemoteException ex) {
                Logger.getLogger(HubWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }//GEN-LAST:event_jBdepositarActionPerformed

    private void jTdepositoValorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTdepositoValorPropertyChange
        if (validateMoney(jTdepositoValor.getText())) {
            jBdepositar.setEnabled(true);
        } else {
            jBdepositar.setEnabled(false);
        }
    }//GEN-LAST:event_jTdepositoValorPropertyChange

    private void jTsaqueValorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTsaqueValorPropertyChange
        if (validateMoney(jTdepositoValor.getText())) {
            jBdepositar.setEnabled(true);
        } else {
            jBdepositar.setEnabled(false);
        }
    }//GEN-LAST:event_jTsaqueValorPropertyChange

    private void jTsaqueValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTsaqueValorKeyReleased
        if (validateMoney(jTsaqueValor.getText())) {
            jBsacar.setEnabled(true);
        } else {
            jBsacar.setEnabled(false);
        }
    }//GEN-LAST:event_jTsaqueValorKeyReleased

    private void jBsacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsacarActionPerformed
        
        new Thread(() -> {
            try {
                manager.notifyServerCallback(server.performOperation(new JSONObject()
                        .put("operation", Account.AccountOperations.WITHDRAW)
                        .put("amount", recoverMoney(jTsaqueValor.getText()))
                        .toString()));
            } catch (RemoteException ex) {
                Logger.getLogger(HubWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

    }//GEN-LAST:event_jBsacarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HubWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HubWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HubWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HubWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HubWindow(new User("Admin", "123"), null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBdepositar;
    public javax.swing.JButton jBsacar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPdeposito;
    private javax.swing.JPanel jPsaldo;
    private javax.swing.JPanel jPsaque;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTextField jTdepositoValor;
    public javax.swing.JLabel jTmensagemDeposito;
    public javax.swing.JLabel jTmensagemSaque;
    public javax.swing.JLabel jTsaldo;
    public javax.swing.JTextField jTsaqueValor;
    private javax.swing.JLabel lblWelcome;
    // End of variables declaration//GEN-END:variables
}
