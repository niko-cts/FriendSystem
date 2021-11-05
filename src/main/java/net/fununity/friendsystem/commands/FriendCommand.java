package net.fununity.friendsystem.commands;

import java.lang.StringBuilder;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.bungee.command.handler.APICommand;
import net.fununity.main.api.bungee.player.APIPlayer;
import net.md_5.bungee.api.CommandSender;

public class FriendCommand extends APICommand {

    public FriendCommand() {
        super("friend", "", TranslationKeys.FRIEND_COMMAND_FRIEND_USAGE, TranslationKeys.FRIEND_COMMAND_FRIEND_DESCRIPTION, "friendhelp");
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        if (args.length != 0) {
            StringBuilder builder = new StringBuilder();
            for(String a : args)
                builder.append(a).append(" ");
            Bukkit.dispatchCommand(apiPlayer.getPlayer(), "friends " + builder.toString());
            return;
        }
        apiPlayer.sendMessage(TranslationKeys.FRIEND_COMMAND_FRIEND_HELP);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] strings) {
        // not needed
    }
}
