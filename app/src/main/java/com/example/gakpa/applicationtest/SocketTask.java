package com.example.gakpa.applicationtest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by chocanto on 28/04/16.
 */
public class SocketTask extends AsyncTask<Void, Void, Void> {
    String addr;
    int port;

    SocketTask(String addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Socket socket = null;
        OutputStream output = null;

        try {
            socket = new Socket(addr, port);

            if (socket.isConnected()) {
                output = socket.getOutputStream();
                output.write(("Hey !").getBytes());
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
