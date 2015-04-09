/*  1:   */ package com.gmail.filoghost.holographicdisplays.nms.v1_6_R3;
/*  2:   */ 
/*  3:   */ import java.util.Collection;
/*  4:   */ import org.bukkit.EntityEffect;
/*  5:   */ import org.bukkit.Location;
/*  6:   */ import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
/*  7:   */ import org.bukkit.craftbukkit.v1_6_R3.entity.CraftSlime;
/*  8:   */ import org.bukkit.entity.Entity;
/*  9:   */ import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
/* 10:   */ import org.bukkit.potion.PotionEffect;
/* 11:   */ import org.bukkit.util.Vector;
/* 12:   */ 
/* 13:   */ public class CraftNMSSlime
/* 14:   */   extends CraftSlime
/* 15:   */ {
/* 16:   */   public CraftNMSSlime(CraftServer server, EntityNMSSlime entity)
/* 17:   */   {
/* 18:17 */     super(server, entity);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void remove() {}
/* 22:   */   
/* 23:   */   public boolean addPotionEffect(PotionEffect effect)
/* 24:   */   {
/* 25:28 */     return false;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public boolean addPotionEffect(PotionEffect effect, boolean param)
/* 29:   */   {
/* 30:29 */     return false;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public boolean addPotionEffects(Collection<PotionEffect> effects)
/* 34:   */   {
/* 35:30 */     return false;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void setRemoveWhenFarAway(boolean remove) {}
/* 39:   */   
/* 40:   */   public void setVelocity(Vector vel) {}
/* 41:   */   
/* 42:   */   public boolean teleport(Location loc)
/* 43:   */   {
/* 44:35 */     return false;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public boolean teleport(Entity entity)
/* 48:   */   {
/* 49:36 */     return false;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public boolean teleport(Location loc, PlayerTeleportEvent.TeleportCause cause)
/* 53:   */   {
/* 54:37 */     return false;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause cause)
/* 58:   */   {
/* 59:38 */     return false;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void setFireTicks(int ticks) {}
/* 63:   */   
/* 64:   */   public boolean setPassenger(Entity entity)
/* 65:   */   {
/* 66:40 */     return false;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public boolean eject()
/* 70:   */   {
/* 71:41 */     return false;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public boolean leaveVehicle()
/* 75:   */   {
/* 76:42 */     return false;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public void playEffect(EntityEffect effect) {}
/* 80:   */   
/* 81:   */   public void setSize(int size) {}
/* 82:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_6_R3.CraftNMSSlime
 * JD-Core Version:    0.7.0.1
 */