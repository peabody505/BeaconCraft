/*   1:    */ package com.gmail.filoghost.holographicdisplays.bridge.protocollib;
/*   2:    */ 
/*   3:    */ import com.comphenix.protocol.PacketType;
/*   4:    */ import com.comphenix.protocol.PacketType.Play.Server;
/*   5:    */ import com.comphenix.protocol.ProtocolLibrary;
/*   6:    */ import com.comphenix.protocol.ProtocolManager;
/*   7:    */ import com.comphenix.protocol.events.PacketContainer;
/*   8:    */ import com.comphenix.protocol.events.PacketEvent;
/*   9:    */ import com.comphenix.protocol.injector.PacketConstructor;
/*  10:    */ import com.comphenix.protocol.reflect.IntEnum;
/*  11:    */ import com.comphenix.protocol.reflect.StructureModifier;
/*  12:    */ import org.bukkit.World;
/*  13:    */ import org.bukkit.entity.Entity;
/*  14:    */ import org.bukkit.entity.Player;
/*  15:    */ 
/*  16:    */ public class WrapperPlayServerSpawnEntity
/*  17:    */   extends AbstractPacket
/*  18:    */ {
/*  19: 30 */   public static final PacketType TYPE = PacketType.Play.Server.SPAWN_ENTITY;
/*  20:    */   private static PacketConstructor entityConstructor;
/*  21:    */   
/*  22:    */   public static class ObjectTypes
/*  23:    */     extends IntEnum
/*  24:    */   {
/*  25:    */     public static final int BOAT = 1;
/*  26:    */     public static final int ITEM_STACK = 2;
/*  27:    */     public static final int MINECART = 10;
/*  28:    */     public static final int MINECART_STORAGE = 11;
/*  29:    */     public static final int MINECART_POWERED = 12;
/*  30:    */     public static final int ACTIVATED_TNT = 50;
/*  31:    */     public static final int ENDER_CRYSTAL = 51;
/*  32:    */     public static final int ARROW_PROJECTILE = 60;
/*  33:    */     public static final int SNOWBALL_PROJECTILE = 61;
/*  34:    */     public static final int EGG_PROJECTILE = 62;
/*  35:    */     public static final int FIRE_BALL_GHAST = 63;
/*  36:    */     public static final int FIRE_BALL_BLAZE = 64;
/*  37:    */     public static final int THROWN_ENDERPEARL = 65;
/*  38:    */     public static final int WITHER_SKULL = 66;
/*  39:    */     public static final int FALLING_BLOCK = 70;
/*  40:    */     public static final int ITEM_FRAME = 71;
/*  41:    */     public static final int EYE_OF_ENDER = 72;
/*  42:    */     public static final int THROWN_POTION = 73;
/*  43:    */     public static final int FALLING_DRAGON_EGG = 74;
/*  44:    */     public static final int THROWN_EXP_BOTTLE = 75;
/*  45:    */     public static final int FIREWORK = 76;
/*  46:    */     public static final int ARMOR_STAND = 78;
/*  47:    */     public static final int FISHING_FLOAT = 90;
/*  48: 67 */     private static ObjectTypes INSTANCE = new ObjectTypes();
/*  49:    */     
/*  50:    */     public static ObjectTypes getInstance()
/*  51:    */     {
/*  52: 74 */       return INSTANCE;
/*  53:    */     }
/*  54:    */   }
/*  55:    */   
/*  56:    */   public WrapperPlayServerSpawnEntity()
/*  57:    */   {
/*  58: 79 */     super(new PacketContainer(TYPE), TYPE);
/*  59: 80 */     this.handle.getModifier().writeDefaults();
/*  60:    */   }
/*  61:    */   
/*  62:    */   public WrapperPlayServerSpawnEntity(PacketContainer packet)
/*  63:    */   {
/*  64: 84 */     super(packet, TYPE);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public WrapperPlayServerSpawnEntity(Entity entity, int type, int objectData)
/*  68:    */   {
/*  69: 88 */     super(fromEntity(entity, type, objectData), TYPE);
/*  70:    */   }
/*  71:    */   
/*  72:    */   private static PacketContainer fromEntity(Entity entity, int type, int objectData)
/*  73:    */   {
/*  74: 93 */     if (entityConstructor == null) {
/*  75: 94 */       entityConstructor = ProtocolLibrary.getProtocolManager().createPacketConstructor(TYPE, new Object[] { entity, Integer.valueOf(type), Integer.valueOf(objectData) });
/*  76:    */     }
/*  77: 95 */     return entityConstructor.createPacket(new Object[] { entity, Integer.valueOf(type), Integer.valueOf(objectData) });
/*  78:    */   }
/*  79:    */   
/*  80:    */   public int getEntityID()
/*  81:    */   {
/*  82:103 */     return ((Integer)this.handle.getIntegers().read(0)).intValue();
/*  83:    */   }
/*  84:    */   
/*  85:    */   public Entity getEntity(World world)
/*  86:    */   {
/*  87:112 */     return (Entity)this.handle.getEntityModifier(world).read(0);
/*  88:    */   }
/*  89:    */   
/*  90:    */   public Entity getEntity(PacketEvent event)
/*  91:    */   {
/*  92:121 */     return getEntity(event.getPlayer().getWorld());
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void setEntityID(int value)
/*  96:    */   {
/*  97:129 */     this.handle.getIntegers().write(0, Integer.valueOf(value));
/*  98:    */   }
/*  99:    */   
/* 100:    */   public int getType()
/* 101:    */   {
/* 102:137 */     return ((Integer)this.handle.getIntegers().read(9)).intValue();
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void setType(int value)
/* 106:    */   {
/* 107:145 */     this.handle.getIntegers().write(9, Integer.valueOf(value));
/* 108:    */   }
/* 109:    */   
/* 110:    */   public double getX()
/* 111:    */   {
/* 112:155 */     return ((Integer)this.handle.getIntegers().read(1)).intValue() / 32.0D;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void setX(double value)
/* 116:    */   {
/* 117:163 */     this.handle.getIntegers().write(1, Integer.valueOf((int)Math.floor(value * 32.0D)));
/* 118:    */   }
/* 119:    */   
/* 120:    */   public double getY()
/* 121:    */   {
/* 122:173 */     return ((Integer)this.handle.getIntegers().read(2)).intValue() / 32.0D;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public void setY(double value)
/* 126:    */   {
/* 127:181 */     this.handle.getIntegers().write(2, Integer.valueOf((int)Math.floor(value * 32.0D)));
/* 128:    */   }
/* 129:    */   
/* 130:    */   public double getZ()
/* 131:    */   {
/* 132:191 */     return ((Integer)this.handle.getIntegers().read(3)).intValue() / 32.0D;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void setZ(double value)
/* 136:    */   {
/* 137:199 */     this.handle.getIntegers().write(3, Integer.valueOf((int)Math.floor(value * 32.0D)));
/* 138:    */   }
/* 139:    */   
/* 140:    */   public double getOptionalSpeedX()
/* 141:    */   {
/* 142:209 */     return ((Integer)this.handle.getIntegers().read(4)).intValue() / 8000.0D;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void setOptionalSpeedX(double value)
/* 146:    */   {
/* 147:217 */     this.handle.getIntegers().write(4, Integer.valueOf((int)(value * 8000.0D)));
/* 148:    */   }
/* 149:    */   
/* 150:    */   public double getOptionalSpeedY()
/* 151:    */   {
/* 152:227 */     return ((Integer)this.handle.getIntegers().read(5)).intValue() / 8000.0D;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public void setOptionalSpeedY(double value)
/* 156:    */   {
/* 157:235 */     this.handle.getIntegers().write(5, Integer.valueOf((int)(value * 8000.0D)));
/* 158:    */   }
/* 159:    */   
/* 160:    */   public double getOptionalSpeedZ()
/* 161:    */   {
/* 162:245 */     return ((Integer)this.handle.getIntegers().read(6)).intValue() / 8000.0D;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public void setOptionalSpeedZ(double value)
/* 166:    */   {
/* 167:253 */     this.handle.getIntegers().write(6, Integer.valueOf((int)(value * 8000.0D)));
/* 168:    */   }
/* 169:    */   
/* 170:    */   public float getYaw()
/* 171:    */   {
/* 172:261 */     return ((Integer)this.handle.getIntegers().read(7)).intValue() * 360.0F / 256.0F;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void setYaw(float value)
/* 176:    */   {
/* 177:269 */     this.handle.getIntegers().write(7, Integer.valueOf((int)(value * 256.0F / 360.0F)));
/* 178:    */   }
/* 179:    */   
/* 180:    */   public float getPitch()
/* 181:    */   {
/* 182:277 */     return ((Integer)this.handle.getIntegers().read(8)).intValue() * 360.0F / 256.0F;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public void setPitch(float value)
/* 186:    */   {
/* 187:285 */     this.handle.getIntegers().write(8, Integer.valueOf((int)(value * 256.0F / 360.0F)));
/* 188:    */   }
/* 189:    */   
/* 190:    */   public int getObjectData()
/* 191:    */   {
/* 192:322 */     return ((Integer)this.handle.getIntegers().read(10)).intValue();
/* 193:    */   }
/* 194:    */   
/* 195:    */   public void setObjectData(int value)
/* 196:    */   {
/* 197:332 */     this.handle.getIntegers().write(10, Integer.valueOf(value));
/* 198:    */   }
/* 199:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.protocollib.WrapperPlayServerSpawnEntity
 * JD-Core Version:    0.7.0.1
 */