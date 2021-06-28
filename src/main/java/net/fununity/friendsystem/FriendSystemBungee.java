package net.fununity.friendsystem;

import net.fununity.cloud.client.CloudClient;
import net.fununity.friendsystem.cloud.BungeeCloudListener;
import net.fununity.friendsystem.commands.FriendCommand;
import net.fununity.friendsystem.database.FriendsTableLoader;
import net.fununity.friendsystem.language.BungeeEnglishMessages;
import net.fununity.friendsystem.language.BungeeGermanMessages;
import net.fununity.friendsystem.listener.PlayerJoinsNetwork;
import net.fununity.friendsystem.listener.PlayerQuitNetwork;
import net.fununity.main.api.bungee.FunUnityAPI;
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

        new BungeeEnglishMessages();
        new BungeeGermanMessages();

        CloudClient.getInstance().getCloudEventManager().addCloudListener(new BungeeCloudListener());

        FunUnityAPI.getInstance().getCommandHandler().addCommands(new FriendCommand());
        getProxy().getPluginManager().registerListener(this, new PlayerJoinsNetwork());
        getProxy().getPluginManager().registerListener(this, new PlayerQuitNetwork());

        FriendsTableLoader.loadDefaultTables();
    }
}
