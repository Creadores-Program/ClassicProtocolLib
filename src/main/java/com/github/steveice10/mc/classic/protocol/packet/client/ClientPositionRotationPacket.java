package com.github.steveice10.mc.classic.protocol.packet.client;

import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import com.github.steveice10.packetlib.packet.Packet;

import java.io.IOException;

/**
 * Sent by a client to set its position and rotation.
 */
public class ClientPositionRotationPacket implements Packet {
    private int unused;
    private float x;
    private float y;
    private float z;
    private float yaw;
    private float pitch;

    @SuppressWarnings("unused")
    private ClientPositionRotationPacket() {
    }

    /**
     * Creates a new ClientPositionRotationPacket instance.
     *
     * @param x     X of the client.
     * @param y     Y of the client.
     * @param z     Z of the client.
     * @param yaw   Yaw of the client.
     * @param pitch Pitch of the client.
     */
    public ClientPositionRotationPacket(float x, float y, float z, float yaw, float pitch) {
        this(255, x, y, z, yaw, pitch);
    }

    /**
     * Creates a new ClientPositionRotationPacket instance.
     *
     * @param unused Unused byte.
     * @param x      X of the client.
     * @param y      Y of the client.
     * @param z      Z of the client.
     * @param yaw    Yaw of the client.
     * @param pitch  Pitch of the client.
     */
    public ClientPositionRotationPacket(int unused, float x, float y, float z, float yaw, float pitch) {
        this.unused = unused;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Gets the unused byte of this packet.
     *
     * @return The packet's unused byte.
     */
    public int getUnused() {
        return this.unused;
    }

    /**
     * Gets the X of the client.
     *
     * @return The client's X.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the Y of the client.
     *
     * @return The client's Y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Gets the Z of the client.
     *
     * @return The client's Z.
     */
    public double getZ() {
        return this.z;
    }

    /**
     * Gets the yaw of the client.
     *
     * @return The client's yaw.
     */
    public float getYaw() {
        return this.yaw;
    }

    /**
     * Gets the pitch of the client.
     *
     * @return The client's pitch.
     */
    public float getPitch() {
        return this.pitch;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.unused = in.readUnsignedByte();
        this.x = (float) in.readShort() / 32;
        this.y = (float) in.readShort() / 32;
        this.z = (float) in.readShort() / 32;
        this.yaw = (in.readUnsignedByte() * 360) / 256f;
        this.pitch = (in.readUnsignedByte() * 360) / 256f;
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeByte(this.unused);
        out.writeShort((short) (this.x * 32));
        out.writeShort((short) (this.y * 32));
        out.writeShort((short) (this.z * 32));
        out.writeByte((byte) ((int) (this.yaw * 256 / 360) & 255));
        out.writeByte((byte) ((int) (this.pitch * 256 / 360) & 255));
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
