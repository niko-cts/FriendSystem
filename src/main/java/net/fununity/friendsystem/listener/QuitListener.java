package net.fununity.friendsystem.listener;

import net.fununity.friendsystem.FriendSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listener class for players quitting a spigot server.
 * @author Niko
 * @since 0.0.1
 */
public class QuitListener implements Listener {

    /**
     * Will be called, when a player leaves the spigot server.
     * @param event PlayerQuitEvent - Event that was triggered.
     * @since 0.0.1
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        FriendSystem.getInstance().removeCache(event.getPlayer().getUniqueId());
    }

}
