package com.gmail.Xeiotos.HabitatGuard;

import com.gmail.Xeiotos.HabitatAPI.HabitatAPI;
import com.gmail.Xeiotos.HabitatGuard.Listeners.BlockBreakListener;
import com.gmail.Xeiotos.HabitatGuard.Listeners.HabitatCriticalListener;
import com.gmail.Xeiotos.HabitatGuard.Listeners.PlayerEscapeHabitatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Chris
 */
public class HabitatGuard extends JavaPlugin {
    
    private static HabitatGuard instance;

    @Override
    public void onEnable() {
        instance = this;
        
        getHabitatAPI();
        
        new HabitatLooper().runTaskTimer(this, 0, 20);
        
        registerEvents();
    }
    
    @Override
    public void onDisable() {
        HabitatAPI.unHook(this);
    }
    
    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        
        pluginManager.registerEvents(new PlayerEscapeHabitatListener(), this);
        pluginManager.registerEvents(new HabitatCriticalListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
    }
    
    private HabitatAPI getHabitatAPI() {
        return HabitatAPI.getHook(this);
    }
    
    public static HabitatGuard getInstance() {
        return instance;
    }
}
