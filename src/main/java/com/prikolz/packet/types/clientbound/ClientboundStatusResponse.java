package com.prikolz.packet.types.clientbound;

import com.prikolz.packet.ClientboundPacket;
import com.prikolz.packet.variable.justByte;
import com.prikolz.packet.variable.VarString;

public class ClientboundStatusResponse extends ClientboundPacket {
    public ClientboundStatusResponse(String jsonStatus) {
        this.bytes = ClientboundPacket.createPacket(
                new justByte((byte) 0x00),
                new VarString(jsonStatus)
        );
    }
}
