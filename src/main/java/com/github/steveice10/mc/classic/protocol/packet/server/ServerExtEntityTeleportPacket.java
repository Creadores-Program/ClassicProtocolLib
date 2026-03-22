package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerExtEntityTeleportPacket implements Packet {
    private int entityId;
    private float x;
    private float y;
    private float z;
    private float yaw;
    private float pitch;

    @SuppressWarnings("unused")
    private ServerExtEntityTeleportPacket() {
    }

    public ServerExtEntityTeleportPacket(int entityId, float x, float y, float z, float yaw, float pitch) {
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    public int getEntityId() { return entityId; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
    public float getYaw() { return yaw; }
    public float getPitch() { return pitch; }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readUnsignedByte();
        this.x = (float) (in.readInt() / 32);
        this.y = (float) (in.readInt() / 32);
        this.z = (float) (in.readInt() / 32);
        this.yaw = (in.readUnsignedByte() * 360) / 256f;
        this.pitch = (in.readUnsignedByte() * 360) / 256f;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte((byte) this.entityId);
        out.writeInt((int) (this.x * 32));
        out.writeInt((int) (this.y * 32));
        out.writeInt((int) (this.z * 32));
        out.writeByte((byte) ((int) (this.yaw * 256 / 360) & 255));
        out.writeByte((byte) ((int) (this.pitch * 256 / 360) & 255));
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
