

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

@WebService(serviceName = "ChatServer")
public class ChatServer {

    private Users userList = new Users();  // all users stored in here

    private static final String systemName = "WebChat";
    private static final String configFileName  = "ChatServer.dat";
    private static final String defaultAdminPassword  = "EMERGENCYepidauros";
    private static String adminPassword;

    public ChatServer ()
    {
        // Constructor, just read adminPassword from ??
        try(BufferedReader br = new BufferedReader(new FileReader(configFileName))) {
            String line = br.readLine();
            adminPassword = line;
            System.out.println("Read configuration data OK ");
        } catch (IOException e) {
            System.err.println("Cannot read connfiguration  file : " + configFileName);
            // set adminPassword to default value
            adminPassword =defaultAdminPassword;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "logOn")
    public boolean logOn(@WebParam(name = "name") final String name) {
        // Add new user, returns 0 if OK or return -ve error if already signed on

        if (userList.addNewUser(name)) {
            User u = userList.getUser(name);
            u.addMessage("Welcome " + name);
            userList.addMessageToAll(systemName, name + " has joined WebChat");
            System.out.println("User : " + name + " logged on");
            return true;
        } else {
            System.err.println(name + " is already logged on elsewhere");
            return false;
        }
    }


    @WebMethod(operationName = "logOff")
    public boolean logOff(@WebParam(name = "name") final String name) {
        // removes user, returns true if removed OK (false if not found)
        System.out.println("User : " + name + " logging off");
        boolean result = userList.removeUser(name);
        userList.addMessageToAll(systemName, "User : " + name + " has left the chat room");
        return result;
    }

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

    @WebMethod(operationName = "addMessage")
    public void addMessage(@WebParam(name = "name") final String name,
                           @WebParam(name = "mesg") final String mesg) {
        // removes user, returns true if removed OK (false if not found)
        if (userList.userExists(name)) {
            userList.addMessageToAll(name, mesg);
        }

    }


    @WebMethod(operationName = "privateMessage")
    public void privateMessage(@WebParam(name = "fromName") final String fromName,
                           @WebParam(name = "toName") final String toName,
                           @WebParam(name = "mesg") final String mesg) {
        // removes user, returns true if removed OK (false if not found)
        System.out.println("Private message from : " + fromName + " to : " + toName + " Mesg : " + mesg);
        if (userList.userExists(toName)) {
            userList.addMessageToUser (toName, "Private message from : " + fromName + " : " +mesg );
            userList.addMessageToUser (fromName, "Private message sent to : " + toName );
        } else {
            userList.addMessageToUser (fromName, "Private message failed, user : " + toName + " does not exist" );
        }
    }

    @WebMethod(operationName = "adminSigOnOff")
    public void adminSigOnOff(@WebParam(name = "name") final String name,
                           @WebParam(name = "pwd") final String pwd) {
        // If pwd matches adminPassword, user becomes admin authorised
        // if pwd is "", unauthorise, else its an error
        if (userList.userExists(name)) {
            User u = userList.getUser(name);
            if (pwd.isEmpty()) {
                u.setAdminUser(false);
                u.addMessage(systemName + " : Signed OFF as Administrator");
            } else  if (pwd.equals(adminPassword)) {
                u.setAdminUser(true);
                u.addMessage(systemName + " : Signed ON as Administrator");
            } else {
                u.addMessage(systemName + " : Incorrect Admin password");
            }
        } // else user does not exist!

    } // adminSigonOff




}  // end class ChatServer


