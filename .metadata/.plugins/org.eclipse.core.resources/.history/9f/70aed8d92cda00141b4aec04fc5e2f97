/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_7_R2;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.FancyMessage;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSArmorStand;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSHorse;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSItem;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSWitherSkull;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine;
/*  12:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchSlimeLine;
/*  13:    */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*  14:    */ import com.gmail.filoghost.holographicdisplays.util.ReflectionUtils;
/*  15:    */ import com.gmail.filoghost.holographicdisplays.util.Validator;
/*  16:    */ import com.gmail.filoghost.holographicdisplays.util.VersionUtils;
/*  17:    */ import java.lang.reflect.Method;
/*  18:    */ import java.util.List;
/*  19:    */ import net.minecraft.server.v1_7_R2.Chunk;
/*  20:    */ import net.minecraft.server.v1_7_R2.ChunkProviderServer;
/*  21:    */ import net.minecraft.server.v1_7_R2.EntityTypes;
/*  22:    */ import net.minecraft.server.v1_7_R2.MathHelper;
/*  23:    */ import net.minecraft.server.v1_7_R2.WorldServer;
/*  24:    */ import org.apache.commons.lang.NotImplementedException;
/*  25:    */ import org.bukkit.Bukkit;
/*  26:    */ import org.bukkit.craftbukkit.v1_7_R2.CraftWorld;
/*  27:    */ import org.bukkit.craftbukkit.v1_7_R2.entity.CraftEntity;
/*  28:    */ import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
/*  29:    */ import org.bukkit.inventory.ItemStack;
/*  30:    */ 
/*  31:    */ public class NmsManagerImpl
/*  32:    */   implements NMSManager
/*  33:    */ {
/*  34:    */   private Method validateEntityMethod;
/*  35:    */   
/*  36:    */   public void setup()
/*  37:    */     throws Exception
/*  38:    */   {
/*  39: 39 */     registerCustomEntity(EntityNMSHorse.class, "EntityHorse", 100);
/*  40: 40 */     registerCustomEntity(EntityNMSWitherSkull.class, "WitherSkull", 19);
/*  41: 41 */     registerCustomEntity(EntityNMSItem.class, "Item", 1);
/*  42: 42 */     registerCustomEntity(EntityNMSSlime.class, "Slime", 55);
/*  43: 44 */     if (!VersionUtils.isMCPCOrCauldron())
/*  44:    */     {
/*  45: 45 */       this.validateEntityMethod = net.minecraft.server.v1_7_R2.World.class.getDeclaredMethod("a", new Class[] { net.minecraft.server.v1_7_R2.Entity.class });
/*  46: 46 */       this.validateEntityMethod.setAccessible(true);
/*  47:    */     }
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void registerCustomEntity(Class entityClass, String name, int id)
/*  51:    */     throws Exception
/*  52:    */   {
/*  53: 52 */     if (VersionUtils.isMCPCOrCauldron())
/*  54:    */     {
/*  55: 54 */       Class<?> entityTypesClass = Class.forName("net.minecraft.server.v1_7_R2.EntityTypes");
/*  56: 55 */       ReflectionUtils.putInPrivateStaticMap(entityTypesClass, "field_75626_c", entityClass, name);
/*  57: 56 */       ReflectionUtils.putInPrivateStaticMap(entityTypesClass, "field_75624_e", entityClass, Integer.valueOf(id));
/*  58:    */     }
/*  59:    */     else
/*  60:    */     {
/*  61: 59 */       ReflectionUtils.putInPrivateStaticMap(EntityTypes.class, "d", entityClass, name);
/*  62: 60 */       ReflectionUtils.putInPrivateStaticMap(EntityTypes.class, "f", entityClass, Integer.valueOf(id));
/*  63:    */     }
/*  64:    */   }
/*  65:    */   
/*  66:    */   public NMSHorse spawnNMSHorse(org.bukkit.World world, double x, double y, double z, CraftHologramLine parentPiece)
/*  67:    */   {
/*  68: 66 */     WorldServer nmsWorld = ((CraftWorld)world).getHandle();
/*  69: 67 */     EntityNMSHorse invisibleHorse = new EntityNMSHorse(nmsWorld, parentPiece);
/*  70: 68 */     invisibleHorse.setLocationNMS(x, y, z);
/*  71: 69 */     if (!addEntityToWorld(nmsWorld, invisibleHorse)) {
/*  72: 70 */       DebugHandler.handleSpawnFail(parentPiece);
/*  73:    */     }
/*  74: 72 */     return invisibleHorse;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public NMSWitherSkull spawnNMSWitherSkull(org.bukkit.World bukkitWorld, double x, double y, double z, CraftHologramLine parentPiece)
/*  78:    */   {
/*  79: 77 */     WorldServer nmsWorld = ((CraftWorld)bukkitWorld).getHandle();
/*  80: 78 */     EntityNMSWitherSkull staticWitherSkull = new EntityNMSWitherSkull(nmsWorld, parentPiece);
/*  81: 79 */     staticWitherSkull.setLocationNMS(x, y, z);
/*  82: 80 */     if (!addEntityToWorld(nmsWorld, staticWitherSkull)) {
/*  83: 81 */       DebugHandler.handleSpawnFail(parentPiece);
/*  84:    */     }
/*  85: 83 */     return staticWitherSkull;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public NMSItem spawnNMSItem(org.bukkit.World bukkitWorld, double x, double y, double z, CraftItemLine parentPiece, ItemStack stack)
/*  89:    */   {
/*  90: 88 */     WorldServer nmsWorld = ((CraftWorld)bukkitWorld).getHandle();
/*  91: 89 */     EntityNMSItem customItem = new EntityNMSItem(nmsWorld, parentPiece);
/*  92: 90 */     customItem.setLocationNMS(x, y, z);
/*  93: 91 */     customItem.setItemStackNMS(stack);
/*  94: 92 */     if (!addEntityToWorld(nmsWorld, customItem)) {
/*  95: 93 */       DebugHandler.handleSpawnFail(parentPiece);
/*  96:    */     }
/*  97: 95 */     return customItem;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public EntityNMSSlime spawnNMSSlime(org.bukkit.World bukkitWorld, double x, double y, double z, CraftTouchSlimeLine parentPiece)
/* 101:    */   {
/* 102:100 */     WorldServer nmsWorld = ((CraftWorld)bukkitWorld).getHandle();
/* 103:101 */     EntityNMSSlime touchSlime = new EntityNMSSlime(nmsWorld, parentPiece);
/* 104:102 */     touchSlime.setLocationNMS(x, y, z);
/* 105:103 */     if (!addEntityToWorld(nmsWorld, touchSlime)) {
/* 106:104 */       DebugHandler.handleSpawnFail(parentPiece);
/* 107:    */     }
/* 108:106 */     return touchSlime;
/* 109:    */   }
/* 110:    */   
/* 111:    */   private boolean addEntityToWorld(WorldServer nmsWorld, net.minecraft.server.v1_7_R2.Entity nmsEntity)
/* 112:    */   {
/* 113:111 */     Validator.isTrue(Bukkit.isPrimaryThread(), "Async entity add");
/* 114:113 */     if (this.validateEntityMethod == null) {
/* 115:114 */       return nmsWorld.addEntity(nmsEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
/* 116:    */     }
/* 117:117 */     int chunkX = MathHelper.floor(nmsEntity.locX / 16.0D);
/* 118:118 */     int chunkZ = MathHelper.floor(nmsEntity.locZ / 16.0D);
/* 119:120 */     if (!nmsWorld.chunkProviderServer.isChunkLoaded(chunkX, chunkZ))
/* 120:    */     {
/* 121:122 */       nmsEntity.dead = true;
/* 122:123 */       return false;
/* 123:    */     }
/* 124:126 */     nmsWorld.getChunkAt(chunkX, chunkZ).a(nmsEntity);
/* 125:127 */     nmsWorld.entityList.add(nmsEntity);
/* 126:    */     try
/* 127:    */     {
/* 128:130 */       this.validateEntityMethod.invoke(nmsWorld, new Object[] { nmsEntity });
/* 129:    */     }
/* 130:    */     catch (Exception e)
/* 131:    */     {
/* 132:132 */       e.printStackTrace();
/* 133:133 */       return false;
/* 134:    */     }
/* 135:135 */     return true;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public boolean isNMSEntityBase(org.bukkit.entity.Entity bukkitEntity)
/* 139:    */   {
/* 140:140 */     return ((CraftEntity)bukkitEntity).getHandle() instanceof NMSEntityBase;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public NMSEntityBase getNMSEntityBase(org.bukkit.entity.Entity bukkitEntity)
/* 144:    */   {
/* 145:146 */     net.minecraft.server.v1_7_R2.Entity nmsEntity = ((CraftEntity)bukkitEntity).getHandle();
/* 146:147 */     if ((nmsEntity instanceof NMSEntityBase)) {
/* 147:148 */       return (NMSEntityBase)nmsEntity;
/* 148:    */     }
/* 149:151 */     return null;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public FancyMessage newFancyMessage(String text)
/* 153:    */   {
/* 154:156 */     return new FancyMessageImpl(text);
/* 155:    */   }
/* 156:    */   
/* 157:    */   public boolean hasChatHoverFeature()
/* 158:    */   {
/* 159:161 */     return true;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public NMSArmorStand spawnNMSArmorStand(org.bukkit.World world, double x, double y, double z, CraftHologramLine parentPiece)
/* 163:    */   {
/* 164:166 */     throw new NotImplementedException("Method can only be used on 1.8 or higher");
/* 165:    */   }
/* 166:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_7_R2.NmsManagerImpl
 * JD-Core Version:    0.7.0.1
 */