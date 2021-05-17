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

        add(TranslationKeys.FRIENDS_COMMAND_FRIENDS_USAGE, "freunde");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDS_DESCRIPTION, "Öffnet das Freundemenu");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_USAGE, "fr <User>");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_DESCRIPTION, "Entfernt einen Freund.");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDADD_USAGE, "fa <User>");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDADD_DESCRIPTION, "Fügt einen Freund hinzu.");
        add(TranslationKeys.FRIENDS_COMMAND_NOT_SELF, "&cDu kannst nicht mit dir selbst befreundet sein.");

        add(TranslationKeys.FRIENDS_REQUEST_DENIED, "&aFreundschaftsanfrage abgelehnt.");
        add(TranslationKeys.FRIENDS_REQUEST_SEND, "&aFreundschaftsanfrage gesendet.");
        add(TranslationKeys.FRIENDS_REQUEST_REMOVED, "&aFreundschaftsanfrage entfernt.");
        add(TranslationKeys.FRIENDS_NO_FRIEND, "&cDieser Spieler ist nicht dein Freund");

        String prefix = "&6&lFreunde &e" + SpecialChars.DOUBLE_ARROW_RIGHT + " ";
        add(TranslationKeys.FRIENDS_REQUEST_RECEIVED, prefix + "&7Du hast eine Freundschaftsanfrage von ${name} &7erhalten.");
        add(TranslationKeys.FRIENDS_NEW_FRIEND, prefix + "&aDu bist nun mit ${name} &abefreundet!");
        add(TranslationKeys.FRIENDS_REMOVED, prefix + "${name} &7ist nun nicht mehr dein Freund.");

        add(TranslationKeys.FRIENDS_GUI_NORMAL, "&6Freunde");
        add(TranslationKeys.FRIENDS_GUI_FRIEND, "&6Freunde - Liste");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_NAME, "&eSpringen");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_LORE, "&7Springe zu deinem Freund.");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_NAME, "&dParty");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_LORE, "&7Lade deinen Freund;&7in deine &dParty&7 ein.;&cComing soon!");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_NAME, "&bChatroom");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_LORE, "&7Lade deinen Freund;&7in deinen &bChatroom&7 ein.;&cComing soon!");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_NAME, "&cEntfernen");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_LORE, "&7Klicken, um deinen Freund zu löschen.");
        add(TranslationKeys.FRIENDS_GUI_QUESTED_TITLE, "&6Freunde - Gesendet");
        add(TranslationKeys.FRIENDS_GUI_REQUESTED_LORE, "&7Klicken, um diese;&7Freundschaftseinladung zu entfernen.");
        add(TranslationKeys.FRIENDS_GUI_REQUESTS, "&6Freunde - Erhalten");
        add(TranslationKeys.FRIENDS_GUI_QUESTS_ACCEPT, "&aFreundschaftsanfrage annehmen");
        add(TranslationKeys.FRIENDS_GUI_REQUESTS_DECLINE, "&cFreundschaftsanfrage ablehnen");


        insertIntoLanguage();
    }
}
