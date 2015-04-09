/*  1:   */ package com.gmail.filoghost.holographicdisplays.object.line;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.api.line.HologramLine;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.util.Validator;
/*  6:   */ import org.bukkit.World;
/*  7:   */ 
/*  8:   */ public abstract class CraftHologramLine
/*  9:   */   implements HologramLine
/* 10:   */ {
/* 11:   */   private final double height;
/* 12:   */   private final CraftHologram parent;
/* 13:   */   private boolean isSpawned;
/* 14:   */   
/* 15:   */   protected CraftHologramLine(double height, CraftHologram parent)
/* 16:   */   {
/* 17:18 */     Validator.notNull(parent, "parent hologram");
/* 18:19 */     this.height = height;
/* 19:20 */     this.parent = parent;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public final double getHeight()
/* 23:   */   {
/* 24:24 */     return this.height;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public final CraftHologram getParent()
/* 28:   */   {
/* 29:29 */     return this.parent;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void removeLine()
/* 33:   */   {
/* 34:33 */     this.parent.removeLine(this);
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void spawn(World world, double x, double y, double z)
/* 38:   */   {
/* 39:37 */     Validator.notNull(world, "world");
/* 40:   */     
/* 41:   */ 
/* 42:40 */     despawn();
/* 43:41 */     this.isSpawned = true;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public void despawn()
/* 47:   */   {
/* 48:47 */     this.isSpawned = false;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public final boolean isSpawned()
/* 52:   */   {
/* 53:51 */     return this.isSpawned;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public abstract int[] getEntitiesIDs();
/* 57:   */   
/* 58:   */   public abstract void teleport(double paramDouble1, double paramDouble2, double paramDouble3);
/* 59:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine
 * JD-Core Version:    0.7.0.1
 */