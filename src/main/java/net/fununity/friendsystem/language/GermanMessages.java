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
public class GermanMessages extends MessageList {

    /**
     * Instantiates the class.
     * Inserts german translations.
     * @since 0.0.1
     */
    public GermanMessages() {
        super(TranslationHandler.getInstance().getLanguageHandler().getLanguageByCode("de"));
        add(TranslationKeys.FRIENDS_REQUEST_ACCEPT_TEXT, "&2[&aAkzeptieren&2]");
        add(TranslationKeys.FRIENDS_REQUEST_ACCEPT_HOVER, "&aAkzeptiere die Freundschaftsanfrage.");
        add(TranslationKeys.FRIENDS_REQUEST_DECLINE_TEXT, "&4[&cAblehnen&4]");
        add(TranslationKeys.FRIENDS_REQUEST_DECLINE_HOVER, "&cLehne die Freundschaftsanfrage ab.");

        add(TranslationKeys.FRIENDS_COMMAND_FRIENDS_USAGE, "freunde");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDS_DESCRIPTION, "Öffne das Freundemenü");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_USAGE, "fr <User>");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_DESCRIPTION, "Entferne einen Freund.");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDADD_USAGE, "fa <User>");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDADD_DESCRIPTION, "Füge einen Freund hinzu.");
        add(TranslationKeys.FRIENDS_COMMAND_NOT_SELF, "&7Du kannst nicht mit &cdir selbst &7befreundet sein.");
        add(TranslationKeys.FRIENDS_COMMAND_REQUEST_ALREADY_SEND, "&cDu hast diesem Spieler bereits eine Freundschaftsanfrage gesendet.");
        add(TranslationKeys.FRIENDS_COMMAND_ALREADY_FRIENDS, "&cDu bist mit diesem Spieler bereits befreundet.");

        add(TranslationKeys.FRIENDS_REQUEST_DENIED, "&7Freundesanfrage &cabgelehnt.");
        add(TranslationKeys.FRIENDS_REQUEST_SEND, "&7Freundesanfrage &agesendet&7!");
        add(TranslationKeys.FRIENDS_REQUEST_REMOVED, "&7Freundesanfrage &centfernt&7.");
        add(TranslationKeys.FRIENDS_NO_FRIEND, "&7Du bist &cnicht &7mit ${name} &7befreundet!");

        String prefix = "&6❤ Freunde &8" + SpecialChars.DOUBLE_ARROW_RIGHT + " ";
        add(TranslationKeys.FRIENDS_REQUEST_RECEIVED, prefix + "&7Du hast eine Freundesanfrage von ${name} &7erhalten.");
        add(TranslationKeys.FRIENDS_NEW_FRIEND, prefix + "&7Du bist nun mit ${name} &abefreundet!");
        add(TranslationKeys.FRIENDS_REMOVED, prefix + "${name} &7ist nun &cnicht mehr &7dein Freund.");

        add(TranslationKeys.FRIENDS_GUI_NORMAL, "&6Freunde");
        add(TranslationKeys.FRIENDS_GUI_FRIEND, "&6Freundemenu");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_NAME, "&eNachspringen");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_LORE, "&7Springe deinem Freund auf;&7den aktuellen Server nach.");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_NAME, "&dParty");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_LORE, "&7Lade deinen Freund;&7in deine &dParty&7 ein.;&cComing soon!");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_NAME, "&bClub");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_LORE, "&7Lade deinen Freund;&7in deinen &bClub&7 ein.;&cComing soon!");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_NAME, "&cEntfernen");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_LORE, "&7Klicke, um deinen Freund zu entfernen.");
        add(TranslationKeys.FRIENDS_GUI_REQUESTED_LORE, "&7Klicke, um die Freundesanfrage;&7zurückzuziehen.");
        add(TranslationKeys.FRIENDS_GUI_REQUESTS, "&7Eingehende &6Freundesanfragen");
        add(TranslationKeys.FRIENDS_GUI_QUESTS_ACCEPT, "&7Freundesanfrage &aannehmen&7!");
        add(TranslationKeys.FRIENDS_GUI_REQUESTS_DECLINE, "&7Freundesanfrage &cablehnen&7!");
        add(TranslationKeys.FRIENDS_GUI_QUESTED_TITLE, "&7Ausgehende &6Freundesanfragen");


        insertIntoLanguage();
    }
}
