package net.fununity.friendsystem;

import net.fununity.friendsystem.database.FriendsRequestsDatabase;
import net.fununity.friendsystem.database.FriendsDatabase;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Player profile class.
 * @author Niko
 * @since 0.0.1
 */
public class PlayerProfile {

    private final UUID uuid;
    private Map<UUID, OffsetDateTime> friends;
    private List<UUID> requester;
    private List<UUID> requested;

    /**
     * Instantiates the class.
     * @param uuid UUID - uuid of player.
     * @since 0.0.1
     */
    public PlayerProfile(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Unloads the friends cache.
     * @since 0.0.1
     */
    public void unloadFriends() {
        friends = null;
    }

    /**
     * Get all friends from the player.
     * @return Map<UUID, OffsetDateTime> - All friends.
     * @since 0.0.1
     */
    public Map<UUID, OffsetDateTime> getFriends() {
        if (friends == null) {
           this.friends = FriendsDatabase.getInstance().getFriends(uuid);
        }
        return friends;
    }

    /**
     * Get all requester.
     * @return List<UUID> - List of all players, who requested to be friends.
     * @since 0.0.1
     */
    public List<UUID> getRequester() {
        if (requester == null) {
            this.requester = FriendsRequestsDatabase.getInstance().getRequester(uuid);
        }
        return requester;
    }

    /**
     * Get all requested friends.
     * @return List<UUID> - List of all players to be friends with.
     * @since 0.0.1
     */
    public List<UUID> getRequested() {
        if (requested == null) {
            this.requested = FriendsRequestsDatabase.getInstance().getRequested(uuid);
        }
        return requested;
    }

    /**
     * Get if friends, requester or requested list is cached.
     * @param i int - id (0-2)
     * @return boolean - is cached.
     * @since 0.0.1
     */
    public boolean isCached(int i) {
        if (i == 0)
            return friends != null;
        if (i == 1)
            return requester != null;
        if (i == 2)
            return requested != null;
        return false;
    }
}
