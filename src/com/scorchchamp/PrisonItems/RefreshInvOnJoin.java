package com.scorchchamp.PrisonItems;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class RefreshInvOnJoin implements Listener {


    private final Plugin plugin = PrisonItems.instance;

    public RefreshInvOnJoin() {
        PrisonItems.pm.registerEvents(this, PrisonItems.instance);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PrisonItems.refreshPlayerInventory(p);
    }
}
