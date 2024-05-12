package com.prikolz.mcprotocol.packet.variable;

import java.io.DataInputStream;
import java.io.IOException;

public class PacketByte implements PacketVariable {
    private final byte value;
    private final byte[] bytes;

    public PacketByte(byte value) {
        this.value = value;
        this.bytes = new byte[]{this.value};
    }

    public static PacketByte readByte(DataInputStream input) throws IOException {
        byte value = input.readByte();
        return new PacketByte(value);
    }

    public byte getByte() {
        return this.value;
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }
}
