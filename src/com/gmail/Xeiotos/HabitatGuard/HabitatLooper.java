package com.gmail.Xeiotos.HabitatGuard;

import com.gmail.Xeiotos.HabitatAPI.Habitat;
import com.gmail.Xeiotos.HabitatAPI.Managers.HabitatPlayerManager;
import com.gmail.Xeiotos.HabitatAPI.Managers.HabitatPluginManager;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Xeiotos
 */
public class HabitatLooper extends BukkitRunnable {

    private HashMap<String, Habitat> previousHabitats = new HashMap<>();

    @Override
    public void run() {
        HashMap<String, Habitat> playerHabitats = getPlayerHabitats();

        if (previousHabitats.isEmpty()) { //If no previous player positions are known
            for (String playername : playerHabitats.keySet()) {
                Player player = Bukkit.getServer().getPlayer(playername);

                Habitat habitat = playerHabitats.get(playername);

                if (player == null || habitat == null) {
                    continue;
                }
                
                habitat.addPlayer(playername, null);
            }
        } else { //If previous player positions are known
            for (String playername : playerHabitats.keySet()) {
                Player player = Bukkit.getServer().getPlayer(playername);
                Habitat habitat = playerHabitats.get(playername);
                Habitat previousHabitat = previousHabitats.get(playername);

                if (player == null) { // If player is null
                    if (previousHabitat != null) { // If player was in a habitat before nulling
                        previousHabitat.removePlayer(playername, habitat);
                    }
                    continue;
                }

                if (habitat == null) { // If current habitat is null/doesn't exist in section
                    if (previousHabitat != null) { // If player was in a valid habitat before joining null habitat
                        previousHabitat.removePlayer(playername, habitat);
                    }
                    continue;
                }

                if (previousHabitat == habitat) {
                    continue;
                }
                
                if (previousHabitat != habitat) {
                    if (previousHabitat != null) {
                        previousHabitat.removePlayer(playername, habitat);                  
                    }
                    
                    habitat.addPlayer(playername, previousHabitat); 
                    player.sendMessage(ChatColor.GREEN + "Entering Habitat " + habitat.getTypeName() + " " + habitat.getRelativeCenter().getX() + "," + habitat.getRelativeCenter().getY());
                }
            }
        }

        HabitatPluginManager.getManager().tickHabitatPlugins();
        previousHabitats = playerHabitats;
    }

    public HashMap<String, Habitat> getPlayerHabitats() {
        HashMap<String, Habitat> playerHabitats = new HashMap<>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld() != Bukkit.getWorld("world")) {
                continue;
            }
            playerHabitats.put(p.getName(), HabitatPlayerManager.getManager().getHabitatPlayer(p).getHabitat());
        }

        return playerHabitats;
    }
}
