/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_8_R1;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSSlime;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchSlimeLine;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.util.ReflectionUtils;
/*   9:    */ import net.minecraft.server.v1_8_R1.AxisAlignedBB;
/*  10:    */ import net.minecraft.server.v1_8_R1.DamageSource;
/*  11:    */ import net.minecraft.server.v1_8_R1.EntityDamageSource;
/*  12:    */ import net.minecraft.server.v1_8_R1.EntityPlayer;
/*  13:    */ import net.minecraft.server.v1_8_R1.EntitySlime;
/*  14:    */ import net.minecraft.server.v1_8_R1.NBTTagCompound;
/*  15:    */ import net.minecraft.server.v1_8_R1.World;
/*  16:    */ import org.bukkit.Bukkit;
/*  17:    */ import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
/*  18:    */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*  19:    */ import org.bukkit.plugin.PluginManager;
/*  20:    */ 
/*  21:    */ public class EntityNMSSlime
/*  22:    */   extends EntitySlime
/*  23:    */   implements NMSSlime
/*  24:    */ {
/*  25:    */   private boolean lockTick;
/*  26:    */   private CraftTouchSlimeLine parentPiece;
/*  27:    */   
/*  28:    */   public EntityNMSSlime(World world, CraftTouchSlimeLine parentPiece)
/*  29:    */   {
/*  30: 29 */     super(world);
/*  31: 30 */     this.persistent = true;
/*  32: 31 */     a(0.0F, 0.0F);
/*  33: 32 */     setSize(1);
/*  34: 33 */     setInvisible(true);
/*  35: 34 */     this.parentPiece = parentPiece;
/*  36: 35 */     forceSetBoundingBox(new NullBoundingBox());
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void a(AxisAlignedBB boundingBox) {}
/*  40:    */   
/*  41:    */   public void forceSetBoundingBox(AxisAlignedBB boundingBox)
/*  42:    */   {
/*  43: 44 */     super.a(boundingBox);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void s_()
/*  47:    */   {
/*  48: 50 */     if (this.ticksLived % 20 == 0) {
/*  49: 52 */       if (this.vehicle == null) {
/*  50: 53 */         die();
/*  51:    */       }
/*  52:    */     }
/*  53: 57 */     if (!this.lockTick) {
/*  54: 58 */       super.s_();
/*  55:    */     }
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void b(NBTTagCompound nbttagcompound) {}
/*  59:    */   
/*  60:    */   public boolean c(NBTTagCompound nbttagcompound)
/*  61:    */   {
/*  62: 70 */     return false;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public boolean d(NBTTagCompound nbttagcompound)
/*  66:    */   {
/*  67: 76 */     return false;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void e(NBTTagCompound nbttagcompound) {}
/*  71:    */   
/*  72:    */   public boolean damageEntity(DamageSource damageSource, float amount)
/*  73:    */   {
/*  74: 86 */     if ((damageSource instanceof EntityDamageSource))
/*  75:    */     {
/*  76: 87 */       EntityDamageSource entityDamageSource = (EntityDamageSource)damageSource;
/*  77: 88 */       if ((entityDamageSource.getEntity() instanceof EntityPlayer)) {
/*  78: 89 */         Bukkit.getPluginManager().callEvent(new PlayerInteractEntityEvent(((EntityPlayer)entityDamageSource.getEntity()).getBukkitEntity(), getBukkitEntity()));
/*  79:    */       }
/*  80:    */     }
/*  81: 92 */     return false;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public boolean isInvulnerable(DamageSource source)
/*  85:    */   {
/*  86:102 */     return true;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void setCustomName(String customName) {}
/*  90:    */   
/*  91:    */   public void setCustomNameVisible(boolean visible) {}
/*  92:    */   
/*  93:    */   public void makeSound(String sound, float volume, float pitch) {}
/*  94:    */   
/*  95:    */   public void setLockTick(boolean lock)
/*  96:    */   {
/*  97:122 */     this.lockTick = lock;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void die()
/* 101:    */   {
/* 102:127 */     setLockTick(false);
/* 103:128 */     super.die();
/* 104:    */   }
/* 105:    */   
/* 106:    */   public CraftEntity getBukkitEntity()
/* 107:    */   {
/* 108:133 */     if (this.bukkitEntity == null) {
/* 109:134 */       this.bukkitEntity = new CraftNMSSlime(this.world.getServer(), this);
/* 110:    */     }
/* 111:136 */     return this.bukkitEntity;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public boolean isDeadNMS()
/* 115:    */   {
/* 116:141 */     return this.dead;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void killEntityNMS()
/* 120:    */   {
/* 121:146 */     die();
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void setLocationNMS(double x, double y, double z)
/* 125:    */   {
/* 126:151 */     super.setPosition(x, y, z);
/* 127:    */   }
/* 128:    */   
/* 129:    */   public int getIdNMS()
/* 130:    */   {
/* 131:156 */     return getId();
/* 132:    */   }
/* 133:    */   
/* 134:    */   public CraftHologramLine getHologramLine()
/* 135:    */   {
/* 136:161 */     return this.parentPiece;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public org.bukkit.entity.Entity getBukkitEntityNMS()
/* 140:    */   {
/* 141:166 */     return getBukkitEntity();
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void setPassengerOfNMS(NMSEntityBase vehicleBase)
/* 145:    */   {
/* 146:171 */     if ((vehicleBase == null) || (!(vehicleBase instanceof net.minecraft.server.v1_8_R1.Entity))) {
/* 147:173 */       return;
/* 148:    */     }
/* 149:176 */     net.minecraft.server.v1_8_R1.Entity entity = (net.minecraft.server.v1_8_R1.Entity)vehicleBase;
/* 150:    */     try
/* 151:    */     {
/* 152:179 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_8_R1.Entity.class, this, "ap", Double.valueOf(0.0D));
/* 153:180 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_8_R1.Entity.class, this, "aq", Double.valueOf(0.0D));
/* 154:    */     }
/* 155:    */     catch (Exception ex)
/* 156:    */     {
/* 157:182 */       DebugHandler.handleDebugException(ex);
/* 158:    */     }
/* 159:185 */     if (this.vehicle != null) {
/* 160:186 */       this.vehicle.passenger = null;
/* 161:    */     }
/* 162:189 */     this.vehicle = entity;
/* 163:190 */     entity.passenger = this;
/* 164:    */   }
/* 165:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_8_R1.EntityNMSSlime
 * JD-Core Version:    0.7.0.1
 */