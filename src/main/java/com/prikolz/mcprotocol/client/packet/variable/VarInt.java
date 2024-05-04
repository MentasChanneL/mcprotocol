package com.prikolz.mcprotocol.client.packet.variable;

import java.io.ByteArrayOutputStream;

public class VarInt implements Var {

    public final byte[] bytes;
    public final int original;

    public VarInt(int value) {
        this.original = value;
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

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }
}
