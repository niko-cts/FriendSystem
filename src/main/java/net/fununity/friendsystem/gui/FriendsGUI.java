package net.fununity.friendsystem.gui;

import net.fununity.cloud.common.events.cloud.CloudEvent;
import net.fununity.friendsystem.FriendSystem;
import net.fununity.friendsystem.FriendSystemSpigot;
import net.fununity.friendsystem.PlayerProfile;
import net.fununity.friendsystem.commands.FriendsCommandUtil;
import net.fununity.friendsystem.language.TranslationKeys;
import net.fununity.main.api.FunUnityAPI;
import net.fununity.main.api.common.util.FormatterUtil;
import net.fununity.main.api.inventory.ClickAction;
import net.fununity.main.api.inventory.CustomInventory;
import net.fununity.main.api.item.ItemBuilder;
import net.fununity.main.api.item.UsefulItems;
import net.fununity.main.api.messages.MessagePrefix;
import net.fununity.main.api.player.APIPlayer;
import net.fununity.main.api.util.PlayerDataUtil;
import net.fununity.misc.translationhandler.translations.Language;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.time.OffsetDateTime;
import java.util.*;

/**
 * Class for all friend menus.
 * @author Niko
 * @since 0.0.1
 */
public class FriendsGUI {

    private FriendsGUI() {
        throw new UnsupportedOperationException("FriendsGUI should not be instantiated.");
    }

