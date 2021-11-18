package com.scorchchamp.PrisonItems.Items.ImpossibleItems;

import com.scorchchamp.PrisonItems.Items.PrisonItem;
import com.scorchchamp.PrisonItems.PrisonItems;
import com.scorchchamp.PrisonItems.Rarities.Decent;
import com.scorchchamp.PrisonItems.Rarities.Impossible;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class ImpossibleItem extends PrisonItem implements Listener {

    public ImpossibleItem() {
        this.setRarity(new Impossible());
        this.addLore(ChatColor.YELLOW + "This is an impossible item!");
        this.addLore("");
        this.addLore(ChatColor.RED + "" + ChatColor.BOLD + "Please open a ticket on the discord server!");
        this.setanyItemMaterialMatches(true);
    }
}
