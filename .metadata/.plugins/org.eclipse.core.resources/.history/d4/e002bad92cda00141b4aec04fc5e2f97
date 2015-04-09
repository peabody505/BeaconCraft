/*   1:    */ package com.gmail.filoghost.holographicdisplays.object;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holograms.api.TouchHandler;
/*   4:    */ import com.gmail.filoghost.holograms.api.replacements.OldTouchHandlerWrapper;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.api.line.TouchableLine;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTextLine;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.placeholder.PlaceholdersManager;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  12:    */ import com.gmail.filoghost.holographicdisplays.util.Validator;
/*  13:    */ import java.util.List;
/*  14:    */ import org.bukkit.Chunk;
/*  15:    */ import org.bukkit.Location;
/*  16:    */ import org.bukkit.World;
/*  17:    */ import org.bukkit.inventory.ItemStack;
/*  18:    */ 
/*  19:    */ public class CraftHologram
/*  20:    */   implements com.gmail.filoghost.holographicdisplays.api.Hologram, com.gmail.filoghost.holograms.api.Hologram
/*  21:    */ {
/*  22:    */   private World world;
/*  23:    */   private double x;
/*  24:    */   private double y;
/*  25:    */   private double z;
/*  26:    */   private int chunkX;
/*  27:    */   private int chunkZ;
/*  28:    */   private final List<CraftHologramLine> lines;
/*  29:    */   private CraftVisibilityManager visibilityManager;
/*  30:    */   private boolean allowPlaceholders;
/*  31:    */   private long creationTimestamp;
/*  32:    */   private boolean deleted;
/*  33:    */   
/*  34:    */   public CraftHologram(Location location)
/*  35:    */   {
/*  36: 43 */     Validator.notNull(location, "location");
/*  37: 44 */     updateLocation(location.getWorld(), location.getX(), location.getY(), location.getZ());
/*  38:    */     
/*  39: 46 */     this.lines = Utils.newList();
/*  40: 47 */     this.allowPlaceholders = false;
/*  41: 48 */     this.creationTimestamp = System.currentTimeMillis();
/*  42: 49 */     this.visibilityManager = new CraftVisibilityManager(this);
/*  43:    */   }
/*  44:    */   
/*  45:    */   public boolean isInChunk(Chunk chunk)
/*  46:    */   {
/*  47: 53 */     return (chunk.getX() == this.chunkX) && (chunk.getZ() == this.chunkZ);
/*  48:    */   }
/*  49:    */   
/*  50:    */   public World getWorld()
/*  51:    */   {
/*  52: 58 */     return this.world;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public double getX()
/*  56:    */   {
/*  57: 63 */     return this.x;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public double getY()
/*  61:    */   {
/*  62: 68 */     return this.y;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public double getZ()
/*  66:    */   {
/*  67: 73 */     return this.z;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public Location getLocation()
/*  71:    */   {
/*  72: 78 */     return new Location(this.world, this.x, this.y, this.z);
/*  73:    */   }
/*  74:    */   
/*  75:    */   private void updateLocation(World world, double x, double y, double z)
/*  76:    */   {
/*  77: 82 */     Validator.notNull(world, "world");
/*  78:    */     
/*  79: 84 */     this.world = world;
/*  80: 85 */     this.x = x;
/*  81: 86 */     this.y = y;
/*  82: 87 */     this.z = z;
/*  83: 88 */     this.chunkX = (Utils.floor(x) >> 4);
/*  84: 89 */     this.chunkZ = (Utils.floor(z) >> 4);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public boolean isDeleted()
/*  88:    */   {
/*  89: 94 */     return this.deleted;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void delete()
/*  93:    */   {
/*  94: 99 */     if (!this.deleted)
/*  95:    */     {
/*  96:100 */       this.deleted = true;
/*  97:101 */       clearLines();
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   public List<CraftHologramLine> getLinesUnsafe()
/* 102:    */   {
/* 103:106 */     return this.lines;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public CraftHologramLine getLine(int index)
/* 107:    */   {
/* 108:111 */     return (CraftHologramLine)this.lines.get(index);
/* 109:    */   }
/* 110:    */   
/* 111:    */   public CraftTextLine appendTextLine(String text)
/* 112:    */   {
/* 113:116 */     Validator.isTrue(!this.deleted, "hologram already deleted");
/* 114:    */     
/* 115:118 */     CraftTextLine line = new CraftTextLine(this, text);
/* 116:119 */     this.lines.add(line);
/* 117:120 */     refreshSingleLines();
/* 118:121 */     return line;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public CraftItemLine appendItemLine(ItemStack itemStack)
/* 122:    */   {
/* 123:126 */     Validator.isTrue(!this.deleted, "hologram already deleted");
/* 124:127 */     Validator.notNull(itemStack, "itemStack");
/* 125:    */     
/* 126:129 */     CraftItemLine line = new CraftItemLine(this, itemStack);
/* 127:130 */     this.lines.add(line);
/* 128:131 */     refreshSingleLines();
/* 129:132 */     return line;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public CraftTextLine insertTextLine(int index, String text)
/* 133:    */   {
/* 134:137 */     Validator.isTrue(!this.deleted, "hologram already deleted");
/* 135:    */     
/* 136:139 */     CraftTextLine line = new CraftTextLine(this, text);
/* 137:140 */     this.lines.add(index, line);
/* 138:141 */     refreshSingleLines();
/* 139:142 */     return line;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public CraftItemLine insertItemLine(int index, ItemStack itemStack)
/* 143:    */   {
/* 144:147 */     Validator.isTrue(!this.deleted, "hologram already deleted");
/* 145:148 */     Validator.notNull(itemStack, "itemStack");
/* 146:    */     
/* 147:150 */     CraftItemLine line = new CraftItemLine(this, itemStack);
/* 148:151 */     this.lines.add(index, line);
/* 149:152 */     refreshSingleLines();
/* 150:153 */     return line;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void removeLine(int index)
/* 154:    */   {
/* 155:158 */     Validator.isTrue(!this.deleted, "hologram already deleted");
/* 156:    */     
/* 157:160 */     ((CraftHologramLine)this.lines.remove(index)).despawn();
/* 158:161 */     refreshSingleLines();
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void removeLine(CraftHologramLine line)
/* 162:    */   {
/* 163:165 */     Validator.isTrue(!this.deleted, "hologram already deleted");
/* 164:    */     
/* 165:167 */     this.lines.remove(line);
/* 166:168 */     line.despawn();
/* 167:169 */     refreshSingleLines();
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void clearLines()
/* 171:    */   {
/* 172:174 */     for (CraftHologramLine line : this.lines) {
/* 173:175 */       line.despawn();
/* 174:    */     }
/* 175:178 */     this.lines.clear();
/* 176:    */   }
/* 177:    */   
/* 178:    */   public int size()
/* 179:    */   {
/* 180:183 */     return this.lines.size();
/* 181:    */   }
/* 182:    */   
/* 183:    */   public double getHeight()
/* 184:    */   {
/* 185:188 */     if (this.lines.isEmpty()) {
/* 186:189 */       return 0.0D;
/* 187:    */     }
/* 188:192 */     double height = 0.0D;
/* 189:194 */     for (CraftHologramLine line : this.lines) {
/* 190:195 */       height += line.getHeight();
/* 191:    */     }
/* 192:198 */     height += Configuration.spaceBetweenLines * (this.lines.size() - 1);
/* 193:199 */     return height;
/* 194:    */   }
/* 195:    */   
/* 196:    */   public CraftVisibilityManager getVisibilityManager()
/* 197:    */   {
/* 198:204 */     return this.visibilityManager;
/* 199:    */   }
/* 200:    */   
/* 201:    */   public long getCreationTimestamp()
/* 202:    */   {
/* 203:210 */     return this.creationTimestamp;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public boolean isAllowPlaceholders()
/* 207:    */   {
/* 208:215 */     return this.allowPlaceholders;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public void setAllowPlaceholders(boolean allowPlaceholders)
/* 212:    */   {
/* 213:220 */     if (this.allowPlaceholders != allowPlaceholders)
/* 214:    */     {
/* 215:222 */       if (allowPlaceholders) {
/* 216:224 */         for (CraftHologramLine line : this.lines) {
/* 217:225 */           if ((line instanceof CraftTextLine)) {
/* 218:226 */             PlaceholdersManager.trackIfNecessary((CraftTextLine)line);
/* 219:    */           }
/* 220:    */         }
/* 221:    */       } else {
/* 222:233 */         for (CraftHologramLine line : this.lines) {
/* 223:234 */           if ((line instanceof CraftTextLine)) {
/* 224:235 */             PlaceholdersManager.untrack((CraftTextLine)line);
/* 225:    */           }
/* 226:    */         }
/* 227:    */       }
/* 228:240 */       this.allowPlaceholders = allowPlaceholders;
/* 229:    */     }
/* 230:    */   }
/* 231:    */   
/* 232:    */   public void refreshAll()
/* 233:    */   {
/* 234:246 */     if (this.world.isChunkLoaded(this.chunkX, this.chunkZ)) {
/* 235:247 */       spawnEntities();
/* 236:    */     }
/* 237:    */   }
/* 238:    */   
/* 239:    */   public void refreshSingleLines()
/* 240:    */   {
/* 241:252 */     if (this.world.isChunkLoaded(this.chunkX, this.chunkZ))
/* 242:    */     {
/* 243:254 */       double currentY = this.y;
/* 244:255 */       boolean first = true;
/* 245:257 */       for (CraftHologramLine line : this.lines)
/* 246:    */       {
/* 247:259 */         currentY -= line.getHeight();
/* 248:261 */         if (first) {
/* 249:262 */           first = false;
/* 250:    */         } else {
/* 251:264 */           currentY -= Configuration.spaceBetweenLines;
/* 252:    */         }
/* 253:267 */         if (line.isSpawned())
/* 254:    */         {
/* 255:268 */           line.teleport(this.x, currentY, this.z);
/* 256:    */         }
/* 257:    */         else
/* 258:    */         {
/* 259:270 */           line.spawn(this.world, this.x, currentY, this.z);
/* 260:271 */           if ((this.allowPlaceholders) && ((line instanceof CraftTextLine))) {
/* 261:272 */             PlaceholdersManager.trackIfNecessary((CraftTextLine)line);
/* 262:    */           }
/* 263:    */         }
/* 264:    */       }
/* 265:    */     }
/* 266:    */   }
/* 267:    */   
/* 268:    */   public void spawnEntities()
/* 269:    */   {
/* 270:283 */     Validator.isTrue(!this.deleted, "hologram already deleted");
/* 271:    */     
/* 272:285 */     despawnEntities();
/* 273:    */     
/* 274:287 */     double currentY = this.y;
/* 275:288 */     boolean first = true;
/* 276:290 */     for (CraftHologramLine line : this.lines)
/* 277:    */     {
/* 278:292 */       currentY -= line.getHeight();
/* 279:294 */       if (first) {
/* 280:295 */         first = false;
/* 281:    */       } else {
/* 282:297 */         currentY -= Configuration.spaceBetweenLines;
/* 283:    */       }
/* 284:300 */       line.spawn(this.world, this.x, currentY, this.z);
/* 285:301 */       if ((this.allowPlaceholders) && ((line instanceof CraftTextLine))) {
/* 286:302 */         PlaceholdersManager.trackIfNecessary((CraftTextLine)line);
/* 287:    */       }
/* 288:    */     }
/* 289:    */   }
/* 290:    */   
/* 291:    */   public void despawnEntities()
/* 292:    */   {
/* 293:311 */     for (CraftHologramLine piece : this.lines) {
/* 294:312 */       piece.despawn();
/* 295:    */     }
/* 296:    */   }
/* 297:    */   
/* 298:    */   public void teleport(Location location)
/* 299:    */   {
/* 300:318 */     Validator.notNull(location, "location");
/* 301:319 */     teleport(location.getWorld(), location.getX(), location.getY(), location.getZ());
/* 302:    */   }
/* 303:    */   
/* 304:    */   public void teleport(World world, double x, double y, double z)
/* 305:    */   {
/* 306:324 */     Validator.isTrue(!this.deleted, "hologram already deleted");
/* 307:325 */     Validator.notNull(world, "world");
/* 308:327 */     if (this.world != world)
/* 309:    */     {
/* 310:328 */       updateLocation(world, x, y, z);
/* 311:329 */       despawnEntities();
/* 312:330 */       refreshAll();
/* 313:331 */       return;
/* 314:    */     }
/* 315:334 */     updateLocation(world, x, y, z);
/* 316:    */     
/* 317:336 */     double currentY = y;
/* 318:337 */     boolean first = true;
/* 319:339 */     for (CraftHologramLine line : this.lines) {
/* 320:341 */       if (line.isSpawned())
/* 321:    */       {
/* 322:345 */         currentY -= line.getHeight();
/* 323:347 */         if (first) {
/* 324:348 */           first = false;
/* 325:    */         } else {
/* 326:350 */           currentY -= Configuration.spaceBetweenLines;
/* 327:    */         }
/* 328:353 */         line.teleport(x, currentY, z);
/* 329:    */       }
/* 330:    */     }
/* 331:    */   }
/* 332:    */   
/* 333:    */   public String toString()
/* 334:    */   {
/* 335:359 */     return "CraftHologram [world=" + this.world + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", lines=" + this.lines + ", deleted=" + this.deleted + "]";
/* 336:    */   }
/* 337:    */   
/* 338:    */   @Deprecated
/* 339:    */   public boolean update()
/* 340:    */   {
/* 341:369 */     return true;
/* 342:    */   }
/* 343:    */   
/* 344:    */   @Deprecated
/* 345:    */   public void hide() {}
/* 346:    */   
/* 347:    */   @Deprecated
/* 348:    */   public void addLine(String text)
/* 349:    */   {
/* 350:381 */     appendTextLine(text);
/* 351:    */   }
/* 352:    */   
/* 353:    */   @Deprecated
/* 354:    */   public void setLine(int index, String text)
/* 355:    */   {
/* 356:387 */     ((CraftHologramLine)this.lines.get(index)).despawn();
/* 357:388 */     this.lines.set(index, new CraftTextLine(this, text));
/* 358:    */   }
/* 359:    */   
/* 360:    */   @Deprecated
/* 361:    */   public void insertLine(int index, String text)
/* 362:    */   {
/* 363:394 */     insertLine(index, text);
/* 364:    */   }
/* 365:    */   
/* 366:    */   @Deprecated
/* 367:    */   public String[] getLines()
/* 368:    */   {
/* 369:400 */     return null;
/* 370:    */   }
/* 371:    */   
/* 372:    */   @Deprecated
/* 373:    */   public int getLinesLength()
/* 374:    */   {
/* 375:406 */     return size();
/* 376:    */   }
/* 377:    */   
/* 378:    */   @Deprecated
/* 379:    */   public void setLocation(Location location)
/* 380:    */   {
/* 381:412 */     teleport(location);
/* 382:    */   }
/* 383:    */   
/* 384:    */   @Deprecated
/* 385:    */   public void setTouchHandler(TouchHandler handler)
/* 386:    */   {
/* 387:418 */     if (size() > 0)
/* 388:    */     {
/* 389:419 */       TouchableLine line0 = (TouchableLine)getLine(0);
/* 390:421 */       if (handler != null) {
/* 391:422 */         line0.setTouchHandler(new OldTouchHandlerWrapper(this, handler));
/* 392:    */       } else {
/* 393:424 */         line0.setTouchHandler(null);
/* 394:    */       }
/* 395:    */     }
/* 396:    */   }
/* 397:    */   
/* 398:    */   @Deprecated
/* 399:    */   public TouchHandler getTouchHandler()
/* 400:    */   {
/* 401:432 */     return null;
/* 402:    */   }
/* 403:    */   
/* 404:    */   @Deprecated
/* 405:    */   public boolean hasTouchHandler()
/* 406:    */   {
/* 407:438 */     return false;
/* 408:    */   }
/* 409:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.CraftHologram
 * JD-Core Version:    0.7.0.1
 */