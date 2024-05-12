package com.prikolz.mcprotocol.server.runnables;

import com.prikolz.mcprotocol.Client;
import com.prikolz.mcprotocol.server.ServerRunnable;

import java.net.Socket;
import java.util.HashMap;

public class DefalutServerRunnable extends ServerRunnable {

    public HashMap<String, Integer> pings = new HashMap<>();

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = this.server.serverSocket.accept();
                ping(clientSocket.getInetAddress().toString());
                this.server.logger.logInfo("Client " + clientSocket.getInetAddress() + " connected");
                Client.createDefaultClient(clientSocket, this.server);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void ping(String ip) {
        if(!pings.containsKey(ip)) {
            pings.put(ip, 1);
            return;
        }
        int count = pings.get(ip);
        count++;
        pings.put(ip, count);
    }

}
