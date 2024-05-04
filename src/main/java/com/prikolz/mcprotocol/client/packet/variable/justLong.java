package com.prikolz.mcprotocol.client.packet.variable;

public class justLong implements Var {
    public final long value;

    public justLong(long value) {
        this.value = value;
    }

    @Override
    public byte[] getBytes() {
        byte[] bytes = new byte[8]; // long занимает 8 байт

        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((this.value >> (i * 8)) & 0xFF); // сдвигаем число на i*8 бит и маскируем его с помощью 0xFF
        }

        return bytes;
    }
}
