package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerVelocityControlPacket implements Packet {
    private int xVelocity;
    private int yVelocity;
    private int zVelocity;
    private boolean modeX;
    private boolean modeY;
    private boolean modeZ;

    @SuppressWarnings("unused")
    private ServerVelocityControlPacket() {
    }

    public ServerVelocityControlPacket(int xVelocity, int yVelocity, int zVelocity, boolean modeX, boolean modeY, boolean modeZ) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.zVelocity = zVelocity;
        this.modeX = modeX;
        this.modeY = modeY;
        this.modeZ = modeZ;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.xVelocity = in.readInt();
        this.yVelocity = in.readInt();
        this.zVelocity = in.readInt();
        this.modeX = in.readBoolean();
        this.modeY = in.readBoolean();
        this.modeZ = in.readBoolean();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeInt(this.xVelocity);
        out.writeInt(this.yVelocity);
        out.writeInt(this.zVelocity);
        out.writeBoolean(this.modeX);
        out.writeBoolean(this.modeY);
        out.writeBoolean(this.modeZ);
    }

    public int getRawX() { return xVelocity; }
    public int getRawY() { return yVelocity; }
    public int getRawZ() { return zVelocity; }
    public boolean getModeX() { return modeX; }
    public boolean getModeY() { return modeY; }
    public boolean getModeZ() { return modeZ; }

    @Override
    public boolean isPriority() {
        return false;
    }
}
