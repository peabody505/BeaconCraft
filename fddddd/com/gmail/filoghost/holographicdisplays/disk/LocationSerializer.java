/*  1:   */ package com.gmail.filoghost.holographicdisplays.disk;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.exception.InvalidFormatException;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.exception.WorldNotFoundException;
/*  5:   */ import java.text.DecimalFormat;
/*  6:   */ import java.text.DecimalFormatSymbols;
/*  7:   */ import org.bukkit.Bukkit;
/*  8:   */ import org.bukkit.Location;
/*  9:   */ import org.bukkit.World;
/* 10:   */ 
/* 11:   */ public class LocationSerializer
/* 12:   */ {
/* 13:18 */   private static DecimalFormat decimalFormat = new DecimalFormat("0.000");
/* 14:   */   
/* 15:   */   static
/* 16:   */   {
/* 17:19 */     DecimalFormatSymbols formatSymbols = decimalFormat.getDecimalFormatSymbols();
/* 18:20 */     formatSymbols.setDecimalSeparator('.');
/* 19:21 */     decimalFormat.setDecimalFormatSymbols(formatSymbols);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static Location locationFromString(String input)
/* 23:   */     throws WorldNotFoundException, InvalidFormatException
/* 24:   */   {
/* 25:25 */     if (input == null) {
/* 26:26 */       throw new InvalidFormatException();
/* 27:   */     }
/* 28:29 */     String[] parts = input.split(",");
/* 29:31 */     if (parts.length != 4) {
/* 30:32 */       throw new InvalidFormatException();
/* 31:   */     }
/* 32:   */     try
/* 33:   */     {
/* 34:36 */       double x = Double.parseDouble(parts[1].replace(" ", ""));
/* 35:37 */       double y = Double.parseDouble(parts[2].replace(" ", ""));
/* 36:38 */       double z = Double.parseDouble(parts[3].replace(" ", ""));
/* 37:   */       
/* 38:40 */       World world = Bukkit.getWorld(parts[0].trim());
/* 39:41 */       if (world == null) {
/* 40:42 */         throw new WorldNotFoundException(parts[0].trim());
/* 41:   */       }
/* 42:45 */       return new Location(world, x, y, z);
/* 43:   */     }
/* 44:   */     catch (NumberFormatException ex)
/* 45:   */     {
/* 46:48 */       throw new InvalidFormatException();
/* 47:   */     }
/* 48:   */   }
/* 49:   */   
/* 50:   */   public static String locationToString(Location loc)
/* 51:   */   {
/* 52:53 */     return loc.getWorld().getName() + ", " + decimalFormat.format(loc.getX()) + ", " + decimalFormat.format(loc.getY()) + ", " + decimalFormat.format(loc.getZ());
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.disk.LocationSerializer
 * JD-Core Version:    0.7.0.1
 */