package chatserver;

import java.lang.Override;
import java.lang.Runnable;
import java.lang.String;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.*;
import java.util.Stack;

/**
 * @author Simon Hewitt
 *         Manages the list of users
 *         For a simple use (as in this app), instantiate ONCE only
 *         (Although no reason why there should not be multiple lists)
 */

public class Users {
    private Map<String, User> userMap = new HashMap<String, User>();

    private static final int timeoutThreshold = 6;
    private static final int timeoutMillis = 5000;
    // Check every timeoutMillis milliseconds, and if no activity for timeoutThreshold ticks, delete the user
    // because user (client) Probably killed program or network disconnect

    public Users() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                checkUserTimeout();
            }
        });
        t.start();
    }

    public boolean addNewUser(String userName) {
        // returns false if username already registered, else adds user and returns True
        userName.replaceAll("\\s+", "");  // strip all whitespace

        if (userExists(userName)) return false;

        User newUser = new User(userName);
        userMap.put(userName, newUser);
        return true;
    }

    public boolean userExists(String userName) {
        userName.replaceAll("\\s+", "");  // strip all whitespace
        // returns True if this user exists
        return (userMap.get(userName) != null);
    }

    public User getUser(String userName) {
        // returns User object, or Null if not found
        userName.replaceAll("\\s+", "");  // strip all whitespace
        return userMap.get(userName);
    }

    public User getUser(byte [] userHash) {
        // returns User object, or Null if not found
        userName.replaceAll("\\s+", "");  // strip all whitespace
        return userMap.get(userName);
    }

    public synchronized boolean removeUser(String userName) {
        // returns True if found and removed
        userName.replaceAll("\\s+", "");  // strip all whitespace

        return (userMap.remove(userName) != null);  // remove returns removed object, or null if not present
    }

    public int userCount() {
        return userMap.size();
    }

    public synchronized List<String> listUserNames() {
        // returns a list of user names
        List<String> users = new ArrayList<String>();
        for (User u : userMap.values()) {
            users.add(u.getUserName());
        }
        return users;
    }

    public synchronized void addMessageToAll (String name, String mesg) {
        String newMesg = name + " : " + mesg;
        for (User u :userMap.values()) {
            u.addMessage (newMesg);
        }

    }

    public synchronized boolean addMessageToUser (String toName, String mesg) {
        User u = getUser(toName);
        if (u == null) return false;
        u.addMessage(mesg);
        return true;
    }

    private void checkUserTimeout() {
        // Note that all activity on the user resets tick to zero, such as getting or sending messages
        // so only deleted if no activity seen eg exited, network disconnect
        // Should be run as a thread
        while (true) {

            Iterator entries = userMap.entrySet().iterator();
            // Use this more complex for of iterator, as it allows delete within the loop
            // simpler iteratirs raise exception if element is deleted in the loop

            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String key = (String)entry.getKey();
                User u = (User)entry.getValue();
                if (u.tick() > timeoutThreshold) {
                    System.err.println("User : " + key + " timed out and deleted" + this.userCount() + " users connected");
                    entries.remove();
                }
            }

            try {
                Thread.sleep(timeoutMillis);
            } catch (InterruptedException e){
                System.err.println("Users:checkUserTimeout: Sleep interrupted");
            }
        }
    }

}