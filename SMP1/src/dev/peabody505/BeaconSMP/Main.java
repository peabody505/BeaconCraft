/*   1:    */ package dev.peabody505.BeaconSMP;
/*   2:    */ 
/*   3:    */ import org.bukkit.Bukkit;
/*   4:    */ import org.bukkit.ChatColor;
/*   5:    */ import org.bukkit.Location;
/*   6:    */ import org.bukkit.Material;
/*   7:    */ import org.bukkit.Server;
/*   8:    */ import org.bukkit.World;
/*   9:    */ import org.bukkit.block.Block;
/*  10:    */ import org.bukkit.command.Command;
/*  11:    */ import org.bukkit.command.CommandSender;
/*  12:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  13:    */ import org.bukkit.entity.Player;
/*  14:    */ import org.bukkit.event.EventHandler;
/*  15:    */ import org.bukkit.event.Listener;
/*  16:    */ import org.bukkit.event.block.Action;
/*  17:    */ import org.bukkit.event.block.BlockBreakEvent;
/*  18:    */ import org.bukkit.event.block.BlockPlaceEvent;
/*  19:    */ import org.bukkit.event.player.PlayerInteractEvent;
/*  20:    */ import org.bukkit.event.player.PlayerJoinEvent;
/*  21:    */ import org.bukkit.plugin.PluginManager;
/*  22:    */ import org.bukkit.plugin.java.JavaPlugin;
/*  23:    */ import org.bukkit.scheduler.BukkitRunnable;
/*  24:    */ import org.bukkit.scoreboard.DisplaySlot;
/*  25:    */ import org.bukkit.scoreboard.Objective;
/*  26:    */ import org.bukkit.scoreboard.Score;
/*  27:    */ import org.bukkit.scoreboard.Scoreboard;
/*  28:    */ import org.bukkit.scoreboard.ScoreboardManager;
/*  29:    */ 
/*  30:    */ public final class Main
/*  31:    */   extends JavaPlugin
/*  32:    */   implements Listener
/*  33:    */ {
/*  34:    */   public void onEnable()
/*  35:    */   {
/*  36: 28 */     Bukkit.getServer().getPluginManager().registerEvents(this, this);
/*  37: 29 */     saveDefaultConfig();
/*  38:    */   }
/*  39:    */   
/*  40:    */   @EventHandler
/*  41:    */   public void onPlayerJoin(PlayerJoinEvent event)
/*  42:    */   {
/*  43: 34 */     Player player = event.getPlayer();
/*  44: 36 */     if (!getConfig().contains(player.getName())) {
/*  45: 37 */       getConfig().set(player.getName(), "None");
/*  46:    */     }
/*  47: 40 */     ScoreboardManager manager = Bukkit.getScoreboardManager();
/*  48: 41 */     Scoreboard board = manager.getNewScoreboard();
/*  49: 42 */     Objective name = board.registerNewObjective("name", "dummy");
/*  50:    */     
/*  51: 44 */     name.setDisplayName(ChatColor.GOLD + ChatColor.BOLD + player.getName());
/*  52: 45 */     name.setDisplaySlot(DisplaySlot.SIDEBAR);
/*  53:    */     
/*  54: 47 */     Score iptitle = name.getScore(ChatColor.GREEN + ChatColor.BOLD + "Server:");
/*  55: 48 */     Score ip = name.getScore("Epsilon SMP");
/*  56: 49 */     Score blank1 = name.getScore(Bukkit.getOfflinePlayer("         "));
/*  57:    */     
/*  58: 51 */     blank1.setScore(3);
/*  59: 52 */     iptitle.setScore(2);
/*  60: 53 */     ip.setScore(1);
/*  61:    */     
/*  62: 55 */     Score ranktitle = name.getScore(ChatColor.AQUA + ChatColor.BOLD + "Rank:");
/*  63: 56 */     Score rank = name.getScore(getConfig().getString(player.getName()));
/*  64: 57 */     Score blank2 = name.getScore("");
/*  65:    */     
/*  66: 59 */     ranktitle.setScore(5);
/*  67: 60 */     rank.setScore(4);
/*  68: 61 */     blank2.setScore(6);
/*  69:    */     
/*  70: 63 */     player.setScoreboard(board);
/*  71:    */   }
/*  72:    */   
/*  73:    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*  74:    */   {
/*  75: 68 */     Player player = (Player)sender;
/*  76:    */     
/*  77: 70 */     Player target = Bukkit.getPlayer(args[1]);
/*  78: 71 */     if (cmd.getLabel().equalsIgnoreCase("setrank"))
/*  79:    */     {
/*  80: 72 */       if (args[0].equalsIgnoreCase("yt"))
/*  81:    */       {
/*  82: 73 */         getConfig().set(target.getName(), "YouTuber");
/*  83:    */         
/*  84: 75 */         ScoreboardManager manager = Bukkit.getScoreboardManager();
/*  85: 76 */         Scoreboard board = manager.getNewScoreboard();
/*  86: 77 */         Objective name = board.registerNewObjective("dummy", "name");
/*  87:    */         
/*  88: 79 */         name.setDisplayName(ChatColor.GOLD + ChatColor.BOLD + player.getName());
/*  89: 80 */         name.setDisplaySlot(DisplaySlot.SIDEBAR);
/*  90:    */         
/*  91: 82 */         Score iptitle = name.getScore(ChatColor.GREEN + ChatColor.BOLD + "Server:");
/*  92: 83 */         Score ip = name.getScore("Epsilon SMP");
/*  93: 84 */         Score blank1 = name.getScore("");
/*  94:    */         
/*  95: 86 */         blank1.setScore(3);
/*  96: 87 */         iptitle.setScore(2);
/*  97: 88 */         ip.setScore(1);
/*  98:    */         
/*  99: 90 */         Score ranktitle = name.getScore(ChatColor.AQUA + ChatColor.BOLD + "Rank:");
/* 100: 91 */         Score rank = name.getScore(getConfig().getString(player.getName()));
/* 101: 92 */         Score blank2 = name.getScore("");
/* 102:    */         
/* 103: 94 */         ranktitle.setScore(5);
/* 104: 95 */         rank.setScore(4);
/* 105:    */         
/* 106: 97 */         blank2.setScore(6);
/* 107:    */         
/* 108: 99 */         player.setScoreboard(board);
/* 109:    */         
/* 110:101 */         saveConfig();
/* 111:102 */         return true;
/* 112:    */       }
/* 113:104 */       if (args[0].equalsIgnoreCase("staff"))
/* 114:    */       {
/* 115:105 */         getConfig().set(target.getName(), "Staff Member");
/* 116:    */         
/* 117:107 */         ScoreboardManager manager = Bukkit.getScoreboardManager();
/* 118:108 */         Scoreboard board = manager.getNewScoreboard();
/* 119:109 */         Objective name = board.registerNewObjective("dummy", "name");
/* 120:    */         
/* 121:111 */         name.setDisplayName(ChatColor.GOLD + ChatColor.BOLD + player.getName());
/* 122:112 */         name.setDisplaySlot(DisplaySlot.SIDEBAR);
/* 123:    */         
/* 124:114 */         Score iptitle = name.getScore(ChatColor.GREEN + ChatColor.BOLD + "Server:");
/* 125:115 */         Score ip = name.getScore("Epsilon SMP");
/* 126:116 */         Score blank1 = name.getScore(" ");
/* 127:    */         
/* 128:118 */         blank1.setScore(3);
/* 129:119 */         iptitle.setScore(2);
/* 130:120 */         ip.setScore(1);
/* 131:    */         
/* 132:122 */         Score ranktitle = name.getScore(ChatColor.AQUA + ChatColor.BOLD + "Rank:");
/* 133:123 */         Score rank = name.getScore(getConfig().getString(player.getName()));
/* 134:124 */         Score blank2 = name.getScore("");
/* 135:    */         
/* 136:126 */         ranktitle.setScore(5);
/* 137:127 */         rank.setScore(4);
/* 138:    */         
/* 139:129 */         blank2.setScore(6);
/* 140:    */         
/* 141:131 */         player.setScoreboard(board);
/* 142:    */         
/* 143:133 */         saveConfig();
/* 144:134 */         return true;
/* 145:    */       }
/* 146:136 */       if (args[0].equalsIgnoreCase("none"))
/* 147:    */       {
/* 148:137 */         getConfig().set(target.getName(), "None");
/* 149:    */         
/* 150:139 */         ScoreboardManager manager = Bukkit.getScoreboardManager();
/* 151:140 */         Scoreboard board = manager.getNewScoreboard();
/* 152:141 */         Objective name = board.registerNewObjective("dummy", "name");
/* 153:    */         
/* 154:143 */         name.setDisplayName(ChatColor.GOLD + ChatColor.BOLD + player.getName());
/* 155:144 */         name.setDisplaySlot(DisplaySlot.SIDEBAR);
/* 156:    */         
/* 157:146 */         Score iptitle = name.getScore(ChatColor.GREEN + ChatColor.BOLD + "Server:");
/* 158:147 */         Score ip = name.getScore("Epsilon SMP");
/* 159:148 */         Score blank1 = name.getScore(" ");
/* 160:    */         
/* 161:150 */         blank1.setScore(3);
/* 162:151 */         iptitle.setScore(2);
/* 163:152 */         ip.setScore(1);
/* 164:    */         
/* 165:154 */         Score ranktitle = name.getScore(ChatColor.AQUA + ChatColor.BOLD + "Rank:");
/* 166:155 */         Score rank = name.getScore(getConfig().getString(player.getName()));
/* 167:156 */         Score blank2 = name.getScore("");
/* 168:    */         
/* 169:158 */         ranktitle.setScore(5);
/* 170:159 */         rank.setScore(4);
/* 171:    */         
/* 172:161 */         blank2.setScore(6);
/* 173:    */         
/* 174:163 */         player.setScoreboard(board);
/* 175:    */         
/* 176:165 */         saveConfig();
/* 177:166 */         return true;
/* 178:    */       }
/* 179:    */     }
/* 180:170 */     return true;
/* 181:    */   }
/* 182:    */   
/* 183:    */   @EventHandler
/* 184:    */   public void onPlayerClickBed(PlayerInteractEvent event)
/* 185:    */   {
/* 186:175 */     final Player player = event.getPlayer();
/* 187:176 */     if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && 
/* 188:177 */       (event.getClickedBlock().getType().equals(Material.BED))) {
/* 189:183 */       new BukkitRunnable()
/* 190:    */       {
/* 191:    */         public void run()
/* 192:    */         {
/* 193:181 */           player.getWorld().setTime(0L);
/* 194:    */         }
/* 195:183 */       }.runTaskLater(this, 40L);
/* 196:    */     }
/* 197:    */   }
/* 198:    */   
/* 199:    */   @EventHandler
/* 200:    */   public void onBlockPlace(BlockPlaceEvent event)
/* 201:    */   {
/* 202:190 */     Player player = event.getPlayer();
/* 203:191 */     Location loc = event.getBlock().getLocation();
/* 204:192 */     if ((loc.getX() <= 200.0D) && (loc.getX() >= -200.0D) && (loc.getZ() <= 200.0D) && (loc.getZ() >= -200.0D))
/* 205:    */     {
/* 206:195 */       player.sendMessage("§9[Info] §7You must be at least 200 blocks away from spawn to build.");
/* 207:196 */       event.setCancelled(true);
/* 208:    */     }
/* 209:    */   }
/* 210:    */   
/* 211:    */   @EventHandler
/* 212:    */   public void onBlockBreak(BlockBreakEvent event)
/* 213:    */   {
/* 214:203 */     Player player = event.getPlayer();
/* 215:204 */     Location loc = event.getBlock().getLocation();
/* 216:205 */     if ((loc.getX() <= 200.0D) && (loc.getX() >= -200.0D) && (loc.getZ() <= 200.0D) && (loc.getZ() >= -200.0D))
/* 217:    */     {
/* 218:208 */       player.sendMessage("§9[Info] §7You must be at least 200 blocks away from spawn to break blocks.");
/* 219:209 */       event.setCancelled(true);
/* 220:    */     }
/* 221:    */   }
/* 222:    */ }


/* Location:           C:\Users\Rufus\Desktop\EpsilonSMP - 1.jar
 * Qualified Name:     dev.peabody505.SMP.Main
 * JD-Core Version:    0.7.0.1
 */