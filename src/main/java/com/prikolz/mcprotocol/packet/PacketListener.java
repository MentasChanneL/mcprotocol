package com.prikolz.mcprotocol.packet;

import com.prikolz.mcprotocol.Client;

import java.io.IOException;

public abstract class PacketListener {
    public abstract void packetReceived(ServerboundPacket serverboundPacket, Client client) throws IOException;
}
