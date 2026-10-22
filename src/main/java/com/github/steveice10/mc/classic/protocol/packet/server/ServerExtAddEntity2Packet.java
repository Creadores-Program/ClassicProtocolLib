package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;
import com.github.steveice10.mc.classic.protocol.packet.ClassicPacketUtil;

import java.io.IOException;

public class ServerExtAddEntity2Packet implements Packet {
    private int entityId;
    private String name;
    private String skin;
    private float x;
    private float y;
    private float z;
    private int yaw;
    private int pitch;

    @SuppressWarnings("unused")
    private ServerExtAddEntity2Packet() {
    }

    public ServerExtAddEntity2Packet(int entityId, String name, String skin, short x, short y, short z, int yaw, int pitch) {
        this.entityId = entityId;
        this.name = name;
        this.skin = skin;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.entityId = in.readUnsignedByte();
        this.name = ClassicPacketUtil.readString(in);
        this.skin = ClassicPacketUtil.readString(in);
        this.x = (float) (in.readShort() / 32);
        this.y = (float) (in.readShort() / 32);
        this.z = (float) (in.readShort() / 32);
        this.yaw = in.readUnsignedByte();
        this.pitch = in.readUnsignedByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.entityId);
        ClassicPacketUtil.writeString(out, this.name);
        ClassicPacketUtil.writeString(out, this.skin);
        out.writeShort((short) (this.x * 32));
        out.writeShort((short) (this.y * 32));
        out.writeShort((short) (this.z * 32));
        out.writeByte(this.yaw);
        out.writeByte(this.pitch);
    }

    public int getEntityId() { return entityId; }
    public String getName() { return name; }
    public String getSkin() { return skin; }

    public int getYaw() { return yaw; }
    public int getPitch() { return pitch; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }

    @Override public boolean isPriority() { return false; }
}
