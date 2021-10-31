package com.scorchchamp.PrisonItems.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.scorchchamp.PrisonItems.PrisonItems;
import com.scorchchamp.PrisonItems.Rarities.Rarity;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PrisonItem {
	private Material itemType = Material.BARRIER;
	private List<String> lore = new ArrayList<>(Arrays.asList(""));
	private boolean unbreakable;
	private String name;
	private Rarity rarity;
	private String item_id = "";



	protected void setItemMaterial(Material mat) {this.itemType = mat;}
	protected void setName(String name) {this.name = name;}
	protected void setID(String ID) {this.item_id = ID;}
	protected void setRarity(Rarity rar) {this.rarity = rar;}


	private List<String> getLore() {return lore;}
	private String getName() {return getRarity().getDisplayColor() + name;}
	public Material getMaterial() { return itemType;}
	public String getID() {return item_id; }
	public boolean getIsUnbreakable() {return unbreakable;}
	protected Rarity getRarity() {return rarity;}
	public ItemMeta getItemMeta() {return this.getDefaultItem().getItemMeta(); }


	public void addLore(String str) {this.lore.add(str); }


	public boolean containsQuery(String query) {
		query = query.toLowerCase();
		if (getID().toLowerCase().contains(query)) return true;
		if (getName().toLowerCase().contains(query)) return true;
		for (String line : getLore()) if (line.toLowerCase().contains(query)) return true;
		return false;
	}

	public ItemStack getDefaultItem() {
		ItemStack item = new ItemStack(this.getMaterial());
		item.setAmount(1);

		ItemMeta meta = item.getItemMeta();
		meta.setLore(this.getLore());
		meta.setDisplayName(this.getName());
		meta.setUnbreakable(this.getIsUnbreakable());
		meta.getPersistentDataContainer().set(new NamespacedKey(PrisonItems.instance, "item_id"), PersistentDataType.STRING, this.getID());

		item.setItemMeta(meta);
		return item;
	}
}
