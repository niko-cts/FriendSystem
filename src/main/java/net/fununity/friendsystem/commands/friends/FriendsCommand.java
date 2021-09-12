package net.fununity.friendsystem.commands.friends;

import net.fununity.friendsystem.gui.FriendsGUI;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.command.handler.APICommand;
import net.fununity.main.api.player.APIPlayer;
import org.bukkit.command.CommandSender;

public class FriendsCommand extends APICommand{

    public FriendsCommand() {
        super("friends", "", TranslationKeys.FRIENDS_COMMAND_FRIENDS_USAGE, TranslationKeys.FRIENDS_COMMAND_FRIENDS_DESCRIPTION, "freunde", "freund");
        addSubCommand(new FriendRemoveCommand());
        addSubCommand(new FriendAddCommand());
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        if (args.length != 0) {
           sendCommandUsage(apiPlayer);
           return;
        }
        FriendsGUI.openGUI(apiPlayer, 0);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] strings) {
        // not needed
    }
}
