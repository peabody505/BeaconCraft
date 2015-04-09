/*   1:    */ package dev.peabody505.SMPWarp;
/*   2:    */ 
/*   3:    */ import java.util.HashMap;
/*   4:    */ import org.bukkit.Bukkit;
/*   5:    */ import org.bukkit.ChatColor;
/*   6:    */ import org.bukkit.Location;
/*   7:    */ import org.bukkit.Material;
/*   8:    */ import org.bukkit.Server;
/*   9:    */ import org.bukkit.World;
/*  10:    */ import org.bukkit.command.Command;
/*  11:    */ import org.bukkit.command.CommandSender;
/*  12:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  13:    */ import org.bukkit.entity.LivingEntity;
/*  14:    */ import org.bukkit.entity.Player;
/*  15:    */ import org.bukkit.entity.Sheep;
/*  16:    */ import org.bukkit.event.EventHandler;
/*  17:    */ import org.bukkit.event.Listener;
/*  18:    */ import org.bukkit.event.entity.EntityDeathEvent;
/*  19:    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*  20:    */ import org.bukkit.event.inventory.FurnaceSmeltEvent;
/*  21:    */ import org.bukkit.event.player.PlayerChatEvent;
/*  22:    */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*  23:    */ import org.bukkit.event.player.PlayerJoinEvent;
/*  24:    */ import org.bukkit.event.player.PlayerQuitEvent;
/*  25:    */ import org.bukkit.event.player.PlayerRespawnEvent;
/*  26:    */ import org.bukkit.inventory.ItemStack;
/*  27:    */ import org.bukkit.inventory.meta.ItemMeta;
/*  28:    */ import org.bukkit.plugin.PluginManager;
/*  29:    */ import org.bukkit.plugin.java.JavaPlugin;
/*  30:    */ import org.bukkit.scheduler.BukkitRunnable;
/*  31:    */ 
/*  32:    */ public final class Main
/*  33:    */   extends JavaPlugin
/*  34:    */   implements Listener
/*  35:    */ {
/*  36:    */   public void onEnable()
/*  37:    */   {
/*  38: 33 */     getServer().getPluginManager().registerEvents(this, this);
/*  39: 34 */     saveDefaultConfig();
/*  40:    */   }
/*  41:    */   
/*  42:    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*  43:    */   {
/*  44: 38 */     final Player player = (Player)sender;
/*  45: 39 */     if ((cmd.getLabel().equalsIgnoreCase("setspawn")) && (player.isOp()))
/*  46:    */     {
/*  47: 40 */       getConfig().set("spawn.world", player.getWorld().getName());
/*  48: 41 */       getConfig().set("spawn.x", Double.valueOf(player.getLocation().getX()));
/*  49: 42 */       getConfig().set("spawn.y", Double.valueOf(player.getLocation().getY()));
/*  50: 43 */       getConfig().set("spawn.z", Double.valueOf(player.getLocation().getZ()));
/*  51: 44 */       saveConfig();
/*  52: 45 */       player.sendMessage("§9[Info] §aSpawn set!");
/*  53: 46 */       return true;
/*  54:    */     }
/*  55: 48 */     if (cmd.getLabel().equalsIgnoreCase("spawn"))
/*  56:    */     {
/*  57: 49 */       World world = Bukkit.getWorld(getConfig().getString("spawn.world"));
/*  58: 50 */       player.teleport(new Location(world, 
/*  59: 51 */         getConfig().getDouble("spawn.x"), 
/*  60: 52 */         getConfig().getDouble("spawn.y"), 
/*  61: 53 */         getConfig().getDouble("spawn.z")));
/*  62: 54 */       player.sendMessage("§9[Info] §7Warped to §bspawn§7!");
/*  63: 55 */       return true;
/*  64:    */     }
/*  65: 57 */     if ((cmd.getLabel().equalsIgnoreCase("setmall")) && (player.isOp()))
/*  66:    */     {
/*  67: 58 */       getConfig().set("mall.world", player.getWorld().getName());
/*  68: 59 */       getConfig().set("mall.x", Double.valueOf(player.getLocation().getX()));
/*  69: 60 */       getConfig().set("mall.y", Double.valueOf(player.getLocation().getY()));
/*  70: 61 */       getConfig().set("mall.z", Double.valueOf(player.getLocation().getZ()));
/*  71: 62 */       saveConfig();
/*  72: 63 */       player.sendMessage("§9[Info] §aMall set!");
/*  73: 64 */       return true;
/*  74:    */     }
/*  75: 66 */     if (cmd.getLabel().equalsIgnoreCase("mall"))
/*  76:    */     {
/*  77: 67 */       World world = Bukkit.getWorld(getConfig().getString("mall.world"));
/*  78: 68 */       player.teleport(new Location(world, 
/*  79: 69 */         getConfig().getDouble("mall.x"), 
/*  80: 70 */         getConfig().getDouble("mall.y"), 
/*  81: 71 */         getConfig().getDouble("mall.z")));
/*  82: 72 */       player.sendMessage("§9[Info] §7Warped to §bmall§7!");
/*  83: 73 */       return true;
/*  84:    */     }
/*  85: 75 */     if (cmd.getLabel().equalsIgnoreCase("sethome"))
/*  86:    */     {
/*  87: 76 */       getConfig().set(player.getName() + ".world", player.getWorld().getName());
/*  88: 77 */       getConfig().set(player.getName() + ".x", Double.valueOf(player.getLocation().getX()));
/*  89: 78 */       getConfig().set(player.getName() + ".y", Double.valueOf(player.getLocation().getY()));
/*  90: 79 */       getConfig().set(player.getName() + ".z", Double.valueOf(player.getLocation().getZ()));
/*  91: 80 */       saveConfig();
/*  92: 81 */       player.sendMessage("§9[Info] §aHome set!");
/*  93: 82 */       return true;
/*  94:    */     }
/*  95: 84 */     if (cmd.getLabel().equalsIgnoreCase("home"))
/*  96:    */     {
/*  97: 85 */       World world = Bukkit.getWorld(getConfig().getString(player.getName() + ".world"));
/*  98: 86 */       player.teleport(new Location(world, 
/*  99: 87 */         getConfig().getDouble(player.getName() + ".x"), 
/* 100: 88 */         getConfig().getDouble(player.getName() + ".y"), 
/* 101: 89 */         getConfig().getDouble(player.getName() + ".z")));
/* 102: 90 */       player.sendMessage("§9[Info] §7Warped §bhome§7!");
/* 103: 91 */       return true;
/* 104:    */     }
/* 105: 93 */     if (cmd.getLabel().equalsIgnoreCase("back"))
/* 106:    */     {
/* 107: 94 */       if ((Double)deathmap.get(player.getName() + "x") == null)
/* 108:    */       {
/* 109: 95 */         player.sendMessage("§9[Info] §7You have nowhere to warp back to.");
/* 110: 96 */         return true;
/* 111:    */       }
/* 112: 98 */       Double x = (Double)deathmap.get(player.getName() + "x");
/* 113: 99 */       Double y = (Double)deathmap.get(player.getName() + "y");
/* 114:100 */       Double z = (Double)deathmap.get(player.getName() + "z");
/* 115:101 */       World world = Bukkit.getWorld((String)deathworldname.get(player.getName()));
/* 116:102 */       Location tp = new Location(world, x.doubleValue(), y.doubleValue(), z.doubleValue());
/* 117:103 */       player.teleport(tp);
/* 118:104 */       deathmap.remove(player.getName() + "x");
/* 119:105 */       deathmap.remove(player.getName() + "y");
/* 120:106 */       deathmap.remove(player.getName() + "z");
/* 121:    */     }
/* 122:108 */     else if (cmd.getLabel().equalsIgnoreCase("suicide"))
/* 123:    */     {
/* 124:109 */       player.sendMessage("§9[Info] §7Your family would be so dissapointed...");
/* 125:    */       
/* 126:111 */       new BukkitRunnable()
/* 127:    */       {
/* 128:    */         public void run()
/* 129:    */         {
/* 130:113 */           player.setHealth(0.0D);
/* 131:    */         }
/* 132:115 */       }.runTaskLater(this, 60L);
/* 133:    */     }
/* 134:117 */     return true;
/* 135:    */   }
/* 136:    */   
/* 137:    */   @EventHandler
/* 138:    */   public void onPlayerJoin(PlayerJoinEvent event)
/* 139:    */   {
/* 140:122 */     Player player = event.getPlayer();
/* 141:123 */     if (!getConfig().contains(player.getName()))
/* 142:    */     {
/* 143:124 */       World world = Bukkit.getWorld(getConfig().getString("spawn.world"));
/* 144:125 */       Location teleport = new Location(world, 
/* 145:126 */         getConfig().getDouble("spawn.x"), 
/* 146:127 */         getConfig().getDouble("spawn.y"), 
/* 147:128 */         getConfig().getDouble("spawn.z"));
/* 148:129 */       player.teleport(teleport);
/* 149:130 */       getConfig().set(player.getName() + ".x", Double.valueOf(getConfig().getDouble("spawn.x")));
/* 150:131 */       getConfig().set(player.getName() + ".y", Double.valueOf(getConfig().getDouble("spawn.y")));
/* 151:132 */       getConfig().set(player.getName() + ".z", Double.valueOf(getConfig().getDouble("spawn.z")));
/* 152:133 */       getConfig().set(player.getName() + ".world", getConfig().getString("spawn.world"));
/* 153:134 */       saveConfig();
/* 154:    */     }
/* 155:136 */     event.setJoinMessage("§9[Join] §7" + player.getName());
/* 156:    */   }
/* 157:    */   
/* 158:    */   @EventHandler
/* 159:    */   public void onRespawn(PlayerRespawnEvent event)
/* 160:    */   {
/* 161:141 */     Player player = event.getPlayer();
/* 162:142 */     World world = Bukkit.getWorld(getConfig().getString(player.getName() + ".world"));
/* 163:143 */     Location teleport = new Location(world, 
/* 164:144 */       getConfig().getDouble(player.getName() + ".x"), 
/* 165:145 */       getConfig().getDouble(player.getName() + ".y"), 
/* 166:146 */       getConfig().getDouble(player.getName() + ".z"));
/* 167:147 */     player.teleport(teleport);
/* 168:    */   }
/* 169:    */   
/* 170:    */   @EventHandler
/* 171:    */   public void onSheepDeath(EntityDeathEvent event)
/* 172:    */   {
/* 173:152 */     if (((event.getEntity() instanceof Sheep)) && 
/* 174:153 */       ((event.getEntity().getKiller() instanceof Player)))
/* 175:    */     {
/* 176:154 */       Location eventloc = event.getEntity().getLocation();
/* 177:155 */       ItemStack lambchop = new ItemStack(Material.PORK, 1);
/* 178:156 */       ItemMeta lcm = lambchop.getItemMeta();
/* 179:157 */       lcm.setDisplayName(ChatColor.WHITE + "Lamb Chop");
/* 180:158 */       lambchop.setItemMeta(lcm);
/* 181:159 */       eventloc.getWorld().dropItemNaturally(eventloc, lambchop);
/* 182:    */     }
/* 183:    */   }
/* 184:    */   
/* 185:    */   @EventHandler
/* 186:    */   public void onCookLambChop(FurnaceSmeltEvent event)
/* 187:    */   {
/* 188:166 */     if (event.getSource().getItemMeta().getDisplayName().contains("Lamb Chop"))
/* 189:    */     {
/* 190:167 */       ItemMeta rm = event.getResult().getItemMeta();
/* 191:168 */       rm.setDisplayName(ChatColor.WHITE + "Cooked Lamb Chop");
/* 192:169 */       event.getResult().setItemMeta(rm);
/* 193:    */     }
/* 194:    */   }
/* 195:    */   
/* 196:173 */   public static HashMap<String, Double> deathmap = new HashMap();
/* 197:174 */   public static HashMap<String, String> deathworldname = new HashMap();
/* 198:    */   
/* 199:    */   @EventHandler
/* 200:    */   public void onPlayerDeath(PlayerDeathEvent event)
/* 201:    */   {
/* 202:178 */     Player player = event.getEntity();
/* 203:179 */     deathmap.put(player.getName() + "x", Double.valueOf(player.getLocation().getX()));
/* 204:180 */     deathmap.put(player.getName() + "y", Double.valueOf(player.getLocation().getY()));
/* 205:181 */     deathmap.put(player.getName() + "z", Double.valueOf(player.getLocation().getZ()));
/* 206:182 */     deathworldname.put(player.getName(), player.getWorld().getName());
/* 207:183 */     player.sendMessage("§9[Info] §7Use §e/back §7to return to your death point.");
/* 208:    */   }
/* 209:    */   
/* 210:    */   @EventHandler
/* 211:    */   public void WorldSaveEvent(World world)
/* 212:    */   {
/* 213:188 */     Bukkit.broadcastMessage("§9[Info] §7World Saved.");
/* 214:    */   }
/* 215:    */   
/* 216:    */   @EventHandler
/* 217:    */   public void onPlayerLeave(PlayerQuitEvent event)
/* 218:    */   {
/* 219:193 */     Player player = event.getPlayer();
/* 220:194 */     event.setQuitMessage("§9[Quit] §7" + player.getName());
/* 221:    */   }
/* 222:    */   
/* 223:    */   @EventHandler
/* 224:    */   public void onPlayerCommandHelp(PlayerCommandPreprocessEvent event)
/* 225:    */   {
/* 226:199 */     Player player = event.getPlayer();
/* 227:200 */     if (event.getMessage().contains("/help"))
/* 228:    */     {
/* 229:201 */       if (!player.isOp())
/* 230:    */       {
/* 231:202 */         player.sendMessage("§9[---------------[§aEpsilon SMP Help§9]---------------]");
/* 232:203 */         player.sendMessage("  §b/sethome §7Sets your home.");
/* 233:204 */         player.sendMessage("  §b/home §7Warps you home.");
/* 234:205 */         player.sendMessage("  §b/mall §7Warps you to the mall.");
/* 235:206 */         player.sendMessage("  §b/spawn §7Warps you to the spawn.");
/* 236:207 */         player.sendMessage("  §b/makeitmine §7Personalises items...");
/* 237:208 */         player.sendMessage("  §b/back §7Sends you back to where you died.");
/* 238:    */       }
/* 239:210 */       else if (player.isOp())
/* 240:    */       {
/* 241:211 */         player.sendMessage("§9[---------------[§aEpsilon SMP Help§9]---------------]");
/* 242:212 */         player.sendMessage("  §b/sethome §7Sets your home.");
/* 243:213 */         player.sendMessage("  §b/home §7Warps you home.");
/* 244:214 */         player.sendMessage("  §b/mall §7Warps you to the mall.");
/* 245:215 */         player.sendMessage("  §b/spawn §7Warps you to spawn.");
/* 246:216 */         player.sendMessage("  §b/makeitmine §7Personalises items...");
/* 247:217 */         player.sendMessage("  §b/back §7Warps you back to where you died.");
/* 248:218 */         player.sendMessage("  §b/setrank §7Sets a player's rank.");
/* 249:219 */         player.sendMessage("  §b/setspawn §7Sets the spawn location.");
/* 250:220 */         player.sendMessage("  §b/setmall §7Sets the mall location.");
/* 251:    */       }
/* 252:222 */       event.setCancelled(true);
/* 253:    */     }
/* 254:    */   }
/* 255:    */   
/* 256:    */   @EventHandler
/* 257:    */   public void onPlayerChat(PlayerChatEvent event)
/* 258:    */   {
/* 259:228 */     Player player = event.getPlayer();
/* 260:229 */     Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.WHITE + ": " + event.getMessage());
/* 261:230 */     event.setCancelled(true);
/* 262:    */   }
/* 263:    */ }


/* Location:           C:\Users\Rufus\Desktop\EpsilonSMP - 2.jar
 * Qualified Name:     dev.peabody505.SMPWarp.Main
 * JD-Core Version:    0.7.0.1
 */