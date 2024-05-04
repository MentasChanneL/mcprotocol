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
                client.data.state = handShake.state;
                client.data.protocol = handShake.protocol;
            }
        }
        if(serverboundPacket instanceof ServerboundStatusRequest) {
            ClientboundStatusResponse packet = new ClientboundStatusResponse(
                    "{\"version\": {\"name\": \"1.20.2\",\"protocol\": 764},\"players\": {\"max\": 666,\"online\": 228,\"sample\": [{\"name\": \"хуйпенис\",\"id\": \"жопажопажопажопа\"}]},\"description\": {\"text\": \"мега. крутой. сервак для людей с большими яй\"},\"enforcesSecureChat\": false,\"previewsChat\": false}"
            );
            client.sendPacket( packet );
        }
        if(serverboundPacket instanceof ServerboundStatusPingRequest) {
            ServerboundStatusPingRequest pm = (ServerboundStatusPingRequest) serverboundPacket;
            ClientboundStatusPingResponse packet = new ClientboundStatusPingResponse(pm.value);
            client.sendPacket(packet);
        }
    }

}
