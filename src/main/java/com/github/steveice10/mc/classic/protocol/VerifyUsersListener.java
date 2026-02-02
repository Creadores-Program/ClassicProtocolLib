package com.github.steveice10.mc.classic.protocol;

import com.github.steveice10.mc.classic.protocol.data.heartbeat.ServerInfo;
import com.github.steveice10.mc.classic.protocol.data.heartbeat.ServerInfoBuilder;
import com.github.steveice10.packetlib.Server;
import com.github.steveice10.packetlib.event.server.ServerAdapter;
import com.github.steveice10.packetlib.event.server.ServerBoundEvent;
import com.github.steveice10.packetlib.event.server.ServerClosingEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.math.BigInteger;

public class VerifyUsersListener extends ServerAdapter {
    private Heartbeat heartbeat;

    @Override
    public void serverBound(ServerBoundEvent event) {
        String salt = new BigInteger(130, new SecureRandom()).toString(32);
        event.getServer().setGlobalFlag(ClassicConstants.SALT_KEY, salt);
        
        this.heartbeat = new Heartbeat(event.getServer());
        new Thread(this.heartbeat, "HeartbeatThread").start();
    }

    @Override
    public void serverClosing(ServerClosingEvent event) {
        if(this.heartbeat != null) {
            this.heartbeat.stopBeating();
            this.heartbeat = null;
        }
    }

    private static class Heartbeat implements Runnable {
        private Server server;
        private boolean beating = true;
        private static final int BEAT_INTERVAL = 45000;
        private static final String HEARTBEAT_URL = "https://www.classicube.net/server/heartbeat/";

        public Heartbeat(Server server) {
            this.server = server;
        }

        public void stopBeating() {
            this.beating = false;
        }

        @Override
        public void run() {
            long lastBeat = System.currentTimeMillis() - BEAT_INTERVAL;
            while(this.beating) {
                long time = System.currentTimeMillis();
                if(time - lastBeat >= BEAT_INTERVAL) {
                    lastBeat = time;
                    sendBeat();
                }
                
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    break;
                }
            }
        }

        private void sendBeat() {
            if(!this.server.hasGlobalFlag(ClassicConstants.SERVER_INFO_BUILDER_KEY)) return;

            BufferedReader reader = null;
            try {
                ServerInfo info = this.server.<ServerInfoBuilder>getGlobalFlag(ClassicConstants.SERVER_INFO_BUILDER_KEY).build(this.server);
                String salt = this.server.getGlobalFlag(ClassicConstants.SALT_KEY);

                StringBuilder query = new StringBuilder();
                query.append("name=").append(URLEncoder.encode(info.getName(), "UTF-8"));
                query.append("&port=").append(info.getPort());
                query.append("&users=").append(info.getPlayers());
                query.append("&max=").append(info.getMaxPlayers());
                query.append("&public=").append(info.isPublic());
                query.append("&salt=").append(URLEncoder.encode(salt, "UTF-8"));
                query.append("&software=").append(URLEncoder.encode("ClassicProtocolLib", "UTF-8"));
                query.append("&web=false");

                URL url = new URL(HEARTBEAT_URL + "?" + query.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("User-Agent", "ClassicProtocolLib");
                conn.setConnectTimeout(10000);

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String resp = reader.readLine();

                if (resp != null && resp.startsWith("http")) {
                    this.server.setGlobalFlag(ClassicConstants.SERVER_URL_KEY, resp);
                } else {
                    System.err.println("ClassiCube Heartbeat fail: " + resp);
                }

            } catch(Exception e) {
                System.err.println("Error heartbeat to ClassiCube.");
                e.printStackTrace();
            } finally {
                if(reader != null) try { reader.close(); } catch(IOException ignored) {}
            }
        }
    }
}
