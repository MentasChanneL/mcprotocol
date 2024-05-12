package com.prikolz.mcprotocol.packet.types.clientbound;

import com.prikolz.mcprotocol.packet.variable.PacketString;
import com.prikolz.mcprotocol.packet.variable.PacketByte;
import com.prikolz.mcprotocol.packet.ClientboundPacket;

public class ClientboundStatusResponse extends ClientboundPacket {
    public ClientboundStatusResponse(String jsonStatus) {
        this.bytes = ClientboundPacket.createPacket(
                new PacketByte((byte) 0x00),
                new PacketString(jsonStatus)
        );
    }
}
