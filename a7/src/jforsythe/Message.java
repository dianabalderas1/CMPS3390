package jforsythe;

import java.io.Serializable;

/**
 * Message object for sending all types of messages
 * @author Jason Forsythe
 * @version 1.0
 */
public class Message implements Serializable {
    private MessageType type;
    private String name;
    private String message;

    /**
     * Constructor of Message object
     * @param type MessageType lets everyone know the type of message being sent
     * @param name String representing the name of the client that sent the message
     * @param message String the message body or content
     */
    public Message(MessageType type, String name, String message) {
        this.type = type;
        this.name = name;
        this.message = message;
    }

    /**
     * Gets the type of the message
     * @return MessageType representing the type of the message
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Sets the type of the message (not used)
     * @param type MessageType representing the type of the message
     */
    public void setType(MessageType type) {
        this.type = type;
    }

    /**
     * Gets the name of the client sending the message
     * @return String representing the name of the client sending the message
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client sending the message
     * @param name String representing the name of the client sending the message
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the message body
     * @return String representing the message the client sent
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message body (not used)
     * @param message String representing the message body that the client sent
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Override of the toString method to print messages nicely on the server
     * @return String representing the message object
     */
    @Override
    public String toString(){
        return String.format("{Type: %-15s|Name: %-20s|Message: %-100s",
                this.getType(), this.getName(), this.getMessage());
    }

}
