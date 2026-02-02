package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.mc.classic.protocol.packet.ClassicPacketUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerChangeModelPacket implements Packet {
    private int entityId;
    private String modelName;

    @SuppressWarnings("unused")
    private ServerChangeModelPacket() {
    }

    public ServerChangeModelPacket(int entityId, String modelName) {
        this.entityId = entityId;
        this.modelName = modelName;
    }

    public String getModelName(){
        return this.modelName;
    }
    public int getEntityId(){
        return this.entityId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readUnsignedByte();
        this.modelName = ClassicPacketUtil.readString(in);
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.entityId);
        ClassicPacketUtil.writeString(out, this.modelName);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
