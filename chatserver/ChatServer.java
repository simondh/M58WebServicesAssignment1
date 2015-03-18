package chatserver;

import chatserver.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.lang.Exception;
import java.lang.String;
import java.util.*;
import java.io.*;

/**
 * @author simon
 */

/*
* This class is the core chat server.
* It uses classes Users and User.
* User manages individual users,
* Users manages all Users as a group
 */

@WebService(serviceName = "ChatServer")
public class ChatServer {

    private Users userList = new Users();  // all users stored in here

    private static final String systemName = "WebChat";
    private static final String configFileName  = "ChatServer.dat";
    private static final String defaultAdminPassword  = "EMERGENCYepidauros";
    private static String adminPassword;

    public ChatServer ()
    {
        // Constructor, just read adminPassword
        try(BufferedReader br = new BufferedReader(new FileReader(configFileName))) {
            String line = br.readLine();
            adminPassword = line;
            System.out.println("Read configuration data OK ");
        } catch (IOException e) {
            System.err.println("Cannot read connfiguration  file : " + configFileName);
            // set adminPassword to default value
            System.err.println("WARNING using emergency Admin passsword");
            adminPassword =defaultAdminPassword;
        }
    }

    /**
     * Web service operations
     */

    /*
    * Log On - chec ks name is unique, log on and send Welcome if it is, and broadcasts the new user name to all
     */
    @WebMethod(operationName = "logOn")
    public boolean logOn(@WebParam(name = "name") final String name) {
        // Add new user, returns 0 if OK or return -ve error if already signed on

        if (userList.addNewUser(name)) {
            User u = userList.getUser(name);
            u.addMessage("Welcome " + name);
            userList.addMessageToAll(systemName, name + " has joined WebChat");
            System.out.println("User : " + name + " logged on" + userList.userCount() + " users connected");
            return true;
        } else {
            System.err.println(name + " is already logged on elsewhere");
            return false;
        }
    }

    /*
    * Log off
     */
    @WebMethod(operationName = "logOff")
    public boolean logOff(@WebParam(name = "name") final String name) {
        // removes user, returns true if removed OK (false if not found)
        System.out.println("User : " + name + " logging off, " + userList.userCount() + " users connected");
        boolean result = userList.removeUser(name);
        userList.addMessageToAll(systemName, "User : " + name + " has left the chat room");
        return result;
    }

    /*
    *Get all messages for the given user into an array of strings
     */
    @WebMethod(operationName = "allMessages")
    public List<String> allMessages(@WebParam(name = "name") final String name) {
        // removes user, returns true if removed OK (false if not found)
        User u = userList.getUser(name);
        if (u == null) {
            System.err.println("ERROR user : " + name + " not found");
            return null;
        }
        return (u.allMessages());
    }

    /*
    * Add a message from user to be broadcast
     */
    @WebMethod(operationName = "addMessage")
    public void addMessage(@WebParam(name = "name") final String name,
                           @WebParam(name = "mesg") final String mesg) {
        // removes user, returns true if removed OK (false if not found)
        if (userList.userExists(name)) {
            userList.addMessageToAll(name, mesg);
        }
    }


    /*
    * Send a private message - returns error if touser does not exist
     */
    @WebMethod(operationName = "privateMessage")
    public void privateMessage(@WebParam(name = "fromName") final String fromName,
                           @WebParam(name = "toName") final String toName,
                           @WebParam(name = "mesg") final String mesg) {
        // removes user, returns true if removed OK (false if not found)

        if (userList.userExists(toName)) {
            userList.addMessageToUser (toName, "Private message from : " + fromName + " : " +mesg );
            userList.addMessageToUser (fromName, "Private message sent to : " + toName );
        } else {
            userList.addMessageToUser (fromName, "Private message failed, user : " + toName + " does not exist" );
        }
    }

    /*
    * Sign ON or OFF as an Admin user
     */
    @WebMethod(operationName = "adminSignOnOff")
    public boolean adminSignOnOff(@WebParam(name = "name") final String name,
                           @WebParam(name = "pwd") final String pwd) {
        // If pwd matches adminPassword, user becomes admin authorised
        // if pwd is "", unauthorise, else its an error
        if (userList.userExists(name)) {
            User u = userList.getUser(name);
            if (pwd.isEmpty()) {
                u.setAdminUser(false);
                u.addMessage(systemName + " : Signed OFF as Administrator");
                return false;
            } else  if (pwd.equals(adminPassword)) {
                u.setAdminUser(true);
                u.addMessage(systemName + " : Signed ON as Administrator");
                return true;
            } else {
                u.addMessage(systemName + " : Incorrect Admin password");
            }
        } // else user does not exist!

        return false;

    } // adminSigonOff

    /*
    * If the sender is authorised, send all users as a privet message sequence
     */
    @WebMethod(operationName = "listUsers")
    public List<String> listUsers(@WebParam(name = "name") final String name) {
        // IF user is admin authorised, return a list of currently connected web chat usernames
        User u = userList.getUser(name);
        if (u == null) {
            System.err.println("ERROR user : " + name + " not found");
            return null;
        }
        if (!u.isAdminUser()) {
            u.addMessage(systemName + "You are not authorised as Admin");
            return null;
        }
        return userList.listUserNames(); // its that easy
    }


}  // end class ChatServer


