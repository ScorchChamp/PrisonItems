package com.scorchchamp.PrisonItems.Crafting;


import com.scorchchamp.PrisonItems.Items.InterfaceItems.Crafting.CraftingResult;
import com.scorchchamp.PrisonItems.Items.InterfaceItems.Universal.GrayFillerItem;
import com.scorchchamp.PrisonItems.Items.PrisonItem;
import com.scorchchamp.PrisonItems.PrisonItems;
import com.scorchchamp.PrisonItems.Rarities.Amazing;
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

public class CraftingGui implements Listener {
    private final boolean DISABLED = false;
    private static Inventory inv;
    ItemStack grayItemFiller = new GrayFillerItem().getDefaultItem();
    private static final ArrayList<Integer> INPUTS = new ArrayList<>(Arrays.asList(10,11,12,19,20,21,28,29,30));
    private static final int OUTPUT = 23;
    private static PrisonItem OUTPUT_ITEM = new CraftingResult();

//    public CraftingGui() {
//        PrisonItems.pm.registerEvents(this, PrisonItems.instance);
//    }
    public void openCrafting(Player player) {
        player.openInventory(this.getGUI(player));
    }

    private Inventory getGUI(Player player) {
        inv = Bukkit.createInventory(null, 45, ChatColor.GRAY + "AutoCrafter By ScorchChamp");
        for (int i = 0; i < inv.getSize(); i++) inv.setItem(i, grayItemFiller);
        for (int i : INPUTS) inv.setItem(i, grayItemFiller);
        for (int i : INPUTS) inv.setItem(i, null);
        inv.setItem(OUTPUT, OUTPUT_ITEM.getDefaultItem());
        return inv;
    }


    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != inv) return;
        e.setCancelled(true);
        if (INPUTS.contains(e.getRawSlot())) e.setCancelled(false);
        if (OUTPUT == e.getRawSlot()) e.setCancelled(false);
        if (e.getRawSlot() > inv.getSize()) e.setCancelled(false);


        ArrayList<ItemStack> inputItems = new ArrayList<>();
        for (int i : INPUTS) inputItems.add(inv.getItem(i));
//        ItemStack resultItem = getResult(inputItems);
    }

//    private ItemStack getResult(ArrayList<ItemStack> inputItems) {
//    }


    @EventHandler
    public void onCraftingTable(PlayerInteractEvent event){
        Action action = event.getAction();
        Block block = event.getClickedBlock();

        if(action.equals(Action.RIGHT_CLICK_BLOCK)){
            if(block.getType().equals(Material.CRAFTING_TABLE)) {
                event.setCancelled(true);
                if (DISABLED) event.getPlayer().sendMessage(PrisonItems.MESSAGES.interfaceDisabled);
                else this.openCrafting(event.getPlayer());
            }
        }
    }
}
