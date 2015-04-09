/*  1:   */ package com.gmail.filoghost.holographicdisplays.nms.v1_7_R1;
/*  2:   */ 
/*  3:   */ import org.bukkit.EntityEffect;
/*  4:   */ import org.bukkit.Location;
/*  5:   */ import org.bukkit.craftbukkit.v1_7_R1.CraftServer;
/*  6:   */ import org.bukkit.craftbukkit.v1_7_R1.entity.CraftWitherSkull;
/*  7:   */ import org.bukkit.entity.Entity;
/*  8:   */ import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
/*  9:   */ import org.bukkit.util.Vector;
/* 10:   */ 
/* 11:   */ public class CraftNMSWitherSkull
/* 12:   */   extends CraftWitherSkull
/* 13:   */ {
/* 14:   */   public CraftNMSWitherSkull(CraftServer server, EntityNMSWitherSkull entity)
/* 15:   */   {
/* 16:14 */     super(server, entity);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void remove() {}
/* 20:   */   
/* 21:   */   public void setDirection(Vector dir) {}
/* 22:   */   
/* 23:   */   public void setBounce(boolean bounce) {}
/* 24:   */   
/* 25:   */   public void setYield(float yield) {}
/* 26:   */   
/* 27:   */   public void setIsIncendiary(boolean fire) {}
/* 28:   */   
/* 29:   */   public void setVelocity(Vector vel) {}
/* 30:   */   
/* 31:   */   public boolean teleport(Location loc)
/* 32:   */   {
/* 33:36 */     return false;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public boolean teleport(Entity entity)
/* 37:   */   {
/* 38:37 */     return false;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public boolean teleport(Location loc, PlayerTeleportEvent.TeleportCause cause)
/* 42:   */   {
/* 43:38 */     return false;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause cause)
/* 47:   */   {
/* 48:39 */     return false;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void setFireTicks(int ticks) {}
/* 52:   */   
/* 53:   */   public boolean setPassenger(Entity entity)
/* 54:   */   {
/* 55:41 */     return false;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public boolean eject()
/* 59:   */   {
/* 60:42 */     return false;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public boolean leaveVehicle()
/* 64:   */   {
/* 65:43 */     return false;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void playEffect(EntityEffect effect) {}
/* 69:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_7_R1.CraftNMSWitherSkull
 * JD-Core Version:    0.7.0.1
 */