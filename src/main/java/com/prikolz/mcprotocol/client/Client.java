package com.prikolz.mcprotocol.client;

import com.prikolz.mcprotocol.client.packet.ClientboundPacket;
import com.prikolz.mcprotocol.client.packet.DecodePacket;
import com.prikolz.mcprotocol.client.packet.ServerboundPacket;
import com.prikolz.mcprotocol.client.packet.handlers.DefaultPacketHandler;
import com.prikolz.mcprotocol.client.packet.listeners.DefaultPacketListener;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public final Socket clientSocket;
    public Thread thread;
    public ClientRunnable body;
    public final ClientData data;

    public Client(ClientRunnable run, Socket newSocket) {
        this.body = run;
        this.body.client = this;
        this.data = new ClientData();
        this.thread = new Thread(this::run);
        this.clientSocket = newSocket;
        this.thread.start();
    }

    public void run() {
        if(!clientIsValid()) {
            this.thread.interrupt();
            System.out.println("Соединение закрыто");
            return;
        }
        body.run();
    }

    private boolean clientIsValid() {
        try {
            if( this.clientSocket.isClosed() ) return false;
            if( !this.clientSocket.isConnected() ) return false;
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public void sendPacket(ClientboundPacket packet) throws IOException {
        OutputStream out = this.clientSocket.getOutputStream();
        out.write(packet.bytes);
        out.flush();
    }

    public static Client createDefaultClient(Socket clientSocket) {
        DefaultPacketListener listener = new DefaultPacketListener();

        ClientRunnable run = new ClientRunnable(){
            @Override
            public void run() {
                try {
                    DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

                    // Обработка пакетов от клиента
                    while (true) {
                        ServerboundPacket serverboundPacket = DecodePacket.decode(inputStream, this.client);
                        System.out.println("Новый пакет");
                        if(serverboundPacket == null) continue;
                        DefaultPacketHandler.handle(serverboundPacket, this.client);
                        listener.packetReceived(serverboundPacket, clientSocket);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Ошибка при обработке клиента: " + e.getMessage());
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        return new Client(run, clientSocket);
    }

}
