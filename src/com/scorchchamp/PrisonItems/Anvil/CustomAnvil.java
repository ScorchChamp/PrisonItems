package com.scorchchamp.PrisonItems.Anvil;

import com.scorchchamp.PrisonItems.PrisonItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomAnvil implements Listener {
    private static Inventory inv;
    ItemStack redItemFiller;
    ItemStack greenItemFiller;

    public CustomAnvil() {
        redItemFiller = PrisonItems.newFillerItem();
        redItemFiller.setType(Material.RED_STAINED_GLASS_PANE);
        greenItemFiller = PrisonItems.newFillerItem();
        greenItemFiller.setType(Material.GREEN_STAINED_GLASS_PANE);
    }

    public void openAnvil(Player player) {
        player.openInventory(this.getAnvilGUI(player));
    }

    private Inventory getAnvilGUI(Player player) {
        inv = Bukkit.createInventory(null, 45, ChatColor.GRAY + "Custom Anvil By ScorchChamp");
        for (int i = 0; i < inv.getSize(); i++)
            inv.setItem(i, PrisonItems.fillerItem);
        inv.setItem(11, redItemFiller);
        inv.setItem(12, redItemFiller);
        ItemStack barrierItem = new ItemStack(Material.BARRIER);
        ItemMeta barrierMeta = barrierItem.getItemMeta();
        barrierMeta.setDisplayName(ChatColor.RED + "Add items to combine first");
        barrierItem.setItemMeta(barrierMeta);
        inv.setItem(13, barrierItem);
        inv.setItem(14, redItemFiller);
        inv.setItem(15, redItemFiller);
        inv.setItem(20, redItemFiller);
        inv.setItem(24, redItemFiller);
        inv.setItem(29, null);
        ItemStack anvilItem = new ItemStack(Material.ANVIL);
        ItemMeta anvilMeta = anvilItem.getItemMeta();
        anvilMeta.setDisplayName(ChatColor.GREEN + "Click to combine!");
        anvilItem.setItemMeta(anvilMeta);
        inv.setItem(22, anvilItem);
        inv.setItem(33, null);
        return inv;
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != inv) return;
        if ((e.getRawSlot() < 0 || e.getRawSlot() > inv.getSize()-1) && !(e.getRawSlot() == 29 || e.getRawSlot() == 33)) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onAnvil(PlayerInteractEvent event){
        Action action = event.getAction();
        Block block = event.getClickedBlock();

        if(action.equals(Action.RIGHT_CLICK_BLOCK)){
            if(block.getType().equals(Material.ANVIL)){
                event.setCancelled(true);
                this.openAnvil(event.getPlayer());
            }
        }
    }
}
