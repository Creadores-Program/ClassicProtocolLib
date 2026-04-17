package com.github.steveice10.mc.classic.protocol;

/**
 * Contains various protocol constants.
 */
public class ClassicConstants {
    /**
     * Current game version.
     */
    public static final String GAME_VERSION = "0.30";

    /**
     * Current protocol version.
     */
    public static final int PROTOCOL_VERSION = 7;

    /**
     * Flag key for a session's player username.
     * <p>
     * Available on both clients and servers, set automatically based on provided username.
     */
    public static final String USERNAME_KEY = "username";

    /**
     * Flag key for Support CPE.
     * <p>
     * Available on both clients, set automatically based on provided isCPE.
     */
    public static final String ISCPE = "iscpe";

    /**
     * Flag key for a session's verification key.
     * <p>
     * Available only on clients, set automatically based on provided key.
     */
    public static final String VERIFICATION_KEY = "verification-key";

    /**
     * Flag key for a session's server info builder.
     * <p>
     * Available only on servers, set by user.
     */
    public static final String SERVER_INFO_BUILDER_KEY = "server-info-builder";

    /**
     * Flag key for a session's server URL.
     * <p>
     * Available only on servers, set automatically by server list heartbeat.
     */
    public static final String SERVER_URL_KEY = "server-url";

    /**
     * Flag key for a session's server list salt.
     * <p>
     * Available only on servers, set automatically by server list heartbeat.
     */
    public static final String SALT_KEY = "salt";
}
