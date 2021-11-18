package com.scorchchamp.PrisonItems.Items.Drillers;

import com.scorchchamp.PrisonItems.Items.PrisonItem;
import com.scorchchamp.PrisonItems.Rarities.Trash;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;


public class TemplateDriller extends Driller {
    private ArrayList<Material> mineableBlocks = new ArrayList<>();

    public TemplateDriller() {
        this.setItemMaterial(Material.WOODEN_PICKAXE);
        this.setName("Template Driller");
        this.setRarity(new Trash());
        this.addLore(ChatColor.YELLOW + "Template Driller!");
        this.setID("TEMPLATE_DRILLER");
    }

    public void addMineableBlock(Material block) {
        this.mineableBlocks.add(block);
    }
    public void addMineableBlock(ArrayList<Material> blocks) {
        this.mineableBlocks.addAll(blocks);
    }
    public ArrayList<Material> getMineableBlocks() {
        return mineableBlocks;
    }
}
