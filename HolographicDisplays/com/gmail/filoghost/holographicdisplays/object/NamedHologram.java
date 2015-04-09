/*  1:   */ package com.gmail.filoghost.holographicdisplays.object;
/*  2:   */ 
/*  3:   */ import org.bukkit.Location;
/*  4:   */ 
/*  5:   */ public class NamedHologram
/*  6:   */   extends CraftHologram
/*  7:   */ {
/*  8:   */   private final String name;
/*  9:   */   
/* 10:   */   public NamedHologram(Location source, String name)
/* 11:   */   {
/* 12:10 */     super(source);
/* 13:11 */     this.name = name;
/* 14:12 */     setAllowPlaceholders(true);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public String getName()
/* 18:   */   {
/* 19:16 */     return this.name;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void delete()
/* 23:   */   {
/* 24:21 */     super.delete();
/* 25:22 */     NamedHologramManager.removeHologram(this);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String toString()
/* 29:   */   {
/* 30:27 */     return "NamedHologram [name=" + this.name + ", super=" + super.toString() + "]";
/* 31:   */   }
/* 32:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.NamedHologram
 * JD-Core Version:    0.7.0.1
 */