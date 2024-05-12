package com.prikolz.mcprotocol.packet.variable;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

public class PacketUUID implements PacketVariable{

    private final byte[] bytes;
    private final UUID uuid;

    public PacketUUID(UUID uuid) {
        this.uuid = uuid;

        long most = uuid.getMostSignificantBits();
        long least = uuid.getLeastSignificantBits();

        this.bytes = new byte[16];
        ByteBuffer.wrap(this.bytes)
                .putLong(most)
                .putLong(least);
    }

    private PacketUUID(UUID uuid, byte[] bytes) {
        this.uuid = uuid;
        this.bytes = bytes;
    }

    public static PacketUUID readUUID(DataInputStream input) throws IOException {
        long uuid1 = input.readLong();
        long uuid2 = input.readLong();
        byte[] bytes = new byte[16];
        ByteBuffer.wrap(bytes)
                .putLong(uuid1)
                .putLong(uuid2);
        UUID uuid = new UUID(uuid1, uuid2);
        return new PacketUUID(uuid, bytes);
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    public UUID getUUID() {
        return this.uuid;
    }

}
