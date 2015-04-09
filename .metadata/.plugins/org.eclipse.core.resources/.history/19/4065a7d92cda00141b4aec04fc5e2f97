/*  1:   */ package com.gmail.filoghost.holographicdisplays.util;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*  4:   */ import java.util.regex.Matcher;
/*  5:   */ import java.util.regex.Pattern;
/*  6:   */ import org.bukkit.Bukkit;
/*  7:   */ import org.bukkit.entity.EntityType;
/*  8:   */ 
/*  9:   */ public class VersionUtils
/* 10:   */ {
/* 11:   */   public static String getBukkitVersion()
/* 12:   */   {
/* 13:19 */     Matcher matcher = Pattern.compile("v\\d+_\\d+_R\\d+").matcher(Bukkit.getServer().getClass().getPackage().getName());
/* 14:20 */     if (matcher.find()) {
/* 15:21 */       return matcher.group();
/* 16:   */     }
/* 17:23 */     return null;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public static String getMinecraftVersion()
/* 21:   */   {
/* 22:33 */     Matcher matcher = Pattern.compile("(\\(MC: )([\\d\\.]+)(\\))").matcher(Bukkit.getVersion());
/* 23:34 */     if (matcher.find()) {
/* 24:35 */       return matcher.group(2);
/* 25:   */     }
/* 26:37 */     return null;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static boolean isMCPCOrCauldron()
/* 30:   */   {
/* 31:46 */     return (Utils.containsIgnoreCase(Bukkit.getName(), "MCPC")) || (Utils.containsIgnoreCase(Bukkit.getName(), "Cauldron"));
/* 32:   */   }
/* 33:   */   
/* 34:   */   public static boolean isArmorstand(EntityType type)
/* 35:   */   {
/* 36:50 */     if (!HolographicDisplays.is1_8()) {
/* 37:51 */       return false;
/* 38:   */     }
/* 39:54 */     return type == EntityType.ARMOR_STAND;
/* 40:   */   }
/* 41:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.util.VersionUtils
 * JD-Core Version:    0.7.0.1
 */