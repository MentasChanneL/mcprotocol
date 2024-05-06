package com.prikolz.mcprotocol;

import com.prikolz.mcprotocol.server.ServerRunnable;
import com.prikolz.mcprotocol.utils.Logger;

import java.io.*;
import java.net.*;

public class Server {

    public final Thread thread;
    public final ServerSocket serverSocket;
    public final Logger logger;
    public final int port;

    public Server(int port, boolean sendLogs, ServerRunnable handler) throws IOException {
        this.port = port;
        this.logger = new Logger(this);
        this.logger.loggerON = sendLogs;
        this.serverSocket = new ServerSocket( port );
        this.logger.logInfo("The server is running on the port " + port);
        handler.server = this;
        this.thread = new Thread(handler);
        this.thread.start();
    }

}
