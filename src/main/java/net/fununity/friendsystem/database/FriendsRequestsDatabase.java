package net.fununity.friendsystem.database;

import net.fununity.friendsystem.FriendSystem;
import net.fununity.misc.databasehandler.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Database class to handle requests.
 * @author Niko
 * @since 0.0.1
 */
public class FriendsRequestsDatabase {

    public static final String TABLE = "system_friends_requests";

    private static FriendsRequestsDatabase instance;

    /**
     * Get the instance of this class.
     * @return FriendRequestDatabase - singleton instance.
     * @since 0.0.1
     */
    public static FriendsRequestsDatabase getInstance() {
        if (instance == null)
            instance = new FriendsRequestsDatabase();
        return instance;
    }

    private final DatabaseHandler databaseHandler;

    /**
     * Instantiates the class.
     * @since 0.0.1
     */
    private FriendsRequestsDatabase() {
        this.databaseHandler = DatabaseHandler.getInstance();
    }

    /**
     * Get all requester from the uuid.
     * @param uuid UUID - uuid of player.
     * @return List<UUID> - List of all friend requester.
     * @since 0.0.1
     */
    public List<UUID> getRequester(UUID uuid) {
        return getSpecific("requester", "WHERE requested='" + uuid + "'");
    }

    /**
     * Get all requested from the uuid.
     * @param uuid UUID - uuid of player.
     * @return List<UUID> - List of all requested friends.
     * @since 0.0.1
     */
    public List<UUID> getRequested(UUID uuid) {
        return getSpecific("requested", "WHERE requester='" + uuid + "'");
    }

    /**
     * Used to get a specific attribute from a uuid.
     * @param get String - what to get
     * @param where String - where clause.
     * @return List<UUID> - the list of uuid's
     * @since 0.0.1
     */
    private List<UUID> getSpecific(String get, String where) {
        List<UUID> list = new ArrayList<>();
        try (ResultSet set = this.databaseHandler.select(TABLE, Collections.singletonList(get), where)) {
            while (set != null && set.next())
                list.add(UUID.fromString(set.getString(get)));

        } catch (SQLException exception) {
            FriendSystem.getInstance().getLogger().warning(exception.getMessage());
        }

        return list;
    }

    /**
     * Insert a new friend request.
     * @param requester UUID - the requester.
     * @param requested UUID - the requested.
     * @since 0.0.1
     */
    public void newRequest(UUID requester, UUID requested) {
        this.databaseHandler.insertIntoTable(TABLE, Arrays.asList(requester.toString(), requested.toString()), Arrays.asList("string", "string"));
    }

    /**
     * Removes a friend request.
     * @param requester UUID - UUID of requester.
     * @param requested UUID - UUID of requested.
     * @since 0.0.1
     */
    public void removeRequest(UUID requester, UUID requested) {
        this.databaseHandler.delete(TABLE, "WHERE requester='" + requester + "' AND requested='" + requested + "' LIMIT 1");
    }

}
