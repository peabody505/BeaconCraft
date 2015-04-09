package dev.peabody505.BeaconSnowfight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

	// ------------------making other classes
	// work--------------------------------

	final FileConfiguration config = this.getConfig();

	private static Plugin plugin;

	public static Plugin getPlugin() {
		return plugin;
	}

	public static void registerEvents(org.bukkit.plugin.Plugin plugin,
			Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager()
					.registerEvents(listener, plugin);
		}
	}

	// -----------------------real plugin
	// begins------------------------------------

	public void onEnable() {
		plugin = this;
		registerEvents(this, this, new PlayerClickListener());
		saveDefaultConfig();
		PowerUpHandler.powerSpawn();
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
				"gamerule sendCommandFeedback false");
	}

	static ArrayList<String> ingame = new ArrayList<String>();
	static HashMap<String, String> powerUpMap = new HashMap<String, String>();
	static HashMap<String, Double> lastPower = new HashMap<String, Double>();
	static ArrayList<String> currentCollect = new ArrayList<String>();
	
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if (ingame.contains(player.getName())) {
			ingame.remove(player.getName());
		}
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		ItemStack snowshooter = new ItemStack(Material.DIAMOND_HOE, 1);
		ItemMeta snowshootermeta = snowshooter.getItemMeta();
		snowshootermeta
				.setDisplayName("�bSnowshooter �7(Right Click to Spawn)");
		snowshooter.setItemMeta(snowshootermeta);
		player.getInventory().setItem(0, snowshooter);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ingame.remove(player.getName());
		player.setGameMode(GameMode.ADVENTURE);
		ItemStack snowshooter = new ItemStack(Material.DIAMOND_HOE, 1);
		ItemMeta snowshootermeta = snowshooter.getItemMeta();
		snowshootermeta
				.setDisplayName("�bSnowshooter �7(Right Click to Spawn)");
		snowshooter.setItemMeta(snowshootermeta);
		player.getInventory().setItem(0, snowshooter);
		event.setJoinMessage(null);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		ingame.remove(player.getName());
		PowerUpHandler.powerClear(player);
		event.setQuitMessage(null);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSnowballHit(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			player.setGameMode(GameMode.ADVENTURE);
			if (event.getDamager() instanceof Snowball) {
				Snowball snowball = (Snowball) event.getDamager();
				player.setHealth(0l);
				player.playSound(player.getLocation(),
						Sound.FIREWORK_LARGE_BLAST, 10, 1);
				;
				player.playSound(player.getLocation(), Sound.BLAZE_HIT, 10, 1);
				;
				for (Player players : Bukkit.getOnlinePlayers()) {
					players.sendMessage("�a�lSNOWFIGHT�r: �b"
							+ player.getName() + " �7was hit by " + "�b"
							+ ((Player) snowball.getShooter()).getName()
							+ "�7!");
				}
			}
		}

		if (event.getEntity() instanceof Pig) {
			if (event.getDamager() instanceof Snowball) {
				Snowball snowball = (Snowball) event.getDamager();
				Player player = (Player) snowball.getShooter();
				Pig pig = (Pig) event.getEntity();
				Location pigLoc = pig.getLocation();
				pig.teleport(new Location(pig.getWorld(), 0, 0, 0));
				pigLoc.getWorld().createExplosion(pigLoc, 10F);
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.setDeathMessage(null);
		event.getDrops().clear();
	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		player.setGameMode(GameMode.ADVENTURE);
		if (cmd.getName().equalsIgnoreCase("sfspawn")) {
			this.getConfig().set(args[0] + ".x", player.getLocation().getX());
			this.getConfig().set(args[0] + ".y", player.getLocation().getY());
			this.getConfig().set(args[0] + ".z", player.getLocation().getZ());
			this.getConfig().set(args[0] + ".world",
					player.getLocation().getWorld().getName());
			player.playSound(player.getLocation(), Sound.FIRE_IGNITE, 10, 1);
			player.sendMessage("�a�lSNOWFIGHT�r: Spawn " + args[0] + " set!");
			this.saveConfig();
		} else if (cmd.getName().equalsIgnoreCase("sfpower")) {
			this.getConfig().set("p" + args[0] + ".x",
					player.getLocation().getX());
			this.getConfig().set("p" + args[0] + ".y",
					player.getLocation().getY());
			this.getConfig().set("p" + args[0] + ".z",
					player.getLocation().getZ());
			this.getConfig().set("p" + args[0] + ".world",
					player.getLocation().getWorld().getName());
			player.playSound(player.getLocation(), Sound.FIRE_IGNITE, 10, 1);
			player.sendMessage("�a�lSNOWFIGHT�r: Power Up Locaion " + args[0]
					+ " set!");
			this.saveConfig();
		}
		return true;
	}

	@EventHandler
	public void onEntityExplodeEvent(EntityExplodeEvent event) {
		event.blockList().clear();
	}

	@EventHandler
	public void onPigDeath(EntityDeathEvent event) {
		event.getDrops().clear();
	}
}
