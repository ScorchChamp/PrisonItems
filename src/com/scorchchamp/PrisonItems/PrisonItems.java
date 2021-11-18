package com.scorchchamp.PrisonItems;

import java.util.Arrays;
import java.util.List;

import com.scorchchamp.PrisonItems.Anvil.CustomAnvil;
import com.scorchchamp.PrisonItems.Crafting.CraftingGui;
import com.scorchchamp.PrisonItems.Items.*;
import com.scorchchamp.PrisonItems.Items.AutoCrafter.AutoCrafter;
import com.scorchchamp.PrisonItems.Items.Drillers.DirtDriller;
import com.scorchchamp.PrisonItems.Items.Drillers.StoneDriller;
import com.scorchchamp.PrisonItems.Items.Drillers.TemplateDriller;
import com.scorchchamp.PrisonItems.Items.ImpossibleItems.Barrier;
import com.scorchchamp.PrisonItems.Items.ImpossibleItems.Bedrock;
import com.scorchchamp.PrisonItems.Items.ImpossibleItems.ImpossibleItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.scorchchamp.PrisonItems.Gui.CommandHandler;
import org.bukkit.scheduler.BukkitScheduler;

public class PrisonItems extends JavaPlugin {
	public static Plugin instance;
	public static PluginManager pm;
	public static List<PrisonItem> items;

	public static List<PrisonItem> getItems() {return items;};
	public static final Messages MESSAGES = new Messages();
	private static RefreshInvOnJoin player_join;
	private static CommandHandler command_handler;
	private static BreakBlockEventHandler breakBlockEventHandler;

	private CustomAnvil customAnvil;
	private CraftingGui customCrafting;
	private int taskID;

	@Override
	public void onEnable() {
		instance = this;
		pm = instance.getServer().getPluginManager();

		customAnvil = new CustomAnvil();
		customCrafting = new CraftingGui();
		player_join = new RefreshInvOnJoin();
		command_handler = new CommandHandler();
		breakBlockEventHandler = new BreakBlockEventHandler();

		items = initItems();
		getCommand("prisonitems").setExecutor(command_handler);
		command_handler.initialiseGuis();

		BukkitScheduler scheduler = PrisonItems.instance.getServer().getScheduler();
		taskID = scheduler.scheduleSyncRepeatingTask(PrisonItems.instance, new Runnable() {
			@Override
			public void run() {
				for (Player p : instance.getServer().getOnlinePlayers()) refreshPlayerInventory(p);
			}
		}, 0L, 20L);

		getLogger().info(MESSAGES.pluginEnabled);
	}


	public static void refreshPlayerInventory(Player p) {
		for (ItemStack item : p.getInventory().getContents()) {
			if (item == null) continue;
			PrisonItem pi = getPrisonItemFromItemStack(item);
			ItemMeta meta = item.getItemMeta();
			if (pi == null) {
				if (hasPrisonItemID(item)) {
					meta.setDisplayName(ChatColor.RED + "DISABLED: " + meta.getPersistentDataContainer().get(new NamespacedKey(PrisonItems.instance, "item_id"), PersistentDataType.STRING));
					item.setItemMeta(meta);
					item.setType(Material.BARRIER);
				} else {
					meta.setDisplayName(ChatColor.RESET + "" + ChatColor.BOLD + item.getType().toString().replace("_", " ").toLowerCase());
					meta.setLore(new PrisonItem().getDefaultItem().getItemMeta().getLore());
					item.setItemMeta(meta);
				}
			} else {
				meta.setDisplayName(pi.getItemMeta().getDisplayName());
				meta.setLore(pi.getItemMeta().getLore());

				item.setItemMeta(meta);
				item.setType(pi.getMaterial());
			}
		}
	}

	public static PrisonItem getPrisonItemFromItemStack(ItemStack item) {
		if (!hasPrisonItemID(item)) {
			for (PrisonItem pi : getItems())
				if (pi.isAnyItemMaterialMatches() && pi.getMaterial().equals(item.getType())) return pi;
			return null;
		}
		String item_id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PrisonItems.instance, "item_id"), PersistentDataType.STRING);
		for (PrisonItem pi : getItems()) {
			if (item_id.equals(pi.getID())) return pi;
		}
		return null;
	}

	public static boolean hasPrisonItemID(ItemStack item) {
		if (item == null) return false;
		return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PrisonItems.instance, "item_id"), PersistentDataType.STRING);
	}
	public static PrisonItem getPrisonItemFromID(String ID) {
		for (PrisonItem pi : getItems()) if (ID.equals(pi.getID())) return pi;
		return null;
	}

	@Override
	public void onDisable() {
		getLogger().info(MESSAGES.pluginDisabled);
		instance.getServer().getScheduler().cancelTask(taskID);
	}

	private static List<PrisonItem> initItems() {
		return Arrays.asList(new AutoCrafter(), new DirtDriller(), new StoneDriller(), new Bedrock(), new Barrier());
	}
}
