/*
 * Copyright (C) 2015 simon
 * Simon Hewitt 806068
 *
 * The GUI is generated using the NetBeans GUI editor, otherwise all code is original.
 * The code is large, as there are a lot of GUI variables and a lot of generated code to create teh GUI
 */

package webchatclient;

import client.ChatServer;
import client.ChatServer_Service;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * @author simon hewitt 806068
 */
public class WebChatClientForm extends javax.swing.JFrame {

    private String signonName = "";   // Name used to connect to chat Server
    private boolean signedOn = false; // Signed on?
    private boolean admin = false;    // signed on as Admin ?

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem adminListNamesMenuItem;
    private javax.swing.JMenuItem adminSignOffMenuItem;
    private javax.swing.JMenuItem adminSignOnMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane messageInputArea;
    private javax.swing.JTextArea messageInputTextArea;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton privateChatButton;
    private javax.swing.JTextField privateChatTextField;
    private javax.swing.JTextField privateNameField;
    private javax.swing.JMenuItem shutServerMenuItem;
    private javax.swing.JButton signOnButton;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>


    /**
     * Constructor
     * Creates new form WebChatClientForm
     */
    public WebChatClientForm() {
        System.out.println("Starting");
        initComponents();
        manualInitComponents();  // NetBeans regenerates initComponents completely,
        // so any local adjustments must go here
        this.setLocationRelativeTo(null);  // Place window in screen centre
    }


    /**
     * MAIN. All generated by NetBeans
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WebChatClientForm().setVisible(true);
            }
        });

    } // main


    private void manualInitComponents() {
        // Any manual adjustements to Swing GUI components
        // as NetBeans completely regerenates initComponents

        messageTextArea.setForeground(Color.BLACK);
        adminListNamesMenuItem.setEnabled(false);
        adminSignOffMenuItem.setEnabled(false);
        adminSignOnMenuItem.setEnabled(false);
        shutServerMenuItem.setEnabled(false);
        
    }

    /*
    * Called when sign-on button pressed. Basically calls web service to sign on,
    * Enables and changes menu and buttons if OK
     */
    private void signOnButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Sign on button, connect to chat server from here
        if (!signedOn) {
            try {
                setAdminEnabled(false, false);
                String chatName = nameField.getText();
                if (logon(chatName)) {
                    signonName = chatName;
                    signedOn = true;
                    setStatusMessage("Signed on as : " + signonName);
                    adminSignOnMenuItem.setEnabled(true);
                    startMessageListener();
                    signOnButton.setText("Sign Off");
                    getMessages();
                } else {
                    signonName = "";
                    signedOn = false;
                    writeMessage("Sign on FAILED (Username already in use)");
                    setStatusMessage("Not logged on");
                    adminSignOnMenuItem.setEnabled(false);
                }
            } catch (Exception e) {
                System.out.println("Error in Web Service");
                e.printStackTrace();
                //throw new OtherException(e);  // stack trace and cause text
            }
        } else logoff();

    }

    /*
    * Send a private message provided name and message text are supplied.
     */
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
            if (toName.equals(signonName)) {
                JOptionPane.showMessageDialog(null, "Don't be silly - no need to send private message to yourself!",
                        "Silly message", javax.swing.JOptionPane.WARNING_MESSAGE);
            } else {
                // send the private message
                sendPrivateMessage(toName, mesg);
            }
        }
    }

    /*
    * Sets the Admin menu enabled state according to signed on or not
     */
    private void setAdminEnabled(boolean onOff, boolean updateStatus) {
        adminListNamesMenuItem.setEnabled(onOff);
        adminSignOffMenuItem.setEnabled(onOff);
        adminSignOnMenuItem.setEnabled(!onOff);
        shutServerMenuItem.setEnabled(onOff);

        admin = onOff;
        if (updateStatus) {
            if (onOff) {
                setStatusMessage("Signed on as ADMIN");
            } else {
                setStatusMessage("Signed OFF as ADMIN");
            }
        }
    }


    /*
    * Processes Admin sign-on.
    * Must be signed on as normal user first
    * Then displays an option pane to get the password, calls the
    * relevant web service,\
    * and sets flags and menu state if response is OK
     */
    private void adminSignOnMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (!signedOn) {
            JOptionPane.showMessageDialog(null, "You must sign on first", "Signon",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String adminPwd;
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Admin password:");
        JPasswordField pass = new JPasswordField(20);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "Admin User",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);
        if(option == 0) // OKJ pressed
        {
            adminPwd = new String (pass.getPassword());
        } else {
            return;
        }

        if (adminPwd.length() == 0) return;

        if (callAdminSignOnOff(adminPwd)) {
            setAdminEnabled(true, true);
        } else {
            setAdminEnabled(false, false);
            setStatusMessage("Admin password INCORRECT");
        }
    }// adminSignOnMenuItemActionPerformed

    /*
    * Admin sign off
     */
    private void adminSignOffMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        callAdminSignOnOff("");  // sending "" cacels Admin
        setAdminEnabled(false, true);
    }// adminSignOffMenuItemActionPerformed

    /*
    Exit menu item pressed, logoff and exit
     */
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (signedOn)
            logoff();

        System.exit(0);
    }

    /*
    * Called for every key in message input area
    * If its <enter> (and we are signed on), send the mesage to teh chat server
     */
    private void messageInputTextAreaKeyPressed(java.awt.event.KeyEvent evt) {
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
    }

    /*
    * Admin menu item <list users> pressed,
    * simple action that fetches the list and displays each line
     */
    private void adminListNamesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//adminListNamesMenuItemActionPerformed
        ChatServer port = getChatServerPort();
        List<String> allUsers = new ArrayList<String>();
        allUsers = port.listUsers(signonName);
        for (String n : allUsers) {
            writeMessage("Web Chat User : " + n);
        }
        getMessages();
    }
  
    /*
    * Displays a message in the incoming message area
     */
    public void writeMessage(String mesg) {
        messageTextArea.append(mesg + "\n");
    }

    /*
    * Get the port for the web service. A utility method
     */
    private ChatServer getChatServerPort() {
        ChatServer port = null;
        try {
            ChatServer_Service service = new ChatServer_Service();
            port = service.getChatServerPort();
        } catch (Exception e) {
            System.err.println("WebChatClientForm:getChatServerPort error : " + e.getCause());
        }
        return port;
    }

    /*
    * logon web service
     */
    private boolean logon(java.lang.String name) {

        return  (getChatServerPort().logOn(name) != null);

    }

    /*
    * logoff web service
     */
    private boolean logoff() {
        if (!signedOn) return false;

        boolean result = getChatServerPort().logOff(signonName);
        signedOn = false;
        signonName = "";
        signOnButton.setText("Sign On");
        setStatusMessage("Signed Off");
        setAdminEnabled(false, false);
        adminSignOnMenuItem.setEnabled(false);

        return result;
    }

    /*
    * Broadcast web service (send mesage to all clients)
     */
    private void sendMessage(String name, String mesg) {
        if (!signedOn) {
            JOptionPane.showMessageDialog(null, "You must sign on first to send messages", "No Sign On",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        getChatServerPort().addMessage(name, mesg);
        getMessages();
    }

    /*
    * Get messages from the web chat server
     */
    private synchronized void getMessages() {
        // Synchronized as can be called from both background thread and also
        // from foreground actions, like sendMessage
        if (!signedOn) return;
        List<String> allMesg = getChatServerPort().allMessages(signonName);

        for (String m : allMesg) {
            writeMessage(m);
        }
    }


    /*
    * Send a private message to one named user
     */
    private void sendPrivateMessage(String toName, String mesg) {
        getChatServerPort().privateMessage(signonName, toName, mesg);
        getMessages();
    }

    /*
    * If a string is specified, attempt to sign on as Admin.
    * If blank "", sign-off
     */
    private boolean callAdminSignOnOff(String pwd) {

        return (getChatServerPort().adminSignOnOff(signonName, pwd));
    }

    /*
    * This is run as a separate thread.
    * It wakes up poeriodiacally to check for messages,
    * until no longer signed in
     */
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

    /*
    * Start the thread to run messageListener
     */
    private void startMessageListener() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                messageListener();
            }
        });
        t.start();
    }

    /*
    * Displays the status message (read-only)
     */
    private void setStatusMessage(String mesg) {
        // Updates status line in GUI
        statusLabel.setText(mesg);
    }


