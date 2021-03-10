package dbalderas1.a8;

import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import jforsythe.Message;

/**
 * ServerListener implements a runnable connection with the server
 * @author Diana Balderas
 * @version 1.0
 */
public class ServerListener extends Thread{
    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private EditText output;
    public boolean running = true;


    /**
     * Used by the socket and controller to disconnect or connect to server
     * @param socket Socket that connects with to the server
     * @param output EditText that hold name and message to the server
     */
    public ServerListener(Socket socket, EditText output) {
        this.socket = socket;
        this.output = output;
        try {
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementation of the Runnable Interface one thread will created for
     * each user that connects to the server
     */
    @Override
    public void run() {
        try{
            while(running) {
                Message tmp = (Message)objectInputStream.readObject();
                output.append(String.format("%s: %s%n", tmp.getName(), tmp.getMessage()));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
