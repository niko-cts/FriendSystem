package net.fununity.friendsystem;

import net.fununity.cloud.client.CloudClient;
import net.fununity.friendsystem.cloud.BungeeCloudListener;
import net.fununity.friendsystem.database.FriendsTableLoader;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Plugin class for bungee server.
 * @author Niko
 * @since 0.0.1
 */
public class FriendSystemBungee extends Plugin {

    private static FriendSystemBungee instance;
    public static FriendSystemBungee getInstance() {
        return instance;
    }

    /**
     * Will be called, when bungee plugin enables.
     * @since 0.0.1
     */
    @Override
    public void onEnable() {
        instance = this;
        FriendSystem.loadInstance(getLogger());

        CloudClient.getInstance().getCloudEventManager().addCloudListener(new BungeeCloudListener());

        FriendsTableLoader.loadDefaultTables();
    }
}
