package com.prikolz.mcprotocol.server.runnables;

import com.prikolz.mcprotocol.Client;
import com.prikolz.mcprotocol.server.ServerRunnable;

import java.net.Socket;

public class DefalutServerRunnable extends ServerRunnable {
    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = this.server.serverSocket.accept();
                this.server.logger.logInfo("Client " + clientSocket.getInetAddress() + " connected");
                Client.createDefaultClient(clientSocket, this.server);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
