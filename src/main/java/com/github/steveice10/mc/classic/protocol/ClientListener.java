package com.github.steveice10.mc.classic.protocol;

import com.github.steveice10.mc.classic.protocol.packet.client.ClientIdentificationPacket;
import com.github.steveice10.mc.classic.protocol.packet.server.ServerDisconnectPacket;
import com.github.steveice10.packetlib.event.session.ConnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;

/**
 * Listener used on Minecraft Classic client sessions.
 */
public class ClientListener extends SessionAdapter {
    @Override
    public void connected(ConnectedEvent event) {
        event.getSession().send(new ClientIdentificationPacket(event.getSession().<String>getFlag(ClassicConstants.USERNAME_KEY), event.getSession().<String>getFlag(ClassicConstants.VERIFICATION_KEY), event.getSession().<Boolean>getFlag(ClassicConstants.ISCPE, false)));
    }

    @Override
    public void packetReceived(PacketReceivedEvent event) {
        if(event.getPacket() instanceof ServerDisconnectPacket) {
            event.getSession().disconnect(event.<ServerDisconnectPacket>getPacket().getReason());
        }
    }
}
