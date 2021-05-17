package net.fununity.friendsystem.cloud;

import net.fununity.cloud.common.events.cloud.CloudEvent;
import net.fununity.cloud.common.events.cloud.CloudEventListener;
import net.fununity.friendsystem.FriendSystem;
import net.fununity.friendsystem.FriendSystemSpigot;
import net.fununity.friendsystem.PlayerProfile;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.FunUnityAPI;
import net.fununity.main.api.player.APIPlayer;
import net.fununity.main.api.util.PlayerDataUtil;
import org.bukkit.Bukkit;

import java.util.UUID;

public class SpigotCloudListener implements CloudEventListener {

    @Override
    public void newCloudEvent(CloudEvent cloudEvent) {
        switch (cloudEvent.getId()) {
            case CloudEvent.FRIENDS_REQUEST_SEND:
                UUID player = (UUID) cloudEvent.getData().get(0);
                UUID friend = (UUID) cloudEvent.getData().get(1);
                APIPlayer friendPlayer = FunUnityAPI.getInstance().getPlayerHandler().getPlayer(friend);
                if (friendPlayer != null) {
                    PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(friend);
                    if (playerProfile.isCached(1))
                        playerProfile.getRequester().add(player);
                    Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), () ->
                            friendPlayer.sendMessage(TranslationKeys.FRIENDS_REQUEST_RECEIVED, "${name}", PlayerDataUtil.getPlayerName(player)));
                }
                break;
            case CloudEvent.FRIENDS_ADDED:
                player = (UUID) cloudEvent.getData().get(0);
                friend = (UUID) cloudEvent.getData().get(1);
                friendPlayer = FunUnityAPI.getInstance().getPlayerHandler().getPlayer(friend);
                if (friendPlayer != null) {
                    PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(friend);
                    playerProfile.unloadFriends();
                    playerProfile.getRequested().remove(player);
                    Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), () ->
                            friendPlayer.sendMessage(TranslationKeys.FRIENDS_NEW_FRIEND, "${name}", PlayerDataUtil.getPlayerName(player)));
                }
                break;
            case CloudEvent.FRIENDS_REMOVE:
                player = (UUID) cloudEvent.getData().get(0);
                friend = (UUID) cloudEvent.getData().get(1);
                friendPlayer = FunUnityAPI.getInstance().getPlayerHandler().getPlayer(friend);
                if (friendPlayer != null) {
                    PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(friend);
                    if (playerProfile.isCached(0))
                        playerProfile.getFriends().remove(player);
                    Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), () ->
                            friendPlayer.sendMessage(TranslationKeys.FRIENDS_REMOVED, "${name}", PlayerDataUtil.getPlayerName(player)));
                }
                break;
            default:
                break;
        }
    }
}
