package net.fununity.friendsystem.listener;

import net.fununity.friendsystem.FriendSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        FriendSystem.getInstance().removeCache(event.getPlayer().getUniqueId());
    }

}
