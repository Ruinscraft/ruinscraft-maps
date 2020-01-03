package com.ruinscraft.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Maps extends JavaPlugin {

	private static Maps instance;

	public static Maps getInstance() {
		return instance;
	}

	public FileConfiguration config = getConfig();

	@Override
	public void onEnable() {

		instance = this;
		defineItems();

		getServer().getPluginManager().registerEvents(new MapCommand(), this);

		getCommand("maps").setExecutor(new MapCommand());

		config.options().copyDefaults(true);
		saveConfig();

	}

	private static ArrayList<Inventory> inventories = new ArrayList<Inventory>();

	public static ArrayList<Inventory> getInventories() {
		return inventories;
	}

	private static Inventory menuInventory = Bukkit.createInventory(null, 54,
			ChatColor.GOLD + "Ruinscraft" + ChatColor.DARK_GRAY + " Map Menu");

	public static Inventory getInventory() {
		return menuInventory;
	}

	private static Inventory menuInventoryTwo = Bukkit.createInventory(null, 54,
			ChatColor.GOLD + "Ruinscraft" + ChatColor.DARK_GRAY + " Map Menu");

	public static Inventory getInventoryTwo() {
		return menuInventoryTwo;
	}

	private static HashMap<ItemStack, Integer> items = new HashMap<ItemStack, Integer>();

	public static HashMap<ItemStack, Integer> getItems() {
		return items;
	}

	private static HashMap<ItemStack, Integer> itemsTwo = new HashMap<ItemStack, Integer>();

	public static HashMap<ItemStack, Integer> getItemsTwo() {
		return itemsTwo;
	}

	private static ArrayList<ItemMeta> itemMeta = new ArrayList<ItemMeta>();

	public static ArrayList<ItemMeta> getItemMeta() {
		return itemMeta;
	}

	private static String prefix = "[Ruinscraft-Maps] ";

	public void defineItems() {

		for (String s : config.getConfigurationSection("maps").getKeys(false)) {

			String title = getConfig().getString("maps." + s + ".title");
			String lore = getConfig().getString("maps." + s + ".lore");
			int position = getConfig().getInt("maps." + s + ".position");
			int mapId = getConfig().getInt("maps." + s + ".mapid");

			if ((position > 0) && (position <= 36)) {
				ItemStack map = new ItemStack(Material.FILLED_MAP, 1);
				MapMeta meta = (MapMeta) map.getItemMeta();
				meta.setMapId(mapId); // deprecated but works
				meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + title);
				meta.setLore(Arrays.asList(lore));
				map.setItemMeta(meta);
				getItems().put(map, position + 8);
			}
			if ((position > 36) && (position < 72)) {
				ItemStack map = new ItemStack(Material.FILLED_MAP, 1);
				MapMeta meta = (MapMeta) map.getItemMeta();
				meta.setMapId(mapId); // deprecated but works
				meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + title);
				meta.setLore(Arrays.asList(lore));
				map.setItemMeta(meta);
				getItemsTwo().put(map, position - 28);
			}
			if (position >= 72) {
				instance.getServer().getLogger().info(prefix + "Incorrect position for " + title + " in config.yml");
				continue;
			}
		}

	}

}
