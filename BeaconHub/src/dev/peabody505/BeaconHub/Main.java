package dev.peabody505.BeaconHub;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.connorlinfoot.titleapi.TitleAPI;

public final class Main extends JavaPlugin implements Listener {

	public void onEnable() {
		Bukkit.getServer().getMessenger()
				.registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		for (final Player player : Bukkit.getOnlinePlayers()) {
			disconnections.put(player, "§8QUIT");
			TitleAPI.sendTitle(player, 10, 100, 10, "§3Beacon§fCraft",
					("§7welcomes you, §a" + player.getName() + "§7."));
			TitleAPI.sendTabTitle(player, "§3Beacon§fCraft",
					"§aplay.beaconcraft.net");
			player.setGameMode(GameMode.ADVENTURE);
			player.getWorld().setDifficulty(Difficulty.PEACEFUL);
			new BukkitRunnable() {
				public void run() {
					player.getWorld().setDifficulty(Difficulty.PEACEFUL);
				}
			}.runTaskLater(this, 30l);
		}
	}

	HashMap<Player, String> disconnections = new HashMap<Player, String>();

	public void sendToServer(Player player, String targetserver) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(targetserver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
	}

	static ItemStack compass = new ItemStack(Material.COMPASS, 1);
	static ItemMeta compassmeta = compass.getItemMeta();
	static {
		compassmeta.setDisplayName("§a§lServer Teleporter");
		compass.setItemMeta(compassmeta);
	}

	static ItemStack settings = new ItemStack(Material.WATCH, 1);
	static ItemMeta settingsmeta = settings.getItemMeta();
	static {
		settingsmeta.setDisplayName("§7§lSettings");
		settings.setItemMeta(settingsmeta);
	}

	static Inventory teleporter = Bukkit.createInventory(null, 27,
			"Teleport Menu");
	static {
		// defining item snowfight
		ItemStack snowfight = new ItemStack(Material.SNOW_BALL, 1);
		ItemMeta snowfightmeta = snowfight.getItemMeta();
		snowfightmeta.setDisplayName("§7§lSnowfight");
		snowfight.setItemMeta(snowfightmeta);

		// defining item smp
		ItemStack smp = new ItemStack(Material.GRASS, 1);
		ItemMeta smpmeta = smp.getItemMeta();
		smpmeta.setDisplayName("§b§lSMP §7(Coming Soon)");
		smp.setItemMeta(smpmeta);
		
		ItemStack sg = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta sgmeta = sg.getItemMeta();
		sgmeta.setDisplayName("§c§lSurvival Games §7(Coming Soon)");
		sg.setItemMeta(sgmeta);

		// setting the items in the inventory
		teleporter.setItem(11, snowfight);
		teleporter.setItem(13, smp);
		teleporter.setItem(15, sg);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		TitleAPI.sendTitle(player, 0, 110, 10, "§3Beacon§fCraft",
				("§7welcomes you, §a" + player.getName() + "§7."));
		TitleAPI.sendTabTitle(player, "§3Beacon§fCraft", "§aplay.beaconcraftmc.net");
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta compassmeta = compass.getItemMeta();
		compassmeta.setDisplayName("§a§lServer Teleporter");
		compass.setItemMeta(compassmeta);
		player.getInventory().setItem(0, compass);
		player.getWorld().setDifficulty(Difficulty.PEACEFUL);
		Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
		event.setJoinMessage("§8§lJOIN§r: " + event.getPlayer().getName());
		disconnections.put(player, "§8§lQUIT");
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (disconnections.get(player).equals(null)) {
			disconnections.put(player, "§8§lQUIT");
			event.setQuitMessage("§8§lQUIT§r: " + player.getName());
		} else {
			event.setQuitMessage(disconnections.get(player) + "§r: "
					+ player.getName());
			disconnections.put(player, "§8§lQUIT");
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		player.getWorld().setDifficulty(Difficulty.PEACEFUL);
		if (player.getItemInHand().equals(compass)) {
			if (event.getAction().equals(Action.LEFT_CLICK_AIR)
					|| event.getAction().equals(Action.LEFT_CLICK_BLOCK)
					|| event.getAction().equals(Action.RIGHT_CLICK_AIR)
					|| event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				player.openInventory(teleporter);
				event.setCancelled(true);
			}
		}
	}

	boolean flying = false;

	@EventHandler
	public void onPlayerFly(PlayerCommandPreprocessEvent event) {
		if (event.getMessage().equals("/fly")) {
			if (event.getPlayer().getName().equals("MinemanTony")) {
				if (flying == false) {
					event.getPlayer().setAllowFlight(true);
					event.getPlayer().setFlying(true);
					event.setCancelled(true);
					flying = true;
				} else {
					event.getPlayer().setAllowFlight(false);
					event.getPlayer().setFlying(false);
					event.setCancelled(true);
					flying = false;
				}
			}
		}
	}

	@EventHandler
	public void onPlayerInventoryClick(InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		if (!(player.getInventory().getItem(0).equals(Material.COMPASS))) {
			ItemStack compass = new ItemStack(Material.COMPASS, 1);
			ItemMeta compassmeta = compass.getItemMeta();
			compassmeta.setDisplayName("§a§lServer Teleporter");
			compass.setItemMeta(compassmeta);
			player.getInventory().setItem(0, compass);
		}
		if (event.getInventory().getName().contains("Teleport Menu")) {
			if (event.getCurrentItem().getType().equals(Material.SNOW_BALL)) {
				player.sendMessage("§6§lSERVER§r: Connecting to §7Snowfight§r...");
				disconnections.put(player, "§8§lTO SNOWFIGHT");
				event.setCancelled(true);
				player.closeInventory();
				new BukkitRunnable() {
					public void run() {
						sendToServer(player, "Snowfight");
					}
				}.runTaskLater(this, 10l);
			} else if (event.getCurrentItem().getType().equals(Material.GRASS)) {
				player.sendMessage("§6§lSERVER§r: §bSMP§r is coming soon!");
				event.setCancelled(true);
				player.closeInventory();
			} else if (event.getCurrentItem().getType()
					.equals(Material.IRON_SWORD)) {
				player.sendMessage("§6§lSERVER§r: §cSurvival Games§r is coming soon!");
				event.setCancelled(true);
				player.closeInventory();
			}
		}
	}

	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if (event.getItemDrop().getItemStack().equals(compass)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			player.setMaxHealth(20l);
			player.setHealth(20l);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerFoodChange(FoodLevelChangeEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			player.setFoodLevel(1000000);
			event.setCancelled(true);
		}
	}
}