/*   1:    */ package com.gmail.filoghost.holographicdisplays.bridge.protocollib;
/*   2:    */ 
/*   3:    */ import com.comphenix.protocol.PacketType;
/*   4:    */ import com.comphenix.protocol.PacketType.Play.Server;
/*   5:    */ import com.comphenix.protocol.ProtocolLibrary;
/*   6:    */ import com.comphenix.protocol.ProtocolManager;
/*   7:    */ import com.comphenix.protocol.events.ListenerPriority;
/*   8:    */ import com.comphenix.protocol.events.PacketAdapter;
/*   9:    */ import com.comphenix.protocol.events.PacketContainer;
/*  10:    */ import com.comphenix.protocol.events.PacketEvent;
/*  11:    */ import com.comphenix.protocol.wrappers.WrappedDataWatcher;
/*  12:    */ import com.comphenix.protocol.wrappers.WrappedWatchableObject;
/*  13:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*  14:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager;
/*  15:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*  16:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSItem;
/*  17:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSNameable;
/*  18:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSSlime;
/*  19:    */ import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
/*  20:    */ import com.gmail.filoghost.holographicdisplays.object.CraftVisibilityManager;
/*  21:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*  22:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine;
/*  23:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTextLine;
/*  24:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchSlimeLine;
/*  25:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchableLine;
/*  26:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  27:    */ import com.gmail.filoghost.holographicdisplays.util.VersionUtils;
/*  28:    */ import java.util.List;
/*  29:    */ import java.util.logging.Logger;
/*  30:    */ import org.bukkit.Bukkit;
/*  31:    */ import org.bukkit.entity.Entity;
/*  32:    */ import org.bukkit.entity.EntityType;
/*  33:    */ import org.bukkit.entity.Player;
/*  34:    */ import org.bukkit.plugin.Plugin;
/*  35:    */ import org.bukkit.plugin.PluginManager;
/*  36:    */ 
/*  37:    */ public class ProtocolLibHook
/*  38:    */ {
/*  39:    */   private static boolean hasProtocolLib;
/*  40:    */   private static NMSManager nmsManager;
/*  41:    */   private static int customNameWatcherIndex;
/*  42:    */   
/*  43:    */   public static boolean load(NMSManager nmsManager, Plugin plugin, boolean is1_8)
/*  44:    */   {
/*  45: 40 */     nmsManager = nmsManager;
/*  46: 42 */     if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib"))
/*  47:    */     {
/*  48: 44 */       hasProtocolLib = true;
/*  49:    */       
/*  50: 46 */       plugin.getLogger().info("Found ProtocolLib, adding support for player relative variables.");
/*  51: 47 */       if (is1_8) {
/*  52: 48 */         customNameWatcherIndex = 2;
/*  53:    */       } else {
/*  54: 50 */         customNameWatcherIndex = 10;
/*  55:    */       }
/*  56: 54 */       ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL, new PacketType[] { PacketType.Play.Server.SPAWN_ENTITY_LIVING, PacketType.Play.Server.SPAWN_ENTITY, PacketType.Play.Server.ENTITY_METADATA })
/*  57:    */       {
/*  58:    */         public void onPacketSending(PacketEvent event)
/*  59:    */         {
/*  60: 59 */           PacketContainer packet = event.getPacket();
/*  61: 62 */           if (packet.getType() == PacketType.Play.Server.SPAWN_ENTITY_LIVING)
/*  62:    */           {
/*  63: 64 */             WrapperPlayServerSpawnEntityLiving spawnEntityPacket = new WrapperPlayServerSpawnEntityLiving(packet);
/*  64: 65 */             Entity entity = spawnEntityPacket.getEntity(event);
/*  65: 67 */             if ((entity == null) || (!ProtocolLibHook.isHologramType(entity.getType()))) {
/*  66: 68 */               return;
/*  67:    */             }
/*  68: 71 */             CraftHologram hologram = ProtocolLibHook.getHologram(entity);
/*  69: 72 */             if (hologram == null) {
/*  70: 73 */               return;
/*  71:    */             }
/*  72: 76 */             Player player = event.getPlayer();
/*  73: 77 */             if (!hologram.getVisibilityManager().isVisibleTo(player))
/*  74:    */             {
/*  75: 78 */               event.setCancelled(true);
/*  76: 79 */               return;
/*  77:    */             }
/*  78: 82 */             WrappedDataWatcher dataWatcher = spawnEntityPacket.getMetadata();
/*  79: 83 */             String customName = dataWatcher.getString(ProtocolLibHook.customNameWatcherIndex);
/*  80: 85 */             if (customName == null) {
/*  81: 86 */               return;
/*  82:    */             }
/*  83: 89 */             if ((customName.contains("{player}")) || (customName.contains("{displayname}")))
/*  84:    */             {
/*  85: 91 */               WrappedDataWatcher dataWatcherClone = dataWatcher.deepClone();
/*  86: 92 */               dataWatcherClone.setObject(ProtocolLibHook.customNameWatcherIndex, customName.replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName()));
/*  87: 93 */               spawnEntityPacket.setMetadata(dataWatcherClone);
/*  88: 94 */               event.setPacket(spawnEntityPacket.getHandle());
/*  89:    */             }
/*  90:    */           }
/*  91: 98 */           else if (packet.getType() == PacketType.Play.Server.SPAWN_ENTITY)
/*  92:    */           {
/*  93:100 */             WrapperPlayServerSpawnEntity spawnEntityPacket = new WrapperPlayServerSpawnEntity(packet);
/*  94:101 */             int objectId = spawnEntityPacket.getType();
/*  95:102 */             if ((objectId != 2) && (objectId != 66) && (objectId != 78)) {
/*  96:103 */               return;
/*  97:    */             }
/*  98:106 */             Entity entity = spawnEntityPacket.getEntity(event);
/*  99:107 */             if (entity == null) {
/* 100:108 */               return;
/* 101:    */             }
/* 102:111 */             CraftHologram hologram = ProtocolLibHook.getHologram(entity);
/* 103:112 */             if (hologram == null) {
/* 104:113 */               return;
/* 105:    */             }
/* 106:116 */             Player player = event.getPlayer();
/* 107:117 */             if (!hologram.getVisibilityManager().isVisibleTo(player)) {
/* 108:118 */               event.setCancelled(true);
/* 109:    */             }
/* 110:    */           }
/* 111:122 */           else if (packet.getType() == PacketType.Play.Server.ENTITY_METADATA)
/* 112:    */           {
/* 113:124 */             WrapperPlayServerEntityMetadata entityMetadataPacket = new WrapperPlayServerEntityMetadata(packet);
/* 114:125 */             Entity entity = entityMetadataPacket.getEntity(event);
/* 115:127 */             if (entity == null) {
/* 116:128 */               return;
/* 117:    */             }
/* 118:131 */             if ((entity.getType() != EntityType.HORSE) && (!VersionUtils.isArmorstand(entity.getType()))) {
/* 119:133 */               return;
/* 120:    */             }
/* 121:136 */             CraftHologram hologram = ProtocolLibHook.getHologram(entity);
/* 122:137 */             if (hologram == null) {
/* 123:138 */               return;
/* 124:    */             }
/* 125:141 */             Player player = event.getPlayer();
/* 126:142 */             if (!hologram.getVisibilityManager().isVisibleTo(player))
/* 127:    */             {
/* 128:143 */               event.setCancelled(true);
/* 129:144 */               return;
/* 130:    */             }
/* 131:147 */             List<WrappedWatchableObject> dataWatcherValues = entityMetadataPacket.getEntityMetadata();
/* 132:149 */             for (int i = 0; i < dataWatcherValues.size(); i++) {
/* 133:151 */               if ((((WrappedWatchableObject)dataWatcherValues.get(i)).getIndex() == ProtocolLibHook.customNameWatcherIndex) && (((WrappedWatchableObject)dataWatcherValues.get(i)).getValue() != null))
/* 134:    */               {
/* 135:153 */                 Object customNameObject = ((WrappedWatchableObject)dataWatcherValues.get(i)).deepClone().getValue();
/* 136:154 */                 if ((customNameObject == null) || (!(customNameObject instanceof String))) {
/* 137:155 */                   return;
/* 138:    */                 }
/* 139:158 */                 String customName = (String)customNameObject;
/* 140:160 */                 if ((customName.contains("{player}")) || (customName.contains("{displayname}")))
/* 141:    */                 {
/* 142:162 */                   entityMetadataPacket = new WrapperPlayServerEntityMetadata(packet.deepClone());
/* 143:163 */                   List<WrappedWatchableObject> clonedList = entityMetadataPacket.getEntityMetadata();
/* 144:164 */                   WrappedWatchableObject clonedElement = (WrappedWatchableObject)clonedList.get(i);
/* 145:165 */                   clonedElement.setValue(customName.replace("{player}", player.getName()).replace("{displayname}", player.getDisplayName()));
/* 146:166 */                   entityMetadataPacket.setEntityMetadata(clonedList);
/* 147:167 */                   event.setPacket(entityMetadataPacket.getHandle());
/* 148:168 */                   return;
/* 149:    */                 }
/* 150:    */               }
/* 151:    */             }
/* 152:    */           }
/* 153:    */         }
/* 154:176 */       });
/* 155:177 */       return true;
/* 156:    */     }
/* 157:180 */     return false;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public static void sendDestroyEntitiesPacket(Player player, CraftHologram hologram)
/* 161:    */   {
/* 162:184 */     if (!hasProtocolLib) {
/* 163:185 */       return;
/* 164:    */     }
/* 165:188 */     List<Integer> ids = Utils.newList();
/* 166:189 */     for (CraftHologramLine line : hologram.getLinesUnsafe()) {
/* 167:190 */       if (line.isSpawned()) {
/* 168:191 */         for (int id : line.getEntitiesIDs()) {
/* 169:192 */           ids.add(Integer.valueOf(id));
/* 170:    */         }
/* 171:    */       }
/* 172:    */     }
/* 173:197 */     if (!ids.isEmpty())
/* 174:    */     {
/* 175:198 */       WrapperPlayServerEntityDestroy packet = new WrapperPlayServerEntityDestroy();
/* 176:199 */       packet.setEntities(ids);
/* 177:200 */       packet.sendPacket(player);
/* 178:    */     }
/* 179:    */   }
/* 180:    */   
/* 181:    */   public static void sendCreateEntitiesPacket(Player player, CraftHologram hologram)
/* 182:    */   {
/* 183:205 */     if (!hasProtocolLib) {
/* 184:206 */       return;
/* 185:    */     }
/* 186:209 */     for (CraftHologramLine line : hologram.getLinesUnsafe()) {
/* 187:210 */       if (line.isSpawned())
/* 188:    */       {
/* 189:212 */         if ((line instanceof CraftTextLine))
/* 190:    */         {
/* 191:213 */           CraftTextLine textLine = (CraftTextLine)line;
/* 192:215 */           if (textLine.isSpawned())
/* 193:    */           {
/* 194:217 */             AbstractPacket nameablePacket = new WrapperPlayServerSpawnEntityLiving(textLine.getNmsNameble().getBukkitEntityNMS());
/* 195:218 */             nameablePacket.sendPacket(player);
/* 196:220 */             if (textLine.getNmsSkullVehicle() != null)
/* 197:    */             {
/* 198:221 */               AbstractPacket vehiclePacket = new WrapperPlayServerSpawnEntity(textLine.getNmsSkullVehicle().getBukkitEntityNMS(), 66, 0);
/* 199:222 */               vehiclePacket.sendPacket(player);
/* 200:    */               
/* 201:224 */               WrapperPlayServerAttachEntity attachPacket = new WrapperPlayServerAttachEntity();
/* 202:225 */               attachPacket.setVehicleId(textLine.getNmsSkullVehicle().getIdNMS());
/* 203:226 */               attachPacket.setEntityId(textLine.getNmsNameble().getIdNMS());
/* 204:227 */               attachPacket.sendPacket(player);
/* 205:    */             }
/* 206:    */           }
/* 207:    */         }
/* 208:231 */         else if ((line instanceof CraftItemLine))
/* 209:    */         {
/* 210:232 */           CraftItemLine itemLine = (CraftItemLine)line;
/* 211:234 */           if (itemLine.isSpawned())
/* 212:    */           {
/* 213:235 */             AbstractPacket itemPacket = new WrapperPlayServerSpawnEntity(itemLine.getNmsItem().getBukkitEntityNMS(), 2, 1);
/* 214:236 */             itemPacket.sendPacket(player);
/* 215:    */             AbstractPacket vehiclePacket;
/* 216:    */             AbstractPacket vehiclePacket;
/* 217:239 */             if (HolographicDisplays.is1_8()) {
/* 218:241 */               vehiclePacket = new WrapperPlayServerSpawnEntityLiving(itemLine.getNmsVehicle().getBukkitEntityNMS());
/* 219:    */             } else {
/* 220:243 */               vehiclePacket = new WrapperPlayServerSpawnEntity(itemLine.getNmsVehicle().getBukkitEntityNMS(), 66, 0);
/* 221:    */             }
/* 222:246 */             vehiclePacket.sendPacket(player);
/* 223:    */             
/* 224:248 */             WrapperPlayServerAttachEntity attachPacket = new WrapperPlayServerAttachEntity();
/* 225:249 */             attachPacket.setVehicleId(itemLine.getNmsVehicle().getIdNMS());
/* 226:250 */             attachPacket.setEntityId(itemLine.getNmsItem().getIdNMS());
/* 227:251 */             attachPacket.sendPacket(player);
/* 228:    */             
/* 229:253 */             WrapperPlayServerEntityMetadata itemDataPacket = new WrapperPlayServerEntityMetadata();
/* 230:    */             
/* 231:255 */             List<WrappedWatchableObject> metadata = Utils.newList();
/* 232:256 */             metadata.add(new WrappedWatchableObject(10, itemLine.getItemStack()));
/* 233:257 */             metadata.add(new WrappedWatchableObject(1, Short.valueOf((short)300)));
/* 234:258 */             metadata.add(new WrappedWatchableObject(0, Byte.valueOf((byte)0)));
/* 235:259 */             itemDataPacket.setEntityMetadata(metadata);
/* 236:260 */             itemDataPacket.setEntityId(itemLine.getNmsItem().getIdNMS());
/* 237:261 */             itemDataPacket.sendPacket(player);
/* 238:    */           }
/* 239:    */         }
/* 240:266 */         CraftTouchableLine touchableLine = (CraftTouchableLine)line;
/* 241:268 */         if ((touchableLine.isSpawned()) && (touchableLine.getTouchSlime() != null))
/* 242:    */         {
/* 243:270 */           CraftTouchSlimeLine touchSlime = touchableLine.getTouchSlime();
/* 244:272 */           if (touchSlime.isSpawned())
/* 245:    */           {
/* 246:    */             AbstractPacket vehiclePacket;
/* 247:    */             AbstractPacket vehiclePacket;
/* 248:275 */             if (HolographicDisplays.is1_8()) {
/* 249:277 */               vehiclePacket = new WrapperPlayServerSpawnEntityLiving(touchSlime.getNmsVehicle().getBukkitEntityNMS());
/* 250:    */             } else {
/* 251:280 */               vehiclePacket = new WrapperPlayServerSpawnEntity(touchSlime.getNmsVehicle().getBukkitEntityNMS(), 66, 0);
/* 252:    */             }
/* 253:282 */             vehiclePacket.sendPacket(player);
/* 254:    */             
/* 255:284 */             AbstractPacket slimePacket = new WrapperPlayServerSpawnEntityLiving(touchSlime.getNmsSlime().getBukkitEntityNMS());
/* 256:285 */             slimePacket.sendPacket(player);
/* 257:    */             
/* 258:287 */             WrapperPlayServerAttachEntity attachPacket = new WrapperPlayServerAttachEntity();
/* 259:288 */             attachPacket.setVehicleId(touchSlime.getNmsVehicle().getIdNMS());
/* 260:289 */             attachPacket.setEntityId(touchSlime.getNmsSlime().getIdNMS());
/* 261:290 */             attachPacket.sendPacket(player);
/* 262:    */           }
/* 263:    */         }
/* 264:    */       }
/* 265:    */     }
/* 266:    */   }
/* 267:    */   
/* 268:    */   private static boolean isHologramType(EntityType type)
/* 269:    */   {
/* 270:298 */     return (type == EntityType.HORSE) || (type == EntityType.WITHER_SKULL) || (type == EntityType.DROPPED_ITEM) || (type == EntityType.SLIME) || (VersionUtils.isArmorstand(type));
/* 271:    */   }
/* 272:    */   
/* 273:    */   private static CraftHologram getHologram(Entity bukkitEntity)
/* 274:    */   {
/* 275:302 */     NMSEntityBase entity = nmsManager.getNMSEntityBase(bukkitEntity);
/* 276:303 */     if (entity != null) {
/* 277:304 */       return entity.getHologramLine().getParent();
/* 278:    */     }
/* 279:307 */     return null;
/* 280:    */   }
/* 281:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.protocollib.ProtocolLibHook
 * JD-Core Version:    0.7.0.1
 */