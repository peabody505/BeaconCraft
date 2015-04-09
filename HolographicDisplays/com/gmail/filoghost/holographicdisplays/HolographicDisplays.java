/*   1:    */ package com.gmail.filoghost.holographicdisplays;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.bridge.bungeecord.BungeeServerTracker;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.bridge.protocollib.ProtocolLibHook;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramsCommandHandler;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.disk.UnicodeSymbols;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.exception.HologramNotFoundException;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.exception.InvalidFormatException;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.exception.WorldNotFoundException;
/*  12:    */ import com.gmail.filoghost.holographicdisplays.listener.MainListener;
/*  13:    */ import com.gmail.filoghost.holographicdisplays.metrics.MetricsLite;
/*  14:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager;
/*  15:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/*  16:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/*  17:    */ import com.gmail.filoghost.holographicdisplays.placeholder.AnimationsRegister;
/*  18:    */ import com.gmail.filoghost.holographicdisplays.placeholder.PlaceholdersManager;
/*  19:    */ import com.gmail.filoghost.holographicdisplays.task.BungeeCleanupTask;
/*  20:    */ import com.gmail.filoghost.holographicdisplays.task.StartupLoadHologramsTask;
/*  21:    */ import com.gmail.filoghost.holographicdisplays.task.WorldPlayerCounterTask;
/*  22:    */ import com.gmail.filoghost.holographicdisplays.util.VersionUtils;
/*  23:    */ import java.io.PrintStream;
/*  24:    */ import java.util.Set;
/*  25:    */ import java.util.logging.Logger;
/*  26:    */ import org.bukkit.Bukkit;
/*  27:    */ import org.bukkit.command.PluginCommand;
/*  28:    */ import org.bukkit.plugin.PluginManager;
/*  29:    */ import org.bukkit.plugin.java.JavaPlugin;
/*  30:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  31:    */ 
/*  32:    */ public class HolographicDisplays
/*  33:    */   extends JavaPlugin
/*  34:    */ {
/*  35:    */   private static HolographicDisplays instance;
/*  36:    */   private static NMSManager nmsManager;
/*  37:    */   private HologramsCommandHandler commandHandler;
/*  38:    */   private static boolean is1_8;
/*  39:    */   private static boolean isPreNetty;
/*  40:    */   private static boolean useProtocolLib;
/*  41:    */   private static String newVersion;
/*  42:    */   
/*  43:    */   public void onEnable()
/*  44:    */   {
/*  45: 57 */     if (instance != null)
/*  46:    */     {
/*  47: 58 */       getLogger().warning("Please do not use /reload or plugin reloaders. Do \"/holograms reload\" instead.");
/*  48: 59 */       return;
/*  49:    */     }
/*  50: 62 */     instance = this;
/*  51:    */     
/*  52:    */ 
/*  53: 65 */     UnicodeSymbols.load(this);
/*  54:    */     
/*  55:    */ 
/*  56: 68 */     Configuration.load(this);
/*  57: 70 */     if (Configuration.updateNotification) {
/*  58: 71 */       new SimpleUpdater(this, 75097).checkForUpdates(new SimpleUpdater.ResponseHandler()
/*  59:    */       {
/*  60:    */         public void onUpdateFound(String newVersion)
/*  61:    */         {
/*  62: 76 */           HolographicDisplays.newVersion = newVersion;
/*  63: 77 */           HolographicDisplays.this.getLogger().info("Found a new version available: " + newVersion);
/*  64: 78 */           HolographicDisplays.this.getLogger().info("Download it on Bukkit Dev:");
/*  65: 79 */           HolographicDisplays.this.getLogger().info("dev.bukkit.org/bukkit-plugins/holographic-displays");
/*  66:    */         }
/*  67:    */       });
/*  68:    */     }
/*  69: 84 */     String version = VersionUtils.getBukkitVersion();
/*  70: 86 */     if (version == null)
/*  71:    */     {
/*  72: 88 */       version = VersionUtils.getMinecraftVersion();
/*  73: 90 */       if ("1.6.4".equals(version))
/*  74:    */       {
/*  75: 91 */         version = "v1_6_R3";
/*  76: 92 */         isPreNetty = true;
/*  77:    */       }
/*  78: 93 */       else if ("1.7.2".equals(version))
/*  79:    */       {
/*  80: 94 */         version = "v1_7_R1";
/*  81:    */       }
/*  82: 95 */       else if ("1.7.5".equals(version))
/*  83:    */       {
/*  84: 96 */         version = "v1_7_R2";
/*  85:    */       }
/*  86: 97 */       else if ("1.7.8".equals(version))
/*  87:    */       {
/*  88: 98 */         version = "v1_7_R3";
/*  89:    */       }
/*  90: 99 */       else if ("1.7.10".equals(version))
/*  91:    */       {
/*  92:100 */         version = "v1_7_R4";
/*  93:    */       }
/*  94:101 */       else if ("1.8".equals(version))
/*  95:    */       {
/*  96:102 */         version = "v1_8_R1";
/*  97:    */       }
/*  98:103 */       else if ("1.8.3".equals(version))
/*  99:    */       {
/* 100:104 */         version = "v1_8_R2";
/* 101:    */       }
/* 102:    */       else
/* 103:    */       {
/* 104:107 */         version = null;
/* 105:    */       }
/* 106:    */     }
/* 107:112 */     if ("v1_6_R3".equals(version))
/* 108:    */     {
/* 109:113 */       nmsManager = new com.gmail.filoghost.holographicdisplays.nms.v1_6_R3.NmsManagerImpl();
/* 110:    */     }
/* 111:114 */     else if ("v1_7_R1".equals(version))
/* 112:    */     {
/* 113:115 */       nmsManager = new com.gmail.filoghost.holographicdisplays.nms.v1_7_R1.NmsManagerImpl();
/* 114:    */     }
/* 115:116 */     else if ("v1_7_R2".equals(version))
/* 116:    */     {
/* 117:117 */       nmsManager = new com.gmail.filoghost.holographicdisplays.nms.v1_7_R2.NmsManagerImpl();
/* 118:    */     }
/* 119:118 */     else if ("v1_7_R3".equals(version))
/* 120:    */     {
/* 121:119 */       nmsManager = new com.gmail.filoghost.holographicdisplays.nms.v1_7_R3.NmsManagerImpl();
/* 122:    */     }
/* 123:120 */     else if ("v1_7_R4".equals(version))
/* 124:    */     {
/* 125:121 */       nmsManager = new com.gmail.filoghost.holographicdisplays.nms.v1_7_R4.NmsManagerImpl();
/* 126:    */     }
/* 127:122 */     else if ("v1_8_R1".equals(version))
/* 128:    */     {
/* 129:123 */       is1_8 = true;
/* 130:124 */       nmsManager = new com.gmail.filoghost.holographicdisplays.nms.v1_8_R1.NmsManagerImpl();
/* 131:    */     }
/* 132:125 */     else if ("v1_8_R2".equals(version))
/* 133:    */     {
/* 134:126 */       is1_8 = true;
/* 135:127 */       nmsManager = new com.gmail.filoghost.holographicdisplays.nms.v1_8_R2.NmsManagerImpl();
/* 136:    */     }
/* 137:    */     else
/* 138:    */     {
/* 139:129 */       printWarnAndDisable(new String[] {
/* 140:130 */         "******************************************************", 
/* 141:131 */         "     This version of HolographicDisplays can", 
/* 142:132 */         "     only work on these server versions:", 
/* 143:133 */         "     from 1.6.4 to 1.8.3.", 
/* 144:134 */         "     The plugin will be disabled.", 
/* 145:135 */         "******************************************************" });
/* 146:    */       
/* 147:137 */       return;
/* 148:    */     }
/* 149:    */     try
/* 150:    */     {
/* 151:141 */       if (VersionUtils.isMCPCOrCauldron()) {
/* 152:142 */         getLogger().info("Trying to enable Cauldron/MCPC+ support...");
/* 153:    */       }
/* 154:145 */       nmsManager.setup();
/* 155:147 */       if (VersionUtils.isMCPCOrCauldron()) {
/* 156:148 */         getLogger().info("Successfully added support for Cauldron/MCPC+!");
/* 157:    */       }
/* 158:    */     }
/* 159:    */     catch (Exception e)
/* 160:    */     {
/* 161:152 */       e.printStackTrace();
/* 162:153 */       printWarnAndDisable(new String[] {
/* 163:154 */         "******************************************************", 
/* 164:155 */         "     HolographicDisplays was unable to register", 
/* 165:156 */         "     custom entities, the plugin will be disabled.", 
/* 166:157 */         "     Are you using the correct Bukkit version?", 
/* 167:158 */         "******************************************************" });
/* 168:    */       
/* 169:160 */       return;
/* 170:    */     }
/* 171:    */     try
/* 172:    */     {
/* 173:165 */       if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
/* 174:166 */         useProtocolLib = ProtocolLibHook.load(nmsManager, this, is1_8);
/* 175:    */       }
/* 176:    */     }
/* 177:    */     catch (Exception ex)
/* 178:    */     {
/* 179:169 */       ex.printStackTrace();
/* 180:170 */       getLogger().warning("Failed to load ProtocolLib support. Is it updated?");
/* 181:    */     }
/* 182:174 */     PlaceholdersManager.load(this);
/* 183:    */     try
/* 184:    */     {
/* 185:176 */       AnimationsRegister.loadAnimations(this);
/* 186:    */     }
/* 187:    */     catch (Exception ex)
/* 188:    */     {
/* 189:178 */       ex.printStackTrace();
/* 190:179 */       getLogger().warning("Failed to load animation files!");
/* 191:    */     }
/* 192:183 */     HologramDatabase.loadYamlFile(this);
/* 193:184 */     BungeeServerTracker.startTask(Configuration.bungeeRefreshSeconds);
/* 194:    */     
/* 195:    */ 
/* 196:187 */     Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BungeeCleanupTask(), 6000L, 6000L);
/* 197:188 */     Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new WorldPlayerCounterTask(), 0L, 60L);
/* 198:    */     
/* 199:190 */     Set<String> savedHologramsNames = HologramDatabase.getHolograms();
/* 200:191 */     if ((savedHologramsNames != null) && (savedHologramsNames.size() > 0)) {
/* 201:192 */       for (String singleHologramName : savedHologramsNames) {
/* 202:    */         try
/* 203:    */         {
/* 204:194 */           NamedHologram singleHologram = HologramDatabase.loadHologram(singleHologramName);
/* 205:195 */           NamedHologramManager.addHologram(singleHologram);
/* 206:    */         }
/* 207:    */         catch (HologramNotFoundException e)
/* 208:    */         {
/* 209:197 */           getLogger().warning("Hologram '" + singleHologramName + "' not found, skipping it.");
/* 210:    */         }
/* 211:    */         catch (InvalidFormatException e)
/* 212:    */         {
/* 213:199 */           getLogger().warning("Hologram '" + singleHologramName + "' has an invalid location format.");
/* 214:    */         }
/* 215:    */         catch (WorldNotFoundException e)
/* 216:    */         {
/* 217:201 */           getLogger().warning("Hologram '" + singleHologramName + "' was in the world '" + e.getMessage() + "' but it wasn't loaded.");
/* 218:    */         }
/* 219:    */         catch (Exception e)
/* 220:    */         {
/* 221:203 */           e.printStackTrace();
/* 222:204 */           getLogger().warning("Unhandled exception while loading the hologram '" + singleHologramName + "'. Please contact the developer.");
/* 223:    */         }
/* 224:    */       }
/* 225:    */     }
/* 226:209 */     if (getCommand("holograms") == null)
/* 227:    */     {
/* 228:210 */       printWarnAndDisable(new String[] {
/* 229:211 */         "******************************************************", 
/* 230:212 */         "     HolographicDisplays was unable to register", 
/* 231:213 */         "     the command \"holograms\". Do not modify", 
/* 232:214 */         "     plugin.yml removing commands, if you're", 
/* 233:215 */         "     doing so.", 
/* 234:216 */         "******************************************************" });
/* 235:    */       
/* 236:218 */       return;
/* 237:    */     }
/* 238:221 */     getCommand("holograms").setExecutor(this.commandHandler = new HologramsCommandHandler());
/* 239:222 */     Bukkit.getPluginManager().registerEvents(new MainListener(nmsManager), this);
/* 240:    */     try
/* 241:    */     {
/* 242:225 */       MetricsLite metrics = new MetricsLite(this);
/* 243:226 */       metrics.start();
/* 244:    */     }
/* 245:    */     catch (Exception localException1) {}
/* 246:230 */     Bukkit.getScheduler().scheduleSyncDelayedTask(this, new StartupLoadHologramsTask(), 10L);
/* 247:    */   }
/* 248:    */   
/* 249:    */   public void onDisable()
/* 250:    */   {
/* 251:236 */     for (NamedHologram hologram : ) {
/* 252:237 */       hologram.despawnEntities();
/* 253:    */     }
/* 254:    */   }
/* 255:    */   
/* 256:    */   public static NMSManager getNMSManager()
/* 257:    */   {
/* 258:242 */     return nmsManager;
/* 259:    */   }
/* 260:    */   
/* 261:    */   public HologramsCommandHandler getCommandHandler()
/* 262:    */   {
/* 263:246 */     return this.commandHandler;
/* 264:    */   }
/* 265:    */   
/* 266:    */   public static boolean is1_8()
/* 267:    */   {
/* 268:250 */     return is1_8;
/* 269:    */   }
/* 270:    */   
/* 271:    */   public static boolean isPreNetty()
/* 272:    */   {
/* 273:254 */     return isPreNetty;
/* 274:    */   }
/* 275:    */   
/* 276:    */   private static void printWarnAndDisable(String... messages)
/* 277:    */   {
/* 278:258 */     StringBuffer buffer = new StringBuffer("\n ");
/* 279:259 */     String[] arrayOfString = messages;int j = messages.length;
/* 280:259 */     for (int i = 0; i < j; i++)
/* 281:    */     {
/* 282:259 */       String message = arrayOfString[i];
/* 283:260 */       buffer.append('\n');
/* 284:261 */       buffer.append(message);
/* 285:    */     }
/* 286:263 */     buffer.append('\n');
/* 287:264 */     System.out.println(buffer.toString());
/* 288:    */     try
/* 289:    */     {
/* 290:266 */       Thread.sleep(5000L);
/* 291:    */     }
/* 292:    */     catch (InterruptedException localInterruptedException) {}
/* 293:268 */     instance.setEnabled(false);
/* 294:    */   }
/* 295:    */   
/* 296:    */   public static HolographicDisplays getInstance()
/* 297:    */   {
/* 298:272 */     return instance;
/* 299:    */   }
/* 300:    */   
/* 301:    */   public static String getNewVersion()
/* 302:    */   {
/* 303:277 */     return newVersion;
/* 304:    */   }
/* 305:    */   
/* 306:    */   public static boolean useProtocolLib()
/* 307:    */   {
/* 308:282 */     return useProtocolLib;
/* 309:    */   }
/* 310:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.HolographicDisplays
 * JD-Core Version:    0.7.0.1
 */