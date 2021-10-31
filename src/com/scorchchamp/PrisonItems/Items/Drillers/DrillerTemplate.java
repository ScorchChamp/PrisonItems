package com.scorchchamp.PrisonItems.Items.Drillers;

import com.scorchchamp.PrisonItems.Items.PrisonItem;
import com.scorchchamp.PrisonItems.Rarities.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;


public class DrillerTemplate extends PrisonItem {

    public DrillerTemplate() {
        this.setItemMaterial(Material.WOODEN_PICKAXE);
        this.setName("Driller Template");
        this.setRarity(new Decent());
        this.addLore(ChatColor.YELLOW + "Template Driller!");
        this.setID("DRILLER_TEMPLATE");
    }
}
