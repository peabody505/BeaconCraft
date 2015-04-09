/*   1:    */ package com.gmail.filoghost.holographicdisplays.disk;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.ServerAddress;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*   5:    */ import java.io.File;
/*   6:    */ import java.io.IOException;
/*   7:    */ import java.text.SimpleDateFormat;
/*   8:    */ import java.util.Arrays;
/*   9:    */ import java.util.List;
/*  10:    */ import java.util.Map;
/*  11:    */ import java.util.TimeZone;
/*  12:    */ import java.util.logging.Logger;
/*  13:    */ import org.bukkit.ChatColor;
/*  14:    */ import org.bukkit.configuration.InvalidConfigurationException;
/*  15:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  16:    */ import org.bukkit.configuration.file.YamlConfigurationOptions;
/*  17:    */ import org.bukkit.plugin.Plugin;
/*  18:    */ 
/*  19:    */ public class Configuration
/*  20:    */ {
/*  21:    */   public static double spaceBetweenLines;
/*  22:    */   public static String imageSymbol;
/*  23:    */   public static String transparencySymbol;
/*  24:    */   public static boolean updateNotification;
/*  25:    */   public static ChatColor transparencyColor;
/*  26:    */   public static SimpleDateFormat timeFormat;
/*  27:    */   public static int bungeeRefreshSeconds;
/*  28:    */   public static boolean useRedisBungee;
/*  29:    */   public static boolean pingerEnable;
/*  30:    */   public static int pingerTimeout;
/*  31:    */   public static String pingerOfflineMotd;
/*  32:    */   public static String pingerStatusOnline;
/*  33:    */   public static String pingerStatusOffline;
/*  34:    */   public static boolean pingerTrimMotd;
/*  35:    */   public static Map<String, ServerAddress> pingerServers;
/*  36:    */   public static boolean debug;
/*  37:    */   
/*  38:    */   public static void load(Plugin plugin)
/*  39:    */   {
/*  40: 48 */     File configFile = new File(plugin.getDataFolder(), "config.yml");
/*  41: 49 */     if (!configFile.exists())
/*  42:    */     {
/*  43: 50 */       plugin.getDataFolder().mkdirs();
/*  44: 51 */       plugin.saveResource("config.yml", true);
/*  45:    */     }
/*  46: 54 */     YamlConfiguration config = new YamlConfiguration();
/*  47:    */     try
/*  48:    */     {
/*  49: 56 */       config.load(configFile);
/*  50:    */     }
/*  51:    */     catch (InvalidConfigurationException e)
/*  52:    */     {
/*  53: 58 */       e.printStackTrace();
/*  54: 59 */       plugin.getLogger().warning("The configuration is not a valid YAML file! Please check it with a tool like http://yaml-online-parser.appspot.com/");
/*  55: 60 */       return;
/*  56:    */     }
/*  57:    */     catch (IOException e)
/*  58:    */     {
/*  59: 62 */       e.printStackTrace();
/*  60: 63 */       plugin.getLogger().warning("I/O error while reading the configuration. Was the file in use?");
/*  61: 64 */       return;
/*  62:    */     }
/*  63:    */     catch (Exception e)
/*  64:    */     {
/*  65: 66 */       e.printStackTrace();
/*  66: 67 */       plugin.getLogger().warning("Unhandled exception while reading the configuration!");
/*  67: 68 */       return;
/*  68:    */     }
/*  69: 71 */     boolean needsSave = false;
/*  70: 73 */     for (ConfigNode node : ConfigNode.values()) {
/*  71: 74 */       if (!config.isSet(node.getPath()))
/*  72:    */       {
/*  73: 75 */         needsSave = true;
/*  74: 76 */         config.set(node.getPath(), node.getDefaultValue());
/*  75:    */       }
/*  76:    */     }
/*  77: 87 */     List<String> nodesToRemove = Arrays.asList(new String[] { "vertical-spacing", "time-format", "bungee-refresh-seconds", "using-RedisBungee", "bungee-online-format", "bungee-offline-format" });
/*  78: 90 */     for (String oldNode : nodesToRemove) {
/*  79: 91 */       if (config.isSet(oldNode))
/*  80:    */       {
/*  81: 92 */         config.set(oldNode, null);
/*  82: 93 */         needsSave = true;
/*  83:    */       }
/*  84:    */     }
/*  85: 99 */     if (needsSave)
/*  86:    */     {
/*  87:100 */       config.options().header(Utils.join(new String[] {
/*  88:101 */         ".", 
/*  89:102 */         ".  Read the tutorial at: http://dev.bukkit.org/bukkit-plugins/holographic-displays/", 
/*  90:103 */         ".", 
/*  91:104 */         ".  Plugin created by filoghost.", 
/*  92:105 */         "." }, 
/*  93:106 */         "\n"));
/*  94:107 */       config.options().copyHeader(true);
/*  95:    */       try
/*  96:    */       {
/*  97:109 */         config.save(configFile);
/*  98:    */       }
/*  99:    */       catch (IOException e)
/* 100:    */       {
/* 101:111 */         e.printStackTrace();
/* 102:112 */         plugin.getLogger().warning("I/O error while saving the configuration. Was the file in use?");
/* 103:    */       }
/* 104:    */     }
/* 105:116 */     spaceBetweenLines = config.getDouble(ConfigNode.SPACE_BETWEEN_LINES.getPath());
/* 106:117 */     updateNotification = config.getBoolean(ConfigNode.UPDATE_NOTIFICATION.getPath());
/* 107:    */     
/* 108:119 */     imageSymbol = StringConverter.toReadableFormat(config.getString(ConfigNode.IMAGES_SYMBOL.getPath()));
/* 109:120 */     transparencySymbol = StringConverter.toReadableFormat(config.getString(ConfigNode.TRANSPARENCY_SPACE.getPath()));
/* 110:121 */     bungeeRefreshSeconds = config.getInt(ConfigNode.BUNGEE_REFRESH_SECONDS.getPath());
/* 111:122 */     useRedisBungee = config.getBoolean(ConfigNode.BUNGEE_USE_REDIS_BUNGEE.getPath());
/* 112:    */     
/* 113:124 */     pingerEnable = config.getBoolean(ConfigNode.BUNGEE_USE_FULL_PINGER.getPath());
/* 114:125 */     pingerTimeout = config.getInt(ConfigNode.BUNGEE_PINGER_TIMEOUT.getPath());
/* 115:126 */     pingerTrimMotd = config.getBoolean(ConfigNode.BUNGEE_PINGER_TRIM_MOTD.getPath());
/* 116:    */     
/* 117:128 */     pingerOfflineMotd = StringConverter.toReadableFormat(config.getString(ConfigNode.BUNGEE_PINGER_OFFLINE_MOTD.getPath()));
/* 118:129 */     pingerStatusOnline = StringConverter.toReadableFormat(config.getString(ConfigNode.BUNGEE_PINGER_ONLINE_FORMAT.getPath()));
/* 119:130 */     pingerStatusOffline = StringConverter.toReadableFormat(config.getString(ConfigNode.BUNGEE_PINGER_OFFLINE_FORMAT.getPath()));
/* 120:132 */     if (pingerTimeout <= 0) {
/* 121:133 */       pingerTimeout = 100;
/* 122:134 */     } else if (pingerTimeout >= 10000) {
/* 123:135 */       pingerTimeout = 10000;
/* 124:    */     }
/* 125:138 */     pingerServers = Utils.newMap();
/* 126:    */     String ip;
/* 127:140 */     if (pingerEnable) {
/* 128:141 */       for (String singleServer : config.getStringList(ConfigNode.BUNGEE_PINGER_SERVERS.getPath()))
/* 129:    */       {
/* 130:142 */         String[] nameAndAddress = singleServer.split(":", 2);
/* 131:143 */         if (nameAndAddress.length < 2)
/* 132:    */         {
/* 133:144 */           plugin.getLogger().warning("The server info \"" + singleServer + "\" is not valid. There should be a name and an address, separated by a colon.");
/* 134:    */         }
/* 135:    */         else
/* 136:    */         {
/* 137:148 */           name = nameAndAddress[0].trim();
/* 138:149 */           address = nameAndAddress[1].replace(" ", "");
/* 139:    */           int port;
/* 140:154 */           if (address.contains(":"))
/* 141:    */           {
/* 142:155 */             String[] ipAndPort = address.split(":", 2);
/* 143:156 */             String ip = ipAndPort[0];
/* 144:    */             try
/* 145:    */             {
/* 146:158 */               port = Integer.parseInt(ipAndPort[1]);
/* 147:    */             }
/* 148:    */             catch (NumberFormatException e)
/* 149:    */             {
/* 150:    */               int port;
/* 151:160 */               plugin.getLogger().warning("Invalid port number in the server info \"" + singleServer + "\".");
/* 152:161 */               continue;
/* 153:    */             }
/* 154:    */           }
/* 155:    */           else
/* 156:    */           {
/* 157:164 */             ip = address;
/* 158:165 */             port = 25565;
/* 159:    */           }
/* 160:168 */           pingerServers.put(name, new ServerAddress(ip, port));
/* 161:    */         }
/* 162:    */       }
/* 163:    */     }
/* 164:172 */     debug = config.getBoolean(ConfigNode.DEBUG.getPath());
/* 165:    */     
/* 166:174 */     String tempColor = config.getString(ConfigNode.TRANSPARENCY_COLOR.getPath()).replace('&', '§');
/* 167:175 */     boolean foundColor = false;
/* 168:176 */     String address = (ip = ChatColor.values()).length;
/* 169:176 */     for (String name = 0; name < address; name++)
/* 170:    */     {
/* 171:176 */       ChatColor chatColor = ip[name];
/* 172:177 */       if (chatColor.toString().equals(tempColor))
/* 173:    */       {
/* 174:178 */         transparencyColor = chatColor;
/* 175:179 */         foundColor = true;
/* 176:    */       }
/* 177:    */     }
/* 178:182 */     if (!foundColor)
/* 179:    */     {
/* 180:183 */       transparencyColor = ChatColor.GRAY;
/* 181:184 */       plugin.getLogger().warning("You didn't set a valid chat color for transparency in the configuration, light gray (&7) will be used.");
/* 182:    */     }
/* 183:    */     try
/* 184:    */     {
/* 185:188 */       timeFormat = new SimpleDateFormat(config.getString(ConfigNode.TIME_FORMAT.getPath()));
/* 186:189 */       timeFormat.setTimeZone(TimeZone.getTimeZone(config.getString(ConfigNode.TIME_ZONE.getPath())));
/* 187:    */     }
/* 188:    */     catch (IllegalArgumentException ex)
/* 189:    */     {
/* 190:191 */       timeFormat = new SimpleDateFormat("H:mm");
/* 191:192 */       plugin.getLogger().warning("Time format not valid in the configuration, using the default.");
/* 192:    */     }
/* 193:195 */     if (bungeeRefreshSeconds < 1)
/* 194:    */     {
/* 195:196 */       plugin.getLogger().warning("The minimum interval for pinging BungeeCord's servers is 1 second. It has been automatically set.");
/* 196:197 */       bungeeRefreshSeconds = 1;
/* 197:    */     }
/* 198:200 */     if (bungeeRefreshSeconds > 60)
/* 199:    */     {
/* 200:201 */       plugin.getLogger().warning("The maximum interval for pinging BungeeCord's servers is 60 seconds. It has been automatically set.");
/* 201:202 */       bungeeRefreshSeconds = 60;
/* 202:    */     }
/* 203:    */   }
/* 204:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.disk.Configuration
 * JD-Core Version:    0.7.0.1
 */