package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEnvColorsPacket implements Packet {
    private int type;
    private int red;
    private int green;
    private int blue;

    @SuppressWarnings("unused")
    private ServerEnvColorsPacket() {
    }

    public ServerEnvColorsPacket(int type, int red, int green, int blue) {
        this.type = type;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public int getType(){
        return this.type;
    }
    public int getRed(){
        return this.red;
    }
    public int geGreen(){
        return this.green;
    }
    public int getBlue(){
        return this.blue;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.type = in.readUnsignedByte();
        this.red = in.readShort();
        this.green = in.readShort();
        this.blue = in.readShort();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.type);
        out.writeShort(this.red);
        out.writeShort(this.green);
        out.writeShort(this.blue);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
