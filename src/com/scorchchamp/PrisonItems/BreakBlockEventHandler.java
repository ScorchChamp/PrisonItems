package com.scorchchamp.PrisonItems;

import com.scorchchamp.PrisonItems.Items.Drillers.Driller;
import com.scorchchamp.PrisonItems.Items.Drillers.TemplateDriller;
import com.scorchchamp.PrisonItems.Items.PrisonItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class BreakBlockEventHandler implements Listener {

	public BreakBlockEventHandler() {
		PrisonItems.pm.registerEvents(this, PrisonItems.instance);
	}

	@EventHandler
	public void breakEvent(PlayerInteractEvent event) {
		Action action = event.getAction();
		if (event.getItem() == null) return;
		if (action.equals(Action.LEFT_CLICK_BLOCK)) {
			PrisonItem itemUsed = PrisonItems.getPrisonItemFromItemStack(event.getItem());
			if (itemUsed == null) return;
			if (itemUsed instanceof Driller)
				if (((Driller) itemUsed).getMineableBlocks().contains(event.getClickedBlock().getType()))
					return;
			event.setCancelled(true);

		}
	}

}
