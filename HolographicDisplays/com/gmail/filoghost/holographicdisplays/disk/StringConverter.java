/*  1:   */ package com.gmail.filoghost.holographicdisplays.disk;
/*  2:   */ 
/*  3:   */ import org.bukkit.ChatColor;
/*  4:   */ 
/*  5:   */ public class StringConverter
/*  6:   */ {
/*  7:   */   public static String toReadableFormat(String input)
/*  8:   */   {
/*  9: 8 */     if (input == null) {
/* 10: 9 */       return null;
/* 11:   */     }
/* 12:12 */     input = UnicodeSymbols.placeholdersToSymbols(input);
/* 13:13 */     input = ChatColor.translateAlternateColorCodes('&', input);
/* 14:14 */     return input;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static String toSaveableFormat(String input)
/* 18:   */   {
/* 19:19 */     if (input == null) {
/* 20:20 */       return null;
/* 21:   */     }
/* 22:23 */     input = UnicodeSymbols.symbolsToPlaceholders(input);
/* 23:24 */     input = input.replace("§", "&");
/* 24:25 */     return input;
/* 25:   */   }
/* 26:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.disk.StringConverter
 * JD-Core Version:    0.7.0.1
 */