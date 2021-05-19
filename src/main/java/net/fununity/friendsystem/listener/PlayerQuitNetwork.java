package net.fununity.friendsystem.listener;

import net.fununity.friendsystem.FriendSystemBungee;
import net.fununity.friendsystem.database.FriendsDatabase;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.bungee.FunUnityAPI;
import net.fununity.main.api.bungee.player.APIPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.UUID;

/**
 * Listener class for player disconnecting the network.
 * @author Niko
 * @since 0.0.1
 */
public class PlayerQuitNetwork implements Listener {

    /**
     * Will be called, when a player disconnects the network.
     * @param event PlayerDisconnectEvent - Event that was triggered.
     * @since 0.0.1
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onDisconnect(PlayerDisconnectEvent event) {
        APIPlayer joinedPlayer = FunUnityAPI.getInstance().getPlayerHandler().getPlayer(event.getPlayer().getUniqueId());
        if (joinedPlayer != null) {
            String displayName = joinedPlayer.getDisplayName();
            FriendSystemBungee.getInstance().getProxy().getScheduler().runAsync(FriendSystemBungee.getInstance(), () -> {
                for (UUID uuid : FriendsDatabase.getInstance().getFriends(event.getPlayer().getUniqueId()).keySet()) {
                    APIPlayer player = FunUnityAPI.getInstance().getPlayerHandler().getPlayer(uuid);
                    if (player != null) {
                        player.sendMessage(TranslationKeys.FRIENDS_QUIT, "${name}", displayName);
                    }
                }
            });
        }
    }

}
