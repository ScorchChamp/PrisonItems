package com.scorchchamp.PrisonItems;

import java.util.Arrays;
import java.util.List;

import com.scorchchamp.PrisonItems.Anvil.CustomAnvil;
import com.scorchchamp.PrisonItems.Items.*;
import com.scorchchamp.PrisonItems.Items.Drillers.DrillerTemplate;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.scorchchamp.PrisonItems.Gui.CommandHandler;
import org.bukkit.scheduler.BukkitScheduler;

public class PrisonItems extends JavaPlugin {
	public static Plugin instance;
	public static List<PrisonItem> items;
	public static List<PrisonItem> getItems() {return items;};
	public static ItemStack fillerItem = newFillerItem();

	private final RefreshInvOnJoin playerJoin = new RefreshInvOnJoin();
	private final CommandHandler ch = new CommandHandler(items);
	private final CustomAnvil CAC = new CustomAnvil();
	private int taskID;


	@Override
	public void onEnable() {
		instance = this;
		items = initItems();

		getCommand("prisonitems").setExecutor(ch);
		Bukkit.getServer().getPluginManager().registerEvents(this.playerJoin, this);
		Bukkit.getServer().getPluginManager().registerEvents(this.CAC, this);
		ch.initialiseGuis();
		BukkitScheduler scheduler = PrisonItems.instance.getServer().getScheduler();
		taskID = scheduler.scheduleSyncRepeatingTask(PrisonItems.instance, new Runnable() {
			@Override
			public void run() {
				for (Player p : instance.getServer().getOnlinePlayers()) PrisonItems.refreshPlayerInventory(p);
			}
		}, 0L, 20L);

		getLogger().info("PrisonItems Enabled!");
	}


	public static void refreshPlayerInventory(Player p) {
		for (ItemStack item : p.getInventory().getContents()) {
			if (item == null) continue;
			if (!item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PrisonItems.instance, "item_id"), PersistentDataType.STRING)) continue;
			PrisonItem pi = getPrisonItemFromItemStack(item);
			if (pi == null) {
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.RED + "DISABLED: " + meta.getPersistentDataContainer().get(new NamespacedKey(PrisonItems.instance, "item_id"), PersistentDataType.STRING));
				item.setItemMeta(meta);
				continue;
			}
			item.setItemMeta(pi.getItemMeta());
			item.setType(pi.getMaterial());
		}
	}

	public static PrisonItem getPrisonItemFromItemStack(ItemStack item) {
		String item_id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PrisonItems.instance, "item_id"), PersistentDataType.STRING);
		for (PrisonItem pi : getItems()) if (item_id.equals(pi.getID())) return pi;
		return null;
	}

	@Override
	public void onDisable() {
		getLogger().info("PrisonItems Disabled!");
		instance.getServer().getScheduler().cancelTask(taskID);
	}

	public static ItemStack newFillerItem() {
		ItemStack fillerItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillerMeta = fillerItem.getItemMeta();
		fillerMeta.setDisplayName(" ");
		fillerItem.setItemMeta(fillerMeta);
		return fillerItem;
	}

	private static List<PrisonItem> initItems() {
		return Arrays.asList(new ImpossibleItem(), new DrillerTemplate());
	}
}
