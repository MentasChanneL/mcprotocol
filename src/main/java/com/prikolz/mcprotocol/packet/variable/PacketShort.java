package com.prikolz.mcprotocol.packet.variable;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class PacketShort implements PacketVariable {
    private final short value;
    private final byte[] bytes;

    public PacketShort(short value) {
        this.value = value;
        this.bytes = ByteBuffer.allocate(Short.BYTES).putShort(value).array();
    }

    public static PacketShort readShort(DataInputStream input) throws IOException {
        short value = input.readShort();
        return new PacketShort(value);
    }

    public short getShort() {
        return this.value;
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }
}
