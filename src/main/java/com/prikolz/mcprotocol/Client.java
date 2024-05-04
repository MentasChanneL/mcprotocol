package com.prikolz.mcprotocol;

import com.prikolz.mcprotocol.client.ClientData;
import com.prikolz.mcprotocol.client.ClientRunnable;
import com.prikolz.mcprotocol.packet.ClientboundPacket;
import com.prikolz.mcprotocol.packet.DecodePacket;
import com.prikolz.mcprotocol.packet.ServerboundPacket;
import com.prikolz.mcprotocol.packet.handlers.DefaultPacketHandler;
import com.prikolz.mcprotocol.packet.listeners.DefaultPacketListener;

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
                        if(serverboundPacket == null) continue;
                        DefaultPacketHandler.handle(serverboundPacket, this.client);
                        listener.packetReceived(serverboundPacket, this.client);
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
