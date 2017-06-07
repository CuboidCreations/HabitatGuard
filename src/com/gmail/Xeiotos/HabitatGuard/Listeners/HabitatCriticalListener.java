package com.gmail.Xeiotos.HabitatGuard.Listeners;

import com.gmail.Xeiotos.HabitatAPI.Events.HabitatCriticalEvent;
import com.gmail.Xeiotos.HabitatGuard.HabitatGuard;
import java.awt.Point;
import java.util.logging.Level;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 *
 * @author Xeiotos
 */
public class HabitatCriticalListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCritical(HabitatCriticalEvent event) {
        Point center = event.getHabitat().getRelativeCenter();
        HabitatGuard.getInstance().getLogger().log(Level.SEVERE, "Habitat {0},{1} critical! IMMEDIATE ACTION REQUIRED!", new Object[]{center.getX(), center.getY()});
    }
}
