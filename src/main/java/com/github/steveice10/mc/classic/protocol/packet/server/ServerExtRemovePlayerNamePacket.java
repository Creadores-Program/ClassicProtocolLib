package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerExtRemovePlayerNamePacket implements Packet {
    private short nameId;

    @SuppressWarnings("unused")
    private ServerExtRemovePlayerNamePacket() {
    }

    public ServerExtRemovePlayerNamePacket(short nameId) {
        this.nameId = nameId;
    }

    public short getNameId() {
        return this.nameId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.nameId = in.readShort();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeShort(this.nameId);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
