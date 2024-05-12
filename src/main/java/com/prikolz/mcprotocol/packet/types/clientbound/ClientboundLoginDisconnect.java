package com.prikolz.mcprotocol.packet.types.clientbound;

import com.prikolz.mcprotocol.packet.ClientboundPacket;
import com.prikolz.mcprotocol.packet.variable.PacketByte;
import com.prikolz.mcprotocol.packet.variable.PacketString;

public class ClientboundLoginDisconnect extends ClientboundPacket {
    public ClientboundLoginDisconnect(String jsonReason) {
        this.bytes = ClientboundPacket.createPacket(
                new PacketByte( (byte) 0x00 ),
                new PacketString(jsonReason)
        );
    }
}
