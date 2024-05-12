package com.prikolz.mcprotocol.packet;

import com.prikolz.mcprotocol.packet.variable.PacketVariable;
import com.prikolz.mcprotocol.packet.variable.PacketVarInt;

public class ClientboundPacket {

    public byte[] bytes;

    public static byte[] createPacket(PacketVariable... vars) {
        int totalLength = 0;
        for (PacketVariable var : vars) {
            totalLength += var.getBytes().length;
        }
        byte[] packet = new byte[totalLength];
        int offset = 0;
        for (PacketVariable var : vars) {
            byte[] array = var.getBytes();
            System.arraycopy(array, 0, packet, offset, array.length);
            offset += array.length;
        }

        PacketVarInt packetLength = new PacketVarInt( packet.length ) ;
        int pLLength = packetLength.getBytes().length;
        byte[] dataToSend = new byte[packetLength.getInt() + pLLength];

        System.arraycopy(packetLength.getBytes(), 0, dataToSend, 0, pLLength);
        System.arraycopy(packet, 0, dataToSend, pLLength, packet.length);

        return dataToSend;
    }

}
