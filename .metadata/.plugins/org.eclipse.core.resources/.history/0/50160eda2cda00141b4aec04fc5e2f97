/*   1:    */ package com.gmail.filoghost.holographicdisplays.bridge.protocollib;
/*   2:    */ 
/*   3:    */ import com.comphenix.protocol.PacketType;
/*   4:    */ import com.comphenix.protocol.PacketType.Play.Server;
/*   5:    */ import com.comphenix.protocol.ProtocolLibrary;
/*   6:    */ import com.comphenix.protocol.ProtocolManager;
/*   7:    */ import com.comphenix.protocol.events.PacketContainer;
/*   8:    */ import com.comphenix.protocol.events.PacketEvent;
/*   9:    */ import com.comphenix.protocol.injector.PacketConstructor;
/*  10:    */ import com.comphenix.protocol.reflect.StructureModifier;
/*  11:    */ import com.comphenix.protocol.wrappers.WrappedDataWatcher;
/*  12:    */ import org.bukkit.World;
/*  13:    */ import org.bukkit.entity.Entity;
/*  14:    */ import org.bukkit.entity.EntityType;
/*  15:    */ import org.bukkit.entity.Player;
/*  16:    */ 
/*  17:    */ public class WrapperPlayServerSpawnEntityLiving
/*  18:    */   extends AbstractPacket
/*  19:    */ {
/*  20: 32 */   public static final PacketType TYPE = PacketType.Play.Server.SPAWN_ENTITY_LIVING;
/*  21:    */   private static PacketConstructor entityConstructor;
/*  22:    */   
/*  23:    */   public WrapperPlayServerSpawnEntityLiving()
/*  24:    */   {
/*  25: 37 */     super(new PacketContainer(TYPE), TYPE);
/*  26: 38 */     this.handle.getModifier().writeDefaults();
/*  27:    */   }
/*  28:    */   
/*  29:    */   public WrapperPlayServerSpawnEntityLiving(PacketContainer packet)
/*  30:    */   {
/*  31: 42 */     super(packet, TYPE);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public WrapperPlayServerSpawnEntityLiving(Entity entity)
/*  35:    */   {
/*  36: 46 */     super(fromEntity(entity), TYPE);
/*  37:    */   }
/*  38:    */   
/*  39:    */   private static PacketContainer fromEntity(Entity entity)
/*  40:    */   {
/*  41: 51 */     if (entityConstructor == null) {
/*  42: 52 */       entityConstructor = ProtocolLibrary.getProtocolManager().createPacketConstructor(TYPE, new Object[] { entity });
/*  43:    */     }
/*  44: 53 */     return entityConstructor.createPacket(new Object[] { entity });
/*  45:    */   }
/*  46:    */   
/*  47:    */   public int getEntityID()
/*  48:    */   {
/*  49: 61 */     return ((Integer)this.handle.getIntegers().read(0)).intValue();
/*  50:    */   }
/*  51:    */   
/*  52:    */   public Entity getEntity(World world)
/*  53:    */   {
/*  54: 70 */     return (Entity)this.handle.getEntityModifier(world).read(0);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public Entity getEntity(PacketEvent event)
/*  58:    */   {
/*  59: 79 */     return getEntity(event.getPlayer().getWorld());
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void setEntityID(int value)
/*  63:    */   {
/*  64: 87 */     this.handle.getIntegers().write(0, Integer.valueOf(value));
/*  65:    */   }
/*  66:    */   
/*  67:    */   public EntityType getType()
/*  68:    */   {
/*  69: 96 */     return EntityType.fromId(((Integer)this.handle.getIntegers().read(1)).intValue());
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void setType(EntityType value)
/*  73:    */   {
/*  74:105 */     this.handle.getIntegers().write(1, Integer.valueOf(value.getTypeId()));
/*  75:    */   }
/*  76:    */   
/*  77:    */   public double getX()
/*  78:    */   {
/*  79:115 */     return ((Integer)this.handle.getIntegers().read(2)).intValue() / 32.0D;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void setX(double value)
/*  83:    */   {
/*  84:123 */     this.handle.getIntegers().write(2, Integer.valueOf((int)Math.floor(value * 32.0D)));
/*  85:    */   }
/*  86:    */   
/*  87:    */   public double getY()
/*  88:    */   {
/*  89:133 */     return ((Integer)this.handle.getIntegers().read(3)).intValue() / 32.0D;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void setY(double value)
/*  93:    */   {
/*  94:141 */     this.handle.getIntegers().write(3, Integer.valueOf((int)Math.floor(value * 32.0D)));
/*  95:    */   }
/*  96:    */   
/*  97:    */   public double getZ()
/*  98:    */   {
/*  99:151 */     return ((Integer)this.handle.getIntegers().read(4)).intValue() / 32.0D;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void setZ(double value)
/* 103:    */   {
/* 104:159 */     this.handle.getIntegers().write(4, Integer.valueOf((int)Math.floor(value * 32.0D)));
/* 105:    */   }
/* 106:    */   
/* 107:    */   public float getYaw()
/* 108:    */   {
/* 109:167 */     return ((Byte)this.handle.getBytes().read(0)).byteValue() * 360.0F / 256.0F;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setYaw(float value)
/* 113:    */   {
/* 114:175 */     this.handle.getBytes().write(0, Byte.valueOf((byte)(int)(value * 256.0F / 360.0F)));
/* 115:    */   }
/* 116:    */   
/* 117:    */   public float getHeadPitch()
/* 118:    */   {
/* 119:183 */     return ((Byte)this.handle.getBytes().read(1)).byteValue() * 360.0F / 256.0F;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void setHeadPitch(float value)
/* 123:    */   {
/* 124:191 */     this.handle.getBytes().write(1, Byte.valueOf((byte)(int)(value * 256.0F / 360.0F)));
/* 125:    */   }
/* 126:    */   
/* 127:    */   public float getHeadYaw()
/* 128:    */   {
/* 129:199 */     return ((Byte)this.handle.getBytes().read(2)).byteValue() * 360.0F / 256.0F;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void setHeadYaw(float value)
/* 133:    */   {
/* 134:207 */     this.handle.getBytes().write(2, Byte.valueOf((byte)(int)(value * 256.0F / 360.0F)));
/* 135:    */   }
/* 136:    */   
/* 137:    */   public double getVelocityX()
/* 138:    */   {
/* 139:215 */     return ((Integer)this.handle.getIntegers().read(5)).intValue() / 8000.0D;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void setVelocityX(double value)
/* 143:    */   {
/* 144:223 */     this.handle.getIntegers().write(5, Integer.valueOf((int)(value * 8000.0D)));
/* 145:    */   }
/* 146:    */   
/* 147:    */   public double getVelocityY()
/* 148:    */   {
/* 149:231 */     return ((Integer)this.handle.getIntegers().read(6)).intValue() / 8000.0D;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public void setVelocityY(double value)
/* 153:    */   {
/* 154:239 */     this.handle.getIntegers().write(6, Integer.valueOf((int)(value * 8000.0D)));
/* 155:    */   }
/* 156:    */   
/* 157:    */   public double getVelocityZ()
/* 158:    */   {
/* 159:247 */     return ((Integer)this.handle.getIntegers().read(7)).intValue() / 8000.0D;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void setVelocityZ(double value)
/* 163:    */   {
/* 164:255 */     this.handle.getIntegers().write(7, Integer.valueOf((int)(value * 8000.0D)));
/* 165:    */   }
/* 166:    */   
/* 167:    */   public WrappedDataWatcher getMetadata()
/* 168:    */   {
/* 169:265 */     return (WrappedDataWatcher)this.handle.getDataWatcherModifier().read(0);
/* 170:    */   }
/* 171:    */   
/* 172:    */   public void setMetadata(WrappedDataWatcher value)
/* 173:    */   {
/* 174:273 */     this.handle.getDataWatcherModifier().write(0, value);
/* 175:    */   }
/* 176:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.protocollib.WrapperPlayServerSpawnEntityLiving
 * JD-Core Version:    0.7.0.1
 */