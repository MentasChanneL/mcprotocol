package com.prikolz.mcprotocol.packet.types.serverbound;

import com.prikolz.mcprotocol.packet.ServerboundPacket;
import com.prikolz.mcprotocol.packet.variable.PacketShort;
import com.prikolz.mcprotocol.packet.variable.PacketVarInt;
import com.prikolz.mcprotocol.packet.variable.PacketString;

public class ServerboundHandShake extends ServerboundPacket {

    public final PacketVarInt protocol;
    public final PacketString adress;
    public final PacketShort port;
    public final PacketVarInt state;

    public ServerboundHandShake(int length, PacketVarInt protocol, PacketString adress, PacketShort port, PacketVarInt state) {
        super(length, protocol, adress, port, state);
        this.protocol = protocol;
        this.adress = adress;
        this.port = port;
        this.state = state;
    }

    @Override
    public String toString() {
        return "ServerboundHandShakePacket( " +
                "protocol=" + this.protocol.getInt() +
                " adress=" + this.adress.getString() +
                " port=" + this.port.getShort() +
                " status=" + this.state.getInt() +
                " )";
    }
}
