package com.example.gakpa.applicationtest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by chocanto on 28/04/16.
 */
public class SocketTask extends AsyncTask<Void, Void, Void> {
    String addr;
    int port;
    String data="";
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
                output.write(("Hey !<EOF>").getBytes());
                InputStream i = socket.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(i));
                StringBuilder total = new StringBuilder();

                this.data = r.readLine();
                Log.d("app",data);
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getData(){
        return this.data;
    }
}
