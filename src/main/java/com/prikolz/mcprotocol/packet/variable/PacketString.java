package com.prikolz.mcprotocol.packet.variable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PacketString implements PacketVariable {
    private final PacketVarInt VarIntLength;
    private final byte[] stringBytes;
    private final byte[] LengthAndString;
    private final String string;

    public PacketString(String string) {
        this.string = string;
        this.stringBytes = string.getBytes(StandardCharsets.UTF_8);
        this.VarIntLength = new PacketVarInt(this.stringBytes.length);
        int lenVarInt = this.VarIntLength.getBytes().length;
        int lenString = this.stringBytes.length;
        this.LengthAndString = new byte[lenVarInt + lenString];
        System.arraycopy(this.VarIntLength.getBytes(), 0, this.LengthAndString, 0, lenVarInt);
        System.arraycopy(this.stringBytes, 0, this.LengthAndString, lenVarInt, lenString);
    }

    private PacketString(PacketVarInt VarIntLength, byte[] stringBytes, byte[] LengthAndString, String string) {
        this.string = string;
        this.VarIntLength = VarIntLength;
        this.stringBytes = stringBytes;
        this.LengthAndString = LengthAndString;
    }

    public static PacketString readString(DataInputStream input) throws IOException {
        PacketVarInt varSize = PacketVarInt.readVarInt(input);
        int size = varSize.getInt();
        byte[] stringBytes = new byte[size];
        input.readFully(stringBytes);
        String string = new String(stringBytes, StandardCharsets.UTF_8);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bytes.write(varSize.getBytes());
        bytes.write(stringBytes);
        byte[] LengthAndString = bytes.toByteArray();
        return new PacketString(varSize, stringBytes, LengthAndString, string);
    }

    public String getString() {
        return this.string;
    }

    public PacketVarInt getLength() {
        return this.VarIntLength;
    }

    public byte[] getStringBytes() {
        return this.stringBytes;
    }

    @Override
    public byte[] getBytes() {
        return this.LengthAndString;
    }
}
