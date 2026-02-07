package com.github.steveice10.mc.classic.protocol.data.game;
public class ExtNames{
  /**
   * Allows controlling how far away blocks can be placed/deleted (/Reach)
   */
  public static final String CLICKDISTANCE = "ClickDistance";

  /**
   * Allows showing blocks 50 - 65 (Cobblestone Slab - Stone Brick)
   */
  public static final String CUSTOMBLOCKS = "CustomBlocks";

  /**
   * Allows separating tab list from entities in current map
   */
  public static final String EXTPLAYERLIST = "ExtPlayerList";

  /**
   * Allows customing environment colors such as sky color (/Env)
   */
  public static final String ENVCOLORS = "EnvColors";

  /**
   * Allows changing models of entities (/Model)
   */
  public static final String CHANGEMODEL = "ChangeModel";

  /**
   * Allows customising weather (/Env)
   */
  public static final String ENVWEATHERTYPE = "EnvWeatherType";

  /**
   * Allows changing hacks permissions (e.g. speed) on the fly
   */
  public static final String HACKCONTROL = "HackControl";

  /**
   * Allows clients to properly render emotes
   */
  public static final String EMOTEFIX = "EmoteFix";

  /**
   * Allows messages to appear in special locations (e.g. top right)
   */
  public static final String MESSAGETYPES = "MessageTypes";

  /**
   * Allows typing multiline chat in clients
   */
  public static final String LONGERMESSAGES = "LongerMessages";

  /*
   * Allows sending block updates in a faster way
   */
  public static final String BULKBLOCKUPDATE = "BulkBlockUpdate";

  /*
   * Allows retrieving precise details on mouse clicks
   */
  public static final String PLAYERCLICK = "PlayerClick";

  /*
   * Allows entities to appear at positions past 1023
   */
  public static final String EXTENTITYPOSITIONS = "ExtEntityPositions";

  /*
   * Allows sending MOTD packets without also needing to resend map
   */
  public static final String INSTANTMOTD = "InstantMOTD";

  /*
   * Allows changing spawn point of players without teleporting them
   */
  public static final String SETSPAWNPOINT = "SetSpawnpoint";

  /*
   * Allows sending more precisely controlled teleports
   */
  public static final String EXTENTITYTELEPORT = "ExtEntityTeleport";

}
