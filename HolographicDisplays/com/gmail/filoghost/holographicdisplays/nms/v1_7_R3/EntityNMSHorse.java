/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_7_R3;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSHorse;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.util.ReflectionUtils;
/*   8:    */ import net.minecraft.server.v1_7_R3.EntityHorse;
/*   9:    */ import net.minecraft.server.v1_7_R3.NBTTagCompound;
/*  10:    */ import net.minecraft.server.v1_7_R3.World;
/*  11:    */ import org.bukkit.craftbukkit.v1_7_R3.entity.CraftEntity;
/*  12:    */ 
/*  13:    */ public class EntityNMSHorse
/*  14:    */   extends EntityHorse
/*  15:    */   implements NMSHorse
/*  16:    */ {
/*  17:    */   private boolean lockTick;
/*  18:    */   private CraftHologramLine parentPiece;
/*  19:    */   
/*  20:    */   public EntityNMSHorse(World world, CraftHologramLine parentPiece)
/*  21:    */   {
/*  22: 22 */     super(world);
/*  23: 23 */     this.ageLocked = true;
/*  24: 24 */     this.persistent = true;
/*  25: 25 */     this.boundingBox.a = 0.0D;
/*  26: 26 */     this.boundingBox.b = 0.0D;
/*  27: 27 */     this.boundingBox.c = 0.0D;
/*  28: 28 */     this.boundingBox.d = 0.0D;
/*  29: 29 */     this.boundingBox.e = 0.0D;
/*  30: 30 */     this.boundingBox.f = 0.0D;
/*  31: 31 */     a(0.0F, 0.0F);
/*  32: 32 */     setAge(-1700000);
/*  33: 33 */     this.parentPiece = parentPiece;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void h()
/*  37:    */   {
/*  38: 39 */     if (this.ticksLived % 20 == 0) {
/*  39: 41 */       if (this.vehicle == null) {
/*  40: 42 */         die();
/*  41:    */       }
/*  42:    */     }
/*  43: 46 */     if (!this.lockTick) {
/*  44: 47 */       super.h();
/*  45:    */     }
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void b(NBTTagCompound nbttagcompound) {}
/*  49:    */   
/*  50:    */   public boolean c(NBTTagCompound nbttagcompound)
/*  51:    */   {
/*  52: 59 */     return false;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public boolean d(NBTTagCompound nbttagcompound)
/*  56:    */   {
/*  57: 65 */     return false;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void e(NBTTagCompound nbttagcompound) {}
/*  61:    */   
/*  62:    */   public boolean isInvulnerable()
/*  63:    */   {
/*  64: 80 */     return true;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void setCustomName(String customName) {}
/*  68:    */   
/*  69:    */   public void setCustomNameVisible(boolean visible) {}
/*  70:    */   
/*  71:    */   public void makeSound(String sound, float volume, float pitch) {}
/*  72:    */   
/*  73:    */   public void setLockTick(boolean lock)
/*  74:    */   {
/*  75:100 */     this.lockTick = lock;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void die()
/*  79:    */   {
/*  80:105 */     setLockTick(false);
/*  81:106 */     super.die();
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setCustomNameNMS(String name)
/*  85:    */   {
/*  86:111 */     if ((name != null) && (name.length() > 300)) {
/*  87:112 */       name = name.substring(0, 300);
/*  88:    */     }
/*  89:114 */     super.setCustomName(name);
/*  90:115 */     super.setCustomNameVisible((name != null) && (!name.isEmpty()));
/*  91:    */   }
/*  92:    */   
/*  93:    */   public CraftEntity getBukkitEntity()
/*  94:    */   {
/*  95:120 */     if (this.bukkitEntity == null) {
/*  96:121 */       this.bukkitEntity = new CraftNMSHorse(this.world.getServer(), this);
/*  97:    */     }
/*  98:123 */     return this.bukkitEntity;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public boolean isDeadNMS()
/* 102:    */   {
/* 103:128 */     return this.dead;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public String getCustomNameNMS()
/* 107:    */   {
/* 108:133 */     return super.getCustomName();
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void killEntityNMS()
/* 112:    */   {
/* 113:138 */     die();
/* 114:    */   }
/* 115:    */   
/* 116:    */   public void setLocationNMS(double x, double y, double z)
/* 117:    */   {
/* 118:143 */     super.setPosition(x, y, z);
/* 119:    */   }
/* 120:    */   
/* 121:    */   public int getIdNMS()
/* 122:    */   {
/* 123:148 */     return getId();
/* 124:    */   }
/* 125:    */   
/* 126:    */   public CraftHologramLine getHologramLine()
/* 127:    */   {
/* 128:153 */     return this.parentPiece;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public org.bukkit.entity.Entity getBukkitEntityNMS()
/* 132:    */   {
/* 133:158 */     return getBukkitEntity();
/* 134:    */   }
/* 135:    */   
/* 136:    */   public void setPassengerOfNMS(NMSEntityBase vehicleBase)
/* 137:    */   {
/* 138:163 */     if ((vehicleBase == null) || (!(vehicleBase instanceof net.minecraft.server.v1_7_R3.Entity))) {
/* 139:165 */       return;
/* 140:    */     }
/* 141:168 */     net.minecraft.server.v1_7_R3.Entity entity = (net.minecraft.server.v1_7_R3.Entity)vehicleBase;
/* 142:    */     try
/* 143:    */     {
/* 144:171 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_7_R3.Entity.class, this, "g", Double.valueOf(0.0D));
/* 145:172 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_7_R3.Entity.class, this, "h", Double.valueOf(0.0D));
/* 146:    */     }
/* 147:    */     catch (Exception ex)
/* 148:    */     {
/* 149:174 */       DebugHandler.handleDebugException(ex);
/* 150:    */     }
/* 151:177 */     if (this.vehicle != null) {
/* 152:178 */       this.vehicle.passenger = null;
/* 153:    */     }
/* 154:181 */     this.vehicle = entity;
/* 155:182 */     entity.passenger = this;
/* 156:    */   }
/* 157:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_7_R3.EntityNMSHorse
 * JD-Core Version:    0.7.0.1
 */