package net.fununity.friendsystem.commands;

import net.fununity.friendsystem.commands.friends.FriendAddCommand;
import net.fununity.friendsystem.commands.friends.FriendRemoveCommand;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.command.handler.APICommand;
import net.fununity.main.api.player.APIPlayer;
import org.bukkit.command.CommandSender;

public class FriendCommand extends APICommand {

    public FriendCommand() {
        super("friend", "", TranslationKeys.FRIEND_COMMAND_FRIEND_USAGE, TranslationKeys.FRIEND_COMMAND_FRIEND_DESCRIPTION, "friendhelp");
        addSubCommand(new FriendAddCommand());
        addSubCommand(new FriendRemoveCommand());
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        apiPlayer.sendMessage(TranslationKeys.FRIEND_COMMAND_FRIEND_HELP);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] strings) {
        // not needed
    }


}
