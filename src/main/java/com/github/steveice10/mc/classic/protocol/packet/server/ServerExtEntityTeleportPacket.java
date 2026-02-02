package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerExtEntityTeleportPacket implements Packet {
    private int entityId;
    private int x;
    private int y;
    private int z;
    private int yaw;
    private int pitch;

    @SuppressWarnings("unused")
    private ServerExtEntityTeleportPacket() {
    }

    public ServerExtEntityTeleportPacket(int entityId, int x, int y, int z, int yaw, int pitch) {
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    public int getEntityId() { return entityId; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
    public int getYaw() { return yaw; }
    public int getPitch() { return pitch; }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readUnsignedByte();
        this.x = in.readInt() / 32;
        this.y = in.readInt() / 32;
        this.z = in.readInt() / 32;
        this.yaw = in.readUnsignedByte();
        this.pitch = in.readUnsignedByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.entityId);
        out.writeInt(this.x * 32);
        out.writeInt(this.y * 32);
        out.writeInt(this.z * 32);
        out.writeByte(this.yaw);
        out.writeByte(this.pitch);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
