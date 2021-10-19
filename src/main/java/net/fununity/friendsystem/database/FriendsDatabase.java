package net.fununity.friendsystem.database;

import net.fununity.friendsystem.FriendSystem;
import net.fununity.misc.databasehandler.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * Database class to handle requests.
 * @author Niko
 * @since 0.0.1
 */
public class FriendsDatabase {

    public static final String TABLE = "system_friends_data";

    private static FriendsDatabase instance;

    /**
     * Get the instance of this class.
     * @return FriendsDatabase - singleton instance.
     * @since 0.0.1
     */
    public static FriendsDatabase getInstance() {
        if (instance == null)
            instance = new FriendsDatabase();
        return instance;
    }

    private final DatabaseHandler databaseHandler;

    /**
     * Instantiates the class.
     * @since 0.0.1
     */
    private FriendsDatabase() {
        this.databaseHandler = DatabaseHandler.getInstance();
    }

    /**
     * Get all friends of the uuid.
     * @param uuid UUID - uuid of player.
     * @return Map<UUID, OffsetDateTime> - friend, date of friendship
     * @since 0.0.1
     */
    public Map<UUID, OffsetDateTime> getFriends(UUID uuid) {
        Map<UUID, OffsetDateTime> friends = new HashMap<>();
        try (ResultSet set = this.databaseHandler.select(TABLE, Collections.singletonList("*"), "WHERE uuid='" + uuid + "' OR friend='" + uuid + "'")) {
            while (set != null && set.next()) {
                friends.put(UUID.fromString(
                        set.getString("friend").equals(uuid.toString()) ?
                                set.getString("uuid") : set.getString("friend")),
                        OffsetDateTime.parse(set.getString("date")));
            }
        } catch (SQLException exception) {
            FriendSystem.getInstance().getLogger().warning(exception.getMessage());
        }
        return friends;
    }

    /**
     * Removes a friend.
     * @param uuid UUID - UUID of player.
     * @param friend UUID - UUID of friend.
     * @since 0.0.1
     */
    public void removeFriend(UUID uuid, UUID friend) {
        this.databaseHandler.delete(TABLE, "WHERE uuid='" + uuid + "' AND friend='" + friend + "' OR uuid='" + friend + "' AND friend='" + uuid + "' LIMIT 1");
    }

    /**
     * Adding a new friend.
     * @param uuid UUID - uuid of player.
     * @param friend UUID - uuid of friend.
     * @since 0.0.1
     */
    public void addFriend(UUID uuid, UUID friend) {
        this.databaseHandler.insertIntoTable(TABLE, Arrays.asList(uuid.toString(), friend.toString(), OffsetDateTime.now().toString()), Arrays.asList("string", "string", "string"));
    }

}
