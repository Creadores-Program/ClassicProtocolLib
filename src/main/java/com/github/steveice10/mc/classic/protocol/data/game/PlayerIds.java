package com.github.steveice10.mc.classic.protocol.data.game;

/**
 * Player ID constants.
 */
public class PlayerIds {
    /**
     * Player ID used to represent the console when sent from a server.
     */
    public static final int CONSOLE = 255;

    /**
     * Player ID used to represent the current player when sent from a client.
     */
    public static final int SELF = 255;
    
    //CPE MessageTypes Ext

    /**
     * Player ID used to represent Chat
     */
    public static final int CHAT = 0;
    
    /**
     * Player IDs used to represent Status
     */
    public static final int STATUS1 = 1;

    public static final int STATUS2 = 2;

    public static final int STATUS3 = 3;

    /**
     * Player IDs used to represent Bottom right
     */
    public static final int BOTTOMRIGHT1 = 11;

    public static final int BOTTOMRIGHT2 = 12;

    public static final int BOTTOMRIGHT3 = 13;

    /**
     * Player ID used to represent Announcement
     */
    public static final int ANNOUNCEMENT = 100;
}
