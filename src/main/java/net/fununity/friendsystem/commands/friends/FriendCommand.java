package net.fununity.friendsystem.commands.friends;

import net.fununity.friendsystem.gui.FriendsGUI;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.command.handler.APICommand;
import net.fununity.main.api.command.handler.APISubCommand;
import net.fununity.main.api.player.APIPlayer;
import org.bukkit.command.CommandSender;

public class FriendCommand extends APICommand{

    public FriendCommand() {
        super("friend", "", TranslationKeys.FRIENDS_COMMAND_FRIENDS_USAGE, TranslationKeys.FRIENDS_COMMAND_FRIENDS_DESCRIPTION, "freunde", "friends");
        addSubCommand(new FriendRemoveCommand());
        addSubCommand(new FriendAddCommand());
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        if (args.length != 0) {
           sendCommandUsage(apiPlayer);
            for (APISubCommand apiSubCommand : getSubCommandList())
                apiSubCommand.sendCommandUsage(apiPlayer);
           return;
        }
        FriendsGUI.openGUI(apiPlayer, 0);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] strings) {
        // not needed
    }
}
