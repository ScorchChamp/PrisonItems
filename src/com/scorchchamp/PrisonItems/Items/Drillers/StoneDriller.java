package com.scorchchamp.PrisonItems.Items.Drillers;

import com.scorchchamp.PrisonItems.Rarities.Decent;
import com.scorchchamp.PrisonItems.Rarities.Trash;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;


public class StoneDriller extends Driller {

    public StoneDriller() {
        this.setItemMaterial(Material.WOODEN_PICKAXE);
        this.setName("Stone Driller");
        this.setRarity(new Decent());
        this.addLore(ChatColor.YELLOW + "Stone Driller!");
        this.setID("STONE_DRILLER");
        this.addMineableBlock(Material.STONE);
    }
}
