package net.fununity.friendsystem.database;

import net.fununity.friendsystem.FriendSystem;
import net.fununity.main.api.FunUnityAPI;
import net.fununity.main.api.common.util.ISkin;
import net.fununity.main.api.player.APIPlayer;
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

    /**
     * Returns a map with uuid and {@link ISkin} from the online players of the given uuids.
     * @param uuids UUID[] - An array of all uuids to get the texture from.
     * @return Map<UUID, String[]> - A map with all players that are currently online and their data: displayname, boolean, skin.
     * @since 0.0.1
     */
    public Map<UUID, String[]> getDataFromOnlinePlayers(List<UUID> uuids) {
        Map<UUID, String[]> textures = new HashMap<>();
        List<UUID> players = new ArrayList<>(uuids);
        for (UUID uuid : uuids) {
            APIPlayer apiPlayer = FunUnityAPI.getInstance().getPlayerHandler().getPlayer(uuid);
            if (apiPlayer != null) {
                players.remove(uuid);
                textures.put(uuid, new String[]{apiPlayer.getDisplayName(), "1", apiPlayer.getDatabasePlayer().getPlayerTextures().getSkin()[0]});
            }
        }

        if (!players.isEmpty()) {
            StringBuilder builder = new StringBuilder("(");
            Iterator<UUID> iterator = players.iterator();
            while (iterator.hasNext()) {
                builder.append("'").append(iterator.next()).append("'");
                if(iterator.hasNext())
                    builder.append(", ");
            }
            builder.append(")");

            try (ResultSet select = DatabaseHandler.getInstance().select("players, player_skins, system_permission_players",
                    Arrays.asList("players.uuid", "name", "texture", "system_permission_players.id", "online", "last_login", "play_time"),
                    "WHERE players.uuid=player_skins.uuid AND players.uuid=system_permission_players.uuid AND players.uuid in " +
                            builder)) {
                while (select != null && select.next()) {
                    String[] array = {FunUnityAPI.getInstance().getPermissionManager().getGroup(select.getInt("system_permission_players.id")).getColor() + select.getString("name"),
                            select.getInt("online") + "",
                            select.getString("texture"),
                            select.getString("last_login"),
                            select.getInt("play_time") + ""};
                    textures.put(UUID.fromString(select.getString("uuid")), array);
                }
            } catch (SQLException exception) {
                FunUnityAPI.getInstance().getLogger().warning(exception.getMessage());
            }
        }

        return textures;
    }

}
