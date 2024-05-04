package com.prikolz.mcprotocol.packet.variable;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class VarString implements Var {
    public final byte[] VarIntLength;
    public final byte[] string;
    public final byte[] LengthAndString;

    public VarString(String string) {
        this.string = string.getBytes(StandardCharsets.UTF_8);
        this.VarIntLength = (new VarInt(this.string.length) ).bytes;
        System.out.println(Arrays.toString(VarIntLength) + this.string.length);
        int lenVarInt = this.VarIntLength.length;
        int lenString = this.string.length;
        this.LengthAndString = new byte[lenVarInt + lenString];
        System.arraycopy(this.VarIntLength, 0, this.LengthAndString, 0, lenVarInt);
        System.arraycopy(this.string, 0, this.LengthAndString, lenVarInt, lenString);
    }

    @Override
    public byte[] getBytes() {
        return this.LengthAndString;
    }
}
