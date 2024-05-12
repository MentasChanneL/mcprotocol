package com.prikolz.mcprotocol.packet.handlers;

import com.prikolz.mcprotocol.Client;
import com.prikolz.mcprotocol.packet.ServerboundPacket;
import com.prikolz.mcprotocol.packet.types.clientbound.ClientboundStatusPingResponse;
import com.prikolz.mcprotocol.packet.types.clientbound.ClientboundStatusResponse;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusPingRequest;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusRequest;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundHandShake;

import java.io.IOException;

public class DefaultPacketHandler {

    public static void handle(ServerboundPacket serverboundPacket, Client client) throws IOException {
        if(serverboundPacket instanceof ServerboundHandShake) {
            if(client.data.state == 0) {
                ServerboundHandShake handShake = (ServerboundHandShake) serverboundPacket;
                client.data.state = handShake.state.getInt();
                client.data.protocol = handShake.protocol.getInt();
            }
        }
    }

}