    /**
     * Opens the default gui.
     * @param player APIPlayer - player to open the gui.
     * @param site int - site (default: 0)
     * @since 0.0.1
     */
    public static void openGUI(APIPlayer player, int site) {
        Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), () -> {
            PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(player.getUniqueId());
            CustomInventory menu = getDefaultInventory(player, TranslationKeys.FRIENDS_GUI_NORMAL, site, playerProfile.getFriends().size());

            List<UUID> friends = new ArrayList<>();
            if (!playerProfile.getFriends().isEmpty())
                friends = new ArrayList<>(playerProfile.getFriends().keySet()).subList(site * 9 * 4, Math.min((site + 1) * 9 * 4, playerProfile.getFriends().size()));

            Map<UUID, String[]> textureFromOnlinePlayers = PlayerDataUtil.getDataFromOnlinePlayers(friends);

            friends.sort(Comparator.comparing(uuid -> textureFromOnlinePlayers.getOrDefault(uuid, new String[]{"", "0"})[1].equals("0")));

            for (UUID friend : friends) {
                String[] data = textureFromOnlinePlayers.get(friend);
                ItemBuilder itemBuilder;
                String[] lore = player.getLanguage().getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND_LORE,
                        Arrays.asList("${friendssince}", "${lastonline}", "${playtime}"),
                        Arrays.asList(FormatterUtil.formatDate(playerProfile.getFriends().get(friend), player.getLanguage()), FormatterUtil.formatDate(OffsetDateTime.parse(data[4]), player.getLanguage()), FormatterUtil.formatNumber(Integer.parseInt(data[5]) / 3600))).split(";");
                if (data[1].equals("1")) {
                    itemBuilder = new ItemBuilder(UsefulItems.PLAYER_HEAD).setName(data[0]).setLore(lore).setSkullOwner(() -> new String[]{data[2], data[3]});
                } else
                    itemBuilder = new ItemBuilder(UsefulItems.SKELETON_SKULL).setName(data[0]).setLore(lore);

                menu.addItem(itemBuilder.craft(), new ClickAction() {
                    @Override
                    public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                        openFriend(apiPlayer, friend, new ItemBuilder(UsefulItems.PLAYER_HEAD).setName(data[0]).setSkullOwner(()->new String[]{data[2], ""}).craft(), data[1].equals("1"));
                    }
                });
            }

            Bukkit.getScheduler().runTask(FriendSystemSpigot.getInstance(), () -> menu.open(player));
        });
    }

    /**
     * Opens the specified menu for a friend.
     * @param player APIPlayer - player to open
     * @param friend UUID - the friend.
     * @param friendItem ItemStack - the friend item.
     * @param online boolean - friend is online.
     * @since 0.0.1
     */
    private static void openFriend(APIPlayer player, UUID friend, ItemStack friendItem, boolean online) {
        Language lang = player.getLanguage();
        CustomInventory menu = new CustomInventory(lang.getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND), 9 * 3);
        String friendName = PlayerDataUtil.getColorlessPlayerName(friend);
        menu.setItem(0, friendItem);

        menu.setItem(11, new ItemBuilder(Material.ENDER_PEARL)
                .setName(lang.getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_NAME))
                .setLore(lang.getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND_JUMP_LORE).split(";")).craft(), new ClickAction(true) {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                if (online)
                    FunUnityAPI.getInstance().getCloudClient().forwardToBungee(new CloudEvent(CloudEvent.REQ_JUMP_TO).addData(friend).addData(player.getUniqueId()));
            }
        });
        menu.setItem(12, new ItemBuilder(Material.FIREWORK)
                .setName(lang.getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_NAME))
                .setLore(lang.getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND_PARTY_LORE).split(";")).craft(), new ClickAction(true) {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                if (online)
                    Bukkit.dispatchCommand(apiPlayer.getPlayer(), "party invite " + friendName);
                else
                    setCloseInventory(false);
            }
        });
        menu.setItem(13, new ItemBuilder(Material.PAPER)
                .setName(lang.getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_NAME))
                .setLore(lang.getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND_CHATROOM_LORE).split(";")).craft(), new ClickAction(true) {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                if (online)
                    Bukkit.dispatchCommand(apiPlayer.getPlayer(), "chatroom invite " + friendName);
            }
        });
        menu.setItem(16, new ItemBuilder(Material.BARRIER)
                .setName(lang.getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_NAME))
                .setLore(lang.getTranslation(TranslationKeys.FRIENDS_GUI_FRIEND_DELETE_LORE).split(";")).craft(), new ClickAction(true) {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                Bukkit.dispatchCommand(apiPlayer.getPlayer(), "fr " + friendName);
                Bukkit.getScheduler().runTaskLater(FriendSystemSpigot.getInstance(), () -> openGUI(apiPlayer, 0), 10L);
            }
        });
        menu.fill(UsefulItems.BACKGROUND_BLACK);

        menu.setItem(menu.getInventory().getSize() - 9, UsefulItems.LEFT_ARROW, new ClickAction() {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                openGUI(apiPlayer, 0);
            }
        });

        Bukkit.getScheduler().runTask(FriendSystemSpigot.getInstance(), () -> menu.open(player));
    }

    /**
     * Open the requested list.
     * @param player APIPlayer - player to open.
     * @param site int - the site (default: 0)
     * @since 0.0.1
     */
    private static void openRequestedGUI(APIPlayer player, int site) {
        Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), () -> {
            PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(player.getUniqueId());

            CustomInventory menu = getDefaultInventory(player, TranslationKeys.FRIENDS_GUI_QUESTED_TITLE, site, playerProfile.getRequested().size());

            List<UUID> requested = new ArrayList<>();
            if (!playerProfile.getRequested().isEmpty())
                requested = playerProfile.getRequested().subList(site * 9 * 4, Math.min((site + 1) * 9 * 4, playerProfile.getRequested().size()));


            Map<UUID, String[]> textureFromOnlinePlayers = PlayerDataUtil.getDataFromOnlinePlayers(requested);

            requested.sort(Comparator.comparing(uuid -> textureFromOnlinePlayers.getOrDefault(uuid, new String[]{"", "0"})[1].equals("1")));

            for (UUID requestedUUID : requested) {
                String[] data = textureFromOnlinePlayers.get(requestedUUID);
                ItemBuilder itemBuilder;
                if (data[1].equals("1")) {
                    itemBuilder = new ItemBuilder(UsefulItems.PLAYER_HEAD).setName(data[0]).setSkullOwner(() -> new String[]{data[2], ""});
                } else
                    itemBuilder = new ItemBuilder(UsefulItems.SKELETON_SKULL).setName(data[0]);
                menu.addItem(itemBuilder.setLore(player.getLanguage().getTranslation(TranslationKeys.FRIENDS_GUI_REQUESTED_LORE).split(";"))
                        .craft(), new ClickAction(true) {
                    @Override
                    public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                        apiPlayer.sendMessage(MessagePrefix.SUCCESS, TranslationKeys.FRIENDS_REQUEST_REMOVED);
                        Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), () -> {
                            FriendsCommandUtil.removeRequest(apiPlayer.getUniqueId(), requestedUUID);
                            openRequestedGUI(apiPlayer, site);
                        });
                    }
                });
            }


            Bukkit.getScheduler().runTask(FriendSystemSpigot.getInstance(), ()->menu.open(player));
        });
    }

    /**
     * Open the requester list.
     * @param player APIPlayer - player to open.
     * @param site int - the site (default: 0)
     * @since 0.0.1
     */
    private static void openRequesterGUI(APIPlayer player, int site) {
        Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), () -> {
            PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(player.getUniqueId());

            CustomInventory menu = getDefaultInventory(player, TranslationKeys.FRIENDS_GUI_REQUESTS, site, playerProfile.getRequester().size());

            List<UUID> requester = new ArrayList<>();
            if (!playerProfile.getRequester().isEmpty())
                requester = playerProfile.getRequester().subList(site * 9 * 4, Math.min((site + 1) * 9 * 4, playerProfile.getRequester().size()));

            Map<UUID, String[]> textureFromOnlinePlayers = PlayerDataUtil.getDataFromOnlinePlayers(requester);

            requester.sort(Comparator.comparing(uuid -> textureFromOnlinePlayers.getOrDefault(uuid, new String[]{"", "0"})[1].equals("1")));


            for (UUID requesterUUID : requester) {
                String[] data = textureFromOnlinePlayers.get(requesterUUID);
                ItemBuilder itemBuilder;
                if (data[1].equals("1")) {
                    itemBuilder = new ItemBuilder(UsefulItems.PLAYER_HEAD).setName(data[0]).setSkullOwner(() -> new String[]{data[2], ""});
                } else
                    itemBuilder = new ItemBuilder(UsefulItems.SKELETON_SKULL).setName(data[0]);
                menu.addItem(itemBuilder.craft(), new ClickAction() {
                    @Override
                    public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                        openRequester(apiPlayer, requesterUUID, new ItemBuilder(UsefulItems.PLAYER_HEAD).setName(data[0]).setSkullOwner(()->new String[]{data[2], ""}).craft());
                    }
                });
            }

            Bukkit.getScheduler().runTask(FriendSystemSpigot.getInstance(), () -> menu.open(player));
        });
    }

    /**
     * Open specified requester menu.
     * @param player APIPlayer - player to open.
     * @param requesterUUID UUID - requester uuid.
     * @param friendItem ItemStack - the item head.
     * @since 0.0.1
     */
    private static void openRequester(APIPlayer player, UUID requesterUUID, ItemStack friendItem) {
        Bukkit.getScheduler().runTaskAsynchronously(FriendSystemSpigot.getInstance(), ()->{
            Language lang = player.getLanguage();
            CustomInventory menu = new CustomInventory(lang.getTranslation(TranslationKeys.FRIENDS_GUI_REQUESTS), 9 * 3);
            menu.setItem(0, friendItem);

            String playerName = PlayerDataUtil.getColorlessPlayerName(requesterUUID);

            menu.setItem(12, new ItemBuilder(UsefulItems.BACKGROUND_GREEN).setName(lang.getTranslation(TranslationKeys.FRIENDS_GUI_QUESTS_ACCEPT)).craft(), new ClickAction(true) {
                @Override
                public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                    Bukkit.dispatchCommand(apiPlayer.getPlayer(), "fa " + playerName);
                    Bukkit.getScheduler().runTaskLater(FriendSystemSpigot.getInstance(), () -> openGUI(apiPlayer, 0), 10L);
                }
            });
            menu.setItem(14, new ItemBuilder(UsefulItems.BACKGROUND_RED).setName(lang.getTranslation(TranslationKeys.FRIENDS_GUI_REQUESTS_DECLINE)).craft(), new ClickAction(true) {
                @Override
                public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                    Bukkit.dispatchCommand(apiPlayer.getPlayer(), "fr " + playerName);
                    Bukkit.getScheduler().runTaskLater(FriendSystemSpigot.getInstance(), () -> openRequesterGUI(apiPlayer, 0), 10L);
                }
            });
            menu.fill(UsefulItems.BACKGROUND_BLACK);
            Bukkit.getScheduler().runTask(FriendSystemSpigot.getInstance(), () -> menu.open(player));
        });
    }

    /**
     * Get the default inventory for friend menu.
     * @param player APIPlayer - the player.
     * @param key String - the menu translation key.
     * @param site int - the site (default: 0)
     * @param max int - the maximum amount of items to display.
     * @return CustomInventory - the default inventory.
     * @since 0.0.1
     */
    private static CustomInventory getDefaultInventory(APIPlayer player, String key, int site, int max) {
        Language lang = player.getLanguage();
        CustomInventory menu = new CustomInventory(lang.getTranslation(key), 9 * 5);
        for (int i = 45 - 8; i < 45; i++)
            menu.setItem(i, UsefulItems.BACKGROUND_BLACK);
        menu.setItem(menu.getInventory().getSize() - 9, UsefulItems.LEFT_ARROW, new ClickAction() {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                if (site > 0) {
                    if (key.equals(TranslationKeys.FRIENDS_GUI_NORMAL))
                        openGUI(player, site - 1);
                    else if (key.equals(TranslationKeys.FRIENDS_GUI_REQUESTS))
                        openRequesterGUI(apiPlayer, site - 1);
                    else
                        openRequestedGUI(apiPlayer, site - 1);
                }
            }
        });
        menu.setItem(menu.getInventory().getSize() - 1, UsefulItems.RIGHT_ARROW, new ClickAction() {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                if ((site + 1) * 9 * 4 > max) {
                    if (key.equals(TranslationKeys.FRIENDS_GUI_NORMAL))
                        openGUI(player, site + 1);
                    else if (key.equals(TranslationKeys.FRIENDS_GUI_REQUESTS))
                        openRequesterGUI(apiPlayer, site + 1);
                    else
                        openRequestedGUI(apiPlayer, site + 1);
                }
            }
        });

        PlayerProfile playerProfile = FriendSystem.getInstance().getPlayerProfile(player.getUniqueId());

        menu.setItem(45 - 6, new ItemBuilder(Material.BOOKSHELF)
                .setName(lang.getTranslation(TranslationKeys.FRIENDS_GUI_NORMAL))
                .setAmount(Math.min(64, Math.max(1, playerProfile.getFriends().size())))
                .addEnchantment(key.equals(TranslationKeys.FRIENDS_GUI_NORMAL) ? Enchantment.DURABILITY : null, 1, true, false).craft(), new ClickAction() {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                openGUI(apiPlayer, 0);
            }
        });

        menu.setItem(45 - 5, new ItemBuilder(Material.BOOK)
                .setName(lang.getTranslation(TranslationKeys.FRIENDS_GUI_REQUESTS))
                .setAmount(Math.min(64, Math.max(1, playerProfile.getRequester().size())))
                .addEnchantment(key.equals(TranslationKeys.FRIENDS_GUI_REQUESTS) ? Enchantment.DURABILITY : null, 1, true, false).craft(), new ClickAction() {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                openRequesterGUI(apiPlayer, 0);
            }
        });
        menu.setItem(45 - 4, new ItemBuilder(Material.PAPER)
                .setName(lang.getTranslation(TranslationKeys.FRIENDS_GUI_QUESTED_TITLE))
                .setAmount(Math.min(64, Math.max(1, playerProfile.getRequested().size())))
                .addEnchantment(key.equals(TranslationKeys.FRIENDS_GUI_QUESTED_TITLE) ? Enchantment.DURABILITY : null, 1, true, false).craft(), new ClickAction() {
            @Override
            public void onClick(APIPlayer apiPlayer, ItemStack itemStack, int i) {
                openRequestedGUI(apiPlayer, 0);
            }
        });
        return menu;
    }

}
