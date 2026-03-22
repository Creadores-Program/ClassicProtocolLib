package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerSetHotbarPacket implements Packet {
    private int blockId;
    private int hotbarIndex;

    @SuppressWarnings("unused")
    private ServerSetHotbarPacket() {
    }

    public ServerSetHotbarPacket(int blockId, int hotbarIndex) {
        this.blockId = blockId;
        this.hotbarIndex = hotbarIndex;
    }

    public int getBlockId() {
        return this.blockId;
    }

    public int getHotbarIndex(){
        return this.hotbarIndex;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.blockId = in.readUnsignedByte();
        this.hotbarIndex = in.readUnsignedByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte((byte) this.blockId);
        out.writeByte((byte) this.hotbarIndex);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
