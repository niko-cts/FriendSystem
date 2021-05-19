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

        insertIntoLanguage();
    }
}
