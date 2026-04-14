package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerSetTextColorPacket implements Packet {
  private int red;
  private int green;
  private int blue;
  private int alpha;
  private char code;

  @SuppressWarnings("unused")
  private ServerSetTextColorPacket(){}

  public ServerSetTextColorPacket(int red, int green, int blue, int alpha, char code){
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
    this.code = code;
  }
  public int getRed(){
    return this.red;
  }
  public int getGreen(){
    return this.green;
  }
  public int getBlue(){
    return this.blue;
  }
  public int getAlpha(){
    return this.alpha;
  }
  public char getCode(){
    return this.code;
  }
  
  @Override
  public void read(NetInput in) throws IOException {
    this.red = (int) in.readUnsignedByte();
    this.green = (int) in.readUnsignedByte();
    this.blue = (int) in.readUnsignedByte();
    this.alpha = (int) in.readUnsignedByte();
    this.code = (char) in.readUnsignedByte();
  }
  @Override
  public void write(NetOutput out) throws IOException {
    out.writeByte((byte) this.red);
    out.writeByte((byte) this.green);
    out.writeByte((byte) this.blue);
    out.writeByte((byte) this.alpha);
    out.writeByte((byte) this.code);
  }
  @Override
  public boolean isPriority() {
      return false;
  }
}
