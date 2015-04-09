/*  1:   */ package com.gmail.filoghost.holographicdisplays.object;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.api.Hologram;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.placeholder.Placeholder;
/*  8:   */ import com.gmail.filoghost.holographicdisplays.placeholder.PlaceholdersRegister;
/*  9:   */ import com.gmail.filoghost.holographicdisplays.util.Validator;
/* 10:   */ import java.util.Collection;
/* 11:   */ import org.bukkit.Bukkit;
/* 12:   */ import org.bukkit.Location;
/* 13:   */ import org.bukkit.entity.Entity;
/* 14:   */ import org.bukkit.plugin.Plugin;
/* 15:   */ 
/* 16:   */ public class BackendAPI
/* 17:   */ {
/* 18:   */   public static Hologram createHologram(Plugin plugin, Location source)
/* 19:   */   {
/* 20:22 */     Validator.notNull(plugin, "plugin");
/* 21:23 */     Validator.notNull(source, "source");
/* 22:24 */     Validator.notNull(source.getWorld(), "source's world");
/* 23:25 */     Validator.isTrue(Bukkit.isPrimaryThread(), "Async hologram creation");
/* 24:   */     
/* 25:27 */     PluginHologram hologram = new PluginHologram(source, plugin);
/* 26:28 */     PluginHologramManager.addHologram(hologram);
/* 27:   */     
/* 28:30 */     return hologram;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public static boolean registerPlaceholder(Plugin plugin, String textPlaceholder, double refreshRate, PlaceholderReplacer replacer)
/* 32:   */   {
/* 33:34 */     Validator.notNull(textPlaceholder, "textPlaceholder");
/* 34:35 */     Validator.isTrue(refreshRate >= 0.0D, "refreshRate should be positive");
/* 35:36 */     Validator.notNull(replacer, "replacer");
/* 36:   */     
/* 37:38 */     return PlaceholdersRegister.register(new Placeholder(plugin, textPlaceholder, refreshRate, replacer));
/* 38:   */   }
/* 39:   */   
/* 40:   */   public static boolean isHologramEntity(Entity bukkitEntity)
/* 41:   */   {
/* 42:42 */     Validator.notNull(bukkitEntity, "bukkitEntity");
/* 43:43 */     return HolographicDisplays.getNMSManager().isNMSEntityBase(bukkitEntity);
/* 44:   */   }
/* 45:   */   
/* 46:   */   public static Collection<Hologram> getHolograms(Plugin plugin)
/* 47:   */   {
/* 48:47 */     Validator.notNull(plugin, "plugin");
/* 49:48 */     return PluginHologramManager.getHolograms(plugin);
/* 50:   */   }
/* 51:   */   
/* 52:   */   public static Collection<String> getRegisteredPlaceholders(Plugin plugin)
/* 53:   */   {
/* 54:52 */     Validator.notNull(plugin, "plugin");
/* 55:53 */     return PlaceholdersRegister.getTextPlaceholdersByPlugin(plugin);
/* 56:   */   }
/* 57:   */   
/* 58:   */   public static boolean unregisterPlaceholder(Plugin plugin, String textPlaceholder)
/* 59:   */   {
/* 60:57 */     Validator.notNull(plugin, "plugin");
/* 61:58 */     Validator.notNull(textPlaceholder, "textPlaceholder");
/* 62:59 */     return PlaceholdersRegister.unregister(plugin, textPlaceholder);
/* 63:   */   }
/* 64:   */   
/* 65:   */   public static void unregisterPlaceholders(Plugin plugin)
/* 66:   */   {
/* 67:63 */     Validator.notNull(plugin, "plugin");
/* 68:64 */     for (String placeholder : getRegisteredPlaceholders(plugin)) {
/* 69:65 */       unregisterPlaceholder(plugin, placeholder);
/* 70:   */     }
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.BackendAPI
 * JD-Core Version:    0.7.0.1
 */