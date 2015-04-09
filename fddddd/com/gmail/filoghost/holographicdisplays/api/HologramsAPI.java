/*  1:   */ package com.gmail.filoghost.holographicdisplays.api;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.object.BackendAPI;
/*  5:   */ import java.util.Collection;
/*  6:   */ import org.bukkit.Location;
/*  7:   */ import org.bukkit.entity.Entity;
/*  8:   */ import org.bukkit.plugin.Plugin;
/*  9:   */ 
/* 10:   */ public class HologramsAPI
/* 11:   */ {
/* 12:   */   public static Hologram createHologram(Plugin plugin, Location source)
/* 13:   */   {
/* 14:27 */     return BackendAPI.createHologram(plugin, source);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static Collection<Hologram> getHolograms(Plugin plugin)
/* 18:   */   {
/* 19:39 */     return BackendAPI.getHolograms(plugin);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static boolean registerPlaceholder(Plugin plugin, String textPlaceholder, double refreshRate, PlaceholderReplacer replacer)
/* 23:   */   {
/* 24:54 */     return BackendAPI.registerPlaceholder(plugin, textPlaceholder, refreshRate, replacer);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public static Collection<String> getRegisteredPlaceholders(Plugin plugin)
/* 28:   */   {
/* 29:65 */     return BackendAPI.getRegisteredPlaceholders(plugin);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public static boolean unregisterPlaceholder(Plugin plugin, String textPlaceholder)
/* 33:   */   {
/* 34:77 */     return BackendAPI.unregisterPlaceholder(plugin, textPlaceholder);
/* 35:   */   }
/* 36:   */   
/* 37:   */   public static void unregisterPlaceholders(Plugin plugin)
/* 38:   */   {
/* 39:88 */     BackendAPI.unregisterPlaceholders(plugin);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public static boolean isHologramEntity(Entity bukkitEntity)
/* 43:   */   {
/* 44:99 */     return BackendAPI.isHologramEntity(bukkitEntity);
/* 45:   */   }
/* 46:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.api.HologramsAPI
 * JD-Core Version:    0.7.0.1
 */