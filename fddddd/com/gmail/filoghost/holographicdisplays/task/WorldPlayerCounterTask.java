/*  1:   */ package com.gmail.filoghost.holographicdisplays.task;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  4:   */ import java.util.List;
/*  5:   */ import java.util.Map;
/*  6:   */ import org.bukkit.Bukkit;
/*  7:   */ import org.bukkit.World;
/*  8:   */ import org.bukkit.entity.Player;
/*  9:   */ 
/* 10:   */ public class WorldPlayerCounterTask
/* 11:   */   implements Runnable
/* 12:   */ {
/* 13:14 */   private static Map<String, Integer> worlds = ;
/* 14:   */   
/* 15:   */   public void run()
/* 16:   */   {
/* 17:18 */     worlds.clear();
/* 18:20 */     for (World world : Bukkit.getWorlds())
/* 19:   */     {
/* 20:21 */       List<Player> players = world.getPlayers();
/* 21:22 */       int count = 0;
/* 22:24 */       for (Player player : players) {
/* 23:25 */         if (!player.hasMetadata("NPC")) {
/* 24:26 */           count++;
/* 25:   */         }
/* 26:   */       }
/* 27:29 */       worlds.put(world.getName(), Integer.valueOf(count));
/* 28:   */     }
/* 29:   */   }
/* 30:   */   
/* 31:   */   public static String getCount(String world)
/* 32:   */   {
/* 33:34 */     Integer count = (Integer)worlds.get(world);
/* 34:35 */     return "[World \"" + world + "\" not found]";
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.task.WorldPlayerCounterTask
 * JD-Core Version:    0.7.0.1
 */