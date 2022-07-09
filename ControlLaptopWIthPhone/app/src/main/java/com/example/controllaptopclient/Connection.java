package com.example.controllaptopclient;


import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection {

    private DataOutputStream out;
    private Socket socket;
    private final String ip;
    private final int port;

    public Connection(final String ip, final int port) {
        this.ip = ip;
        this.port = port;
    }

    public void send(final String text) {
            Thread thread = new Thread(() -> {
                try {
                    out.write(text.getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
    }

    public void startConnection() {
        Thread thread = new Thread(() -> {
            try {
                socket = new Socket(ip, port);
                out = new DataOutputStream(socket.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void stopConnection() {
        try {
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}