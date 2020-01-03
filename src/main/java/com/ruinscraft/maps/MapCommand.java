package com.ruinscraft.maps;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MapCommand implements CommandExecutor, Listener {

	private static ItemStack infoItem = new ItemStack(Material.EXPERIENCE_BOTTLE);
	private static ItemStack blackGlassItem = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
	private static ItemStack suggestionItem = new ItemStack(Material.ENCHANTED_BOOK);
	private static ItemStack nextPageItem = new ItemStack(Material.PAPER);
	private static ItemStack previousPageItem = new ItemStack(Material.PAPER);

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		ItemMeta blackGlassItemMeta = blackGlassItem.getItemMeta();
		ItemMeta infoItemMeta = infoItem.getItemMeta();
		ItemMeta suggestionItemMeta = suggestionItem.getItemMeta();
		ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();
		ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();

		blackGlassItemMeta.setDisplayName(ChatColor.BLACK + " ");
		infoItemMeta.setDisplayName(ChatColor.YELLOW + "Info");
		suggestionItemMeta.setDisplayName(ChatColor.YELLOW + "Suggest new RC maps!");
		nextPageItemMeta.setDisplayName(ChatColor.YELLOW + "Next Page");
		previousPageItemMeta.setDisplayName(ChatColor.YELLOW + "Previous Page");

		infoItemMeta.setLore(Arrays.asList("This is a custom database of", "maps which can be used for",
				"design. Check the book below", "on how to suggest new maps!"));

		blackGlassItem.setItemMeta(blackGlassItemMeta);
		infoItem.setItemMeta(infoItemMeta);
		suggestionItem.setItemMeta(suggestionItemMeta);
		nextPageItem.setItemMeta(nextPageItemMeta);
		previousPageItem.setItemMeta(previousPageItemMeta);

		Maps.getInventory().setItem(0, blackGlassItem);
		Maps.getInventory().setItem(1, blackGlassItem);
		Maps.getInventory().setItem(2, blackGlassItem);
		Maps.getInventory().setItem(3, blackGlassItem);
		Maps.getInventory().setItem(4, infoItem);
		Maps.getInventory().setItem(5, blackGlassItem);
		Maps.getInventory().setItem(6, blackGlassItem);
		Maps.getInventory().setItem(7, blackGlassItem);
		Maps.getInventory().setItem(8, blackGlassItem);
		Maps.getInventory().setItem(45, blackGlassItem);
		Maps.getInventory().setItem(46, blackGlassItem);
		Maps.getInventory().setItem(47, previousPageItem);
		Maps.getInventory().setItem(48, blackGlassItem);
		Maps.getInventory().setItem(49, suggestionItem);
		Maps.getInventory().setItem(50, blackGlassItem);
		Maps.getInventory().setItem(51, nextPageItem);
		Maps.getInventory().setItem(52, blackGlassItem);
		Maps.getInventory().setItem(53, blackGlassItem);

		for (ItemStack map : Maps.getItems().keySet()) {
			Maps.getInventory().setItem(Maps.getItems().get(map), map);
		}

		Maps.getInventoryTwo().setItem(0, blackGlassItem);
		Maps.getInventoryTwo().setItem(1, blackGlassItem);
		Maps.getInventoryTwo().setItem(2, blackGlassItem);
		Maps.getInventoryTwo().setItem(3, blackGlassItem);
		Maps.getInventoryTwo().setItem(4, infoItem);
		Maps.getInventoryTwo().setItem(5, blackGlassItem);
		Maps.getInventoryTwo().setItem(6, blackGlassItem);
		Maps.getInventoryTwo().setItem(7, blackGlassItem);
		Maps.getInventoryTwo().setItem(8, blackGlassItem);
		Maps.getInventoryTwo().setItem(45, blackGlassItem);
		Maps.getInventoryTwo().setItem(46, blackGlassItem);
		Maps.getInventoryTwo().setItem(47, previousPageItem);
		Maps.getInventoryTwo().setItem(48, blackGlassItem);
		Maps.getInventoryTwo().setItem(49, suggestionItem);
		Maps.getInventoryTwo().setItem(50, blackGlassItem);
		Maps.getInventoryTwo().setItem(51, nextPageItem);
		Maps.getInventoryTwo().setItem(52, blackGlassItem);
		Maps.getInventoryTwo().setItem(53, blackGlassItem);

		for (ItemStack map : Maps.getItemsTwo().keySet()) {
			Maps.getInventoryTwo().setItem(Maps.getItemsTwo().get(map), map);
		}

		Player player = (Player) sender;

		player.openInventory(Maps.getInventory());

		return true;

	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onInventoryClick(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();

		Inventory inventory = event.getInventory();

		if (inventory.equals(Maps.getInventory())) {

			event.setCancelled(true);

			if (event.getClick().isShiftClick()) {

				return;

			}

			for (ItemStack map : Maps.getItems().keySet()) {
				if (event.getRawSlot() == Maps.getItems().get(map)) {
					player.getInventory().addItem(map);
					player.sendMessage(ChatColor.YELLOW + "Received " + map.getItemMeta().getDisplayName());
				}
			}
			// make an array of the titles in config
			// go to bed

			if (event.getRawSlot() == 49) {

				player.sendMessage(ChatColor.YELLOW + "Make suggestions for /maps: \n" + ChatColor.GOLD + ""
						+ ChatColor.UNDERLINE + "https://goo.gl/forms/GLIv00xVevgnSMsW2");
				player.closeInventory();

			}

			if (event.getRawSlot() == 51) {

				player.openInventory(Maps.getInventoryTwo());

			}

		}

		if (inventory.equals(Maps.getInventoryTwo())) {

			event.setCancelled(true);

			if (event.getClick().isShiftClick()) {

				return;

			}

			for (ItemStack map : Maps.getItemsTwo().keySet()) {
				if (event.getRawSlot() == Maps.getItemsTwo().get(map)) {
					player.getInventory().addItem(map);
					player.sendMessage(ChatColor.YELLOW + "Received " + map.getItemMeta().getDisplayName());
				}
			}

			if (event.getRawSlot() == 49) {

				player.sendMessage(ChatColor.YELLOW + "Make suggestions for /maps: \n" + ChatColor.GOLD + ""
						+ ChatColor.UNDERLINE + "https://goo.gl/forms/GLIv00xVevgnSMsW2");
				player.closeInventory();

			}

			if (event.getRawSlot() == 47) {

				player.openInventory(Maps.getInventory());

			}

		}

	}

}
