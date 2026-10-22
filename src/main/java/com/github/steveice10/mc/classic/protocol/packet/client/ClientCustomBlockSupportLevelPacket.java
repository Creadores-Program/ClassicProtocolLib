package com.github.steveice10.mc.classic.protocol.packet.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientCustomBlockSupportLevelPacket implements Packet {
    private int supportLevel;

    @SuppressWarnings("unused")
    private ClientCustomBlockSupportLevelPacket() {
    }

    public ClientCustomBlockSupportLevelPacket(int supportLevel) {
        this.supportLevel = supportLevel;
    }

    public int getSupportLevel() {
        return this.supportLevel;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.supportLevel = in.readUnsignedByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.supportLevel);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
