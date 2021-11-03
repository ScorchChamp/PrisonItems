package com.scorchchamp.PrisonItems.Gui;

import com.scorchchamp.PrisonItems.Items.PrisonItem;
import com.scorchchamp.PrisonItems.PrisonItems;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor {
	private final List<Gui> guis = new ArrayList<>();
	private final int guiSize = 54;

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 2 && args[0].equals("gui")) {
			try {
				if (sender instanceof HumanEntity) {
					if (Integer.parseInt(args[1]) > guis.size() - 1)
						return false;
					guis.get(Integer.parseInt(args[1])).openInventory((HumanEntity) sender);
					return true;
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		if (args.length == 3 && args[0].equals("give")) {
			Player p = PrisonItems.instance.getServer().getPlayer(args[1]);
			for (PrisonItem item : PrisonItems.items) {
				if (item.getID().equals(args[2])) {
					assert p != null;
					p.getInventory().addItem(item.getDefaultItem());
					return true;
				}
			}
		}
		if (args.length == 2 && args[0].equals("search")) {
			if (sender instanceof HumanEntity) {
				Gui queryGui = createGui(getQueryLikeItem(args[1]));
				queryGui.openInventory((HumanEntity) sender);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	private List<PrisonItem> getQueryLikeItem(String query) {
		List<PrisonItem> tempList = new ArrayList<>();
		for (PrisonItem item : PrisonItems.items) {
			if (item.containsQuery(query)) tempList.add(item);
		}
		return tempList;
	}

	public void initialiseGuis() {
		int itemAmount = PrisonItems.items.size();
		for (int i = 0; i < Math.floor(itemAmount / guiSize) + 1; i++) {
			List<PrisonItem> tempList = new ArrayList<>();
			for (int j = i * guiSize; j < (i * guiSize) + itemAmount - (guiSize * i); j++) {
				tempList.add(PrisonItems.items.get(j));
			}
			guis.add(createGui(tempList));
		}
	}
	private Gui createGui(List<PrisonItem> items) {
		Gui gui = new Gui(items, this.guiSize);
		Bukkit.getServer().getPluginManager().registerEvents(gui, PrisonItems.instance);
		return gui;
	}
}
