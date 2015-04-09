/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_6_R3;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSWitherSkull;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*   6:    */ import net.minecraft.server.v1_6_R3.EntityPlayer;
/*   7:    */ import net.minecraft.server.v1_6_R3.EntityWitherSkull;
/*   8:    */ import net.minecraft.server.v1_6_R3.NBTTagCompound;
/*   9:    */ import net.minecraft.server.v1_6_R3.Packet34EntityTeleport;
/*  10:    */ import net.minecraft.server.v1_6_R3.PlayerConnection;
/*  11:    */ import net.minecraft.server.v1_6_R3.World;
/*  12:    */ import org.bukkit.craftbukkit.v1_6_R3.entity.CraftEntity;
/*  13:    */ import org.bukkit.entity.Entity;
/*  14:    */ 
/*  15:    */ public class EntityNMSWitherSkull
/*  16:    */   extends EntityWitherSkull
/*  17:    */   implements NMSWitherSkull
/*  18:    */ {
/*  19:    */   private boolean lockTick;
/*  20:    */   private CraftHologramLine parentPiece;
/*  21:    */   private int teleportedRecently;
/*  22:    */   
/*  23:    */   public EntityNMSWitherSkull(World world, CraftHologramLine parentPiece)
/*  24:    */   {
/*  25: 23 */     super(world);
/*  26: 24 */     this.motX = 0.0D;
/*  27: 25 */     this.motY = 0.0D;
/*  28: 26 */     this.motZ = 0.0D;
/*  29: 27 */     this.dirX = 0.0D;
/*  30: 28 */     this.dirY = 0.0D;
/*  31: 29 */     this.dirZ = 0.0D;
/*  32: 30 */     this.boundingBox.a = 0.0D;
/*  33: 31 */     this.boundingBox.b = 0.0D;
/*  34: 32 */     this.boundingBox.c = 0.0D;
/*  35: 33 */     this.boundingBox.d = 0.0D;
/*  36: 34 */     this.boundingBox.e = 0.0D;
/*  37: 35 */     this.boundingBox.f = 0.0D;
/*  38: 36 */     a(0.0F, 0.0F);
/*  39: 37 */     this.parentPiece = parentPiece;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void b(NBTTagCompound nbttagcompound) {}
/*  43:    */   
/*  44:    */   public boolean c(NBTTagCompound nbttagcompound)
/*  45:    */   {
/*  46: 48 */     return false;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public boolean d(NBTTagCompound nbttagcompound)
/*  50:    */   {
/*  51: 54 */     return false;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void e(NBTTagCompound nbttagcompound) {}
/*  55:    */   
/*  56:    */   public boolean isInvulnerable()
/*  57:    */   {
/*  58: 69 */     return true;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void l_()
/*  62:    */   {
/*  63: 75 */     if ((this.teleportedRecently != -1) && 
/*  64: 76 */       (this.teleportedRecently++ > 10))
/*  65:    */     {
/*  66: 77 */       this.teleportedRecently = -1;
/*  67: 78 */       Packet34EntityTeleport teleportPacket = new Packet34EntityTeleport(this);
/*  68: 80 */       for (Object obj : this.world.players) {
/*  69: 81 */         if ((obj instanceof EntityPlayer))
/*  70:    */         {
/*  71: 82 */           EntityPlayer nmsPlayer = (EntityPlayer)obj;
/*  72:    */           
/*  73: 84 */           double distanceSquared = Utils.square(nmsPlayer.locX - this.locX) + Utils.square(nmsPlayer.locZ - this.locZ);
/*  74: 85 */           if ((distanceSquared < 8192.0D) && (nmsPlayer.playerConnection != null)) {
/*  75: 86 */             nmsPlayer.playerConnection.sendPacket(teleportPacket);
/*  76:    */           }
/*  77:    */         }
/*  78:    */       }
/*  79:    */     }
/*  80: 93 */     if (!this.lockTick) {
/*  81: 94 */       super.l_();
/*  82:    */     }
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void makeSound(String sound, float f1, float f2) {}
/*  86:    */   
/*  87:    */   public void setLockTick(boolean lock)
/*  88:    */   {
/*  89:105 */     this.lockTick = lock;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void die()
/*  93:    */   {
/*  94:110 */     setLockTick(false);
/*  95:111 */     super.die();
/*  96:    */   }
/*  97:    */   
/*  98:    */   public CraftEntity getBukkitEntity()
/*  99:    */   {
/* 100:116 */     if (this.bukkitEntity == null) {
/* 101:117 */       this.bukkitEntity = new CraftNMSWitherSkull(this.world.getServer(), this);
/* 102:    */     }
/* 103:119 */     return this.bukkitEntity;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public void killEntityNMS()
/* 107:    */   {
/* 108:124 */     die();
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void setLocationNMS(double x, double y, double z)
/* 112:    */   {
/* 113:129 */     super.setPosition(x, y, z);
/* 114:    */     
/* 115:131 */     this.teleportedRecently = 0;
/* 116:    */     
/* 117:    */ 
/* 118:134 */     Packet34EntityTeleport teleportPacket = new Packet34EntityTeleport(this);
/* 119:136 */     for (Object obj : this.world.players) {
/* 120:137 */       if ((obj instanceof EntityPlayer))
/* 121:    */       {
/* 122:138 */         EntityPlayer nmsPlayer = (EntityPlayer)obj;
/* 123:    */         
/* 124:140 */         double distanceSquared = Utils.square(nmsPlayer.locX - this.locX) + Utils.square(nmsPlayer.locZ - this.locZ);
/* 125:141 */         if ((distanceSquared < 8192.0D) && (nmsPlayer.playerConnection != null)) {
/* 126:142 */           nmsPlayer.playerConnection.sendPacket(teleportPacket);
/* 127:    */         }
/* 128:    */       }
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   public boolean isDeadNMS()
/* 133:    */   {
/* 134:150 */     return this.dead;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public int getIdNMS()
/* 138:    */   {
/* 139:155 */     return this.id;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public CraftHologramLine getHologramLine()
/* 143:    */   {
/* 144:160 */     return this.parentPiece;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public Entity getBukkitEntityNMS()
/* 148:    */   {
/* 149:165 */     return getBukkitEntity();
/* 150:    */   }
/* 151:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_6_R3.EntityNMSWitherSkull
 * JD-Core Version:    0.7.0.1
 */