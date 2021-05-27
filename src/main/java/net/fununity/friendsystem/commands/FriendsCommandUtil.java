package net.fununity.friendsystem.commands;

import net.fununity.cloud.common.events.cloud.CloudEvent;
import net.fununity.friendsystem.FriendSystem;
import net.fununity.friendsystem.FriendSystemSpigot;
import net.fununity.friendsystem.PlayerProfile;
import net.fununity.friendsystem.database.FriendsDatabase;
import net.fununity.friendsystem.database.FriendsRequestsDatabase;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.FunUnityAPI;
import net.fununity.main.api.messages.MessagePrefix;
import net.fununity.main.api.player.APIPlayer;
import net.fununity.main.api.util.PlayerDataUtil;
import org.bukkit.Bukkit;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Util class for friend removes, requests and add.
 * @author Niko
 * @since 0.0.1
 */
public class FriendsCommandUtil {

    private FriendsCommandUtil() {
        throw new UnsupportedOperationException("FriendsCommandUtil is a utility class.");
    }

    /**
     * Removes a friend/request.
     * @param apiPlayer APIPlayer - the player who removes.
     * @param friend String - the name of friend
     * @since 0.0.1
     */
    public static void removeFriend(APIPlayer apiPlayer, String friend) {
        Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), () -> {
            if (friend.equalsIgnoreCase(apiPlayer.getPlayer().getName())) {
                apiPlayer.sendMessage(MessagePrefix.ERROR, TranslationKeys.FRIENDS_COMMAND_NOT_SELF);
                return;
            }

            UUID friendUUID = PlayerDataUtil.getPlayerUUID(friend);
            if (friendUUID == null) {
                apiPlayer.sendMessage(MessagePrefix.ERROR, net.fununity.main.api.messages.TranslationKeys.API_PLAYER_NOT_FOUND);
                return;
            }
            PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(apiPlayer.getUniqueId());
            if (playerProfile.getRequester().contains(friendUUID)) {
                playerProfile.getRequester().remove(friendUUID);
                apiPlayer.sendMessage(MessagePrefix.SUCCESS, TranslationKeys.FRIENDS_REQUEST_DENIED);
                return;
            }

            if (!playerProfile.getFriends().containsKey(friendUUID)) {
                apiPlayer.sendMessage(MessagePrefix.ERROR, TranslationKeys.FRIENDS_NO_FRIEND);
                return;
            }

            playerProfile.getFriends().remove(friendUUID);
            FriendsDatabase.getInstance().removeFriend(apiPlayer.getUniqueId(), friendUUID);
            FunUnityAPI.getInstance().getCloudClient().forwardToBungee(new CloudEvent(CloudEvent.FRIENDS_REMOVE).addData(apiPlayer.getUniqueId()).addData(friendUUID));
            apiPlayer.sendMessage(TranslationKeys.FRIENDS_REMOVED, "${name}", PlayerDataUtil.getPlayerName(friendUUID));
        });
    }

    /**
     * Adds a friend/Sends request.
     * @param apiPlayer APIPlayer - the player who adds.
     * @param friend String - the name of friend.
     * @since 0.0.1
     */
    public static void addFriend(APIPlayer apiPlayer, String friend) {
        Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), () -> {
            if (friend.equalsIgnoreCase(apiPlayer.getPlayer().getName())) {
                apiPlayer.sendMessage(MessagePrefix.ERROR, TranslationKeys.FRIENDS_COMMAND_NOT_SELF);
                return;
            }

            UUID friendUUID = PlayerDataUtil.getPlayerUUID(friend);
            if (friendUUID == null) {
                apiPlayer.sendMessage(MessagePrefix.ERROR, net.fununity.main.api.messages.TranslationKeys.API_PLAYER_NOT_FOUND);
                return;
            }
            PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(apiPlayer.getUniqueId());

            for (int i = 100; i <= 500; i += 50) {
                if (playerProfile.getFriends().size() >= i) {
                    if (apiPlayer.hasPermission("friends.amount." + i)){
                        apiPlayer.sendMessage(MessagePrefix.ERROR, net.fununity.main.api.messages.TranslationKeys.API_COMMAND_PREMIUM_ONLY);
                        return;
                    }
                } else
                    break;
            }

            if (playerProfile.getFriends().containsKey(friendUUID)) {
                apiPlayer.sendMessage(MessagePrefix.ERROR, TranslationKeys.FRIENDS_COMMAND_ALREADY_FRIENDS);
                return;
            }

            if (playerProfile.getRequested().contains(friendUUID)) {
                apiPlayer.sendMessage(MessagePrefix.ERROR, TranslationKeys.FRIENDS_COMMAND_REQUEST_ALREADY_SEND);
                return;
            }

            if (playerProfile.getRequester().contains(friendUUID)) {
                OffsetDateTime now = OffsetDateTime.now();
                playerProfile.getRequester().remove(friendUUID);
                playerProfile.getFriends().put(friendUUID, now);
                FunUnityAPI.getInstance().getCloudClient().forwardToBungee(new CloudEvent(CloudEvent.FRIENDS_ADDED).addData(apiPlayer.getUniqueId()).addData(friendUUID));
                apiPlayer.sendMessage(TranslationKeys.FRIENDS_NEW_FRIEND, "${name}", friend);
                FriendsDatabase.getInstance().addFriend(apiPlayer.getUniqueId(), friendUUID);
                FriendsRequestsDatabase.getInstance().removeRequest(friendUUID, apiPlayer.getUniqueId());
            } else {
                apiPlayer.sendMessage(MessagePrefix.SUCCESS, TranslationKeys.FRIENDS_REQUEST_SEND);
                FunUnityAPI.getInstance().getCloudClient().forwardToBungee(new CloudEvent(CloudEvent.FRIENDS_REQUEST_SEND).addData(apiPlayer.getUniqueId()).addData(friendUUID));
                playerProfile.getRequested().add(friendUUID);
                FriendsRequestsDatabase.getInstance().newRequest(apiPlayer.getUniqueId(), friendUUID);
            }
        });
    }

    public static void removeRequest(UUID uuid, UUID requestedUUID) {
        PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(uuid);
        if (playerProfile.isCached(2))
            playerProfile.getRequested().remove(requestedUUID);
        FriendsRequestsDatabase.getInstance().removeRequest(uuid, requestedUUID);
    }
}
