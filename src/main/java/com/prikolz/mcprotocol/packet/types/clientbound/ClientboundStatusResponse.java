package com.prikolz.mcprotocol.packet.types.clientbound;

import com.prikolz.mcprotocol.packet.variable.VarString;
import com.prikolz.mcprotocol.packet.variable.justByte;
import com.prikolz.mcprotocol.packet.ClientboundPacket;

public class ClientboundStatusResponse extends ClientboundPacket {
    public ClientboundStatusResponse(String jsonStatus) {
        this.bytes = ClientboundPacket.createPacket(
                new justByte((byte) 0x00),
                new VarString(jsonStatus)
        );
    }
}
