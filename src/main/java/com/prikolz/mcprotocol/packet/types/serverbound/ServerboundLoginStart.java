package com.prikolz.mcprotocol.packet.types.serverbound;

import com.prikolz.mcprotocol.packet.ServerboundPacket;

import java.util.UUID;

public class ServerboundLoginStart extends ServerboundPacket {

    public final String name;
    public final UUID uuid;

    public ServerboundLoginStart(int length, String name, UUID uuid) {
        super(length);
        this.name = name;
        this.uuid = uuid;
    }
}
