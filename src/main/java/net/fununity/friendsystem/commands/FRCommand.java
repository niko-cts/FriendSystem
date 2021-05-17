package net.fununity.friendsystem.commands;

import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.command.handler.APICommand;
import net.fununity.main.api.player.APIPlayer;
import org.bukkit.command.CommandSender;

public class FRCommand extends APICommand {

    public FRCommand() {
        super("fr", "", TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_USAGE, TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_DESCRIPTION, "friendremove");
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        if(args.length == 0) {
            sendCommandUsage(apiPlayer);
            return;
        }
        FriendsCommandUtil.removeFriend(apiPlayer, args[0]);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] strings) {
        // not needed
    }
}
