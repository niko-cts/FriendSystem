package net.fununity.friendsystem.commands.friends;

import net.fununity.friendsystem.commands.FriendsCommandUtil;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.command.handler.APISubCommand;
import net.fununity.main.api.player.APIPlayer;
import org.bukkit.command.CommandSender;

public class FriendRemoveCommand extends APISubCommand {

    public FriendRemoveCommand() {
        super("remove", "", TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_USAGE, TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_DESCRIPTION, 1, "delete", "decline");
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        FriendsCommandUtil.removeFriend(apiPlayer, args[0]);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] strings) {
        // NOT NEEDED
    }
}
