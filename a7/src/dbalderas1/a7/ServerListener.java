package dbalderas1.a7;

import jforsythe.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * ServerListener implements a runnable connection with the server
 * @author Diana Balderas
 * @version 1.0
 */
public class ServerListener extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    Controller controller;

    /**
     * Used by the socket and controller to disconnect or connect to server
     * @param socket Socket that connects with to the server
     * @param controller Controller holds the name and message to the server
     * @throws IOException can cause an IO exception if port is not available
     */
    public ServerListener(Socket socket, Controller controller) throws IOException {
        this.socket = socket;
        this.controller = controller;
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
    }

    /**
     * Implementation of the Runnable Interface one thread will created for
     * each user that connects to the server
     */
    @Override
    public void run() {
        try{
            while(true) {
                Message tmp = (Message) objectInputStream.readObject();
                controller.addMessage(String.format("%s: %s%n", tmp.getName(), tmp.getMessage()));
            }
        } catch (ClassNotFoundException | IOException e) {
            System.err.print("Disconnected from server");
        } finally {
            try {
                objectInputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
