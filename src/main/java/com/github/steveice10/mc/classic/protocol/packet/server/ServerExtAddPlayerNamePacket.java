package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.mc.classic.protocol.packet.ClassicPacketUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerExtAddPlayerNamePacket implements Packet {
    private short nameId;
    private String playerName;
    private String listName;
    private String groupName;
    private int groupRank;

    @SuppressWarnings("unused")
    private ServerExtAddPlayerNamePacket() {
    }

    public ServerExtAddPlayerNamePacket(short nameId, String playerName, String listName, String groupName, int groupRank) {
        this.nameId = nameId;
        this.playerName = playerName;
        this.listName = listName;
        this.groupName = groupName;
        this.groupRank = groupRank;
    }

    public short getNameId() {
        return this.nameId;
    }
    public String getPlayerName(){
        return this.playerName;
    }
    public String getListName(){
        return this.listName;
    }
    public String getGroupName(){
        return this.groupName;
    }
    public int getGroupRank(){
        return this.groupRank;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.nameId = in.readShort();
        this.playerName = ClassicPacketUtil.readString(in);
        this.listName = ClassicPacketUtil.readString(in);
        this.groupName = ClassicPacketUtil.readString(in);
        this.groupRank = in.readUnsignedByte();
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeShort(this.nameId);
        ClassicPacketUtil.writeString(out, this.playerName);
        ClassicPacketUtil.writeString(out, this.listName);
        ClassicPacketUtil.writeString(out, this.groupName);
        out.writeByte(this.groupRank);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
