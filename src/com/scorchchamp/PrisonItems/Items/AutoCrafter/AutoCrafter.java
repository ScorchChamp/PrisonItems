package com.scorchchamp.PrisonItems.Items.AutoCrafter;

import com.scorchchamp.PrisonItems.Items.InterfaceItems.Anvil.AnvilButton;
import com.scorchchamp.PrisonItems.Items.InterfaceItems.Anvil.AnvilResult;
import com.scorchchamp.PrisonItems.Items.InterfaceItems.Universal.GrayFillerItem;
import com.scorchchamp.PrisonItems.Items.InterfaceItems.Universal.GreenFillerItem;
import com.scorchchamp.PrisonItems.Items.InterfaceItems.Universal.RedFillerItem;
import com.scorchchamp.PrisonItems.Items.PrisonItem;
import com.scorchchamp.PrisonItems.PrisonItems;
import com.scorchchamp.PrisonItems.Rarities.Amazing;
import com.scorchchamp.PrisonItems.Rarities.Decent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;

public class AutoCrafter extends PrisonItem implements Listener {
    private final boolean DISABLED = false;
    private static Inventory inv;
    ItemStack grayItemFiller = new GrayFillerItem().getDefaultItem();
    private static final ArrayList<Integer> INPUTS = new ArrayList<>(Arrays.asList(10,11,12,13,14,15,16));

    public AutoCrafter() {
        this.setItemMaterial(Material.DISPENSER);
        this.setName("Template autocrafter");
        this.setRarity(new Amazing());
        this.addLore(ChatColor.YELLOW + "Template Autocrafter!");
        this.setID("DRILLER_AUTOCRAFTER");
        PrisonItems.pm.registerEvents(this, PrisonItems.instance);
    }
    public void openCrafter(Player player) {
        player.openInventory(this.getGUI(player));
    }

    private Inventory getGUI(Player player) {
        inv = Bukkit.createInventory(null, 27, ChatColor.GRAY + "AutoCrafter By ScorchChamp");
        for (int i = 0; i < inv.getSize(); i++) inv.setItem(i, grayItemFiller);
        for (int i = 0; i < INPUTS.size(); i++) {
            if (getPrisonItemInSlot(player.getItemInHand(), i) == null) inv.setItem(INPUTS.get(i), null);
            else inv.setItem(INPUTS.get(i), getPrisonItemInSlot(player.getItemInHand(), i).getDefaultItem());
        }
        return inv;
    }


    private void storeNewPrisonItemInSlot(ItemStack item, String prisonItem_ID, int slot) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(PrisonItems.instance, "SLOT_"+Integer.toString(slot)), PersistentDataType.STRING, prisonItem_ID);
        item.setItemMeta(meta);
    }
    private void resetSlot(ItemStack item, int slot) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().remove(new NamespacedKey(PrisonItems.instance, "SLOT_"+Integer.toString(slot)));
        item.setItemMeta(meta);
    }

    private PrisonItem getPrisonItemInSlot(ItemStack item, int slot) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (!meta.getPersistentDataContainer().has(new NamespacedKey(PrisonItems.instance, "SLOT_"+Integer.toString(slot)), PersistentDataType.STRING)) return null;
        String id = meta.getPersistentDataContainer().get(new NamespacedKey(PrisonItems.instance, "SLOT_"+Integer.toString(slot)), PersistentDataType.STRING);
        return PrisonItems.getPrisonItemFromID(id);
    }
    private int getFirstEmptySlot(ItemStack item) {
        for (int i = 0; i < INPUTS.size(); i++) {
            if (getPrisonItemInSlot(item, i) == null) return i;
        }
        return -1;
    }


    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        ItemStack ItemInHand = e.getWhoClicked().getItemInHand();
        if (e.getInventory() != inv) return;
        if (INPUTS.contains(e.getRawSlot())) {
            for (int i = 0; i < INPUTS.size(); i++) {
                if (INPUTS.get(i) == e.getRawSlot()) {
                    resetSlot(ItemInHand, i);
                    inv.setItem(INPUTS.get(i), new ItemStack(Material.AIR));
                }
            }
            return;
        }
        e.setCancelled(true);
        if (e.getRawSlot() < inv.getSize()) return;
        int slot = getFirstEmptySlot(ItemInHand);
        if (slot < 0) {
            e.getWhoClicked().sendMessage(PrisonItems.MESSAGES.NO_SLOT_LEFT);
            return;
        }
        if (PrisonItems.getPrisonItemFromItemStack(e.getCurrentItem()) == null) {
            e.getWhoClicked().sendMessage(PrisonItems.MESSAGES.NOT_A_PRISONITEM_FOR_AUTOCRAFTER);
            return;
        }
        String itemClickedID = PrisonItems.getPrisonItemFromItemStack(e.getCurrentItem()).getID();
        if (isItemInCrafter(ItemInHand, itemClickedID)) {
            e.getWhoClicked().sendMessage(PrisonItems.MESSAGES.DUPLICATE_ITEMS_IN_AUTOCRAFTER);
            return;
        }
        storeNewPrisonItemInSlot(ItemInHand, itemClickedID, slot);
        inv.setItem(INPUTS.get(slot), e.getCurrentItem());
    }

    private boolean isItemInCrafter(ItemStack item, String ID) {
        for (int i = 0; i < INPUTS.size(); i++) {
            PrisonItem itemInSlot = getPrisonItemInSlot(item, i);
            if (itemInSlot == null) continue;
            if (itemInSlot.getID().equals(ID)) return true;
        }
        return false;
    }

    @EventHandler
    public void onOpen(PlayerInteractEvent event){
        ItemStack usedItem = event.getItem();
        if (usedItem == null) return;

        PrisonItem prisonItem = PrisonItems.getPrisonItemFromItemStack(usedItem);
        if (prisonItem == null) return;

        Action action = event.getAction();

        if(action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)){
            if(prisonItem.getID().equals(getID())) {
                event.setCancelled(true);
                if (DISABLED) event.getPlayer().sendMessage(PrisonItems.MESSAGES.interfaceDisabled);
                else this.openCrafter(event.getPlayer());
            }
        }
    }
}
