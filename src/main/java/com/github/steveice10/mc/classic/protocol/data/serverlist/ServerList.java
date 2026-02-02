package com.github.steveice10.mc.classic.protocol.data.serverlist;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import com.github.steveice10.mc.classic.protocol.exception.AuthenticationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains methods for communicating with the Minecraft Classic server list.
 */
public class ServerList {
    static {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }
    private static final String API_SERVERS = "https://www.classicube.net/api/servers";
    private static final String API_LOGIN = "https://www.classicube.net/api/login";

    /**
     * Logs in to classicube.net.
     *
     * @param username Username to log in with.
     * @param password Password to log in with.
     * @throws AuthenticationException If an error occurs while authenticating with classicube.net.
     */
    public static String login(String username, String password) throws AuthenticationException {
        try {
            String initialResponse = fetchUrl(API_LOGIN, "", "GET");
            JSONObject initialJson = JSON.parseObject(initialResponse);
        
            String initialToken = initialJson.getString("token");
            if (initialToken == null) {
                 throw new AuthenticationException("Login attempt unsuccessful.");
            }

            String params = "username=" + URLEncoder.encode(username, "UTF-8") 
                      + "&password=" + URLEncoder.encode(password, "UTF-8")
                      + "&token=" + URLEncoder.encode(initialToken, "UTF-8");
        
            String authResponse = fetchUrl(API_LOGIN, params, "POST");
            JSONObject authJson = JSON.parseObject(authResponse);

            if (authJson.getBooleanValue("authenticated")) {
                return authJson.getString("token");
            } else {
                JSONArray errors = authJson.getJSONArray("errors");
                throw new AuthenticationException("Login attempt unsuccessful: " + (errors != null ? errors.toString() : "Incorrect credentials"));
            }
        } catch (IOException e) {
            throw new AuthenticationException("Login attempt unsuccessful.");
        }
    }

    /**
     * Gets a map of servers' names to their list information from the server list.
     *
     * @return A map of servers' names and list information.
     */
    public static Map<String, ServerListInfo> getServers() {
        Map<String, ServerListInfo> servers = new HashMap<>();
        String response = fetchUrl(API_SERVERS, "", "GET");
        
        JSONObject root = JSON.parseObject(response);
        JSONArray serversArray = root.getJSONArray("servers");

        for (int i = 0; i < serversArray.size(); i++) {
            JSONObject s = serversArray.getJSONObject(i);
            
            String name = s.getString("name");
            String hash = s.getString("hash");
            int players = s.getIntValue("players");
            int max = s.getIntValue("maxplayers");
            String uptime = "N/A";
            servers.put(name, new ServerListInfo("https://www.classicube.net/server/play/" + hash, name, players, max, uptime));
        }

        return servers;
    }

    /**
     * Gets a server's list information from the server list.
     *
     * @param name Name of the server.
     * @return The server's information.
     */
    public static ServerListInfo getServerListInfo(String name) {
        return getServers().get(name);
    }

    /**
     * Gets a server's URL information from the server list.
     *
     * @param url URL of the server.
     * @return The server's URL information.
     */
    public static ServerURLInfo getServerURLInfo(String url) {
        String hash = serverUrl.substring(serverUrl.lastIndexOf("/") + 1);
        
        String response = fetchUrl(API_SERVERS, "", "GET");
        JSONObject root = JSON.parseObject(response);
        JSONArray serversArray = root.getJSONArray("servers");

        for (int i = 0; i < serversArray.size(); i++) {
            JSONObject s = serversArray.getJSONObject(i);
            if (s.getString("hash").equals(hash)) {
                return new ServerURLInfo(
                    s.getString("ip"),
                    s.getIntValue("port"),
                    s.getString("name"),
                    s.getString("hash")
                );
            }
        }
        return null;
    }

    private static String fetchUrl(String urlStr, String params, String method) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "ClassicProtocolLib");
            conn.setRequestMethod(method);
            conn.setConnectTimeout(5000);

            if (method.equals("POST") && !params.isEmpty()) {
                conn.setDoOutput(true);
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(params.getBytes("UTF-8"));
                }
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) sb.append(line);
            }
        } catch (Exception e) {
            return "{}";
        }
        return sb.toString();
    }

    private static String getAppletParameter(String page, String param) {
        String str = "param name=\"" + param + "\" value=\"";
        int index = page.indexOf(str);
        if(index > 0) {
            index += str.length();
            int index2 = page.indexOf("\"", index);
            if(index2 > 0) {
                return page.substring(index, index2);
            }
        }

        return "";
    }
}
