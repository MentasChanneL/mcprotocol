package com.prikolz.mcprotocol.packet.types.serverbound;

import com.prikolz.mcprotocol.packet.ServerboundPacket;
import com.prikolz.mcprotocol.packet.variable.PacketString;
import com.prikolz.mcprotocol.packet.variable.PacketUUID;

import java.util.UUID;

public class ServerboundLoginStart extends ServerboundPacket {

    public final PacketString name;
    public final PacketUUID uuid;

    public ServerboundLoginStart(int length, PacketString name, PacketUUID uuid) {
        super(length, name, uuid);
        this.name = name;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ServerboundLoginStart(" +
                " name=" + this.name.getString() +
                " uuid=" + this.uuid.toString() +
                " )";
    }

}
