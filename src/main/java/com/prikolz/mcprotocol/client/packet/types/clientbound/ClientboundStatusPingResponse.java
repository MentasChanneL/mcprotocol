package com.prikolz.mcprotocol.client.packet.types.clientbound;

import com.prikolz.mcprotocol.client.packet.ClientboundPacket;
import com.prikolz.mcprotocol.client.packet.variable.justByte;
import com.prikolz.mcprotocol.client.packet.variable.justLong;

public class ClientboundStatusPingResponse extends ClientboundPacket {
    public ClientboundStatusPingResponse(long key) {
        this.bytes = ClientboundPacket.createPacket(
                new justByte((byte) 0x01),
                new justLong(key)
        );
    }
}