/*
* FROM HERE TO THE END OF FILE IS AUTO-GENERATED GUI CODE
* CREATED BY NetBeans
* (although it was me who layed out the GUI!)
 */
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        adminSignOnMenuItem = new javax.swing.JMenuItem();
        adminSignOffMenuItem = new javax.swing.JMenuItem();
        adminListNamesMenuItem = new javax.swing.JMenuItem();
        shutServerMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameField.setText("Name");
        nameField.setFocusCycleRoot(true);
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

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

        statusLabel.setText("Status");

        messageTextArea.setEditable(false);
        messageTextArea.setColumns(20);
        messageTextArea.setLineWrap(true);
        messageTextArea.setRows(5);
        messageTextArea.setToolTipText("Web Chat Messages appear here");
        messageTextArea.setWrapStyleWord(true);
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
//        privateChatTextField.addAncestorListener(new javax.swing.event.AncestorListener() {
//            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
//            }
//            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
//                privateChatTextFieldAncestorAdded(evt);
//            }
//            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
//            }
//        });
//        privateChatTextField.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                privateChatTextFieldActionPerformed(evt);
//            }
//        });

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
        adminSignOffMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminSignOffMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(adminSignOffMenuItem);

        adminListNamesMenuItem.setText("List Users");
        adminListNamesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminListNamesMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(adminListNamesMenuItem);

        shutServerMenuItem.setText("shut Server");
        shutServerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutServerMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(shutServerMenuItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(privateChatTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(privateChatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(signOnButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(privateNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                    .addComponent(nameField))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(messageInputArea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signOnButton))
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

    private void shutServerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutServerMenuItemActionPerformed
        if (admin) {
        ChatServer port = getChatServerPort();
        port.shutServer(signonName);
        getMessages();
        }
        
    }//GEN-LAST:event_shutServerMenuItemActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed


} // end class WebChatClientForm
