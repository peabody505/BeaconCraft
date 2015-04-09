/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_8_R2;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSArmorStand;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.util.ReflectionUtils;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*   7:    */ import net.minecraft.server.v1_8_R2.AxisAlignedBB;
/*   8:    */ import net.minecraft.server.v1_8_R2.DamageSource;
/*   9:    */ import net.minecraft.server.v1_8_R2.EntityArmorStand;
/*  10:    */ import net.minecraft.server.v1_8_R2.EntityHuman;
/*  11:    */ import net.minecraft.server.v1_8_R2.EntityPlayer;
/*  12:    */ import net.minecraft.server.v1_8_R2.ItemStack;
/*  13:    */ import net.minecraft.server.v1_8_R2.NBTTagCompound;
/*  14:    */ import net.minecraft.server.v1_8_R2.PacketPlayOutEntityTeleport;
/*  15:    */ import net.minecraft.server.v1_8_R2.PlayerConnection;
/*  16:    */ import net.minecraft.server.v1_8_R2.Vec3D;
/*  17:    */ import net.minecraft.server.v1_8_R2.World;
/*  18:    */ import org.bukkit.craftbukkit.v1_8_R2.entity.CraftEntity;
/*  19:    */ import org.bukkit.entity.Entity;
/*  20:    */ 
/*  21:    */ public class EntityNMSArmorStand
/*  22:    */   extends EntityArmorStand
/*  23:    */   implements NMSArmorStand
/*  24:    */ {
/*  25:    */   private boolean lockTick;
/*  26:    */   private CraftHologramLine parentPiece;
/*  27:    */   
/*  28:    */   public EntityNMSArmorStand(World world, CraftHologramLine parentPiece)
/*  29:    */   {
/*  30: 27 */     super(world);
/*  31: 28 */     setInvisible(true);
/*  32: 29 */     setSmall(true);
/*  33: 30 */     setArms(false);
/*  34: 31 */     setGravity(true);
/*  35: 32 */     setBasePlate(true);
/*  36: 33 */     this.parentPiece = parentPiece;
/*  37:    */     try
/*  38:    */     {
/*  39: 35 */       ReflectionUtils.setPrivateField(EntityArmorStand.class, this, "bi", Integer.valueOf(2147483647));
/*  40:    */     }
/*  41:    */     catch (Exception localException) {}
/*  42: 39 */     forceSetBoundingBox(new NullBoundingBox());
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void b(NBTTagCompound nbttagcompound) {}
/*  46:    */   
/*  47:    */   public boolean c(NBTTagCompound nbttagcompound)
/*  48:    */   {
/*  49: 51 */     return false;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public boolean d(NBTTagCompound nbttagcompound)
/*  53:    */   {
/*  54: 57 */     return false;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void e(NBTTagCompound nbttagcompound) {}
/*  58:    */   
/*  59:    */   public boolean isInvulnerable(DamageSource source)
/*  60:    */   {
/*  61: 73 */     return true;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void setCustomName(String customName) {}
/*  65:    */   
/*  66:    */   public void setCustomNameVisible(boolean visible) {}
/*  67:    */   
/*  68:    */   public boolean a(EntityHuman human, Vec3D vec3d)
/*  69:    */   {
/*  70: 89 */     return true;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public boolean d(int i, ItemStack item)
/*  74:    */   {
/*  75: 95 */     return false;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void setEquipment(int i, ItemStack item) {}
/*  79:    */   
/*  80:    */   public void a(AxisAlignedBB boundingBox) {}
/*  81:    */   
/*  82:    */   public void forceSetBoundingBox(AxisAlignedBB boundingBox)
/*  83:    */   {
/*  84:109 */     super.a(boundingBox);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public int getId()
/*  88:    */   {
/*  89:115 */     StackTraceElement[] elements = Thread.currentThread().getStackTrace();
/*  90:116 */     if ((elements.length > 2) && (elements[2] != null) && (elements[2].getFileName().equals("EntityTrackerEntry.java")) && (elements[2].getLineNumber() > 137) && (elements[2].getLineNumber() < 147)) {
/*  91:118 */       return -1;
/*  92:    */     }
/*  93:121 */     return super.getId();
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void t_()
/*  97:    */   {
/*  98:126 */     if (!this.lockTick) {
/*  99:127 */       super.t_();
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void makeSound(String sound, float f1, float f2) {}
/* 104:    */   
/* 105:    */   public void setCustomNameNMS(String name)
/* 106:    */   {
/* 107:138 */     if ((name != null) && (name.length() > 300)) {
/* 108:139 */       name = name.substring(0, 300);
/* 109:    */     }
/* 110:141 */     super.setCustomName(name);
/* 111:142 */     super.setCustomNameVisible((name != null) && (!name.isEmpty()));
/* 112:    */   }
/* 113:    */   
/* 114:    */   public String getCustomNameNMS()
/* 115:    */   {
/* 116:147 */     return super.getCustomName();
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void callSuperTick()
/* 120:    */   {
/* 121:152 */     super.h();
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void setLockTick(boolean lock)
/* 125:    */   {
/* 126:157 */     this.lockTick = lock;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public void die()
/* 130:    */   {
/* 131:162 */     setLockTick(false);
/* 132:163 */     super.die();
/* 133:    */   }
/* 134:    */   
/* 135:    */   public CraftEntity getBukkitEntity()
/* 136:    */   {
/* 137:168 */     if (this.bukkitEntity == null) {
/* 138:169 */       this.bukkitEntity = new CraftNMSArmorStand(this.world.getServer(), this);
/* 139:    */     }
/* 140:171 */     return this.bukkitEntity;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void killEntityNMS()
/* 144:    */   {
/* 145:176 */     die();
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void setLocationNMS(double x, double y, double z)
/* 149:    */   {
/* 150:181 */     super.setPosition(x, y, z);
/* 151:    */     
/* 152:    */ 
/* 153:    */ 
/* 154:185 */     PacketPlayOutEntityTeleport teleportPacket = new PacketPlayOutEntityTeleport(this);
/* 155:187 */     for (Object obj : this.world.players) {
/* 156:188 */       if ((obj instanceof EntityPlayer))
/* 157:    */       {
/* 158:189 */         EntityPlayer nmsPlayer = (EntityPlayer)obj;
/* 159:    */         
/* 160:191 */         double distanceSquared = Utils.square(nmsPlayer.locX - this.locX) + Utils.square(nmsPlayer.locZ - this.locZ);
/* 161:192 */         if ((distanceSquared < 8192.0D) && (nmsPlayer.playerConnection != null)) {
/* 162:193 */           nmsPlayer.playerConnection.sendPacket(teleportPacket);
/* 163:    */         }
/* 164:    */       }
/* 165:    */     }
/* 166:    */   }
/* 167:    */   
/* 168:    */   public boolean isDeadNMS()
/* 169:    */   {
/* 170:201 */     return this.dead;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public int getIdNMS()
/* 174:    */   {
/* 175:206 */     return getId();
/* 176:    */   }
/* 177:    */   
/* 178:    */   public CraftHologramLine getHologramLine()
/* 179:    */   {
/* 180:211 */     return this.parentPiece;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public Entity getBukkitEntityNMS()
/* 184:    */   {
/* 185:216 */     return getBukkitEntity();
/* 186:    */   }
/* 187:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_8_R2.EntityNMSArmorStand
 * JD-Core Version:    0.7.0.1
 */