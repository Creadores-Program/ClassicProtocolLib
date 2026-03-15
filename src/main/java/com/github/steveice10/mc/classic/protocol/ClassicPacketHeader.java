package com.github.steveice10.mc.classic.protocol;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.PacketHeader;

import java.io.IOException;

/**
 * Packet header used by Minecraft Classic packets.
 */
public class ClassicPacketHeader implements PacketHeader {
    private static final int LENGTHS[] = new int[] {130, 0, 0, 1027, 6, 8, 7, 73, 9, 6, 4, 3, 1, 65, 64, 1, 66, 68, 2, 1, 2, 133, 195, 0, 2, 7, 85, 1, 3, 65, 0, 1, 7, 137, 14, 79, 1, 87, 1281, 5, 64, 5, 6, 3, 2, 2, 8, 15, 35, 25, 115, 166, 1, 65, 10, 2, 9, 4, 8, 1};

    @Override
    public boolean isLengthVariable() {
        return false;
    }

    @Override
    public int getLengthSize() {
        return 0;
    }

    @Override
    public int getLengthSize(int length) {
        return 0;
    }

    @Override
    public int readLength(NetInput in, int available) throws IOException {
        return available;
    }

    @Override
    public void writeLength(NetOutput out, int length) throws IOException {
    }

    @Override
    public int readPacketId(NetInput in) throws IOException {
        int id = in.readUnsignedByte();
        if(id >= 0 && id < LENGTHS.length && LENGTHS[id] > in.available()) {
            return -1;
        }

        return id;
    }

    @Override
    public void writePacketId(NetOutput out, int packetId) throws IOException {
        out.writeByte(packetId);
    }
}
