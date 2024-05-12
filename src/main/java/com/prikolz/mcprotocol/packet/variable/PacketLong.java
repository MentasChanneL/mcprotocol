package com.prikolz.mcprotocol.packet.variable;

import java.io.DataInputStream;
import java.io.IOException;

public class PacketLong implements PacketVariable {
    private final long value;
    private final byte[] bytes;

    public PacketLong(long value) {
        this.value = value;
        byte[] bytes = new byte[8];

        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((this.value >> (i * 8)) & 0xFF);
        }

        this.bytes = bytes;
    }

    public static PacketLong readLong(DataInputStream input) throws IOException {
        long value = input.readLong();
        return new PacketLong(value);
    }

    public long getLong() {
        return this.value;
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }
}
