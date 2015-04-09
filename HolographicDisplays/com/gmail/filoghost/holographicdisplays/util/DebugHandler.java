/*  1:   */ package com.gmail.filoghost.holographicdisplays.util;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.api.line.HologramLine;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*  6:   */ import java.util.logging.Logger;
/*  7:   */ 
/*  8:   */ public class DebugHandler
/*  9:   */ {
/* 10:   */   public static void logToConsole(String msg)
/* 11:   */   {
/* 12:10 */     if (Configuration.debug) {
/* 13:11 */       HolographicDisplays.getInstance().getLogger().info("[Debug] " + msg);
/* 14:   */     }
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static void handleAnimationLoadSuccess(String name, double speed)
/* 18:   */   {
/* 19:16 */     logToConsole("Successfully loaded animation '" + name + "', speed = " + speed + ".");
/* 20:   */   }
/* 21:   */   
/* 22:   */   public static void handleSpawnFail(HologramLine parentPiece)
/* 23:   */   {
/* 24:20 */     if (Configuration.debug) {
/* 25:21 */       HolographicDisplays.getInstance().getLogger().severe("[Debug] Coulnd't spawn entity for this hologram: " + parentPiece.getParent().toString());
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static void handleDebugException(Exception e)
/* 30:   */   {
/* 31:26 */     if (Configuration.debug) {
/* 32:27 */       e.printStackTrace();
/* 33:   */     }
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.util.DebugHandler
 * JD-Core Version:    0.7.0.1
 */