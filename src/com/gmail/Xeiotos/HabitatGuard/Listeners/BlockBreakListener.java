/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.Xeiotos.HabitatGuard.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 *
 * @author Chris
 */
public class BlockBreakListener implements Listener {
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType().equals(Material.STAINED_GLASS)) {
            if (block.getData() == 15) {
                event.setCancelled(true);
            }
        }
    }
}
