/*  1:   */ package com.gmail.filoghost.holographicdisplays.task;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.bridge.bungeecord.BungeeServerInfo;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.bridge.bungeecord.BungeeServerTracker;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*  6:   */ import java.util.Iterator;
/*  7:   */ import java.util.Map;
/*  8:   */ import java.util.Map.Entry;
/*  9:   */ import java.util.Set;
/* 10:   */ 
/* 11:   */ public class BungeeCleanupTask
/* 12:   */   implements Runnable
/* 13:   */ {
/* 14:   */   public void run()
/* 15:   */   {
/* 16:18 */     Iterator<Map.Entry<String, BungeeServerInfo>> iter = BungeeServerTracker.getTrackedServers().entrySet().iterator();
/* 17:20 */     while (iter.hasNext())
/* 18:   */     {
/* 19:21 */       Map.Entry<String, BungeeServerInfo> next = (Map.Entry)iter.next();
/* 20:22 */       long lastRequest = ((BungeeServerInfo)next.getValue()).getLastRequest();
/* 21:24 */       if ((lastRequest != 0L) && (System.currentTimeMillis() - lastRequest > 600000L))
/* 22:   */       {
/* 23:26 */         iter.remove();
/* 24:27 */         DebugHandler.logToConsole("Untracked bungee server \"" + (String)next.getKey() + "\" due to inactivity.");
/* 25:   */       }
/* 26:   */     }
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.task.BungeeCleanupTask
 * JD-Core Version:    0.7.0.1
 */