package com.github.steveice10.mc.classic.protocol.packet.client;

import com.github.steveice10.mc.classic.protocol.packet.ClassicPacketUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ClientPlayerClickPacket implements Packet {
	private int button;
  private int action;
  private short yaw;
  private short pitch;
  private int targetEntityID;
  private short targetBlockX;
  private short targetBlockY;
  private short targetBlockZ;
	private int targetBlockFace;

	@SuppressWarnings("unused")
	private ClientPlayerClickPacket() {
	}

	public ClientPlayerClickPacket(int button, int action, short yaw, short pitch, int targetEntityID, short targetBlockX, short targetBlockY, short targetBlockZ, int targetBlockFace) {
		this.button = button;
		this.action = action;
    this.yaw = yaw;
    this.pitch = pitch;
    this.targetEntityID = targetEntityID;
    this.targetBlockX = targetBlockX;
    this.targetBlockY = targetBlockY;
    this.targetBlockZ = targetBlockZ;
    this.targetBlockFace = targetBlockFace;
	}

	public int getButton() {
		return this.button;
	}

	public int getAction() {
		return this.action;
	}
  public short getYaw(){
    return this.yaw;
  }
  public short getPitch(){
    return this.pitch;
  }
  public int getTargetEntityID(){
    return this.targetEntityID;
  }
  public short getTargetBlockX(){
    return this.targetBlockX;
  }
  public short getTargetBlockY(){
    return this.targetBlockY;
  }
  public short getTargetBlockZ(){
    return this.targetBlockZ;
  }
  public int getTargetBlockFace(){
    return this.targetBlockFace;
  }

	@Override
	public void read(NetInput in) throws IOException {
    this.button = in.readUnsignedByte();
    this.action = in.readUnsignedByte();
    this.yaw = in.readShort();
    this.pitch = in.readShort();
    this.targetEntityID = in.readUnsignedByte();
    this.targetBlockX = in.readShort();
    this.targetBlockY = in.readShort();
    this.targetBlockZ = in.readShort();
    this.targetBlockFace = in.readUnsignedByte();
	}

	@Override
	public void write(NetOutput out) throws IOException {
    out.writeByte(this.button);
    out.writeByte(this.action);
    out.writeShort(this.yaw);
    out.writeShort(this.pitch);
    out.writeByte(this.targetEntityID);
    out.writeShort(this.targetBlockX);
    out.writeShort(this.targetBlockY);
    out.writeShort(this.targetBlockZ);
    out.writeByte(this.targetBlockFace);
	}

	@Override
	public boolean isPriority() {
		return false;
	}
}
