package com.gmail.Xeiotos.HabitatGuard.Listeners;

import com.gmail.Xeiotos.HabitatAPI.Events.PlayerEscapeHabitatEvent;
import com.gmail.Xeiotos.HabitatAPI.Managers.HabitatPlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 *
 * @author Xeiotos
 */
public class PlayerEscapeHabitatListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEscape(PlayerEscapeHabitatEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.RED + "[HabitatGuard] You have left the habitat zone!");
        HabitatPlayerManager.getManager().getHabitatPlayer(player).teleportToHabitat(event.getHabitat());
    }
}
