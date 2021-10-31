package com.scorchchamp.PrisonItems.Gui;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.scorchchamp.PrisonItems.Items.PrisonItem;

public class Gui implements Listener {

	private Inventory inv;
	private List<PrisonItem> items;
	private int guiSize;

	public Gui(List<PrisonItem> list, int guiSize) {
		this.items = list;
		this.guiSize = guiSize;
		initialiseGui();
	}

	public PrisonItem getPrisonItem(ItemStack is) {
		for (PrisonItem pi : items) {
			if (pi.getDefaultItem().isSimilar(is)) return pi;
		}
		return null;
	}

	public void initialiseGui() {
		inv = Bukkit.createInventory(null, this.guiSize, ChatColor.RED + "PrisonItems By ScorchChamp");
		for (PrisonItem item : items) inv.addItem(item.getDefaultItem());
	}

	public void openInventory(final HumanEntity ent) {
		ent.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {
		if (e.getInventory() != inv) return;

		e.setCancelled(true);
		final Player p = (Player) e.getWhoClicked();

		if (e.getRawSlot() < 0 || e.getRawSlot() > this.guiSize-1) return;
		ItemStack item = inv.getItem(e.getRawSlot());
		if (item == null) return;
		final ItemStack clickedItem = e.getCurrentItem();
		if (clickedItem == null || clickedItem.getType().isAir())
			return;
		p.getInventory().addItem(getPrisonItem(item).getDefaultItem());
	}

	// Cancel dragging in our inventory
	@EventHandler
	public void onInventoryClick(final InventoryDragEvent e) {
		if (e.getInventory().equals(inv)) e.setCancelled(true);
	}

}
