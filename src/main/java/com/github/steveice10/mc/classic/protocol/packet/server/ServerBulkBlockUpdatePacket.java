package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import java.io.IOException;

public class ServerBulkBlockUpdatePacket implements Packet {
    private int[] indices;
    private byte[] blocks;

    @SuppressWarnings("unused")
    private ServerBulkBlockUpdatePacket() {}

    public ServerBulkBlockUpdatePacket(int[] indices, byte[] blocks) {
        this.indices = indices;
        this.blocks = blocks;
    }

    @Override
    public void read(NetInput in) throws IOException {
        int count = (int) (in.readUnsignedByte() + 1);
        this.indices = ClassicPacketUtil.readBulkIndices(in, count);
        this.blocks = ClassicPacketUtil.readBulkBlocks(in, count);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte((byte) (this.blocks.length - 1));
        ClassicPacketUtil.writeBulkIndices(out, this.indices);
        ClassicPacketUtil.writeBulkBlocks(out, this.blocks);
    }

    public int[] getIndices() { return indices; }
    public byte[] getBlocks() { return blocks; }

    @Override public boolean isPriority() { return false; }
}
