package net.fununity.friendsystem.commands;

import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.command.handler.APICommand;
import net.fununity.main.api.player.APIPlayer;
import org.bukkit.command.CommandSender;

public class FACommand extends APICommand {

    public FACommand() {
        super("fa", "", TranslationKeys.FRIENDS_COMMAND_FRIENDADD_USAGE, TranslationKeys.FRIENDS_COMMAND_FRIENDADD_DESCRIPTION, "friendadd", "friendsadd");
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        if (args.length == 0) {
            sendCommandUsage(apiPlayer);
            return;
        }
        FriendsCommandUtil.addFriend(apiPlayer, args[0]);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] strings) {
        // NOT NEEDED
    }
}
