package dev.peabody505.BeaconSMP;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin implements Listener {

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}

	public static HashMap<String, Double> deathmap = new HashMap<String, Double>();
	public static HashMap<String, String> deathworldname = new HashMap<String, String>();

	// fired when player joins the server
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!getConfig().contains(player.getName())) {
			World world = Bukkit.getWorld(getConfig().getString("spawn.world"));
			Location teleport = new Location(world, getConfig().getDouble(
					"spawn.x"), getConfig().getDouble("spawn.y"), getConfig()
					.getDouble("spawn.z"));
			player.teleport(teleport);
			getConfig().set(player.getName() + ".x",
					Double.valueOf(getConfig().getDouble("spawn.x")));
			getConfig().set(player.getName() + ".y",
					Double.valueOf(getConfig().getDouble("spawn.y")));
			getConfig().set(player.getName() + ".z",
					Double.valueOf(getConfig().getDouble("spawn.z")));
			getConfig().set(player.getName() + ".world",
					getConfig().getString("spawn.world"));
			saveConfig();
		}

	}

	// block is placed
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		// if the player is in the safezone
		Location loc = event.getBlock().getLocation();
		if ((loc.getX() <= 200.0D) && (loc.getX() >= -200.0D)
				&& (loc.getZ() <= 200.0D) && (loc.getZ() >= -200.0D)) {
			player.sendMessage("�3�lINFO�r: You must be at least 200 blocks away from spawn to place blocks.");
			event.setCancelled(true);
		}
	}

	// block is broken
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		// if the player is in the safezone
		Location loc = event.getBlock().getLocation();
		if ((loc.getX() <= 200.0D) && (loc.getX() >= -200.0D)
				&& (loc.getZ() <= 200.0D) && (loc.getZ() >= -200.0D)) {
			player.sendMessage("�3�lINFO�r: You must be at least 200 blocks away from spawn to break blocks.");
			event.setCancelled(true);
		}
	}
	
//-------------------------------------------------------------------------------------
//------------------------------ commands ---------------------------------------------
//-------------------------------------------------------------------------------------
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		final Player player = (Player) sender;
		if ((cmd.getLabel().equalsIgnoreCase("smpsetspawn")) && (player.isOp())) {
			getConfig().set("spawn.world", player.getWorld().getName());
			getConfig().set("spawn.x",
					Double.valueOf(player.getLocation().getX()));
			getConfig().set("spawn.y",
					Double.valueOf(player.getLocation().getY()));
			getConfig().set("spawn.z",
					Double.valueOf(player.getLocation().getZ()));
			saveConfig();
			player.sendMessage("�3�lINFO�r: �fSpawn set!");
			return true;
		}
		if (cmd.getLabel().equalsIgnoreCase("spawn")) {
			World world = Bukkit.getWorld(getConfig().getString("spawn.world"));
			player.teleport(new Location(world, getConfig()
					.getDouble("spawn.x"), getConfig().getDouble("spawn.y"),
					getConfig().getDouble("spawn.z")));
			player.sendMessage("�3�lINFO�r: �rWarped to �bspawn�r!");
			return true;
		}
		if (cmd.getLabel().equalsIgnoreCase("sethome")) {
			getConfig().set(player.getName() + ".world",
					player.getWorld().getName());
			getConfig().set(player.getName() + ".x",
					Double.valueOf(player.getLocation().getX()));
			getConfig().set(player.getName() + ".y",
					Double.valueOf(player.getLocation().getY()));
			getConfig().set(player.getName() + ".z",
					Double.valueOf(player.getLocation().getZ()));
			saveConfig();
			player.sendMessage("�3�lINFO�r: �fHome set!");
			return true;
		}
		if (cmd.getLabel().equalsIgnoreCase("home")) {
			World world = Bukkit.getWorld(getConfig().getString(
					player.getName() + ".world"));
			player.teleport(new Location(world, getConfig().getDouble(
					player.getName() + ".x"), getConfig().getDouble(
					player.getName() + ".y"), getConfig().getDouble(
					player.getName() + ".z")));
			player.sendMessage("�3�lINFO�r: �rWarped �bhome�r!");
			return true;
		}
		if (cmd.getLabel().equalsIgnoreCase("back")) {
			if ((Double) deathmap.get(player.getName() + "x") == null) {
				player.sendMessage("�3�lINFO�r: �rYou have nowhere to warp back to.");
				return true;
			}
			Double x = (Double) deathmap.get(player.getName() + "x");
			Double y = (Double) deathmap.get(player.getName() + "y");
			Double z = (Double) deathmap.get(player.getName() + "z");
			World world = Bukkit.getWorld((String) deathworldname.get(player
					.getName()));
			Location tp = new Location(world, x.doubleValue(), y.doubleValue(),
					z.doubleValue());
			player.teleport(tp);
			deathmap.remove(player.getName() + "x");
			deathmap.remove(player.getName() + "y");
			deathmap.remove(player.getName() + "z");
		} else if (cmd.getLabel().equalsIgnoreCase("suicide")) {
			player.sendMessage("�3�lINFO�r: �rYour family would be so dissapointed...");

			new BukkitRunnable() {
				public void run() {
					player.setHealth(0.0D);
				}
			}.runTaskLater(this, 60L);
		}
		return true;
	}
	
//-------------------------------------------------------------------------------------
//--------------------------------- end commands --------------------------------------
//-------------------------------------------------------------------------------------
	
	//handles player death
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		World world = Bukkit.getWorld(getConfig().getString(
				player.getName() + ".world"));
		Location teleport = new Location(world, getConfig().getDouble(
				player.getName() + ".x"), getConfig().getDouble(
				player.getName() + ".y"), getConfig().getDouble(
				player.getName() + ".z"));
		player.teleport(teleport);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		deathmap.put(player.getName() + "x",
				Double.valueOf(player.getLocation().getX()));
		deathmap.put(player.getName() + "y",
				Double.valueOf(player.getLocation().getY()));
		deathmap.put(player.getName() + "z",
				Double.valueOf(player.getLocation().getZ()));
		deathworldname.put(player.getName(), player.getWorld().getName());
		player.sendMessage("�3�lINFO�r: Use �e/back �rto return to your death point.");
	}
}
