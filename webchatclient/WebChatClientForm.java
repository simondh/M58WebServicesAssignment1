/*
 * Copyright (C) 2015 simon
 * Simon Hewitt 806068
 *
 * The GUI is generated using the NetBeans GUI editor, otherwise all code is original.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package webchatclient;

import client.ChatServer;
import client.ChatServer_Service;

import javax.swing.*;
import java.lang.String;
import java.util.List;

/**
 * @author simon
 */
public class WebChatClientForm extends javax.swing.JFrame {

    private String signonName = "";
    private boolean signedOn = false;

    /**
     * Creates new form WebChatClientForm
     */
    public WebChatClientForm() {
        initComponents();
        this.setLocationRelativeTo(null);  // *** this will center your app ***
    }


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
            java.util.logging.Logger.getLogger(WebChatClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WebChatClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WebChatClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WebChatClientForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WebChatClientForm().setVisible(true);
            }
        });

    } // main


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameField = new javax.swing.JTextField();
        privateNameField = new javax.swing.JTextField();
        signOnButton = new javax.swing.JButton();
        privateChatButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        messageInputArea = new javax.swing.JScrollPane();
        messageInputTextArea = new javax.swing.JTextArea();
        privateChatTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        adminSignOnMenuItem = new javax.swing.JMenuItem();
        adminSignOffMenuItem = new javax.swing.JMenuItem();
        adminListNamesMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameField.setText("Name");
        nameField.setFocusCycleRoot(true);

        privateNameField.setToolTipText("Enter the name of a ChatRoom user to send a private message");

        signOnButton.setText("Sign On");
        signOnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOnButtonActionPerformed(evt);
            }
        });

        privateChatButton.setText("Private Chat");
        privateChatButton.setToolTipText("Send a private message");
        privateChatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                privateChatButtonActionPerformed(evt);
            }
        });

        statusLabel.setText("Logged Off");

        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        messageTextArea.setEnabled(false);
        jScrollPane1.setViewportView(messageTextArea);

        messageInputTextArea.setColumns(20);
        messageInputTextArea.setRows(5);
        messageInputTextArea.setToolTipText("Enter messages here");
        messageInputTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageInputTextAreaKeyPressed(evt);
            }
        });
        messageInputArea.setViewportView(messageInputTextArea);

        privateChatTextField.setToolTipText("Enter private text to send to one ChatRoom user");
 //       privateChatTextField.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                privateChatTextFieldActionPerformed(evt);
//            }  TODO: delete here
  //      });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Admin");

        adminSignOnMenuItem.setText("Admin Sign On");
        adminSignOnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminSignOnMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(adminSignOnMenuItem);

        adminSignOffMenuItem.setText("Admin Sign Off");
        jMenu2.add(adminSignOffMenuItem);

        adminListNamesMenuItem.setText("List Users");
        jMenu2.add(adminListNamesMenuItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(messageInputArea)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(privateChatTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(privateChatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(signOnButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(nameField)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(privateNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(signOnButton))
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(privateNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(privateChatButton))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(privateChatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(statusLabel)
                                                .addGap(12, 12, 12))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(messageInputArea, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
        );

        this.requestFocus();

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void signOnButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Sign on button, connect to chat server from here
        try {
            String chatName = nameField.getText();
            if (logon(chatName)) {
                signonName = chatName;
                signedOn = true;
                setStatusMessage("Signed on as : " + signonName);
                startMessageListener();
            } else {
                signonName = "";
                signedOn = false;
                writeMessage("Sign on FAILED (Username already in use)");
                setStatusMessage("Not logged on");
            }
        } catch (Exception e) {
            System.out.println("Error in Web Service");
        }

    }

    private void privateChatButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (!signedOn) {
            JOptionPane.showMessageDialog(null, "You must sign on first to send messages", "No Sign On",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String mesg = privateChatTextField.getText();
        String toName = privateNameField.getText();

        if ((mesg.isEmpty()) || (toName.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Enter private NAME and private TEXT", "Incomplete message",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        } else {
            if (toName.equals(signonName)){
                JOptionPane.showMessageDialog(null, "Don't be silly - no need to send private message to yourself!",
                        "Silly message", javax.swing.JOptionPane.WARNING_MESSAGE);
            } else {
                // send the private message
                sendPrivateMessage(toName, mesg);
            }
        }
    }

    private void adminSignOnMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminSignOnMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminSignOnMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        if (signedOn)
            logoff();

        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void messageInputTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageInputTextAreaKeyPressed
        // If the key is Enter, send the text to server

        if (evt.getKeyCode() == 10) { // this is enter
            if (!signedOn) {
                JOptionPane.showMessageDialog(null, "You must sign on first to send messages", "No Sign On",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            String[] tokens = messageInputTextArea.getText().split("\n");
            String mesg = tokens[tokens.length - 1];
            sendMessage(signonName, mesg);
            getMessages();
        }
    }//GEN-LAST:event_messageInputTextAreaKeyPressed


    public void writeMessage(String mesg) {
        messageTextArea.append(mesg + "\n");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // <editor-fold desc="Description">
    private javax.swing.JMenuItem adminListNamesMenuItem;
    private javax.swing.JMenuItem adminSignOffMenuItem;
    private javax.swing.JMenuItem adminSignOnMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane messageInputArea;
    private javax.swing.JTextArea messageInputTextArea;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton privateChatButton;
    private javax.swing.JTextField privateChatTextField;
    private javax.swing.JTextField privateNameField;
    private javax.swing.JButton signOnButton;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>


    private boolean logon(java.lang.String name) {
        boolean result;
        ChatServer_Service service = new ChatServer_Service();
        ChatServer port = service.getChatServerPort();
        result = port.logOn(name);
        getMessages();
        return result;
    }

    private boolean logoff() {
        if (!signedOn) return false;

        boolean result;
        ChatServer_Service service = new ChatServer_Service();
        ChatServer port = service.getChatServerPort();
        result = port.logOff(signonName);
        signedOn = false;
        signonName = "";
        return result;
    }

    private void sendMessage(String name, String mesg) {
        if (!signedOn) {
            JOptionPane.showMessageDialog(null, "You must sign on first to send messages", "No Sign On",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        ChatServer_Service service = new ChatServer_Service();
        ChatServer port = service.getChatServerPort();
        port.addMessage(name, mesg);
        getMessages();
    }

    private synchronized void getMessages() {
        // Synchronized as can be called from both background thread and also
        // from foreground actions, like sendMessage
        if (!signedOn) return;
        ChatServer_Service service = new ChatServer_Service();
        ChatServer port = service.getChatServerPort();
        List<String> allMesg = port.allMessages(signonName);

        for (String m : allMesg) {
            writeMessage(m);
        }
    }


    private void sendPrivateMessage(String toName, String mesg) {
        ChatServer_Service service = new ChatServer_Service();
        ChatServer port = service.getChatServerPort();
        port.privateMessage(signonName, toName, mesg);
    }

    private void messageListener() {
        while (signedOn) {
            getMessages();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("WebChatClientForm:messageListener: Sleep interrupted");
            }
        }
    }


    private void startMessageListener() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                messageListener();
            }
        });
        t.start();
    }

    private void setStatusMessage(String mesg)
    {
        // Updates status line in GUI
        statusLabel.setText(mesg);
    }
}
