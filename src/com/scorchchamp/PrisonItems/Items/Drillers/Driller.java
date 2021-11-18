package com.scorchchamp.PrisonItems.Items.Drillers;

import com.scorchchamp.PrisonItems.Items.PrisonItem;
import com.scorchchamp.PrisonItems.PrisonItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;


public class Driller extends PrisonItem implements Listener {
    private ArrayList<Material> mineableBlocks = new ArrayList<>();

    public Driller() {
        this.setanyItemMaterialMatches(true);
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


    public void setDrillerTier(ItemStack item, int tier) {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(PrisonItems.instance, "driller_tier"), PersistentDataType.INTEGER, tier);
        item.setItemMeta(meta);
    }
    public int getDrillerTier(ItemStack item) {
        if (item == null) return -1;
        if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(PrisonItems.instance, "driller_tier"), PersistentDataType.INTEGER))
            return item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(PrisonItems.instance, "driller_tier"), PersistentDataType.INTEGER);
        else return -1;
    }
}
