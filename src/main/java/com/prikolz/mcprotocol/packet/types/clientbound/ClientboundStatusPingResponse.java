package com.prikolz.mcprotocol.packet.types.clientbound;

import com.prikolz.mcprotocol.packet.ClientboundPacket;
import com.prikolz.mcprotocol.packet.variable.PacketByte;
import com.prikolz.mcprotocol.packet.variable.PacketLong;

public class ClientboundStatusPingResponse extends ClientboundPacket {
    public ClientboundStatusPingResponse(long key) {
        this.bytes = ClientboundPacket.createPacket(
                new PacketByte((byte) 0x01),
                new PacketLong(key)
        );
    }
}
