package net.fununity.friendsystem.language;

import net.fununity.main.api.common.messages.MessageList;
import net.fununity.main.api.common.util.SpecialChars;
import net.fununity.misc.translationhandler.TranslationHandler;
/**
 * Translation class for german translations.
 * @see MessageList
 * @author Niko
 * @since 0.0.1
 */
public class BungeeGermanMessages extends MessageList {
    /**
     * Instantiates the class.
     * Inserts german translations.
     * @since 0.0.1
     */
    public BungeeGermanMessages() {
        super(TranslationHandler.getInstance().getLanguageHandler().getLanguageByCode("de"));

        String prefix = "&6❤ Friends &8" + SpecialChars.DOUBLE_ARROW_RIGHT + " ";
        add(TranslationKeys.FRIENDS_ONLINE, prefix + "${name} &7ist nun &aonline&7.");
        add(TranslationKeys.FRIENDS_QUIT, prefix + "${name} &7ist nun &coffline&7.");

        add(TranslationKeys.FRIEND_COMMAND_FRIEND_USAGE, "friend");
        add(TranslationKeys.FRIEND_COMMAND_FRIEND_DESCRIPTION, "&7Zeigt alle Commands vom Freunde system.");
        add(TranslationKeys.FRIEND_COMMAND_FRIEND_HELP, "\n" +
                "&7/&efa <User> &7- Sende/Akzeptiere eine Freundschaftsanfrage\n" +
                "&7/&efr <User> &7- Entferne eine(n) Freund(- schaftsanfrage)\n" +
                "&7/&efriends &7- Zeige alle Freunde, anfragen und einladungen.\n" +
                "");

        insertIntoLanguage();
    }
}
