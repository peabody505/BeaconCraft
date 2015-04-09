package dev.peabody505.BeaconSG;

import listeners.BlockListener;
import listeners.PlayerDeathListener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	private static Plugin plugin;

	public static void registerEvents(org.bukkit.plugin.Plugin plugin,
			Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager()
					.registerEvents(listener, plugin);
		}
	}

	public void onEnable() {
		registerEvents(this, new PlayerDeathListener(), new BlockListener());
		plugin = this;
	}

	public void onDisable() {
		plugin = null;
	}

	public static Plugin getPlugin() {
		return plugin;
	}
}
