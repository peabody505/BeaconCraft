/*  1:   */ package com.gmail.filoghost.holographicdisplays.object;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.api.Hologram;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.Collections;
/*  7:   */ import java.util.List;
/*  8:   */ import java.util.Set;
/*  9:   */ import org.bukkit.Chunk;
/* 10:   */ import org.bukkit.plugin.Plugin;
/* 11:   */ 
/* 12:   */ public class PluginHologramManager
/* 13:   */ {
/* 14:19 */   private static List<PluginHologram> pluginHolograms = ;
/* 15:   */   
/* 16:   */   public static void addHologram(PluginHologram hologram)
/* 17:   */   {
/* 18:22 */     pluginHolograms.add(hologram);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public static void removeHologram(PluginHologram hologram)
/* 22:   */   {
/* 23:26 */     pluginHolograms.remove(hologram);
/* 24:27 */     if (!hologram.isDeleted()) {
/* 25:28 */       hologram.delete();
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static List<PluginHologram> getHolograms()
/* 30:   */   {
/* 31:33 */     return new ArrayList(pluginHolograms);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public static Set<Hologram> getHolograms(Plugin plugin)
/* 35:   */   {
/* 36:37 */     Set<Hologram> ownedHolograms = Utils.newSet();
/* 37:39 */     for (PluginHologram hologram : pluginHolograms) {
/* 38:40 */       if (hologram.getOwner().equals(plugin)) {
/* 39:41 */         ownedHolograms.add(hologram);
/* 40:   */       }
/* 41:   */     }
/* 42:45 */     return Collections.unmodifiableSet(ownedHolograms);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public static void onChunkLoad(Chunk chunk)
/* 46:   */   {
/* 47:50 */     for (PluginHologram hologram : pluginHolograms) {
/* 48:51 */       if (hologram.isInChunk(chunk)) {
/* 49:52 */         hologram.spawnEntities();
/* 50:   */       }
/* 51:   */     }
/* 52:   */   }
/* 53:   */   
/* 54:   */   public static void onChunkUnload(Chunk chunk)
/* 55:   */   {
/* 56:59 */     for (PluginHologram hologram : pluginHolograms) {
/* 57:60 */       if (hologram.isInChunk(chunk)) {
/* 58:61 */         hologram.despawnEntities();
/* 59:   */       }
/* 60:   */     }
/* 61:   */   }
/* 62:   */   
/* 63:   */   public static void clearAll()
/* 64:   */   {
/* 65:67 */     List<PluginHologram> oldHolograms = new ArrayList(pluginHolograms);
/* 66:68 */     pluginHolograms.clear();
/* 67:70 */     for (PluginHologram hologram : oldHolograms) {
/* 68:71 */       hologram.delete();
/* 69:   */     }
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.PluginHologramManager
 * JD-Core Version:    0.7.0.1
 */