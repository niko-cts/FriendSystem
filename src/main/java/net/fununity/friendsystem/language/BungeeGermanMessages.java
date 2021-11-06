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
        add(TranslationKeys.FRIENDS_ONLINE_TEXT, prefix + "${name} &7ist nun &aonline&7.");
        add(TranslationKeys.FRIENDS_ONLINE_HOVER, "&7Klicken, um private Nachricht zu schreiben");
        add(TranslationKeys.FRIENDS_QUIT, prefix + "${name} &7ist nun &coffline&7.");

        insertIntoLanguage();
    }
}
