package net.fununity.friendsystem.commands.friends;

import net.fununity.friendsystem.commands.FriendsCommandUtil;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.command.handler.APISubCommand;
import net.fununity.main.api.player.APIPlayer;
import org.bukkit.command.CommandSender;

public class FriendAddCommand extends APISubCommand {

    public FriendAddCommand() {
        super("add", "", TranslationKeys.FRIENDS_COMMAND_FRIENDADD_USAGE, TranslationKeys.FRIENDS_COMMAND_FRIENDADD_DESCRIPTION, 1, "request", "invite");
    }

    @Override
    public void onCommand(APIPlayer apiPlayer, String[] args) {
        FriendsCommandUtil.addFriend(apiPlayer, args[0]);
    }

    @Override
    public void onConsole(CommandSender commandSender, String[] strings) {
        // NOT NEEDED
    }
}
