package com.scorchchamp.PrisonItems.Rarities;

import net.md_5.bungee.api.ChatColor;

public class Rarity {
	private String displayName = "Confused Rarity";
	private String displayColor = ChatColor.UNDERLINE + "";

	public Rarity(String dn, String dc) {
		this.setDisplayName(dn);
		this.setDisplayColor(dc);
	}

	public void setDisplayName(String dn) {
		this.displayName = dn;
	}

	public void setDisplayColor(String dc) {
		this.displayColor = dc;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public String getDisplayColor() {
		return this.displayColor;
	}
}
