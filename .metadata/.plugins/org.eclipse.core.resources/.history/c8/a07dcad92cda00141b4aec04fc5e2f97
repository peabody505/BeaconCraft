/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_7_R4;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSSlime;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchSlimeLine;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.util.ReflectionUtils;
/*   9:    */ import net.minecraft.server.v1_7_R4.DamageSource;
/*  10:    */ import net.minecraft.server.v1_7_R4.EntityDamageSource;
/*  11:    */ import net.minecraft.server.v1_7_R4.EntityPlayer;
/*  12:    */ import net.minecraft.server.v1_7_R4.EntitySlime;
/*  13:    */ import net.minecraft.server.v1_7_R4.NBTTagCompound;
/*  14:    */ import net.minecraft.server.v1_7_R4.World;
/*  15:    */ import org.bukkit.Bukkit;
/*  16:    */ import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
/*  17:    */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*  18:    */ import org.bukkit.plugin.PluginManager;
/*  19:    */ 
/*  20:    */ public class EntityNMSSlime
/*  21:    */   extends EntitySlime
/*  22:    */   implements NMSSlime
/*  23:    */ {
/*  24:    */   private boolean lockTick;
/*  25:    */   private CraftTouchSlimeLine parentPiece;
/*  26:    */   
/*  27:    */   public EntityNMSSlime(World world, CraftTouchSlimeLine parentPiece)
/*  28:    */   {
/*  29: 28 */     super(world);
/*  30: 29 */     this.persistent = true;
/*  31: 30 */     this.boundingBox.a = 0.0D;
/*  32: 31 */     this.boundingBox.b = 0.0D;
/*  33: 32 */     this.boundingBox.c = 0.0D;
/*  34: 33 */     this.boundingBox.d = 0.0D;
/*  35: 34 */     this.boundingBox.e = 0.0D;
/*  36: 35 */     this.boundingBox.f = 0.0D;
/*  37: 36 */     a(0.0F, 0.0F);
/*  38: 37 */     setSize(1);
/*  39: 38 */     setInvisible(true);
/*  40: 39 */     this.parentPiece = parentPiece;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void h()
/*  44:    */   {
/*  45: 45 */     if (this.ticksLived % 20 == 0) {
/*  46: 47 */       if (this.vehicle == null) {
/*  47: 48 */         die();
/*  48:    */       }
/*  49:    */     }
/*  50: 52 */     if (!this.lockTick) {
/*  51: 53 */       super.h();
/*  52:    */     }
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void b(NBTTagCompound nbttagcompound) {}
/*  56:    */   
/*  57:    */   public boolean c(NBTTagCompound nbttagcompound)
/*  58:    */   {
/*  59: 65 */     return false;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public boolean d(NBTTagCompound nbttagcompound)
/*  63:    */   {
/*  64: 71 */     return false;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void e(NBTTagCompound nbttagcompound) {}
/*  68:    */   
/*  69:    */   public boolean damageEntity(DamageSource damageSource, float amount)
/*  70:    */   {
/*  71: 81 */     if ((damageSource instanceof EntityDamageSource))
/*  72:    */     {
/*  73: 82 */       EntityDamageSource entityDamageSource = (EntityDamageSource)damageSource;
/*  74: 83 */       if ((entityDamageSource.getEntity() instanceof EntityPlayer)) {
/*  75: 84 */         Bukkit.getPluginManager().callEvent(new PlayerInteractEntityEvent(((EntityPlayer)entityDamageSource.getEntity()).getBukkitEntity(), getBukkitEntity()));
/*  76:    */       }
/*  77:    */     }
/*  78: 87 */     return false;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public boolean isInvulnerable()
/*  82:    */   {
/*  83: 97 */     return true;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void setCustomName(String customName) {}
/*  87:    */   
/*  88:    */   public void setCustomNameVisible(boolean visible) {}
/*  89:    */   
/*  90:    */   public void makeSound(String sound, float volume, float pitch) {}
/*  91:    */   
/*  92:    */   public void setLockTick(boolean lock)
/*  93:    */   {
/*  94:117 */     this.lockTick = lock;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void die()
/*  98:    */   {
/*  99:122 */     setLockTick(false);
/* 100:123 */     super.die();
/* 101:    */   }
/* 102:    */   
/* 103:    */   public CraftEntity getBukkitEntity()
/* 104:    */   {
/* 105:128 */     if (this.bukkitEntity == null) {
/* 106:129 */       this.bukkitEntity = new CraftNMSSlime(this.world.getServer(), this);
/* 107:    */     }
/* 108:131 */     return this.bukkitEntity;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public boolean isDeadNMS()
/* 112:    */   {
/* 113:136 */     return this.dead;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public void killEntityNMS()
/* 117:    */   {
/* 118:141 */     die();
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void setLocationNMS(double x, double y, double z)
/* 122:    */   {
/* 123:146 */     super.setPosition(x, y, z);
/* 124:    */   }
/* 125:    */   
/* 126:    */   public int getIdNMS()
/* 127:    */   {
/* 128:151 */     return getId();
/* 129:    */   }
/* 130:    */   
/* 131:    */   public CraftHologramLine getHologramLine()
/* 132:    */   {
/* 133:156 */     return this.parentPiece;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public org.bukkit.entity.Entity getBukkitEntityNMS()
/* 137:    */   {
/* 138:161 */     return getBukkitEntity();
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void setPassengerOfNMS(NMSEntityBase vehicleBase)
/* 142:    */   {
/* 143:166 */     if ((vehicleBase == null) || (!(vehicleBase instanceof net.minecraft.server.v1_7_R4.Entity))) {
/* 144:168 */       return;
/* 145:    */     }
/* 146:171 */     net.minecraft.server.v1_7_R4.Entity entity = (net.minecraft.server.v1_7_R4.Entity)vehicleBase;
/* 147:    */     try
/* 148:    */     {
/* 149:174 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_7_R4.Entity.class, this, "g", Double.valueOf(0.0D));
/* 150:175 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_7_R4.Entity.class, this, "h", Double.valueOf(0.0D));
/* 151:    */     }
/* 152:    */     catch (Exception ex)
/* 153:    */     {
/* 154:177 */       DebugHandler.handleDebugException(ex);
/* 155:    */     }
/* 156:180 */     if (this.vehicle != null) {
/* 157:181 */       this.vehicle.passenger = null;
/* 158:    */     }
/* 159:184 */     this.vehicle = entity;
/* 160:185 */     entity.passenger = this;
/* 161:    */   }
/* 162:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_7_R4.EntityNMSSlime
 * JD-Core Version:    0.7.0.1
 */