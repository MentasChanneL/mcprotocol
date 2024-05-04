package com.prikolz;

import com.prikolz.client.ClientData;
import com.prikolz.client.ClientRunnable;
import com.prikolz.packet.ClientboundPacket;
import com.prikolz.packet.DecodePacket;
import com.prikolz.packet.ServerboundPacket;
import com.prikolz.packet.handlers.DefaultPacketHandler;
import com.prikolz.packet.listeners.DefaultPacketListener;
import com.prikolz.packet.types.clientbound.ClientboundStatusResponse;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

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
