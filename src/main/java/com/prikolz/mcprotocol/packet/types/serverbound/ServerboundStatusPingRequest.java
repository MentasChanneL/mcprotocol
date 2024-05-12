package com.prikolz.mcprotocol.packet.types.serverbound;

import com.prikolz.mcprotocol.packet.ServerboundPacket;
import com.prikolz.mcprotocol.packet.variable.PacketLong;

public class ServerboundStatusPingRequest extends ServerboundPacket {

    public final PacketLong value;

    public ServerboundStatusPingRequest(int length, PacketLong value) {
        super(length, value);
        this.value = value;
    }

    public String toString() {
        return "ServerboundPingRequest( value=" + this.value + " )";
    }

}
