package com.prikolz.mcprotocol.packet.variable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class VarString implements Var {
    public final VarInt VarIntLength;
    public final byte[] stringBytes;
    public final byte[] LengthAndString;
    public final String string;

    public VarString(String string) {
        this.string = string;
        this.stringBytes = string.getBytes(StandardCharsets.UTF_8);
        this.VarIntLength = new VarInt(this.stringBytes.length);
        int lenVarInt = this.VarIntLength.getBytes().length;
        int lenString = this.stringBytes.length;
        this.LengthAndString = new byte[lenVarInt + lenString];
        System.arraycopy(this.VarIntLength.getBytes(), 0, this.LengthAndString, 0, lenVarInt);
        System.arraycopy(this.stringBytes, 0, this.LengthAndString, lenVarInt, lenString);
    }

    private VarString(VarInt VarIntLength, byte[] stringBytes, byte[] LengthAndString, String string) {
        this.string = string;
        this.VarIntLength = VarIntLength;
        this.stringBytes = stringBytes;
        this.LengthAndString = LengthAndString;
    }

    public static VarString readString(DataInputStream input) throws IOException {
        VarInt varSize = VarInt.readVarInt(input);
        int size = varSize.getInt();
        byte[] stringBytes = new byte[size];
        input.readFully(stringBytes);
        String string = new String(stringBytes, StandardCharsets.UTF_8);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bytes.write(varSize.getBytes());
        bytes.write(stringBytes);
        byte[] LengthAndString = bytes.toByteArray();
        return new VarString(varSize, stringBytes, LengthAndString, string);
    }

    @Override
    public byte[] getBytes() {
        return this.LengthAndString;
    }
}
