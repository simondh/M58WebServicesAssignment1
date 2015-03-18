
package client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AllMessages_QNAME = new QName("http://chatserver/", "allMessages");
    private final static QName _LogOff_QNAME = new QName("http://chatserver/", "logOff");
    private final static QName _PrivateMessage_QNAME = new QName("http://chatserver/", "privateMessage");
    private final static QName _AdminSignOnOffResponse_QNAME = new QName("http://chatserver/", "adminSignOnOffResponse");
    private final static QName _ListUsers_QNAME = new QName("http://chatserver/", "listUsers");
    private final static QName _ListUsersResponse_QNAME = new QName("http://chatserver/", "listUsersResponse");
    private final static QName _LogOnResponse_QNAME = new QName("http://chatserver/", "logOnResponse");
    private final static QName _AddMessageResponse_QNAME = new QName("http://chatserver/", "addMessageResponse");
    private final static QName _AddMessage_QNAME = new QName("http://chatserver/", "addMessage");
    private final static QName _LogOffResponse_QNAME = new QName("http://chatserver/", "logOffResponse");
    private final static QName _PrivateMessageResponse_QNAME = new QName("http://chatserver/", "privateMessageResponse");
    private final static QName _LogOn_QNAME = new QName("http://chatserver/", "logOn");
    private final static QName _AllMessagesResponse_QNAME = new QName("http://chatserver/", "allMessagesResponse");
    private final static QName _AdminSignOnOff_QNAME = new QName("http://chatserver/", "adminSignOnOff");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AllMessagesResponse }
     * 
     */
    public AllMessagesResponse createAllMessagesResponse() {
        return new AllMessagesResponse();
    }

    /**
     * Create an instance of {@link AdminSignOnOff }
     * 
     */
    public AdminSignOnOff createAdminSignOnOff() {
        return new AdminSignOnOff();
    }

    /**
     * Create an instance of {@link LogOn }
     * 
     */
    public LogOn createLogOn() {
        return new LogOn();
    }

    /**
     * Create an instance of {@link PrivateMessageResponse }
     * 
     */
    public PrivateMessageResponse createPrivateMessageResponse() {
        return new PrivateMessageResponse();
    }

    /**
     * Create an instance of {@link LogOffResponse }
     * 
     */
    public LogOffResponse createLogOffResponse() {
        return new LogOffResponse();
    }

    /**
     * Create an instance of {@link AddMessageResponse }
     * 
     */
    public AddMessageResponse createAddMessageResponse() {
        return new AddMessageResponse();
    }

    /**
     * Create an instance of {@link AddMessage }
     * 
     */
    public AddMessage createAddMessage() {
        return new AddMessage();
    }

    /**
     * Create an instance of {@link LogOnResponse }
     * 
     */
    public LogOnResponse createLogOnResponse() {
        return new LogOnResponse();
    }

    /**
     * Create an instance of {@link ListUsersResponse }
     * 
     */
    public ListUsersResponse createListUsersResponse() {
        return new ListUsersResponse();
    }

    /**
     * Create an instance of {@link AdminSignOnOffResponse }
     * 
     */
    public AdminSignOnOffResponse createAdminSignOnOffResponse() {
        return new AdminSignOnOffResponse();
    }

    /**
     * Create an instance of {@link ListUsers }
     * 
     */
    public ListUsers createListUsers() {
        return new ListUsers();
    }

    /**
     * Create an instance of {@link AllMessages }
     * 
     */
    public AllMessages createAllMessages() {
        return new AllMessages();
    }

    /**
     * Create an instance of {@link LogOff }
     * 
     */
    public LogOff createLogOff() {
        return new LogOff();
    }

    /**
     * Create an instance of {@link PrivateMessage }
     * 
     */
    public PrivateMessage createPrivateMessage() {
        return new PrivateMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllMessages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "allMessages")
    public JAXBElement<AllMessages> createAllMessages(AllMessages value) {
        return new JAXBElement<AllMessages>(_AllMessages_QNAME, AllMessages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogOff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "logOff")
    public JAXBElement<LogOff> createLogOff(LogOff value) {
        return new JAXBElement<LogOff>(_LogOff_QNAME, LogOff.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrivateMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "privateMessage")
    public JAXBElement<PrivateMessage> createPrivateMessage(PrivateMessage value) {
        return new JAXBElement<PrivateMessage>(_PrivateMessage_QNAME, PrivateMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdminSignOnOffResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "adminSignOnOffResponse")
    public JAXBElement<AdminSignOnOffResponse> createAdminSignOnOffResponse(AdminSignOnOffResponse value) {
        return new JAXBElement<AdminSignOnOffResponse>(_AdminSignOnOffResponse_QNAME, AdminSignOnOffResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "listUsers")
    public JAXBElement<ListUsers> createListUsers(ListUsers value) {
        return new JAXBElement<ListUsers>(_ListUsers_QNAME, ListUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "listUsersResponse")
    public JAXBElement<ListUsersResponse> createListUsersResponse(ListUsersResponse value) {
        return new JAXBElement<ListUsersResponse>(_ListUsersResponse_QNAME, ListUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogOnResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "logOnResponse")
    public JAXBElement<LogOnResponse> createLogOnResponse(LogOnResponse value) {
        return new JAXBElement<LogOnResponse>(_LogOnResponse_QNAME, LogOnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "addMessageResponse")
    public JAXBElement<AddMessageResponse> createAddMessageResponse(AddMessageResponse value) {
        return new JAXBElement<AddMessageResponse>(_AddMessageResponse_QNAME, AddMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "addMessage")
    public JAXBElement<AddMessage> createAddMessage(AddMessage value) {
        return new JAXBElement<AddMessage>(_AddMessage_QNAME, AddMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogOffResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "logOffResponse")
    public JAXBElement<LogOffResponse> createLogOffResponse(LogOffResponse value) {
        return new JAXBElement<LogOffResponse>(_LogOffResponse_QNAME, LogOffResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrivateMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "privateMessageResponse")
    public JAXBElement<PrivateMessageResponse> createPrivateMessageResponse(PrivateMessageResponse value) {
        return new JAXBElement<PrivateMessageResponse>(_PrivateMessageResponse_QNAME, PrivateMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogOn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "logOn")
    public JAXBElement<LogOn> createLogOn(LogOn value) {
        return new JAXBElement<LogOn>(_LogOn_QNAME, LogOn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllMessagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "allMessagesResponse")
    public JAXBElement<AllMessagesResponse> createAllMessagesResponse(AllMessagesResponse value) {
        return new JAXBElement<AllMessagesResponse>(_AllMessagesResponse_QNAME, AllMessagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdminSignOnOff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chatserver/", name = "adminSignOnOff")
    public JAXBElement<AdminSignOnOff> createAdminSignOnOff(AdminSignOnOff value) {
        return new JAXBElement<AdminSignOnOff>(_AdminSignOnOff_QNAME, AdminSignOnOff.class, null, value);
    }

}
