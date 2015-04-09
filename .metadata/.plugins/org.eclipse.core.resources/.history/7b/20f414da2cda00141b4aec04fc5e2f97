/*   1:    */ package com.gmail.filoghost.holographicdisplays.bridge.bungeecord;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.PingResponse;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.ServerAddress;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.ServerPinger;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*   9:    */ import java.io.IOException;
/*  10:    */ import java.net.SocketTimeoutException;
/*  11:    */ import java.net.UnknownHostException;
/*  12:    */ import java.util.Map;
/*  13:    */ import java.util.Map.Entry;
/*  14:    */ import java.util.concurrent.ConcurrentHashMap;
/*  15:    */ import java.util.logging.Logger;
/*  16:    */ import org.bukkit.Bukkit;
/*  17:    */ import org.bukkit.scheduler.BukkitRunnable;
/*  18:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  19:    */ 
/*  20:    */ public class BungeeServerTracker
/*  21:    */ {
/*  22: 22 */   private static Map<String, BungeeServerInfo> trackedServers = new ConcurrentHashMap();
/*  23: 23 */   private static int taskID = -1;
/*  24: 25 */   private static ServerPinger pinger = HolographicDisplays.isPreNetty() ? ServerPinger.PRE_NETTY_REWRITE : ServerPinger.POST_NETTY_REWRITE;
/*  25:    */   
/*  26:    */   public static void resetTrackedServers()
/*  27:    */   {
/*  28: 28 */     trackedServers.clear();
/*  29:    */   }
/*  30:    */   
/*  31:    */   public static void track(String server)
/*  32:    */   {
/*  33: 32 */     if (!trackedServers.containsKey(server))
/*  34:    */     {
/*  35: 33 */       BungeeServerInfo info = new BungeeServerInfo();
/*  36: 34 */       info.setMotd(Configuration.pingerOfflineMotd);
/*  37: 35 */       trackedServers.put(server, info);
/*  38: 37 */       if (!Configuration.pingerEnable) {
/*  39: 38 */         BungeeChannel.getInstance().askPlayerCount(server);
/*  40:    */       }
/*  41:    */     }
/*  42:    */   }
/*  43:    */   
/*  44:    */   public static void untrack(String server)
/*  45:    */   {
/*  46: 44 */     trackedServers.remove(server);
/*  47:    */   }
/*  48:    */   
/*  49:    */   protected static BungeeServerInfo getOrCreateServerInfo(String server)
/*  50:    */   {
/*  51: 48 */     BungeeServerInfo info = (BungeeServerInfo)trackedServers.get(server);
/*  52: 49 */     if (info == null)
/*  53:    */     {
/*  54: 50 */       info = new BungeeServerInfo();
/*  55: 51 */       info.setMotd(Configuration.pingerOfflineMotd);
/*  56: 52 */       trackedServers.put(server, info);
/*  57:    */     }
/*  58: 55 */     return info;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public static int getPlayersOnline(String server)
/*  62:    */   {
/*  63: 59 */     BungeeServerInfo info = (BungeeServerInfo)trackedServers.get(server);
/*  64: 60 */     if (info != null)
/*  65:    */     {
/*  66: 61 */       info.updateLastRequest();
/*  67: 62 */       return info.getOnlinePlayers();
/*  68:    */     }
/*  69: 65 */     track(server);
/*  70: 66 */     return 0;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public static String getMaxPlayers(String server)
/*  74:    */   {
/*  75: 72 */     if (!Configuration.pingerEnable) {
/*  76: 73 */       return "[Please enable pinger]";
/*  77:    */     }
/*  78: 76 */     BungeeServerInfo info = (BungeeServerInfo)trackedServers.get(server);
/*  79: 77 */     if (info != null)
/*  80:    */     {
/*  81: 78 */       info.updateLastRequest();
/*  82: 79 */       return String.valueOf(info.getMaxPlayers());
/*  83:    */     }
/*  84: 82 */     track(server);
/*  85: 83 */     return "0";
/*  86:    */   }
/*  87:    */   
/*  88:    */   public static String getMotd1(String server)
/*  89:    */   {
/*  90: 89 */     if (!Configuration.pingerEnable) {
/*  91: 90 */       return "[Please enable pinger]";
/*  92:    */     }
/*  93: 93 */     BungeeServerInfo info = (BungeeServerInfo)trackedServers.get(server);
/*  94: 94 */     if (info != null)
/*  95:    */     {
/*  96: 95 */       info.updateLastRequest();
/*  97: 96 */       return info.getMotd1();
/*  98:    */     }
/*  99: 99 */     track(server);
/* 100:100 */     return Configuration.pingerOfflineMotd;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public static String getMotd2(String server)
/* 104:    */   {
/* 105:106 */     if (!Configuration.pingerEnable) {
/* 106:107 */       return "[Please enable pinger]";
/* 107:    */     }
/* 108:110 */     BungeeServerInfo info = (BungeeServerInfo)trackedServers.get(server);
/* 109:111 */     if (info != null)
/* 110:    */     {
/* 111:112 */       info.updateLastRequest();
/* 112:113 */       return info.getMotd2();
/* 113:    */     }
/* 114:116 */     track(server);
/* 115:117 */     return "";
/* 116:    */   }
/* 117:    */   
/* 118:    */   public static String getOnlineStatus(String server)
/* 119:    */   {
/* 120:123 */     if (!Configuration.pingerEnable) {
/* 121:124 */       return "[Please enable pinger]";
/* 122:    */     }
/* 123:127 */     BungeeServerInfo info = (BungeeServerInfo)trackedServers.get(server);
/* 124:128 */     if (info != null)
/* 125:    */     {
/* 126:129 */       info.updateLastRequest();
/* 127:130 */       return info.isOnline() ? Configuration.pingerStatusOnline : Configuration.pingerStatusOffline;
/* 128:    */     }
/* 129:133 */     track(server);
/* 130:134 */     return "0";
/* 131:    */   }
/* 132:    */   
/* 133:    */   public static Map<String, BungeeServerInfo> getTrackedServers()
/* 134:    */   {
/* 135:139 */     return trackedServers;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public static void startTask(int refreshSeconds)
/* 139:    */   {
/* 140:144 */     if (taskID != -1) {
/* 141:145 */       Bukkit.getScheduler().cancelTask(taskID);
/* 142:    */     }
/* 143:148 */     taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(HolographicDisplays.getInstance(), new Runnable()
/* 144:    */     {
/* 145:    */       public void run()
/* 146:    */       {
/* 147:153 */         if (Configuration.pingerEnable) {
/* 148:195 */           new BukkitRunnable()
/* 149:    */           {
/* 150:    */             public void run()
/* 151:    */             {
/* 152:158 */               for (Map.Entry<String, ServerAddress> entry : Configuration.pingerServers.entrySet())
/* 153:    */               {
/* 154:160 */                 BungeeServerInfo serverInfo = BungeeServerTracker.getOrCreateServerInfo((String)entry.getKey());
/* 155:161 */                 boolean displayOffline = false;
/* 156:    */                 try
/* 157:    */                 {
/* 158:164 */                   PingResponse data = BungeeServerTracker.pinger.fetchData((ServerAddress)entry.getValue(), Configuration.pingerTimeout);
/* 159:166 */                   if (data.isOnline())
/* 160:    */                   {
/* 161:167 */                     serverInfo.setOnline(true);
/* 162:168 */                     serverInfo.setOnlinePlayers(data.getOnlinePlayers());
/* 163:169 */                     serverInfo.setMaxPlayers(data.getMaxPlayers());
/* 164:170 */                     serverInfo.setMotd(data.getMotd());
/* 165:    */                   }
/* 166:    */                   else
/* 167:    */                   {
/* 168:172 */                     displayOffline = true;
/* 169:    */                   }
/* 170:    */                 }
/* 171:    */                 catch (SocketTimeoutException e)
/* 172:    */                 {
/* 173:175 */                   displayOffline = true;
/* 174:    */                 }
/* 175:    */                 catch (UnknownHostException e)
/* 176:    */                 {
/* 177:177 */                   HolographicDisplays.getInstance().getLogger().warning("Couldn't fetch data from " + (String)entry.getKey() + "(" + ((ServerAddress)entry.getValue()).toString() + "): unknown host address.");
/* 178:178 */                   displayOffline = true;
/* 179:    */                 }
/* 180:    */                 catch (IOException e)
/* 181:    */                 {
/* 182:180 */                   displayOffline = true;
/* 183:    */                 }
/* 184:    */                 catch (Exception e)
/* 185:    */                 {
/* 186:182 */                   displayOffline = true;
/* 187:183 */                   HolographicDisplays.getInstance().getLogger().warning("Couldn't fetch data from " + (String)entry.getKey() + "(" + ((ServerAddress)entry.getValue()).toString() + "), unhandled exception: " + e.toString());
/* 188:184 */                   DebugHandler.handleDebugException(e);
/* 189:    */                 }
/* 190:187 */                 if (displayOffline)
/* 191:    */                 {
/* 192:188 */                   serverInfo.setOnline(false);
/* 193:189 */                   serverInfo.setOnlinePlayers(0);
/* 194:190 */                   serverInfo.setMaxPlayers(0);
/* 195:191 */                   serverInfo.setMotd(Configuration.pingerOfflineMotd);
/* 196:    */                 }
/* 197:    */               }
/* 198:    */             }
/* 199:195 */           }.runTaskAsynchronously(HolographicDisplays.getInstance());
/* 200:    */         } else {
/* 201:198 */           for (String server : BungeeServerTracker.trackedServers.keySet()) {
/* 202:199 */             BungeeChannel.getInstance().askPlayerCount(server);
/* 203:    */           }
/* 204:    */         }
/* 205:    */       }
/* 206:204 */     }, 1L, refreshSeconds * 20);
/* 207:    */   }
/* 208:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.bungeecord.BungeeServerTracker
 * JD-Core Version:    0.7.0.1
 */