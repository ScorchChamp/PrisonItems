package com.scorchchamp.PrisonItems.Items.InterfaceItems.Anvil;

import com.scorchchamp.PrisonItems.Items.InterfaceItems.InterfaceItems;
import com.scorchchamp.PrisonItems.Items.PrisonItem;
import com.scorchchamp.PrisonItems.Rarities.Decent;
import com.scorchchamp.PrisonItems.Rarities.NoRarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;


public class AnvilButton extends InterfaceItems {

    public AnvilButton() {
        super("ANVIL_BUTTON", Material.ANVIL);
        this.setName(ChatColor.GREEN + "Click to combine!");
    }
}
