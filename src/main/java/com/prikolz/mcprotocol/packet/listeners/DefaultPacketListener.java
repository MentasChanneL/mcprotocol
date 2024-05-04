package com.prikolz.mcprotocol.packet.listeners;

import com.prikolz.mcprotocol.Client;
import com.prikolz.mcprotocol.packet.ServerboundPacket;
import com.prikolz.mcprotocol.packet.PacketListener;
import com.prikolz.mcprotocol.packet.types.clientbound.ClientboundStatusPingResponse;
import com.prikolz.mcprotocol.packet.types.clientbound.ClientboundStatusResponse;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusPingRequest;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusRequest;

import java.io.IOException;

public class DefaultPacketListener extends PacketListener {

    @Override
    public void packetReceived(ServerboundPacket serverboundPacket, Client client) throws IOException {
        if(serverboundPacket instanceof ServerboundStatusRequest) {
            ClientboundStatusResponse packet = new ClientboundStatusResponse(
                    "{\"version\": {\"name\": \"1.20.2\",\"protocol\": 764},\"players\": {\"max\": 666,\"online\": 228,\"sample\": [{\"name\": \"§a◦◆ Creative+ ◆◦\",\"id\": \"00000000-0000-0000-0000-000000000000\"},{\"name\": \"Сотни режимов!\",\"id\": \"00000000-0000-0000-0000-000000000001\"},{\"name\": \"Создай свой!\",\"id\": \"00000000-0000-0000-0000-000000000002\"},{\"name\": \"\",\"id\": \"00000000-0000-0000-0000-000000000003\"},{\"name\": \"ну там и остальное че-то\",\"id\": \"00000000-0000-0000-0000-000000000004\"}]},\"description\": {\"text\": \"мега. крутой. сервак для людей с большими яй\"},\"enforcesSecureChat\": false,\"previewsChat\": false}"
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
