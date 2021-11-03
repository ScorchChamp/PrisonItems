package com.scorchchamp.PrisonItems.Anvil;

import com.scorchchamp.PrisonItems.Items.InterfaceItems.Anvil.*;
import com.scorchchamp.PrisonItems.Items.InterfaceItems.Universal.*;
import com.scorchchamp.PrisonItems.PrisonItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomAnvil implements Listener {
    private final boolean DISABLED = false;
    private static Inventory inv;
    ItemStack redItemFiller = new RedFillerItem().getDefaultItem();
    ItemStack greenItemFiller = new GreenFillerItem().getDefaultItem();
    ItemStack grayItemFiller = new GrayFillerItem().getDefaultItem();
    ItemStack anvilButton = new AnvilButton().getDefaultItem();
    ItemStack anvilResult = new AnvilResult().getDefaultItem();
    private static final ArrayList<Integer> READY_INDICATORS = new ArrayList<>(Arrays.asList(11, 12, 14, 15, 20, 24));
    private static final ArrayList<Integer> INPUTS = new ArrayList<>(Arrays.asList(29, 33));
    private static final int OUTPUT = 13;
    private static final int ANVIL_BUTTON = 22;

//    public CustomAnvil() {PrisonItems.pm.registerEvents(this, PrisonItems.instance);}


    public void openAnvil(Player player) {
        player.openInventory(this.getAnvilGUI(player));
    }

    private Inventory getAnvilGUI(Player player) {
        inv = Bukkit.createInventory(null, 45, ChatColor.GRAY + "Custom Anvil By ScorchChamp");

        for (int i = 0; i < inv.getSize(); i++) inv.setItem(i, grayItemFiller);
        for (int i : READY_INDICATORS) inv.setItem(i, redItemFiller);
        for (int i : INPUTS) inv.setItem(i, null);

        inv.setItem(OUTPUT, anvilResult);
        inv.setItem(ANVIL_BUTTON, anvilButton);

        return inv;
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != inv) return;
        if ((e.getRawSlot() < 0 || e.getRawSlot() > inv.getSize()-1) && !INPUTS.contains(e.getRawSlot())) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onAnvil(PlayerInteractEvent event){
        Action action = event.getAction();
        Block block = event.getClickedBlock();

        if(action.equals(Action.RIGHT_CLICK_BLOCK)){
            if(block.getType().equals(Material.ANVIL)) {
                event.setCancelled(true);
                if (DISABLED) event.getPlayer().sendMessage(PrisonItems.MESSAGES.interfaceDisabled);
                else this.openAnvil(event.getPlayer());
            }
        }
    }
}
