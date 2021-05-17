package net.fununity.friendsystem;

import net.fununity.cloud.client.CloudClient;
import net.fununity.friendsystem.cloud.SpigotCloudListener;
import net.fununity.friendsystem.commands.FACommand;
import net.fununity.friendsystem.commands.FRCommand;
import net.fununity.friendsystem.commands.friends.FriendCommand;
import net.fununity.friendsystem.language.EnglishMessages;
import net.fununity.friendsystem.language.GermanMessages;
import net.fununity.friendsystem.listener.QuitListener;
import net.fununity.main.api.util.RegisterUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin class for spigot server.
 * @author Niko
 * @since 0.0.1
 */
public class FriendSystemSpigot extends JavaPlugin {

    private static FriendSystemSpigot instance;

    /**
     * Get the instance of this class.
     * @return FriendSystemSpigot - spigot instance.
     * @since 0.0.1
     */
    public static FriendSystemSpigot getInstance() {
        return instance;
    }

    /**
     * Will be called, when spigot plugin enables.
     * @since 0.0.1
     */
    @Override
    public void onEnable() {
        instance = this;
        FriendSystem.loadInstance(getLogger());

        new GermanMessages();
        new EnglishMessages();


        CloudClient.getInstance().getCloudEventManager().addCloudListener(new SpigotCloudListener());

        RegisterUtil registerUtil = new RegisterUtil(this);
        registerUtil.addCommands(new FriendCommand(), new FRCommand(), new FACommand());
        registerUtil.addListeners(new QuitListener());
        registerUtil.register();
    }
}
