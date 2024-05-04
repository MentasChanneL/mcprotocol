package com.prikolz.mcprotocol.client.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class VarReader {
    // Чтение VarInt из потока
    public static int readVarInt(DataInputStream inputStream) throws IOException {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = inputStream.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));
            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt слишком большой");
            }
        } while ((read & 0b10000000) != 0);
        return result;
    }


    // Чтение VarLong из потока (аналогично VarInt, но для long)
    public static long readVarLong(DataInputStream inputStream) throws IOException {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = inputStream.readByte();
            long value = (read & 0b01111111);
            result |= (value << (7 * numRead));
            numRead++;
            if (numRead > 10) {
                throw new RuntimeException("VarLong слишком большой");
            }
        } while ((read & 0b10000000) != 0);
        return result;
    }

    // Запись VarLong в поток (аналогично VarInt, но для long)
    public static void writeVarLong(DataOutputStream outputStream, long value) throws IOException {
        do {
            byte temp = (byte)(value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            outputStream.writeByte(temp);
        } while (value != 0);
    }
}
