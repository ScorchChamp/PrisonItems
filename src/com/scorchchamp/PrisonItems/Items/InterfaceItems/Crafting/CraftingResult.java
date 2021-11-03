package com.scorchchamp.PrisonItems.Items.InterfaceItems.Crafting;

import com.scorchchamp.PrisonItems.Items.InterfaceItems.InterfaceItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;


public class CraftingResult extends InterfaceItems {

    public CraftingResult() {
        super("ANVIL_RESULT", Material.BARRIER);
        this.setName(ChatColor.RED + "Add items to combine first");
    }
}
