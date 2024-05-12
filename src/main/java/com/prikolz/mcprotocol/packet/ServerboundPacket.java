package com.prikolz.mcprotocol.packet;

import com.prikolz.mcprotocol.packet.variable.PacketVarInt;
import com.prikolz.mcprotocol.packet.variable.PacketVariable;

public class ServerboundPacket {
    public final int length;
    public final byte[] bytes;

    public ServerboundPacket(int length, PacketVariable ... vars) {
        this.length = length;
        this.bytes = bytes(vars, length);
    }

    private byte[] bytes(PacketVariable[] vars, int length) {
        byte[] result = new byte[length];
        int offset = 0;
        for (PacketVariable var : vars) {
            byte[] array = var.getBytes();
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        PacketVarInt s = new PacketVarInt(length);
        System.arraycopy(s.getBytes(), 0, result, offset, s.getBytes().length);
        return result;
    }

}
