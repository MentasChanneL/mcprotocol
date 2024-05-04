package com.prikolz.mcprotocol.packet.types.serverbound;

import com.prikolz.mcprotocol.packet.ServerboundPacket;

public class ServerboundStatusRequest extends ServerboundPacket {
    public ServerboundStatusRequest(int length) {
        super(length);
    }

    public String toString() {
        return "ServerboundStatusRequest()";
    }

}
