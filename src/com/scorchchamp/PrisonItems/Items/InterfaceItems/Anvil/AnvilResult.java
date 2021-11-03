package com.scorchchamp.PrisonItems.Items.InterfaceItems.Anvil;

import com.scorchchamp.PrisonItems.Items.InterfaceItems.InterfaceItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;


public class AnvilResult extends InterfaceItems {

    public AnvilResult() {
        super("ANVIL_RESULT", Material.BARRIER);
        this.setName(ChatColor.RED + "Add items to combine first");
    }
}
