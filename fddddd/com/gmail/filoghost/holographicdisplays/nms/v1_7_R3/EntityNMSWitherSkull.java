/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_7_R3;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSWitherSkull;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*   6:    */ import net.minecraft.server.v1_7_R3.EntityPlayer;
/*   7:    */ import net.minecraft.server.v1_7_R3.EntityWitherSkull;
/*   8:    */ import net.minecraft.server.v1_7_R3.NBTTagCompound;
/*   9:    */ import net.minecraft.server.v1_7_R3.PacketPlayOutEntityTeleport;
/*  10:    */ import net.minecraft.server.v1_7_R3.PlayerConnection;
/*  11:    */ import net.minecraft.server.v1_7_R3.World;
/*  12:    */ import org.bukkit.craftbukkit.v1_7_R3.entity.CraftEntity;
/*  13:    */ import org.bukkit.entity.Entity;
/*  14:    */ 
/*  15:    */ public class EntityNMSWitherSkull
/*  16:    */   extends EntityWitherSkull
/*  17:    */   implements NMSWitherSkull
/*  18:    */ {
/*  19:    */   private boolean lockTick;
/*  20:    */   private CraftHologramLine parentPiece;
/*  21:    */   
/*  22:    */   public EntityNMSWitherSkull(World world, CraftHologramLine parentPiece)
/*  23:    */   {
/*  24: 21 */     super(world);
/*  25: 22 */     this.motX = 0.0D;
/*  26: 23 */     this.motY = 0.0D;
/*  27: 24 */     this.motZ = 0.0D;
/*  28: 25 */     this.dirX = 0.0D;
/*  29: 26 */     this.dirY = 0.0D;
/*  30: 27 */     this.dirZ = 0.0D;
/*  31: 28 */     this.boundingBox.a = 0.0D;
/*  32: 29 */     this.boundingBox.b = 0.0D;
/*  33: 30 */     this.boundingBox.c = 0.0D;
/*  34: 31 */     this.boundingBox.d = 0.0D;
/*  35: 32 */     this.boundingBox.e = 0.0D;
/*  36: 33 */     this.boundingBox.f = 0.0D;
/*  37: 34 */     a(0.0F, 0.0F);
/*  38: 35 */     this.parentPiece = parentPiece;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public void b(NBTTagCompound nbttagcompound) {}
/*  42:    */   
/*  43:    */   public boolean c(NBTTagCompound nbttagcompound)
/*  44:    */   {
/*  45: 46 */     return false;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public boolean d(NBTTagCompound nbttagcompound)
/*  49:    */   {
/*  50: 52 */     return false;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void e(NBTTagCompound nbttagcompound) {}
/*  54:    */   
/*  55:    */   public boolean isInvulnerable()
/*  56:    */   {
/*  57: 67 */     return true;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public int getId()
/*  61:    */   {
/*  62: 73 */     StackTraceElement[] elements = Thread.currentThread().getStackTrace();
/*  63: 74 */     if ((elements.length > 2) && (elements[2] != null) && (elements[2].getFileName().equals("EntityTrackerEntry.java")) && (elements[2].getLineNumber() > 134) && (elements[2].getLineNumber() < 144)) {
/*  64: 76 */       return -1;
/*  65:    */     }
/*  66: 79 */     return super.getId();
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void h()
/*  70:    */   {
/*  71: 84 */     if (!this.lockTick) {
/*  72: 85 */       super.h();
/*  73:    */     }
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void makeSound(String sound, float f1, float f2) {}
/*  77:    */   
/*  78:    */   public void setLockTick(boolean lock)
/*  79:    */   {
/*  80: 96 */     this.lockTick = lock;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void die()
/*  84:    */   {
/*  85:101 */     setLockTick(false);
/*  86:102 */     super.die();
/*  87:    */   }
/*  88:    */   
/*  89:    */   public CraftEntity getBukkitEntity()
/*  90:    */   {
/*  91:107 */     if (this.bukkitEntity == null) {
/*  92:108 */       this.bukkitEntity = new CraftNMSWitherSkull(this.world.getServer(), this);
/*  93:    */     }
/*  94:110 */     return this.bukkitEntity;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void killEntityNMS()
/*  98:    */   {
/*  99:115 */     die();
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void setLocationNMS(double x, double y, double z)
/* 103:    */   {
/* 104:120 */     super.setPosition(x, y, z);
/* 105:    */     
/* 106:    */ 
/* 107:123 */     PacketPlayOutEntityTeleport teleportPacket = new PacketPlayOutEntityTeleport(this);
/* 108:125 */     for (Object obj : this.world.players) {
/* 109:126 */       if ((obj instanceof EntityPlayer))
/* 110:    */       {
/* 111:127 */         EntityPlayer nmsPlayer = (EntityPlayer)obj;
/* 112:    */         
/* 113:129 */         double distanceSquared = Utils.square(nmsPlayer.locX - this.locX) + Utils.square(nmsPlayer.locZ - this.locZ);
/* 114:130 */         if ((distanceSquared < 8192.0D) && (nmsPlayer.playerConnection != null)) {
/* 115:131 */           nmsPlayer.playerConnection.sendPacket(teleportPacket);
/* 116:    */         }
/* 117:    */       }
/* 118:    */     }
/* 119:    */   }
/* 120:    */   
/* 121:    */   public boolean isDeadNMS()
/* 122:    */   {
/* 123:139 */     return this.dead;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public int getIdNMS()
/* 127:    */   {
/* 128:144 */     return getId();
/* 129:    */   }
/* 130:    */   
/* 131:    */   public CraftHologramLine getHologramLine()
/* 132:    */   {
/* 133:149 */     return this.parentPiece;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public Entity getBukkitEntityNMS()
/* 137:    */   {
/* 138:154 */     return getBukkitEntity();
/* 139:    */   }
/* 140:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_7_R3.EntityNMSWitherSkull
 * JD-Core Version:    0.7.0.1
 */