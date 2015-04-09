/*   1:    */ package com.gmail.filoghost.holographicdisplays.placeholder;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.bridge.bungeecord.BungeeServerTracker;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSNameable;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTextLine;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.task.WorldPlayerCounterTask;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*   9:    */ import java.util.Iterator;
/*  10:    */ import java.util.Map;
/*  11:    */ import java.util.Map.Entry;
/*  12:    */ import java.util.Set;
/*  13:    */ import java.util.regex.Matcher;
/*  14:    */ import java.util.regex.Pattern;
/*  15:    */ import org.bukkit.Bukkit;
/*  16:    */ import org.bukkit.plugin.Plugin;
/*  17:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  18:    */ 
/*  19:    */ public class PlaceholdersManager
/*  20:    */ {
/*  21:    */   private static long elapsedTenthsOfSecond;
/*  22: 23 */   protected static Set<DynamicLineData> linesToUpdate = ;
/*  23: 25 */   private static final Pattern BUNGEE_ONLINE_PATTERN = makePlaceholderWithArgsPattern("online");
/*  24: 26 */   private static final Pattern BUNGEE_MAX_PATTERN = makePlaceholderWithArgsPattern("max_players");
/*  25: 27 */   private static final Pattern BUNGEE_MOTD_PATTERN = makePlaceholderWithArgsPattern("motd");
/*  26: 28 */   private static final Pattern BUNGEE_MOTD_2_PATTERN = makePlaceholderWithArgsPattern("motd2");
/*  27: 29 */   private static final Pattern BUNGEE_STATUS_PATTERN = makePlaceholderWithArgsPattern("status");
/*  28: 30 */   private static final Pattern ANIMATION_PATTERN = makePlaceholderWithArgsPattern("animation");
/*  29: 31 */   private static final Pattern WORLD_PATTERN = makePlaceholderWithArgsPattern("world");
/*  30:    */   
/*  31:    */   private static Pattern makePlaceholderWithArgsPattern(String prefix)
/*  32:    */   {
/*  33: 34 */     return Pattern.compile("(\\{" + Pattern.quote(prefix) + ":)(.+?)(\\})");
/*  34:    */   }
/*  35:    */   
/*  36:    */   private static String extractArgumentFromPlaceholder(Matcher matcher)
/*  37:    */   {
/*  38: 38 */     return matcher.group(2).trim();
/*  39:    */   }
/*  40:    */   
/*  41:    */   public static void load(Plugin plugin)
/*  42:    */   {
/*  43: 44 */     Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
/*  44:    */     {
/*  45:    */       public void run()
/*  46:    */       {
/*  47: 49 */         for (Placeholder placeholder : ) {
/*  48: 50 */           if (PlaceholdersManager.elapsedTenthsOfSecond % placeholder.getTenthsToRefresh() == 0L) {
/*  49: 51 */             placeholder.update();
/*  50:    */           }
/*  51:    */         }
/*  52: 55 */         for (Placeholder placeholder : AnimationsRegister.getAnimations().values()) {
/*  53: 56 */           if (PlaceholdersManager.elapsedTenthsOfSecond % placeholder.getTenthsToRefresh() == 0L) {
/*  54: 57 */             placeholder.update();
/*  55:    */           }
/*  56:    */         }
/*  57: 61 */         Iterator<DynamicLineData> iter = PlaceholdersManager.linesToUpdate.iterator();
/*  58: 64 */         while (iter.hasNext())
/*  59:    */         {
/*  60: 65 */           DynamicLineData currentLineData = (DynamicLineData)iter.next();
/*  61: 67 */           if (currentLineData.getEntity().isDeadNMS()) {
/*  62: 68 */             iter.remove();
/*  63:    */           } else {
/*  64: 70 */             PlaceholdersManager.updatePlaceholders(currentLineData);
/*  65:    */           }
/*  66:    */         }
/*  67: 74 */         PlaceholdersManager.elapsedTenthsOfSecond += 1L;
/*  68:    */       }
/*  69: 77 */     }, 2L, 2L);
/*  70:    */   }
/*  71:    */   
/*  72:    */   public static void untrackAll()
/*  73:    */   {
/*  74: 82 */     linesToUpdate.clear();
/*  75:    */   }
/*  76:    */   
/*  77:    */   public static void untrack(CraftTextLine line)
/*  78:    */   {
/*  79: 87 */     if ((line == null) || (!line.isSpawned())) {
/*  80: 88 */       return;
/*  81:    */     }
/*  82: 91 */     Iterator<DynamicLineData> iter = linesToUpdate.iterator();
/*  83: 92 */     while (iter.hasNext())
/*  84:    */     {
/*  85: 93 */       DynamicLineData data = (DynamicLineData)iter.next();
/*  86: 94 */       if (data.getEntity() == line.getNmsNameble())
/*  87:    */       {
/*  88: 95 */         iter.remove();
/*  89: 96 */         data.getEntity().setCustomNameNMS(data.getOriginalName());
/*  90:    */       }
/*  91:    */     }
/*  92:    */   }
/*  93:    */   
/*  94:    */   public static void trackIfNecessary(CraftTextLine line)
/*  95:    */   {
/*  96:103 */     NMSNameable nameableEntity = line.getNmsNameble();
/*  97:104 */     String name = line.getText();
/*  98:106 */     if (nameableEntity == null) {
/*  99:107 */       return;
/* 100:    */     }
/* 101:110 */     boolean updateName = false;
/* 102:112 */     if ((name == null) || (name.isEmpty())) {
/* 103:113 */       return;
/* 104:    */     }
/* 105:117 */     Set<Placeholder> normalPlaceholders = null;
/* 106:    */     
/* 107:119 */     Map<String, PlaceholderReplacer> bungeeReplacers = null;
/* 108:    */     
/* 109:121 */     Map<String, PlaceholderReplacer> worldsOnlinePlayersReplacers = null;
/* 110:122 */     Map<String, Placeholder> animationsPlaceholders = null;
/* 111:126 */     for (Placeholder placeholder : PlaceholdersRegister.getPlaceholders()) {
/* 112:128 */       if (name.contains(placeholder.getTextPlaceholder()))
/* 113:    */       {
/* 114:130 */         if (normalPlaceholders == null) {
/* 115:131 */           normalPlaceholders = Utils.newSet();
/* 116:    */         }
/* 117:134 */         normalPlaceholders.add(placeholder);
/* 118:    */       }
/* 119:    */     }
/* 120:140 */     Matcher matcher = WORLD_PATTERN.matcher(name);
/* 121:141 */     while (matcher.find())
/* 122:    */     {
/* 123:143 */       if (worldsOnlinePlayersReplacers == null) {
/* 124:144 */         worldsOnlinePlayersReplacers = Utils.newMap();
/* 125:    */       }
/* 126:147 */       String worldName = extractArgumentFromPlaceholder(matcher);
/* 127:148 */       worldsOnlinePlayersReplacers.put(matcher.group(), new PlaceholderReplacer()
/* 128:    */       {
/* 129:    */         public String update()
/* 130:    */         {
/* 131:152 */           return WorldPlayerCounterTask.getCount(PlaceholdersManager.this);
/* 132:    */         }
/* 133:    */       });
/* 134:    */     }
/* 135:158 */     matcher = BUNGEE_ONLINE_PATTERN.matcher(name);
/* 136:159 */     while (matcher.find())
/* 137:    */     {
/* 138:161 */       if (bungeeReplacers == null) {
/* 139:162 */         bungeeReplacers = Utils.newMap();
/* 140:    */       }
/* 141:165 */       String serverName = extractArgumentFromPlaceholder(matcher);
/* 142:166 */       BungeeServerTracker.track(serverName);
/* 143:168 */       if (serverName.contains(","))
/* 144:    */       {
/* 145:170 */         String[] split = serverName.split(",");
/* 146:171 */         for (int i = 0; i < split.length; i++) {
/* 147:172 */           split[i] = split[i].trim();
/* 148:    */         }
/* 149:175 */         String[] serversToTrack = split;
/* 150:    */         
/* 151:    */ 
/* 152:178 */         bungeeReplacers.put(matcher.group(), new PlaceholderReplacer()
/* 153:    */         {
/* 154:    */           public String update()
/* 155:    */           {
/* 156:182 */             int count = 0;
/* 157:183 */             for (String serverToTrack : PlaceholdersManager.this) {
/* 158:184 */               count += BungeeServerTracker.getPlayersOnline(serverToTrack);
/* 159:    */             }
/* 160:186 */             return String.valueOf(count);
/* 161:    */           }
/* 162:    */         });
/* 163:    */       }
/* 164:    */       else
/* 165:    */       {
/* 166:191 */         bungeeReplacers.put(matcher.group(), new PlaceholderReplacer()
/* 167:    */         {
/* 168:    */           public String update()
/* 169:    */           {
/* 170:195 */             return String.valueOf(BungeeServerTracker.getPlayersOnline(PlaceholdersManager.this));
/* 171:    */           }
/* 172:    */         });
/* 173:    */       }
/* 174:    */     }
/* 175:202 */     matcher = BUNGEE_MAX_PATTERN.matcher(name);
/* 176:203 */     while (matcher.find())
/* 177:    */     {
/* 178:205 */       if (bungeeReplacers == null) {
/* 179:206 */         bungeeReplacers = Utils.newMap();
/* 180:    */       }
/* 181:209 */       String serverName = extractArgumentFromPlaceholder(matcher);
/* 182:210 */       BungeeServerTracker.track(serverName);
/* 183:    */       
/* 184:    */ 
/* 185:213 */       bungeeReplacers.put(matcher.group(), new PlaceholderReplacer()
/* 186:    */       {
/* 187:    */         public String update()
/* 188:    */         {
/* 189:217 */           return BungeeServerTracker.getMaxPlayers(PlaceholdersManager.this);
/* 190:    */         }
/* 191:    */       });
/* 192:    */     }
/* 193:223 */     matcher = BUNGEE_MOTD_PATTERN.matcher(name);
/* 194:224 */     while (matcher.find())
/* 195:    */     {
/* 196:226 */       if (bungeeReplacers == null) {
/* 197:227 */         bungeeReplacers = Utils.newMap();
/* 198:    */       }
/* 199:230 */       String serverName = extractArgumentFromPlaceholder(matcher);
/* 200:231 */       BungeeServerTracker.track(serverName);
/* 201:    */       
/* 202:    */ 
/* 203:234 */       bungeeReplacers.put(matcher.group(), new PlaceholderReplacer()
/* 204:    */       {
/* 205:    */         public String update()
/* 206:    */         {
/* 207:238 */           return BungeeServerTracker.getMotd1(PlaceholdersManager.this);
/* 208:    */         }
/* 209:    */       });
/* 210:    */     }
/* 211:244 */     matcher = BUNGEE_MOTD_2_PATTERN.matcher(name);
/* 212:245 */     while (matcher.find())
/* 213:    */     {
/* 214:247 */       if (bungeeReplacers == null) {
/* 215:248 */         bungeeReplacers = Utils.newMap();
/* 216:    */       }
/* 217:251 */       String serverName = extractArgumentFromPlaceholder(matcher);
/* 218:252 */       BungeeServerTracker.track(serverName);
/* 219:    */       
/* 220:    */ 
/* 221:255 */       bungeeReplacers.put(matcher.group(), new PlaceholderReplacer()
/* 222:    */       {
/* 223:    */         public String update()
/* 224:    */         {
/* 225:259 */           return BungeeServerTracker.getMotd2(PlaceholdersManager.this);
/* 226:    */         }
/* 227:    */       });
/* 228:    */     }
/* 229:265 */     matcher = BUNGEE_STATUS_PATTERN.matcher(name);
/* 230:266 */     while (matcher.find())
/* 231:    */     {
/* 232:268 */       if (bungeeReplacers == null) {
/* 233:269 */         bungeeReplacers = Utils.newMap();
/* 234:    */       }
/* 235:272 */       String serverName = extractArgumentFromPlaceholder(matcher);
/* 236:273 */       BungeeServerTracker.track(serverName);
/* 237:    */       
/* 238:    */ 
/* 239:276 */       bungeeReplacers.put(matcher.group(), new PlaceholderReplacer()
/* 240:    */       {
/* 241:    */         public String update()
/* 242:    */         {
/* 243:280 */           return BungeeServerTracker.getOnlineStatus(PlaceholdersManager.this);
/* 244:    */         }
/* 245:    */       });
/* 246:    */     }
/* 247:287 */     matcher = ANIMATION_PATTERN.matcher(name);
/* 248:288 */     while (matcher.find())
/* 249:    */     {
/* 250:290 */       String fileName = extractArgumentFromPlaceholder(matcher);
/* 251:291 */       Placeholder animation = AnimationsRegister.getAnimation(fileName);
/* 252:294 */       if (animation != null)
/* 253:    */       {
/* 254:296 */         if (animationsPlaceholders == null) {
/* 255:297 */           animationsPlaceholders = Utils.newMap();
/* 256:    */         }
/* 257:300 */         animationsPlaceholders.put(matcher.group(), animation);
/* 258:    */       }
/* 259:    */       else
/* 260:    */       {
/* 261:303 */         name = name.replace(matcher.group(), "[Animation not found: " + fileName + "]");
/* 262:304 */         updateName = true;
/* 263:    */       }
/* 264:    */     }
/* 265:308 */     if (Utils.isThereNonNull(new Object[] { normalPlaceholders, bungeeReplacers, worldsOnlinePlayersReplacers, animationsPlaceholders }))
/* 266:    */     {
/* 267:310 */       DynamicLineData lineData = new DynamicLineData(nameableEntity, name);
/* 268:312 */       if (normalPlaceholders != null) {
/* 269:313 */         lineData.setPlaceholders(normalPlaceholders);
/* 270:    */       }
/* 271:316 */       if (bungeeReplacers != null) {
/* 272:317 */         lineData.getReplacers().putAll(bungeeReplacers);
/* 273:    */       }
/* 274:320 */       if (worldsOnlinePlayersReplacers != null) {
/* 275:321 */         lineData.getReplacers().putAll(worldsOnlinePlayersReplacers);
/* 276:    */       }
/* 277:324 */       if (animationsPlaceholders != null) {
/* 278:325 */         lineData.getAnimations().putAll(animationsPlaceholders);
/* 279:    */       }
/* 280:329 */       if (!linesToUpdate.add(lineData))
/* 281:    */       {
/* 282:330 */         linesToUpdate.remove(lineData);
/* 283:331 */         linesToUpdate.add(lineData);
/* 284:    */       }
/* 285:334 */       updatePlaceholders(lineData);
/* 286:    */     }
/* 287:339 */     else if (updateName)
/* 288:    */     {
/* 289:340 */       nameableEntity.setCustomNameNMS(name);
/* 290:    */     }
/* 291:    */   }
/* 292:    */   
/* 293:    */   private static void updatePlaceholders(DynamicLineData lineData)
/* 294:    */   {
/* 295:348 */     String oldCustomName = lineData.getEntity().getCustomNameNMS();
/* 296:349 */     String newCustomName = lineData.getOriginalName();
/* 297:351 */     if (!lineData.getPlaceholders().isEmpty()) {
/* 298:352 */       for (Placeholder placeholder : lineData.getPlaceholders()) {
/* 299:353 */         newCustomName = newCustomName.replace(placeholder.getTextPlaceholder(), Utils.sanitize(placeholder.getCurrentReplacement()));
/* 300:    */       }
/* 301:    */     }
/* 302:357 */     if (!lineData.getReplacers().isEmpty()) {
/* 303:358 */       for (Map.Entry<String, PlaceholderReplacer> entry : lineData.getReplacers().entrySet()) {
/* 304:359 */         newCustomName = newCustomName.replace((CharSequence)entry.getKey(), Utils.sanitize(((PlaceholderReplacer)entry.getValue()).update()));
/* 305:    */       }
/* 306:    */     }
/* 307:363 */     if (!lineData.getAnimations().isEmpty()) {
/* 308:364 */       for (Map.Entry<String, Placeholder> entry : lineData.getAnimations().entrySet()) {
/* 309:365 */         newCustomName = newCustomName.replace((CharSequence)entry.getKey(), Utils.sanitize(((Placeholder)entry.getValue()).getCurrentReplacement()));
/* 310:    */       }
/* 311:    */     }
/* 312:370 */     if (!oldCustomName.equals(newCustomName)) {
/* 313:371 */       lineData.getEntity().setCustomNameNMS(newCustomName);
/* 314:    */     }
/* 315:    */   }
/* 316:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.placeholder.PlaceholdersManager
 * JD-Core Version:    0.7.0.1
 */