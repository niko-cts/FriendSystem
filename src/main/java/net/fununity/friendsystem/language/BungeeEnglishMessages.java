package net.fununity.friendsystem.language;

import net.fununity.main.api.common.messages.MessageList;
import net.fununity.main.api.common.util.SpecialChars;
import net.fununity.misc.translationhandler.TranslationHandler;
/**
 * Translation class for english bungee translations.
 * @see MessageList
 * @author Niko
 * @since 0.0.1
 */
public class BungeeEnglishMessages extends MessageList {
    /**
     * Instantiates the class.
     * Inserts english bungee translations.
     * @since 0.0.1
     */
    public BungeeEnglishMessages() {
        super(TranslationHandler.getInstance().getLanguageHandler().getLanguageByCode("en"));

        String prefix = "&6❤ Friends &8" + SpecialChars.DOUBLE_ARROW_RIGHT + " ";
        add(TranslationKeys.FRIENDS_ONLINE, prefix + "${name} &7is now &aonline&7.");
        add(TranslationKeys.FRIENDS_QUIT, prefix + "${name} &7is now &coffline&7.");

        add(TranslationKeys.FRIEND_COMMAND_FRIEND_USAGE, "friend");
        add(TranslationKeys.FRIEND_COMMAND_FRIEND_DESCRIPTION, "&7Shows all commands of the FriendSystem.");
        add(TranslationKeys.FRIEND_COMMAND_FRIEND_HELP, "\n" +
                                    "&7/&efa <User> &7- Request/Accept a new friend\n" +
                                    "&7/&efr <User> &7- Remove/Decline a user\n" +
                                    "&7/&efriends &7- Show all friends, requests and offers.\n" +
                "");

        insertIntoLanguage();
    }
}
