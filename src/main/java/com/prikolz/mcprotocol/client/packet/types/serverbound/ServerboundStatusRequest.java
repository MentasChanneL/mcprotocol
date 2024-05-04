package com.prikolz.mcprotocol.client.packet.types.serverbound;

import com.prikolz.mcprotocol.client.packet.ServerboundPacket;

public class ServerboundStatusRequest extends ServerboundPacket {
    public ServerboundStatusRequest(int length) {
        super(length);
    }

    public String toString() {
        return "ServerboundStatusRequest()";
    }

}
