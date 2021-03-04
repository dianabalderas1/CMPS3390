package dbalderas1.a7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import jforsythe.Message;
import jforsythe.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Controller allows the server to connect to the user's information
 * @author Diana Balderas
 * @version 1.0
 */
public class Controller {
    @FXML
    TextField txtInput;

    @FXML
    TextArea txtOutput;

    private String name;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;

    /**
     * Initialize constructor allows access to connect to the different
     * functions in the server
     * @throws IOException can cause an IO exception if port is not available
     */
    public void initialize() throws IOException {
        TextInputDialog nameInput = new TextInputDialog("What is your name?");
        nameInput.setHeaderText("Welcome to CMPS3390 Chat");
        nameInput.showAndWait();
        name = nameInput.getResult();

        socket = new Socket("odin.cs.csub.edu", 3390);
        outputStream = socket.getOutputStream();
        outputStream.flush();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.flush();

        ServerListener serverListener = new ServerListener(this.socket, this);
        serverListener.start();

        Message tmp = new Message(MessageType.CONNECT, name, "Hello");
        objectOutputStream.writeObject(tmp);
        objectOutputStream.flush();

    }

    /**
     * Listens and receives the message from the user
     * @param actionEvent ActionEvent outputs the message of the user
     * @throws IOException can cause an IO exception if port is not available
     */
    public void onInputAction(ActionEvent actionEvent) throws IOException {
        Message tmp = new Message(MessageType.MESSAGE, name, txtInput.getText());
        txtInput.clear();
        objectOutputStream.writeObject(tmp);
        objectOutputStream.flush();
    }

    /**
     * If the user exits the application, it closers the chat window
     * @throws IOException can cause an IO exception if port is not available
     */
    public void exit() throws IOException {
        objectOutputStream.close();
        outputStream.close();
        socket.close();
    }

    /**
     * Message is obtain in the application by the user
     * when the message was type it, what the message said,
     * and who sent it
     * @param msg String to output the message of the user
     */
    public void addMessage(String msg) {
        txtOutput.appendText(msg);
    }
}
