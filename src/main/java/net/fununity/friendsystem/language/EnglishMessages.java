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
public class EnglishMessages extends MessageList {

    /**
     * Instantiates the class.
     * Inserts german translations.
     * @since 0.0.1
     */
    public EnglishMessages() {
        super(TranslationHandler.getInstance().getLanguageHandler().getLanguageByCode("en"));
        add(TranslationKeys.FRIENDS_REQUEST_ACCEPT_TEXT, "&2[&aAccept&2]");
        add(TranslationKeys.FRIENDS_REQUEST_ACCEPT_HOVER, "&aAccept the friendship request.");
        add(TranslationKeys.FRIENDS_REQUEST_DECLINE_TEXT, "&4[&cDecline&4]");
        add(TranslationKeys.FRIENDS_REQUEST_DECLINE_HOVER, "&cDecline the friendship.");

        add(TranslationKeys.FRIENDS_COMMAND_FRIENDS_USAGE, "friends");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDS_DESCRIPTION, "Open the friend menu");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_USAGE, "fr <User>");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_DESCRIPTION, "Remove a friend.");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDADD_USAGE, "fa <User>");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDADD_DESCRIPTION, "Add a friend.");
        add(TranslationKeys.FRIENDS_COMMAND_NOT_SELF, "&7You &ccan't add yourself &7as a friend!");
        add(TranslationKeys.FRIENDS_COMMAND_REQUEST_ALREADY_SEND, "&cYou've already send a request.");
        add(TranslationKeys.FRIENDS_COMMAND_ALREADY_FRIENDS, "&cYou're already friends!");

        add(TranslationKeys.FRIENDS_REQUEST_DENIED, "&cReject &7friend request!");
        add(TranslationKeys.FRIENDS_REQUEST_SEND, "&7Friend request &asent&7!");
        add(TranslationKeys.FRIENDS_REQUEST_REMOVED, "&7Friend request &cremoved&7!");
        add(TranslationKeys.FRIENDS_NO_FRIEND, "&7You &caren't &7friends with ${name}!");

        String prefix = "&6❤ Friends &8" + SpecialChars.DOUBLE_ARROW_RIGHT + " ";
        add(TranslationKeys.FRIENDS_REQUEST_RECEIVED, prefix + "&7You recieved a friend request from ${name}&7!");
        add(TranslationKeys.FRIENDS_NEW_FRIEND, prefix + "&7You are now &afriends &7with ${name}&7!");
        add(TranslationKeys.FRIENDS_REMOVED, prefix + "${name} &7is &cno longer &7your friend!.");

        add(TranslationKeys.FRIENDS_GUI_NORMAL, "&6Friends");
        add(TranslationKeys.FRIENDS_GUI_FRIEND, "&6Friends menu");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_NAME, "&eJump");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_LORE, "&7Join your friend on their;&7current server.");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_NAME, "&dParty");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_LORE, "&7Invite your friend to your;&7party!.;&7&k &8 " + SpecialChars.DOUBLE_ARROW_RIGHT + " &dComing soon!");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_NAME, "&bClub");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_LORE, "&7Invite your friend to;&7your club!;&7&k &8 " + SpecialChars.DOUBLE_ARROW_RIGHT + " &bComing soon!");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_NAME, "&cRemove");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_LORE, "&7Click to remove your friend.");
        add(TranslationKeys.FRIENDS_GUI_REQUESTED_LORE, "&7Click to withdraw your friend request.");
        add(TranslationKeys.FRIENDS_GUI_REQUESTS, "&7Incoming &6friend requests");
        add(TranslationKeys.FRIENDS_GUI_QUESTS_ACCEPT, "&aAccept &7friend request!");
        add(TranslationKeys.FRIENDS_GUI_REQUESTS_DECLINE, "&cReject &7friend request!");
        add(TranslationKeys.FRIENDS_GUI_QUESTED_TITLE, "&7Outgoing &6friend requests");
        insertIntoLanguage();
    }
}
