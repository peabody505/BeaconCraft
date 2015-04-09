/*  1:   */ package com.gmail.filoghost.holographicdisplays.event;
/*  2:   */ 
/*  3:   */ import org.bukkit.event.Event;
/*  4:   */ import org.bukkit.event.HandlerList;
/*  5:   */ 
/*  6:   */ public class HolographicDisplaysReloadEvent
/*  7:   */   extends Event
/*  8:   */ {
/*  9: 8 */   private static final HandlerList handlers = new HandlerList();
/* 10:   */   
/* 11:   */   public HandlerList getHandlers()
/* 12:   */   {
/* 13:12 */     return handlers;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public static HandlerList getHandlerList()
/* 17:   */   {
/* 18:16 */     return handlers;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.event.HolographicDisplaysReloadEvent
 * JD-Core Version:    0.7.0.1
 */