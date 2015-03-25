package chatserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

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
    private Timer exitTimer = new Timer();

    public ChatServer ()
    {
        // Constructor, just read adminPassword
        try(BufferedReader br = new BufferedReader(new FileReader(configFileName))) {
            String line = br.readLine();
            adminPassword = line;
            System.out.println("ChatServer:ChatServer Read configuration data OK ");
        } catch (IOException e) {
            System.err.println("ChatServer:ChatServer Cannot read connfiguration  file : " + configFileName);
            // set adminPassword to default value
            System.err.println("ChatServer:ChatServer WARNING using emergency Admin passsword");
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
    public String logOn(@WebParam(name = "name") final String name) {
        // Add new user, returns 0 if OK or return -ve error if already signed on

        String cleanName = Users.cleanName(name);
        if (userList.addNewUser(cleanName)) {
            User u = userList.getUser(cleanName);
            u.addMessage("Welcome " + cleanName);
            userList.addMessageToAll(systemName, cleanName + " has joined WebChat");
            System.out.println("ChatServer:logOn User : " + cleanName + " logged on, " + userList.userCount() + " users connected");
            return cleanName;
        } else {
            System.err.println(cleanName + " is already logged on elsewhere (ChatServer:logOn)");
            return null;
        }
    }

    /*
    * Log off
     */
    @WebMethod(operationName = "logOff")
    public boolean logOff(@WebParam(name = "name") final String name) {
        // removes user, returns true if removed OK (false if not found)
        String cleanName = Users.cleanName(name);
        boolean result = userList.removeUser(cleanName);
        System.out.println("ChatServer:logOff User : " + cleanName + " logging off, " + userList.userCount() + " users connected");
        userList.addMessageToAll(systemName, "User : " + cleanName + " has left the chat room");
        return result;
    }

    /*
    *Get all messages for the given user into an array of strings
     */
    @WebMethod(operationName = "allMessages")
    public List<String> allMessages(@WebParam(name = "name") final String name) {
        // removes user, returns true if removed OK (false if not found)
        String cleanName = Users.cleanName(name);
        User u = userList.getUser(cleanName);
        if (u == null) {
            System.err.println("ChatServer:allMessages ERROR user : " + cleanName + " not found");
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
        String cleanName = Users.cleanName(name);
        if (userList.userExists(cleanName)) {
            userList.addMessageToAll(cleanName, mesg);
        }
    }


    /*
    * Send a private message - returns error if touser does not exist
     */
    @WebMethod(operationName = "privateMessage")
    public void privateMessage(@WebParam(name = "fromName") final String fromNameIN,
                           @WebParam(name = "toName") final String toNameIN,
                           @WebParam(name = "mesg") final String mesg) {
        // removes user, returns true if removed OK (false if not found)
        String fromName = Users.cleanName(fromNameIN);
        String toName = Users.cleanName(toNameIN);
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
    public boolean adminSignOnOff(@WebParam(name = "name") final String nameIN,
                           @WebParam(name = "pwd") final String pwd) {
        // If pwd matches adminPassword, user becomes admin authorised
        // if pwd is "", unauthorise, else its an error
        String name = Users.cleanName(nameIN);
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
    public List<String> listUsers(@WebParam(name = "name") final String nameIN) {
        // IF user is admin authorised, return a list of currently connected web chat usernames
        String name = Users.cleanName(nameIN);
        User u = userList.getUser(name);
        if (u == null) {
            System.err.println("ChatServer:listUsers ERROR user : " + name + " not found");
            return null;
        }
        if (!u.isAdminUser()) {
            u.addMessage(systemName + "You are not authorised as Admin");
            return null;
        }
        return userList.listUserNames(); // its that easy
    }

      /*
    * If the sender is authorised, shuts down the serever
     */
    @WebMethod(operationName = "shutServer")
    public void shutServer (@WebParam(name = "name") final String nameIN) {
        // IF user is admin authorised, return a list of currently connected web chat usernames
        String name = Users.cleanName(nameIN);
        User u = userList.getUser(name);
        if (u == null) {
            System.err.println("ChatServer:shutServer ERROR user : " + name + " not found");
            return ;
        }
        if (!u.isAdminUser()) {
            System.err.println("ChatServer:shutServer ERROR user : " + name + " not authorised");
            u.addMessage(systemName + "You are not authorised as Admin");
            return ;
        }
        // start shutdown sequence
        this.addMessage("SYSTEM", "Server is shutting down in 10 seconds");
        System.err.println("ChatServer:shutServer SHUTTING DOWN IN 10 SECONDS");

        exitTimer.schedule(exitApp, new Date(System.currentTimeMillis()+10*1000));

    }

    
    private TimerTask exitApp = new TimerTask() {
    public void run() {
        System.err.println("ChatServer:shutServer SHUTTING DOWN NOW");
        System.exit(0);
        }
    };
}  // end class ChatServer


