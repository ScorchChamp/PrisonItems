package com.scorchchamp.PrisonItems.Items;

import com.scorchchamp.PrisonItems.PrisonItems;
import com.scorchchamp.PrisonItems.Rarities.Decent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class ImpossibleItem extends PrisonItem implements Listener {

    public ImpossibleItem() {
        this.setItemMaterial(Material.BARRIER);
        this.setName("IMPOSSIBLE ITEM!");
        this.setRarity(new Decent());
        this.setID("IMPOSSIBLE_ITEM");
        this.addLore(ChatColor.YELLOW + "This is an impossible item!");
        this.addLore("");
        this.addLore(ChatColor.RED + "" + ChatColor.BOLD + "Please open a ticket on the discord server!");
        PrisonItems.pm.registerEvents(this, PrisonItems.instance);
    }

    @EventHandler
    public void onUse(PlayerInteractEvent event){
        if (event.getItem().getItemMeta().getDisplayName().contains("DISABLED")) event.setCancelled(true);

        if (event.getItem() == null) return;
        if (PrisonItems.getPrisonItemFromItemStack(event.getItem()) == null) return;
        if (PrisonItems.getPrisonItemFromItemStack(event.getItem()).getID().equals(this.getID())) event.setCancelled(true);
    }
}
