package net.fununity.friendsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * The friend system main class.
 * @author Niko
 * @since 0.0.1
 */
public class FriendSystem {

    private static FriendSystem instance;

    /**
     * Loads the instance.
     * @see FriendSystemSpigot
     * @see FriendSystemBungee
     * @param logger Logger - the logger for the plugin.
     * @since 0.0.1
     */
    protected static void loadInstance(Logger logger) {
        instance = new FriendSystem(logger);
    }

    /**
     * Get the instance of this class.
     * @return {@link FriendSystem} - the friend system instance.
     * @since 0.0.1
     */
    public static FriendSystem getInstance() {
        return instance;
    }

    private final Logger logger;
    private final Map<UUID, PlayerProfile> playerProfiles;

    /**
     * Instantiates the class.
     * @param logger Logger - the logger for the plugin.
     * @since 0.0.1
     */
    private FriendSystem (Logger logger) {
        this.logger = logger;
        this.playerProfiles = new HashMap<>();
    }

    /**
     * Get the logger for the plugin.
     * @return Logger - the logger.
     * @since 0.0.1
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Removes a player from the cache.
     * @see net.fununity.friendsystem.listener.QuitListener
     * @param uuid UUID - uuid of player.
     * @since 0.0.1
     */
    public void removeCache(UUID uuid) {
        playerProfiles.remove(uuid);
    }

    /**
     * Gets the player profile of a player.
     * @param uuid UUID - uuid of the player.
     * @return {@link PlayerProfile} - the friends profile.
     * @since 0.0.1
     */
    public PlayerProfile getPlayerProfile(UUID uuid) {
        if (playerProfiles.containsKey(uuid))
            return playerProfiles.get(uuid);
        PlayerProfile playerProfile = new PlayerProfile(uuid);
        playerProfiles.put(uuid, playerProfile);
        return playerProfile;
    }
}
