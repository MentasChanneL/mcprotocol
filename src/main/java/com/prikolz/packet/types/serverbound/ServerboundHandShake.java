package com.prikolz.packet.types.serverbound;

import com.prikolz.packet.ServerboundPacket;

public class ServerboundHandShake extends ServerboundPacket {

    public final int protocol;
    public final String adress;
    public final short port;
    public final int state;

    public ServerboundHandShake(int length, int protocol, String adress, short port, int state) {
        super(length);
        this.protocol = protocol;
        this.adress = adress;
        this.port = port;
        this.state = state;
    }

    public String toString() {
        return "HandShakePacket( " +
                "protocol=" + this.protocol +
                " adress=" + this.adress +
                " port=" + this.port +
                " status=" + this.state +
                " )";
    }
}
