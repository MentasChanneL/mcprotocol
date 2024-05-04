package com.prikolz.mcprotocol.client.packet.types.serverbound;

import com.prikolz.mcprotocol.client.packet.ServerboundPacket;

public class ServerboundStatusPingRequest extends ServerboundPacket {

    public final long value;

    public ServerboundStatusPingRequest(int length, long value) {
        super(length);
        this.value = value;
    }

    public String toString() {
        return "ServerboundPingRequest( value=" + this.value + " )";
    }

}
