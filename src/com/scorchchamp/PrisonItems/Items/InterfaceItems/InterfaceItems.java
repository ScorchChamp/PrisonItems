package com.scorchchamp.PrisonItems.Items.InterfaceItems;

import com.scorchchamp.PrisonItems.Items.PrisonItem;
import com.scorchchamp.PrisonItems.Rarities.Decent;
import com.scorchchamp.PrisonItems.Rarities.NoRarity;
import org.bukkit.Material;

public class InterfaceItems extends PrisonItem {

    public InterfaceItems(String id, Material mat) {
        this.setName(" ");
        this.setID(id);
        this.setItemMaterial(mat);
        this.setRarity(new NoRarity());
    }
}
