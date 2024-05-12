package com.prikolz.mcprotocol.packet.variable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class PacketVarInt implements PacketVariable {

    private final byte[] bytes;
    private final int value;

    public PacketVarInt(int value) {
        this.value = value;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        do {
            byte temp = (byte)(value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= (byte) 0b10000000;
            }
            baos.write(temp);
        } while (value != 0);
        this.bytes = baos.toByteArray();
    }

    private PacketVarInt(byte[] bytes, int value) {
        this.bytes = bytes;
        this.value = value;
    }

    public static PacketVarInt readVarInt(DataInputStream inputStream) throws IOException {
        int numRead = 0;
        int original = 0;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte read;
        do {
            read = inputStream.readByte();
            bytes.write(read);
            int value = (read & 0b01111111);
            original |= (value << (7 * numRead));
            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is bigger");
            }
        } while ((read & 0b10000000) != 0);
        return new PacketVarInt(bytes.toByteArray(), original);
    }

    public int getInt() { return this.value; }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }
}
