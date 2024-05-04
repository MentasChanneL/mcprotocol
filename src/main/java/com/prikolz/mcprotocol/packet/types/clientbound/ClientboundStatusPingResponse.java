package com.prikolz.mcprotocol.packet.types.clientbound;

import com.prikolz.mcprotocol.packet.ClientboundPacket;
import com.prikolz.mcprotocol.packet.variable.justByte;
import com.prikolz.mcprotocol.packet.variable.justLong;

public class ClientboundStatusPingResponse extends ClientboundPacket {
    public ClientboundStatusPingResponse(long key) {
        this.bytes = ClientboundPacket.createPacket(
                new justByte((byte) 0x01),
                new justLong(key)
        );
    }
}
