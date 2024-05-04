package com.prikolz.packet.types.clientbound;

import com.prikolz.packet.ClientboundPacket;
import com.prikolz.packet.variable.justByte;
import com.prikolz.packet.variable.justLong;

public class ClientboundStatusPingResponse extends ClientboundPacket {
    public ClientboundStatusPingResponse(long key) {
        this.bytes = ClientboundPacket.createPacket(
                new justByte((byte) 0x01),
                new justLong(key)
        );
    }
}
