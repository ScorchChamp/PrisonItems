package com.scorchchamp.PrisonItems.Items;

import com.scorchchamp.PrisonItems.Rarities.Decent;
import org.bukkit.ChatColor;
import org.bukkit.Material;


public class ImpossibleItem extends PrisonItem {

    public ImpossibleItem() {
        this.setItemMaterial(Material.BARRIER);
        this.setName("IMPOSSIBLE ITEM!");
        this.setRarity(new Decent());
        this.setID("IMPOSSIBLE_ITEM");
        this.addLore(ChatColor.YELLOW + "This is an impossible item!");
        this.addLore("");
        this.addLore(ChatColor.RED + "" + ChatColor.BOLD + "Please open a ticket on the discord server!");
    }
}
