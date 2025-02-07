package net.fununity.friendsystem.language;

import net.fununity.main.api.common.messages.MessageList;
import net.fununity.main.api.common.util.SpecialChars;
import net.fununity.misc.translationhandler.TranslationHandler;

/**
 * Translation class for english translations.
 * @see MessageList
 * @author Niko
 * @since 0.0.1
 */
public class EnglishMessages extends MessageList {

    /**
     * Instantiates the class.
     * Inserts english translations.
     * @since 0.0.1
     */
    public EnglishMessages() {
        super(TranslationHandler.getInstance().getLanguageHandler().getLanguageByCode("en"));
        add(TranslationKeys.FRIENDS_REQUEST_ACCEPT_TEXT, "&7[&aAccept&7]");
        add(TranslationKeys.FRIENDS_REQUEST_ACCEPT_HOVER, "&aAccept the friend request.");
        add(TranslationKeys.FRIENDS_REQUEST_DECLINE_TEXT, "&7[&cDecline&7]");
        add(TranslationKeys.FRIENDS_REQUEST_DECLINE_HOVER, "&cDecline the friend request.");

        add(TranslationKeys.FRIENDS_COMMAND_FRIENDS_USAGE, "friends");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDS_DESCRIPTION, "Open the friend menu");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_USAGE, "fr <User>");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDREMOVE_DESCRIPTION, "Remove a friend.");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDADD_USAGE, "fa <User>");
        add(TranslationKeys.FRIENDS_COMMAND_FRIENDADD_DESCRIPTION, "Add a friend.");
        add(TranslationKeys.FRIENDS_COMMAND_NOT_SELF, "&7You &ccan't add yourself &7as a friend!");
        add(TranslationKeys.FRIENDS_COMMAND_REQUEST_ALREADY_SEND, "&7Request &calready &7sent!.");
        add(TranslationKeys.FRIENDS_COMMAND_ALREADY_FRIENDS, "&7You're &calready &7friends!");

        add(TranslationKeys.FRIENDS_REQUEST_DENIED, "&cReject &7friend request!");
        add(TranslationKeys.FRIENDS_REQUEST_SEND, "&7Friend request &asent&7!");
        add(TranslationKeys.FRIENDS_REQUEST_REMOVED, "&7Friend request &cdeclined&7!");
        add(TranslationKeys.FRIENDS_NO_FRIEND, "&7You &caren't &7friends with ${name}!");

        String prefix = "&6❤ Friends &8" + SpecialChars.DOUBLE_ARROW_RIGHT + " ";
        add(TranslationKeys.FRIENDS_REQUEST_RECEIVED, prefix + "&7You recieved a friend request from ${name}&7!");
        add(TranslationKeys.FRIENDS_NEW_FRIEND, prefix + "&7You are now &afriends &7with ${name}&7!");
        add(TranslationKeys.FRIENDS_REMOVED, prefix + "${name} &7is &cno longer &7your friend!.");

        add(TranslationKeys.FRIENDS_GUI_NORMAL, "&6Friends");
        add(TranslationKeys.FRIENDS_GUI_FRIEND, "&6Friends menu");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_LORE, "&7Friends since: &e${friendssince};&7Last online: &e${lastonline}");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_NAME, "&eJump");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_LORE, "&7Join your friend on their;&7current server.");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_NAME, "&dParty");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_LORE, "&7Invite your friend to your;&7party!.");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_NAME, "&bClub");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_LORE, "&7Invite your friend to;&7your club!;&8" + SpecialChars.DOUBLE_ARROW_RIGHT + " &bComing soon!");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_NAME, "&cRemove");
        add(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_LORE, "&7Click to remove your friend.");
        add(TranslationKeys.FRIENDS_GUI_REQUESTED_LORE, "&7Click to withdraw your friend request.");
        add(TranslationKeys.FRIENDS_GUI_REQUESTS, "&7Incoming &6friend requests");
        add(TranslationKeys.FRIENDS_GUI_QUESTS_ACCEPT, "&aAccept &7friend request!");
        add(TranslationKeys.FRIENDS_GUI_REQUESTS_DECLINE, "&cReject &7friend request!");
        add(TranslationKeys.FRIENDS_GUI_QUESTED_TITLE, "&7Outgoing &6friend requests");

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
