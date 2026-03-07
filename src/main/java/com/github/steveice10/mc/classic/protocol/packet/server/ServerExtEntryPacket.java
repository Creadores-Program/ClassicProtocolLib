package com.github.steveice10.mc.classic.protocol.packet.server;

import com.github.steveice10.mc.classic.protocol.packet.ClassicPacketUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

public class ServerExtEntryPacket implements Packet {
	private int version;
	private String extName;

	@SuppressWarnings("unused")
	private ServerExtEntryPacket() {
	}

	public ServerExtEntryPacket(int version, String extName) {
		this.version = version;
		this.extName = extName;
	}

	public int getVersion() {
		return this.version;
	}

	public String getExtName() {
		return this.extName;
	}

	@Override
	public void read(NetInput in) throws IOException {
		this.extName = ClassicPacketUtil.readString(in);
        this.version = in.readInt();
	}

	@Override
	public void write(NetOutput out) throws IOException {
		ClassicPacketUtil.writeString(out, this.extName);
        out.writeInt(this.version);
	}

	@Override
	public boolean isPriority() {
		return false;
	}
}
