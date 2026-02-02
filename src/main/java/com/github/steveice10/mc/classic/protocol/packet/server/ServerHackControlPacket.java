package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerHackControlPacket implements Packet {
    private boolean flying;
    private boolean noclip;
    private boolean speeding;
    private boolean spawnControl;
    private boolean thirdPerson;
    private int jumpHeight;

    @SuppressWarnings("unused")
    private ServerHackControlPacket() {
    }

    public ServerHackControlPacket(boolean flying, boolean noclip, boolean speeding, boolean spawnControl, boolean thirdPerson, int jumpHeight) {
        this.flying = flying;
        this.noclip = noclip;
        this.speeding = speeding;
        this.spawnControl = spawnControl;
        this.thirdPerson = thirdPerson;
        this.jumpHeight = jumpHeight;
    }

    public boolean getFlying(){
        return this.flying;
    }
    public boolean getNoClip(){
        return this.noclip;
    }
    public boolean getSpeeding(){
        return this.speeding;
    }
    public boolean getSpawnControl(){
        return this.spawnControl;
    }
    public boolean getThirdPerson(){
        return this.thirdPerson;
    }
    public int getJumpHeight(){
        return this.jumpHeight;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.flying = in.readBoolean();
        this.noclip = in.readBoolean();
        this.speeding = in.readBoolean();
        this.spawnControl = in.readBoolean();
        this.thirdPerson = in.readBoolean();
        this.jumpHeight = in.readShort();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeBoolean(this.flying);
        out.writeBoolean(this.noclip);
        out.writeBoolean(this.speeding);
        out.writeBoolean(this.spawnControl);
        out.writeBoolean(this.thirdPerson);
        out.writeShort(this.jumpHeight);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
