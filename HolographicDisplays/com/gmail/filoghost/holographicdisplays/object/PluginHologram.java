/*  1:   */ package com.gmail.filoghost.holographicdisplays.object;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.util.Validator;
/*  4:   */ import org.bukkit.Location;
/*  5:   */ import org.bukkit.plugin.Plugin;
/*  6:   */ 
/*  7:   */ public class PluginHologram
/*  8:   */   extends CraftHologram
/*  9:   */ {
/* 10:   */   private Plugin plugin;
/* 11:   */   
/* 12:   */   public PluginHologram(Location source, Plugin plugin)
/* 13:   */   {
/* 14:16 */     super(source);
/* 15:17 */     Validator.notNull(plugin, "plugin");
/* 16:18 */     this.plugin = plugin;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public Plugin getOwner()
/* 20:   */   {
/* 21:22 */     return this.plugin;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void delete()
/* 25:   */   {
/* 26:27 */     super.delete();
/* 27:28 */     PluginHologramManager.removeHologram(this);
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.PluginHologram
 * JD-Core Version:    0.7.0.1
 */