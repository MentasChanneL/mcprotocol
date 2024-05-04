package com.prikolz.packet;

import com.prikolz.Client;
import com.prikolz.packet.types.serverbound.ServerboundHandShake;
import com.prikolz.packet.types.serverbound.ServerboundStatusPingRequest;
import com.prikolz.packet.types.serverbound.ServerboundStatusRequest;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class DecodePacket {
    public static ServerboundPacket decode(DataInputStream input, Client client) throws IOException {
        int packetLength = VarReader.readVarInt(input);
        byte packetId = input.readByte();
        switch (packetId) {
            case 0x00:
                return packet0(input, packetLength, client);
            case 0x01:
                return packet1(input, packetLength, client);
        }
        return null;
    }

    private static ServerboundPacket packet0(DataInputStream input, int len, Client client) throws IOException {
        if(client.data.state == 0) {
            int protocol = VarReader.readVarInt(input);

            int adressSize = VarReader.readVarInt(input);
            byte[] stringBytes = new byte[adressSize];
            input.readFully(stringBytes);
            String adress = new String(stringBytes, StandardCharsets.UTF_8);

            short port = input.readShort();
            int state = VarReader.readVarInt(input);
            return new ServerboundHandShake(len, protocol, adress, port, state);
        }
        if(client.data.state == 1) {
            return new ServerboundStatusRequest(len);
        }
        return null;
    }

    private static ServerboundPacket packet1(DataInputStream input, int len, Client client) throws IOException {
        if(client.data.state == 1) {
            long value = input.readLong();

            return new ServerboundStatusPingRequest(len, value);
        }
        return null;
    }

}
