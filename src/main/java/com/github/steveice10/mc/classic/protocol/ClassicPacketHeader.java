package com.github.steveice10.mc.classic.protocol;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.PacketHeader;

import java.io.IOException;

/**
 * Packet header used by Minecraft Classic packets.
 */
public class ClassicPacketHeader implements PacketHeader {
    private static final int MAXLENGTH = 1035;

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
        if(in.available() < 1){
            return -1;
        }
        int id = in.readUnsignedByte();
        if(in.available() > MAXLENGTH) {
            return -1;
        }

        return id;
    }

    @Override
    public void writePacketId(NetOutput out, int packetId) throws IOException {
        out.writeByte(packetId);
    }
}
