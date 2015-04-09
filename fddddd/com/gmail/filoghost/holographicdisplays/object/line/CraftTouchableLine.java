/*  1:   */ package com.gmail.filoghost.holographicdisplays.object.line;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
/*  5:   */ import org.bukkit.World;
/*  6:   */ 
/*  7:   */ public abstract class CraftTouchableLine
/*  8:   */   extends CraftHologramLine
/*  9:   */ {
/* 10:   */   protected CraftTouchSlimeLine touchSlime;
/* 11:   */   private TouchHandler touchHandler;
/* 12:   */   
/* 13:   */   protected CraftTouchableLine(double height, CraftHologram parent)
/* 14:   */   {
/* 15:19 */     super(height, parent);
/* 16:   */   }
/* 17:   */   
/* 18:   */   protected void setTouchHandler(TouchHandler touchHandler, World world, double x, double y, double z)
/* 19:   */   {
/* 20:24 */     this.touchHandler = touchHandler;
/* 21:26 */     if ((touchHandler != null) && (this.touchSlime == null) && (world != null))
/* 22:   */     {
/* 23:28 */       this.touchSlime = new CraftTouchSlimeLine(getParent(), this);
/* 24:29 */       this.touchSlime.spawn(world, x, y + (getHeight() / 2.0D - this.touchSlime.getHeight() / 2.0D), z);
/* 25:   */     }
/* 26:31 */     else if ((touchHandler == null) && (this.touchSlime != null))
/* 27:   */     {
/* 28:33 */       this.touchSlime.despawn();
/* 29:34 */       this.touchSlime = null;
/* 30:   */     }
/* 31:   */   }
/* 32:   */   
/* 33:   */   public TouchHandler getTouchHandler()
/* 34:   */   {
/* 35:40 */     return this.touchHandler;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void spawn(World world, double x, double y, double z)
/* 39:   */   {
/* 40:46 */     super.spawn(world, x, y, z);
/* 41:48 */     if (this.touchHandler != null)
/* 42:   */     {
/* 43:49 */       this.touchSlime = new CraftTouchSlimeLine(getParent(), this);
/* 44:50 */       this.touchSlime.spawn(world, x, y + (getHeight() / 2.0D - this.touchSlime.getHeight() / 2.0D), z);
/* 45:   */     }
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void despawn()
/* 49:   */   {
/* 50:57 */     super.despawn();
/* 51:59 */     if (this.touchSlime != null)
/* 52:   */     {
/* 53:60 */       this.touchSlime.despawn();
/* 54:61 */       this.touchSlime = null;
/* 55:   */     }
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void teleport(double x, double y, double z)
/* 59:   */   {
/* 60:68 */     if (this.touchSlime != null) {
/* 61:69 */       this.touchSlime.teleport(x, y + (getHeight() / 2.0D - this.touchSlime.getHeight() / 2.0D), z);
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public CraftTouchSlimeLine getTouchSlime()
/* 66:   */   {
/* 67:75 */     return this.touchSlime;
/* 68:   */   }
/* 69:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.line.CraftTouchableLine
 * JD-Core Version:    0.7.0.1
 */