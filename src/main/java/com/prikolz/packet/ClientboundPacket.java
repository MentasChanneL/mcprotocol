package com.prikolz.packet;

import com.prikolz.packet.variable.Var;
import com.prikolz.packet.variable.VarInt;

import java.util.Arrays;

public class ClientboundPacket {

    public byte[] bytes;

    public static byte[] createPacket(Var ... vars) {
        int totalLength = 0;
        for (Var var : vars) {
            totalLength += var.getBytes().length;
        }
        byte[] packet = new byte[totalLength];
        int offset = 0;
        for (Var var : vars) {
            byte[] array = var.getBytes();
            System.arraycopy(array, 0, packet, offset, array.length);
            offset += array.length;
        }

        VarInt packetLength = new VarInt( packet.length ) ;
        int pLLength = packetLength.getBytes().length;
        byte[] dataToSend = new byte[packetLength.original + pLLength];

        System.arraycopy(packetLength.getBytes(), 0, dataToSend, 0, pLLength);
        System.arraycopy(packet, 0, dataToSend, pLLength, packet.length);

        return dataToSend;
    }

}