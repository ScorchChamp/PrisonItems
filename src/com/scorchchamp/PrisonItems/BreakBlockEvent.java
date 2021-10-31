package com.scorchchamp.PrisonItems;

import java.util.List;

import com.scorchchamp.PrisonItems.Items.PrisonItem;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class BreakBlockEvent implements Listener {

	private List<PrisonItem> items;
	private Plugin plugin;

	public BreakBlockEvent(Plugin pl, List<PrisonItem> list) {
		this.items = list;
		this.plugin = pl;
	}
}
