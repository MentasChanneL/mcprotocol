package com.prikolz.mcprotocol.packet;

import com.prikolz.mcprotocol.Client;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundHandShake;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundLoginStart;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusPingRequest;
import com.prikolz.mcprotocol.packet.types.serverbound.ServerboundStatusRequest;
import com.prikolz.mcprotocol.packet.variable.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

public abstract class DecodePacket {
    public static ServerboundPacket decode(DataInputStream input, Client client) throws IOException {
        int packetLength = PacketVarInt.readVarInt(input).getInt();
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
            PacketVarInt protocol = PacketVarInt.readVarInt(input);

            PacketString adress = PacketString.readString(input);

            PacketShort port = PacketShort.readShort(input);
            PacketVarInt state = PacketVarInt.readVarInt(input);
            return new ServerboundHandShake(len, protocol, adress, port, state);
        }
        if(client.data.state == 1) {
            return new ServerboundStatusRequest(len);
        }
        if(client.data.state == 2) {
            PacketString name = PacketString.readString(input);
            PacketUUID uuid = PacketUUID.readUUID(input);

            return new ServerboundLoginStart(len, name, uuid);
        }
        return null;
    }

    private static ServerboundPacket packet1(DataInputStream input, int len, Client client) throws IOException {
        if(client.data.state == 1) {
            PacketLong value = PacketLong.readLong(input);

            return new ServerboundStatusPingRequest(len, value);
        }
        return null;
    }

}
