/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_8_R1;
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
/*  19:    */ import net.minecraft.server.v1_8_R1.Chunk;
/*  20:    */ import net.minecraft.server.v1_8_R1.ChunkProviderServer;
/*  21:    */ import net.minecraft.server.v1_8_R1.EntityTypes;
/*  22:    */ import net.minecraft.server.v1_8_R1.MathHelper;
/*  23:    */ import net.minecraft.server.v1_8_R1.WorldServer;
/*  24:    */ import org.apache.commons.lang.NotImplementedException;
/*  25:    */ import org.bukkit.Bukkit;
/*  26:    */ import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
/*  27:    */ import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
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
/*  39: 39 */     registerCustomEntity(EntityNMSArmorStand.class, "ArmorStand", 30);
/*  40: 40 */     registerCustomEntity(EntityNMSItem.class, "Item", 1);
/*  41: 41 */     registerCustomEntity(EntityNMSSlime.class, "Slime", 55);
/*  42: 43 */     if (!VersionUtils.isMCPCOrCauldron())
/*  43:    */     {
/*  44: 44 */       this.validateEntityMethod = net.minecraft.server.v1_8_R1.World.class.getDeclaredMethod("a", new Class[] { net.minecraft.server.v1_8_R1.Entity.class });
/*  45: 45 */       this.validateEntityMethod.setAccessible(true);
/*  46:    */     }
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void registerCustomEntity(Class entityClass, String name, int id)
/*  50:    */     throws Exception
/*  51:    */   {
/*  52: 51 */     if (VersionUtils.isMCPCOrCauldron())
/*  53:    */     {
/*  54: 53 */       Class<?> entityTypesClass = Class.forName("net.minecraft.server.v1_8_R1.EntityTypes");
/*  55: 54 */       ReflectionUtils.putInPrivateStaticMap(entityTypesClass, "field_75626_c", entityClass, name);
/*  56: 55 */       ReflectionUtils.putInPrivateStaticMap(entityTypesClass, "field_75624_e", entityClass, Integer.valueOf(id));
/*  57:    */     }
/*  58:    */     else
/*  59:    */     {
/*  60: 58 */       ReflectionUtils.putInPrivateStaticMap(EntityTypes.class, "d", entityClass, name);
/*  61: 59 */       ReflectionUtils.putInPrivateStaticMap(EntityTypes.class, "f", entityClass, Integer.valueOf(id));
/*  62:    */     }
/*  63:    */   }
/*  64:    */   
/*  65:    */   public NMSHorse spawnNMSHorse(org.bukkit.World world, double x, double y, double z, CraftHologramLine parentPiece)
/*  66:    */   {
/*  67: 65 */     throw new NotImplementedException("Method can only be used on 1.7 or lower");
/*  68:    */   }
/*  69:    */   
/*  70:    */   public NMSWitherSkull spawnNMSWitherSkull(org.bukkit.World bukkitWorld, double x, double y, double z, CraftHologramLine parentPiece)
/*  71:    */   {
/*  72: 70 */     throw new NotImplementedException("Method can only be used on 1.7 or lower");
/*  73:    */   }
/*  74:    */   
/*  75:    */   public NMSItem spawnNMSItem(org.bukkit.World bukkitWorld, double x, double y, double z, CraftItemLine parentPiece, ItemStack stack)
/*  76:    */   {
/*  77: 75 */     WorldServer nmsWorld = ((CraftWorld)bukkitWorld).getHandle();
/*  78: 76 */     EntityNMSItem customItem = new EntityNMSItem(nmsWorld, parentPiece);
/*  79: 77 */     customItem.setLocationNMS(x, y, z);
/*  80: 78 */     customItem.setItemStackNMS(stack);
/*  81: 79 */     if (!addEntityToWorld(nmsWorld, customItem)) {
/*  82: 80 */       DebugHandler.handleSpawnFail(parentPiece);
/*  83:    */     }
/*  84: 82 */     return customItem;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public EntityNMSSlime spawnNMSSlime(org.bukkit.World bukkitWorld, double x, double y, double z, CraftTouchSlimeLine parentPiece)
/*  88:    */   {
/*  89: 87 */     WorldServer nmsWorld = ((CraftWorld)bukkitWorld).getHandle();
/*  90: 88 */     EntityNMSSlime touchSlime = new EntityNMSSlime(nmsWorld, parentPiece);
/*  91: 89 */     touchSlime.setLocationNMS(x, y, z);
/*  92: 90 */     if (!addEntityToWorld(nmsWorld, touchSlime)) {
/*  93: 91 */       DebugHandler.handleSpawnFail(parentPiece);
/*  94:    */     }
/*  95: 93 */     return touchSlime;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public NMSArmorStand spawnNMSArmorStand(org.bukkit.World world, double x, double y, double z, CraftHologramLine parentPiece)
/*  99:    */   {
/* 100: 98 */     WorldServer nmsWorld = ((CraftWorld)world).getHandle();
/* 101: 99 */     EntityNMSArmorStand invisibleArmorStand = new EntityNMSArmorStand(nmsWorld, parentPiece);
/* 102:100 */     invisibleArmorStand.setLocationNMS(x, y, z);
/* 103:101 */     if (!addEntityToWorld(nmsWorld, invisibleArmorStand)) {
/* 104:102 */       DebugHandler.handleSpawnFail(parentPiece);
/* 105:    */     }
/* 106:104 */     return invisibleArmorStand;
/* 107:    */   }
/* 108:    */   
/* 109:    */   private boolean addEntityToWorld(WorldServer nmsWorld, net.minecraft.server.v1_8_R1.Entity nmsEntity)
/* 110:    */   {
/* 111:109 */     Validator.isTrue(Bukkit.isPrimaryThread(), "Async entity add");
/* 112:111 */     if (this.validateEntityMethod == null) {
/* 113:112 */       return nmsWorld.addEntity(nmsEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
/* 114:    */     }
/* 115:115 */     int chunkX = MathHelper.floor(nmsEntity.locX / 16.0D);
/* 116:116 */     int chunkZ = MathHelper.floor(nmsEntity.locZ / 16.0D);
/* 117:118 */     if (!nmsWorld.chunkProviderServer.isChunkLoaded(chunkX, chunkZ))
/* 118:    */     {
/* 119:120 */       nmsEntity.dead = true;
/* 120:121 */       return false;
/* 121:    */     }
/* 122:124 */     nmsWorld.getChunkAt(chunkX, chunkZ).a(nmsEntity);
/* 123:125 */     nmsWorld.entityList.add(nmsEntity);
/* 124:    */     try
/* 125:    */     {
/* 126:128 */       this.validateEntityMethod.invoke(nmsWorld, new Object[] { nmsEntity });
/* 127:    */     }
/* 128:    */     catch (Exception e)
/* 129:    */     {
/* 130:130 */       e.printStackTrace();
/* 131:131 */       return false;
/* 132:    */     }
/* 133:133 */     return true;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public boolean isNMSEntityBase(org.bukkit.entity.Entity bukkitEntity)
/* 137:    */   {
/* 138:138 */     return ((CraftEntity)bukkitEntity).getHandle() instanceof NMSEntityBase;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public NMSEntityBase getNMSEntityBase(org.bukkit.entity.Entity bukkitEntity)
/* 142:    */   {
/* 143:144 */     net.minecraft.server.v1_8_R1.Entity nmsEntity = ((CraftEntity)bukkitEntity).getHandle();
/* 144:145 */     if ((nmsEntity instanceof NMSEntityBase)) {
/* 145:146 */       return (NMSEntityBase)nmsEntity;
/* 146:    */     }
/* 147:149 */     return null;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public FancyMessage newFancyMessage(String text)
/* 151:    */   {
/* 152:154 */     return new FancyMessageImpl(text);
/* 153:    */   }
/* 154:    */   
/* 155:    */   public boolean hasChatHoverFeature()
/* 156:    */   {
/* 157:159 */     return true;
/* 158:    */   }
/* 159:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_8_R1.NmsManagerImpl
 * JD-Core Version:    0.7.0.1
 */