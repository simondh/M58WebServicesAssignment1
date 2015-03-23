
//package chatserver;

import javax.xml.ws.Endpoint;
import chatserver.ChatServer;

/**
 *
 * @author simon
 */


public class WebChatPublisher {
    public static void main(String[] args) {

        final String URL = "http://localhost:8080/wc";
        System.out.println("Publishing Web Chat service at endpoint  : " + URL);
        Endpoint.publish(URL, new ChatServer());
    }
}

