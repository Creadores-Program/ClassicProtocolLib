package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerEnvSetWeatherTypePacket implements Packet {
    private int weatherType;

    @SuppressWarnings("unused")
    private ServerEnvSetWeatherTypePacket() {
    }

    public ServerEnvSetWeatherTypePacket(int weatherType) {
        this.weatherType = weatherType;
    }

    public int getWeatherType() {
        return this.weatherType;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.weatherType = in.readUnsignedByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.weatherType);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
