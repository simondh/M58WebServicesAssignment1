package chatserver;

import java.lang.String;
import java.util.ArrayList;
import java.util.*;

/**
 * @author Simon Hewitt
 *         Stores both user details and also user messages
 */

public class User {
    private String userName;
    private boolean adminUser;
    private List<String> messageList = new ArrayList<String>();
    private int timeoutCount;  // if this reaches a threshold, user must be disconnected, delete

    User(String name) {
        name.replaceAll("\\s+", "");  // strip all whitespace
        userName = name;
        adminUser = false;
        timeoutCount = 0;
    }

    public String getUserName() {
        timeoutCount = 0;
        return userName;
    }

    public boolean isAdminUser() {
        timeoutCount = 0;
        return adminUser;
    }

    public void setAdminUser(boolean admin) {
        timeoutCount = 0;
        System.out.println("Set admin user : " + admin);
        adminUser = admin;
    }

    public void addMessage(String mesg) {
        timeoutCount = 0;
        messageList.add(mesg);
    }

    public String popMessage() {
        // returns NULL if no mesages on queue
        timeoutCount = 0;
        if (messageList.size() == 0) return null;
        String nextMessage = messageList.get(0);
        messageList.remove(0);
        return nextMessage;
    }

    public List<String> allMessages() {
        List<String> messages = new ArrayList<String>();
        timeoutCount = 0;

        for (int i = 0; i < messageList.size(); i++) {
            messages.add(messageList.get(i));
            messageList.remove(i);
        }
        return messages;
    }

    public int qLength() {
        timeoutCount = 0;
        return messageList.size();
    }

    public int tick () {return ++timeoutCount;}
}