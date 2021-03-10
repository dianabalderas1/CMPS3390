package dbalderas1.a8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import jforsythe.Message;
import jforsythe.MessageType;

/**
 * Main Driver class for A8
 * @author Diana Balderas
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private EditText txtInput;
    private EditText txtOutput;
    private String name;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private ServerListener serverListener;

    /**
     * Loads and saves the values when application is running
     * @param savedInstanceState Bundle is the primary scene of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        this.txtInput = findViewById(R.id.txtInput);
        this.txtInput.setOnEditorActionListener(this);
        this.txtOutput = findViewById(R.id.txtOutput);

        getUserName();

    }

    /**
     * Closes the server and the application after exiting
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        serverListener.running = false;
        try{
            objectOutputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Connects with the server and application after launching
     */
    private void connect() {
        try {
            socket = new Socket("odin.cs.csub.edu", 3390);
            outputStream = socket.getOutputStream();
            outputStream.flush();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.flush();

            serverListener = new ServerListener(socket, txtOutput);
            serverListener.start();

            Message connect = new Message(MessageType.CONNECT, name, "Hi, From Android");
            objectOutputStream.writeObject(connect);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * After launching application, user name is required for the
     * identification of the user while chatting with others
     */
    private void getUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("User Name");
        EditText userNameInput = new EditText(this);
        userNameInput.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(userNameInput);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = userNameInput.getText().toString();
                Log.d("USER_NAME", name);
                if(name.equals("")) getUserName();
                else connect();
            }
        });


        builder.show();
    }

    /**
     * While in the application, user's name and text will be tracked
     * based on their activity while remaining active
     * @param v TextView element appearance on the application
     * @param actionId int unique to every user's input
     * @param event KeyEvent to keep track of the activity
     * @return
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (event == null || event.getAction() == KeyEvent.ACTION_UP) {
            Message tmp = new Message(MessageType.MESSAGE, name, txtInput.getText().toString());
            try {
                objectOutputStream.writeObject(tmp);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            txtInput.setText("");
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return true;
    }
}