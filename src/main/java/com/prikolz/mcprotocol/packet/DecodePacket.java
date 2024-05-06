package com.prikolz.mcprotocol.packet;

import com.prikolz.mcprotocol.Client;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundHandShake;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundLoginStart;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusPingRequest;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusRequest;
import com.prikolz.mcprotocol.packet.variable.VarInt;
import com.prikolz.mcprotocol.packet.variable.VarString;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class DecodePacket {
    public static ServerboundPacket decode(DataInputStream input, Client client) throws IOException {
        int packetLength = VarInt.readVarInt(input).getInt();
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
            int protocol = VarInt.readVarInt(input).getInt();

            String adress = VarString.readString(input).string;

            short port = input.readShort();
            int state = VarInt.readVarInt(input).getInt();
            return new ServerboundHandShake(len, protocol, adress, port, state);
        }
        if(client.data.state == 1) {
            return new ServerboundStatusRequest(len);
        }
        if(client.data.state == 2) {
            String name = VarString.readString(input).string;
            System.out.println(name);

            return new ServerboundLoginStart(len, name, null);
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
