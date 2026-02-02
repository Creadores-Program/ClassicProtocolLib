package com.github.steveice10.mc.classic.protocol.packet.client;

import com.github.steveice10.mc.classic.protocol.ClassicConstants;
import com.github.steveice10.mc.classic.protocol.packet.ClassicPacketUtil;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

/**
 * Sent by a client to identify itself to the server.
 */
public class ClientIdentificationPacket implements Packet {
    private int protocolVersion;
    private String username;
    private String verificationKey;
    private int unused;
    private boolean isCpe;

    @SuppressWarnings("unused")
    private ClientIdentificationPacket() {
    }

    /**
     * Creates a new ClientIdentificationPacket instance.
     *
     * @param username        Username of the client.
     * @param verificationKey Verification key of the client.
     */
    public ClientIdentificationPacket(String username, String verificationKey) {
        this(username, verificationKey, 0);
    }

    /**
     * Creates a new ClientIdentificationPacket instance.
     *
     * @param username        Username of the client.
     * @param verificationKey Verification key of the client.
     * @param unused          Unused byte.
     */
    public ClientIdentificationPacket(String username, String verificationKey, int unused) {
        this.protocolVersion = ClassicConstants.PROTOCOL_VERSION;
        this.username = username;
        this.verificationKey = verificationKey;
        this.unused = unused;
        if(((byte) unused) == 0x42){
			this.isCpe = true;
		}
    }
    /**
     * Creates a new ClientIdentificationPacket instance.
     *
     * @param username        Username of the client.
     * @param verificationKey Verification key of the client.
     * @param isCpe          Support CPE.
     */
    public ClientIdentificationPacket(String username, String verificationKey, boolean isCpe) {
        this.protocolVersion = ClassicConstants.PROTOCOL_VERSION;
        this.username = username;
        this.verificationKey = verificationKey;
        if(isCpe){
            this.unused = 0x42;
        }else{
            this.unused = 0;
        }
		this.isCpe = isCpe;
    }

    /**
     * Gets the protocol version of the client.
     *
     * @return The client's protocol version.
     */
    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    /**
     * Gets the username of the client.
     *
     * @return The client's username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the verification key of the client.
     *
     * @return The client's verification key.
     */
    public String getVerificationKey() {
        return this.verificationKey;
    }

    /**
     * Gets the unused byte of this packet.
     *
     * @return The packet's unused byte.
     */
    public int getUnused() {
        return this.unused;
    }

    public boolean isCPE(){
        return this.isCpe;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.protocolVersion = in.readUnsignedByte();
        this.username = ClassicPacketUtil.readString(in).replaceAll("[^A-Za-z0-9_-]+", "");
        this.verificationKey = ClassicPacketUtil.readString(in);
        this.unused = in.readUnsignedByte();
        if(((byte) unused) == 0x42){
			this.isCpe = true;
		}
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.protocolVersion);
        ClassicPacketUtil.writeString(out, this.username);
        ClassicPacketUtil.writeString(out, this.verificationKey);
        out.writeByte(this.unused);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
