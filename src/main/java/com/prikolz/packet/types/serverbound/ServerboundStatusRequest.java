package com.prikolz.packet.types.serverbound;

import com.prikolz.packet.ServerboundPacket;

public class ServerboundStatusRequest extends ServerboundPacket {
    public ServerboundStatusRequest(int length) {
        super(length);
    }

    public String toString() {
        return "ServerboundStatusRequest()";
    }

}
