/*  1:   */ package com.gmail.filoghost.holographicdisplays.object;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import java.util.List;
/*  6:   */ import org.bukkit.Chunk;
/*  7:   */ 
/*  8:   */ public class NamedHologramManager
/*  9:   */ {
/* 10:15 */   private static List<NamedHologram> pluginHolograms = ;
/* 11:   */   
/* 12:   */   public static void addHologram(NamedHologram hologram)
/* 13:   */   {
/* 14:18 */     pluginHolograms.add(hologram);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static void removeHologram(NamedHologram hologram)
/* 18:   */   {
/* 19:22 */     pluginHolograms.remove(hologram);
/* 20:23 */     if (!hologram.isDeleted()) {
/* 21:24 */       hologram.delete();
/* 22:   */     }
/* 23:   */   }
/* 24:   */   
/* 25:   */   public static List<NamedHologram> getHolograms()
/* 26:   */   {
/* 27:29 */     return new ArrayList(pluginHolograms);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public static NamedHologram getHologram(String name)
/* 31:   */   {
/* 32:33 */     for (NamedHologram hologram : pluginHolograms) {
/* 33:34 */       if (hologram.getName().equals(name)) {
/* 34:35 */         return hologram;
/* 35:   */       }
/* 36:   */     }
/* 37:38 */     return null;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public static boolean isExistingHologram(String name)
/* 41:   */   {
/* 42:42 */     return getHologram(name) != null;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public static void onChunkLoad(Chunk chunk)
/* 46:   */   {
/* 47:47 */     for (NamedHologram hologram : pluginHolograms) {
/* 48:48 */       if (hologram.isInChunk(chunk)) {
/* 49:49 */         hologram.spawnEntities();
/* 50:   */       }
/* 51:   */     }
/* 52:   */   }
/* 53:   */   
/* 54:   */   public static void onChunkUnload(Chunk chunk)
/* 55:   */   {
/* 56:56 */     for (NamedHologram hologram : pluginHolograms) {
/* 57:57 */       if (hologram.isInChunk(chunk)) {
/* 58:58 */         hologram.despawnEntities();
/* 59:   */       }
/* 60:   */     }
/* 61:   */   }
/* 62:   */   
/* 63:   */   public static void clearAll()
/* 64:   */   {
/* 65:64 */     List<NamedHologram> oldHolograms = new ArrayList(pluginHolograms);
/* 66:65 */     pluginHolograms.clear();
/* 67:67 */     for (NamedHologram hologram : oldHolograms) {
/* 68:68 */       hologram.delete();
/* 69:   */     }
/* 70:   */   }
/* 71:   */   
/* 72:   */   public static int size()
/* 73:   */   {
/* 74:73 */     return pluginHolograms.size();
/* 75:   */   }
/* 76:   */   
/* 77:   */   public static NamedHologram get(int i)
/* 78:   */   {
/* 79:77 */     return (NamedHologram)pluginHolograms.get(i);
/* 80:   */   }
/* 81:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.NamedHologramManager
 * JD-Core Version:    0.7.0.1
 */