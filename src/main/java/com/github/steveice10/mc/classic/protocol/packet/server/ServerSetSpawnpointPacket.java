package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerSetSpawnpointPacket implements Packet {
    private float x;
    private float y;
    private float z;
    private float yaw;
    private float pitch;

    @SuppressWarnings("unused")
    private ServerSetSpawnpointPacket() {
    }

    public ServerSetSpawnpointPacket(int x, int y, int z, int yaw, int pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.x = (float) (in.readShort() / 32);
        this.y = (float) (in.readShort() / 32);
        this.z = (float) (in.readShort() / 32);
        this.yaw = (in.readUnsignedByte() * 360) / 256f;
        this.pitch = (in.readUnsignedByte() * 360) / 256f;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeShort((short) (this.x * 32));
        out.writeShort((short) (this.y * 32));
        out.writeShort((short) (this.z * 32));
        out.writeByte((byte) ((int) (this.yaw * 256 / 360) & 255));
        out.writeByte((byte) ((int) (this.pitch * 256 / 360) & 255));
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
    public float getYaw() { return yaw; }
    public float getPitch() { return pitch; }
    @Override
    public boolean isPriority() {
        return false;
    }
}
