package com.scorchchamp.PrisonItems.Items.Drillers;

import com.scorchchamp.PrisonItems.Rarities.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;


public class DirtDriller extends Driller {

    public DirtDriller() {
        this.setItemMaterial(Material.WOODEN_PICKAXE);
        this.setName("Dirt Driller");
        this.setRarity(new Trash());
        this.addLore(ChatColor.YELLOW + "Dirt Driller!");
        this.setID("DIRT_DRILLER");
        this.addMineableBlock(Material.DIRT);
    }
}
