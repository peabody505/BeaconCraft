/*   1:    */ package com.gmail.filoghost.holographicdisplays.metrics;
/*   2:    */ 
/*   3:    */ import java.io.BufferedReader;
/*   4:    */ import java.io.ByteArrayOutputStream;
/*   5:    */ import java.io.File;
/*   6:    */ import java.io.IOException;
/*   7:    */ import java.io.InputStreamReader;
/*   8:    */ import java.io.OutputStream;
/*   9:    */ import java.io.PrintStream;
/*  10:    */ import java.io.UnsupportedEncodingException;
/*  11:    */ import java.net.Proxy;
/*  12:    */ import java.net.URL;
/*  13:    */ import java.net.URLConnection;
/*  14:    */ import java.net.URLEncoder;
/*  15:    */ import java.util.UUID;
/*  16:    */ import java.util.logging.Level;
/*  17:    */ import java.util.logging.Logger;
/*  18:    */ import java.util.zip.GZIPOutputStream;
/*  19:    */ import org.bukkit.Bukkit;
/*  20:    */ import org.bukkit.Server;
/*  21:    */ import org.bukkit.configuration.InvalidConfigurationException;
/*  22:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  23:    */ import org.bukkit.configuration.file.YamlConfigurationOptions;
/*  24:    */ import org.bukkit.plugin.Plugin;
/*  25:    */ import org.bukkit.plugin.PluginDescriptionFile;
/*  26:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  27:    */ import org.bukkit.scheduler.BukkitTask;
/*  28:    */ 
/*  29:    */ public class MetricsLite
/*  30:    */ {
/*  31:    */   private static final int REVISION = 7;
/*  32:    */   private static final String BASE_URL = "http://report.mcstats.org";
/*  33:    */   private static final String REPORT_URL = "/plugin/%s";
/*  34:    */   private static final int PING_INTERVAL = 15;
/*  35:    */   private final Plugin plugin;
/*  36:    */   private final YamlConfiguration configuration;
/*  37:    */   private final File configurationFile;
/*  38:    */   private final String guid;
/*  39:    */   private final boolean debug;
/*  40:103 */   private final Object optOutLock = new Object();
/*  41:108 */   private volatile BukkitTask task = null;
/*  42:    */   
/*  43:    */   public MetricsLite(Plugin plugin)
/*  44:    */     throws IOException
/*  45:    */   {
/*  46:111 */     if (plugin == null) {
/*  47:112 */       throw new IllegalArgumentException("Plugin cannot be null");
/*  48:    */     }
/*  49:115 */     this.plugin = plugin;
/*  50:    */     
/*  51:    */ 
/*  52:118 */     this.configurationFile = getConfigFile();
/*  53:119 */     this.configuration = YamlConfiguration.loadConfiguration(this.configurationFile);
/*  54:    */     
/*  55:    */ 
/*  56:122 */     this.configuration.addDefault("opt-out", Boolean.valueOf(false));
/*  57:123 */     this.configuration.addDefault("guid", UUID.randomUUID().toString());
/*  58:124 */     this.configuration.addDefault("debug", Boolean.valueOf(false));
/*  59:127 */     if (this.configuration.get("guid", null) == null)
/*  60:    */     {
/*  61:128 */       this.configuration.options().header("http://mcstats.org").copyDefaults(true);
/*  62:129 */       this.configuration.save(this.configurationFile);
/*  63:    */     }
/*  64:133 */     this.guid = this.configuration.getString("guid");
/*  65:134 */     this.debug = this.configuration.getBoolean("debug", false);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean start()
/*  69:    */   {
/*  70:145 */     synchronized (this.optOutLock)
/*  71:    */     {
/*  72:147 */       if (isOptOut()) {
/*  73:148 */         return false;
/*  74:    */       }
/*  75:152 */       if (this.task != null) {
/*  76:153 */         return true;
/*  77:    */       }
/*  78:157 */       this.task = this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, new Runnable()
/*  79:    */       {
/*  80:159 */         private boolean firstPost = true;
/*  81:    */         
/*  82:    */         public void run()
/*  83:    */         {
/*  84:    */           try
/*  85:    */           {
/*  86:164 */             synchronized (MetricsLite.this.optOutLock)
/*  87:    */             {
/*  88:166 */               if ((MetricsLite.this.isOptOut()) && (MetricsLite.this.task != null))
/*  89:    */               {
/*  90:167 */                 MetricsLite.this.task.cancel();
/*  91:168 */                 MetricsLite.this.task = null;
/*  92:    */               }
/*  93:    */             }
/*  94:175 */             MetricsLite.this.postPlugin(!this.firstPost);
/*  95:    */             
/*  96:    */ 
/*  97:    */ 
/*  98:179 */             this.firstPost = false;
/*  99:    */           }
/* 100:    */           catch (IOException e)
/* 101:    */           {
/* 102:181 */             if (MetricsLite.this.debug) {
/* 103:182 */               Bukkit.getLogger().log(Level.INFO, "[Metrics] " + e.getMessage());
/* 104:    */             }
/* 105:    */           }
/* 106:    */         }
/* 107:186 */       }, 0L, 18000L);
/* 108:    */       
/* 109:188 */       return true;
/* 110:    */     }
/* 111:    */   }
/* 112:    */   
/* 113:    */   public boolean isOptOut()
/* 114:    */   {
/* 115:198 */     synchronized (this.optOutLock)
/* 116:    */     {
/* 117:    */       try
/* 118:    */       {
/* 119:201 */         this.configuration.load(getConfigFile());
/* 120:    */       }
/* 121:    */       catch (IOException ex)
/* 122:    */       {
/* 123:203 */         if (this.debug) {
/* 124:204 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/* 125:    */         }
/* 126:206 */         return true;
/* 127:    */       }
/* 128:    */       catch (InvalidConfigurationException ex)
/* 129:    */       {
/* 130:208 */         if (this.debug) {
/* 131:209 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/* 132:    */         }
/* 133:211 */         return true;
/* 134:    */       }
/* 135:213 */       return this.configuration.getBoolean("opt-out", false);
/* 136:    */     }
/* 137:    */   }
/* 138:    */   
/* 139:    */   public void enable()
/* 140:    */     throws IOException
/* 141:    */   {
/* 142:224 */     synchronized (this.optOutLock)
/* 143:    */     {
/* 144:226 */       if (isOptOut())
/* 145:    */       {
/* 146:227 */         this.configuration.set("opt-out", Boolean.valueOf(false));
/* 147:228 */         this.configuration.save(this.configurationFile);
/* 148:    */       }
/* 149:232 */       if (this.task == null) {
/* 150:233 */         start();
/* 151:    */       }
/* 152:    */     }
/* 153:    */   }
/* 154:    */   
/* 155:    */   public void disable()
/* 156:    */     throws IOException
/* 157:    */   {
/* 158:245 */     synchronized (this.optOutLock)
/* 159:    */     {
/* 160:247 */       if (!isOptOut())
/* 161:    */       {
/* 162:248 */         this.configuration.set("opt-out", Boolean.valueOf(true));
/* 163:249 */         this.configuration.save(this.configurationFile);
/* 164:    */       }
/* 165:253 */       if (this.task != null)
/* 166:    */       {
/* 167:254 */         this.task.cancel();
/* 168:255 */         this.task = null;
/* 169:    */       }
/* 170:    */     }
/* 171:    */   }
/* 172:    */   
/* 173:    */   public File getConfigFile()
/* 174:    */   {
/* 175:271 */     File pluginsFolder = this.plugin.getDataFolder().getParentFile();
/* 176:    */     
/* 177:    */ 
/* 178:274 */     return new File(new File(pluginsFolder, "PluginMetrics"), "config.yml");
/* 179:    */   }
/* 180:    */   
/* 181:    */   private void postPlugin(boolean isPing)
/* 182:    */     throws IOException
/* 183:    */   {
/* 184:283 */     PluginDescriptionFile description = this.plugin.getDescription();
/* 185:284 */     String pluginName = description.getName();
/* 186:285 */     boolean onlineMode = Bukkit.getServer().getOnlineMode();
/* 187:286 */     String pluginVersion = description.getVersion();
/* 188:287 */     String serverVersion = Bukkit.getVersion();
/* 189:288 */     int playersOnline = Bukkit.getServer().getOnlinePlayers().length;
/* 190:    */     
/* 191:    */ 
/* 192:    */ 
/* 193:    */ 
/* 194:293 */     StringBuilder json = new StringBuilder(1024);
/* 195:294 */     json.append('{');
/* 196:    */     
/* 197:    */ 
/* 198:297 */     appendJSONPair(json, "guid", this.guid);
/* 199:298 */     appendJSONPair(json, "plugin_version", pluginVersion);
/* 200:299 */     appendJSONPair(json, "server_version", serverVersion);
/* 201:300 */     appendJSONPair(json, "players_online", Integer.toString(playersOnline));
/* 202:    */     
/* 203:    */ 
/* 204:303 */     String osname = System.getProperty("os.name");
/* 205:304 */     String osarch = System.getProperty("os.arch");
/* 206:305 */     String osversion = System.getProperty("os.version");
/* 207:306 */     String java_version = System.getProperty("java.version");
/* 208:307 */     int coreCount = Runtime.getRuntime().availableProcessors();
/* 209:310 */     if (osarch.equals("amd64")) {
/* 210:311 */       osarch = "x86_64";
/* 211:    */     }
/* 212:314 */     appendJSONPair(json, "osname", osname);
/* 213:315 */     appendJSONPair(json, "osarch", osarch);
/* 214:316 */     appendJSONPair(json, "osversion", osversion);
/* 215:317 */     appendJSONPair(json, "cores", Integer.toString(coreCount));
/* 216:318 */     appendJSONPair(json, "auth_mode", onlineMode ? "1" : "0");
/* 217:319 */     appendJSONPair(json, "java_version", java_version);
/* 218:322 */     if (isPing) {
/* 219:323 */       appendJSONPair(json, "ping", "1");
/* 220:    */     }
/* 221:327 */     json.append('}');
/* 222:    */     
/* 223:    */ 
/* 224:330 */     URL url = new URL("http://report.mcstats.org" + String.format("/plugin/%s", new Object[] { urlEncode(pluginName) }));
/* 225:    */     URLConnection connection;
/* 226:    */     URLConnection connection;
/* 227:337 */     if (isMineshafterPresent()) {
/* 228:338 */       connection = url.openConnection(Proxy.NO_PROXY);
/* 229:    */     } else {
/* 230:340 */       connection = url.openConnection();
/* 231:    */     }
/* 232:344 */     byte[] uncompressed = json.toString().getBytes();
/* 233:345 */     byte[] compressed = gzip(json.toString());
/* 234:    */     
/* 235:    */ 
/* 236:348 */     connection.addRequestProperty("User-Agent", "MCStats/7");
/* 237:349 */     connection.addRequestProperty("Content-Type", "application/json");
/* 238:350 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 239:351 */     connection.addRequestProperty("Content-Length", Integer.toString(compressed.length));
/* 240:352 */     connection.addRequestProperty("Accept", "application/json");
/* 241:353 */     connection.addRequestProperty("Connection", "close");
/* 242:    */     
/* 243:355 */     connection.setDoOutput(true);
/* 244:357 */     if (this.debug) {
/* 245:358 */       System.out.println("[Metrics] Prepared request for " + pluginName + " uncompressed=" + uncompressed.length + " compressed=" + compressed.length);
/* 246:    */     }
/* 247:362 */     OutputStream os = connection.getOutputStream();
/* 248:363 */     os.write(compressed);
/* 249:364 */     os.flush();
/* 250:    */     
/* 251:    */ 
/* 252:367 */     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/* 253:368 */     String response = reader.readLine();
/* 254:    */     
/* 255:    */ 
/* 256:371 */     os.close();
/* 257:372 */     reader.close();
/* 258:374 */     if ((response == null) || (response.startsWith("ERR")) || (response.startsWith("7")))
/* 259:    */     {
/* 260:375 */       if (response == null) {
/* 261:376 */         response = "null";
/* 262:377 */       } else if (response.startsWith("7")) {
/* 263:378 */         response = response.substring(response.startsWith("7,") ? 2 : 1);
/* 264:    */       }
/* 265:381 */       throw new IOException(response);
/* 266:    */     }
/* 267:    */   }
/* 268:    */   
/* 269:    */   public static byte[] gzip(String input)
/* 270:    */   {
/* 271:392 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 272:393 */     GZIPOutputStream gzos = null;
/* 273:    */     try
/* 274:    */     {
/* 275:396 */       gzos = new GZIPOutputStream(baos);
/* 276:397 */       gzos.write(input.getBytes("UTF-8"));
/* 277:    */     }
/* 278:    */     catch (IOException e)
/* 279:    */     {
/* 280:399 */       e.printStackTrace();
/* 281:401 */       if (gzos != null) {
/* 282:    */         try
/* 283:    */         {
/* 284:402 */           gzos.close();
/* 285:    */         }
/* 286:    */         catch (IOException localIOException1) {}
/* 287:    */       }
/* 288:    */     }
/* 289:    */     finally
/* 290:    */     {
/* 291:401 */       if (gzos != null) {
/* 292:    */         try
/* 293:    */         {
/* 294:402 */           gzos.close();
/* 295:    */         }
/* 296:    */         catch (IOException localIOException2) {}
/* 297:    */       }
/* 298:    */     }
/* 299:407 */     return baos.toByteArray();
/* 300:    */   }
/* 301:    */   
/* 302:    */   private boolean isMineshafterPresent()
/* 303:    */   {
/* 304:    */     try
/* 305:    */     {
/* 306:417 */       Class.forName("mineshafter.MineServer");
/* 307:418 */       return true;
/* 308:    */     }
/* 309:    */     catch (Exception e) {}
/* 310:420 */     return false;
/* 311:    */   }
/* 312:    */   
/* 313:    */   private static void appendJSONPair(StringBuilder json, String key, String value)
/* 314:    */     throws UnsupportedEncodingException
/* 315:    */   {
/* 316:433 */     boolean isValueNumeric = false;
/* 317:    */     try
/* 318:    */     {
/* 319:436 */       if ((value.equals("0")) || (!value.endsWith("0")))
/* 320:    */       {
/* 321:437 */         Double.parseDouble(value);
/* 322:438 */         isValueNumeric = true;
/* 323:    */       }
/* 324:    */     }
/* 325:    */     catch (NumberFormatException e)
/* 326:    */     {
/* 327:441 */       isValueNumeric = false;
/* 328:    */     }
/* 329:444 */     if (json.charAt(json.length() - 1) != '{') {
/* 330:445 */       json.append(',');
/* 331:    */     }
/* 332:448 */     json.append(escapeJSON(key));
/* 333:449 */     json.append(':');
/* 334:451 */     if (isValueNumeric) {
/* 335:452 */       json.append(value);
/* 336:    */     } else {
/* 337:454 */       json.append(escapeJSON(value));
/* 338:    */     }
/* 339:    */   }
/* 340:    */   
/* 341:    */   private static String escapeJSON(String text)
/* 342:    */   {
/* 343:465 */     StringBuilder builder = new StringBuilder();
/* 344:    */     
/* 345:467 */     builder.append('"');
/* 346:468 */     for (int index = 0; index < text.length(); index++)
/* 347:    */     {
/* 348:469 */       char chr = text.charAt(index);
/* 349:471 */       switch (chr)
/* 350:    */       {
/* 351:    */       case '"': 
/* 352:    */       case '\\': 
/* 353:474 */         builder.append('\\');
/* 354:475 */         builder.append(chr);
/* 355:476 */         break;
/* 356:    */       case '\b': 
/* 357:478 */         builder.append("\\b");
/* 358:479 */         break;
/* 359:    */       case '\t': 
/* 360:481 */         builder.append("\\t");
/* 361:482 */         break;
/* 362:    */       case '\n': 
/* 363:484 */         builder.append("\\n");
/* 364:485 */         break;
/* 365:    */       case '\r': 
/* 366:487 */         builder.append("\\r");
/* 367:488 */         break;
/* 368:    */       default: 
/* 369:490 */         if (chr < ' ')
/* 370:    */         {
/* 371:491 */           String t = "000" + Integer.toHexString(chr);
/* 372:492 */           builder.append("\\u" + t.substring(t.length() - 4));
/* 373:    */         }
/* 374:    */         else
/* 375:    */         {
/* 376:494 */           builder.append(chr);
/* 377:    */         }
/* 378:    */         break;
/* 379:    */       }
/* 380:    */     }
/* 381:499 */     builder.append('"');
/* 382:    */     
/* 383:501 */     return builder.toString();
/* 384:    */   }
/* 385:    */   
/* 386:    */   private static String urlEncode(String text)
/* 387:    */     throws UnsupportedEncodingException
/* 388:    */   {
/* 389:511 */     return URLEncoder.encode(text, "UTF-8");
/* 390:    */   }
/* 391:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.metrics.MetricsLite
 * JD-Core Version:    0.7.0.1
 */