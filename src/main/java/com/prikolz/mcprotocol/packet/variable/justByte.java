package com.prikolz.mcprotocol.packet.variable;

public class justByte implements Var {
    public final byte value;

    public justByte(byte value) {
        this.value = value;
    }

    @Override
    public byte[] getBytes() {
        return new byte[]{this.value};
    }
}
