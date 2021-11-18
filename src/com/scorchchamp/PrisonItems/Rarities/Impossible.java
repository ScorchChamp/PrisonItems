package com.scorchchamp.PrisonItems.Rarities;

import com.scorchchamp.PrisonItems.PrisonItems;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Impossible extends Rarity implements Listener {

	public Impossible() {
		super(ChatColor.RED  + "" + ChatColor.BOLD + "Impossible Rarity",
				ChatColor.RED  + "" + ChatColor.BOLD + "Impossible ");
		PrisonItems.pm.registerEvents(this, PrisonItems.instance);
	}

	@EventHandler
	public void onUse(PlayerInteractEvent event){
		if (event.getItem() == null) return;
		if (event.getItem().getItemMeta().getDisplayName().contains("DISABLED")) event.setCancelled(true);
		if (PrisonItems.getPrisonItemFromItemStack(event.getItem()) == null) return;
		if (PrisonItems.getPrisonItemFromItemStack(event.getItem()).getRarity().getDisplayName().equals(this.getDisplayName())) event.setCancelled(true);
	}
}
