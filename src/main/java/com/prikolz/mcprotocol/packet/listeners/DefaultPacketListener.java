package com.prikolz.mcprotocol.packet.listeners;

import com.prikolz.mcprotocol.Client;
import com.prikolz.mcprotocol.packet.ServerboundPacket;
import com.prikolz.mcprotocol.packet.PacketListener;
import com.prikolz.mcprotocol.packet.types.clientbound.ClientboundLoginDisconnect;
import com.prikolz.mcprotocol.packet.types.clientbound.ClientboundStatusPingResponse;
import com.prikolz.mcprotocol.packet.types.clientbound.ClientboundStatusResponse;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundLoginStart;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusPingRequest;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusRequest;
import com.prikolz.mcprotocol.utils.Base64Encoder;
import com.prikolz.mcprotocol.utils.ResourceReader;
import com.prikolz.mcprotocol.utils.ServerStatusConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class DefaultPacketListener extends PacketListener {

    @Override
    public void packetReceived(ServerboundPacket serverboundPacket, Client client) throws IOException {

        if(serverboundPacket instanceof ServerboundStatusRequest) {

            ServerStatusConstructor constructor = new ServerStatusConstructor("§6Simple §eText", 764, 2147483647, new Random().nextInt(1000), false, false);
            constructor
                    .addPlayerSample("")
                    .addPlayerSample("§6Join on this server!")
                    .addPlayerSample("")
                    .addPlayerSample("§a☂ � ◎ ❣ ❤")
                    .addPlayerSample("§8───────────")
                    .addPlayerSample("");
            constructor.setJsonDescription("{\"text\": \"§aServer description\n§fline 2 §c★§e★§9★§b★\"}");
            constructor.setBase64Icon( Base64Encoder.encodeFileToBase64(new Random().nextInt(5) + 1 + ".png", DefaultPacketListener.class.getClassLoader()) );

            ClientboundStatusResponse packet = new ClientboundStatusResponse( constructor.getJson(true) );
            client.sendPacket( packet );
        }

        if(serverboundPacket instanceof ServerboundStatusPingRequest) {
            ServerboundStatusPingRequest pm = (ServerboundStatusPingRequest) serverboundPacket;
            ClientboundStatusPingResponse packet = new ClientboundStatusPingResponse( pm.value.getLong() );
            client.sendPacket(packet);
        }

        if (serverboundPacket instanceof ServerboundLoginStart) {
            try {
                String content = ResourceReader.read(DefaultPacketListener.class.getClassLoader().getResource("foke.txt"));
                client.sendPacket(new ClientboundLoginDisconnect(content));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
