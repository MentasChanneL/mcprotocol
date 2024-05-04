package com.prikolz.mcprotocol;

import com.prikolz.mcprotocol.Client;

import java.io.*;
import java.net.*;

public class Server {

    public final Thread thread;
    public final ServerSocket serverSocket;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket( port );
        System.out.println("Сервер запущен на порту 25565...");
        this.thread = new Thread( () -> {
            while (true) {
                try {
                    Socket clientSocket = this.serverSocket.accept();
                    System.out.println("Клиент подключился: " + clientSocket.getInetAddress());
                    Client.createDefaultClient(clientSocket);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.thread.start();
    }
}
