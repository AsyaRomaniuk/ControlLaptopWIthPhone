package com.example.controllaptopclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class PyCommandsActivity extends AppCompatActivity {

    public static final String SEND_MSG = "Команда була надіслана на виконання";
    public static final String EMPTY_INPUT_MSG = "Пуста команда, команда не надіслана";
    public static final String CREATE_CONNECTION_ERROR_MSG = "Не вдалося підключитися до сервера";
    public static final String SENDING_COMMAND_FAILED_MSG = "Не вдалося надіслати команду";
    public static String server_ip_address; //"192.168.1.16"
    public static final int SERVER_PORT = 2000;
    public static String pyCommand = "";
    public Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_py_commands);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            server_ip_address = extras.getString("ip");
        }
        // Toast.makeText(this, server_ip_address, Toast.LENGTH_SHORT).show();
        connection = new Connection(server_ip_address, SERVER_PORT);
        try {
            connection.startConnection();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        connection.stopConnection();
    }

    public void sendCommand(View view) {
        EditText command = (EditText) findViewById(R.id.command_line);
        pyCommand = command.getText().toString();
        if (pyCommand.isEmpty()) {
            Toast.makeText(this, EMPTY_INPUT_MSG, Toast.LENGTH_LONG).show();
        } else {
            if (connection != null) {
                try {
                    connection.send(pyCommand);
                    Toast.makeText(this,
                            SEND_MSG,
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this,
                            SENDING_COMMAND_FAILED_MSG,
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this,
                        CREATE_CONNECTION_ERROR_MSG,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}