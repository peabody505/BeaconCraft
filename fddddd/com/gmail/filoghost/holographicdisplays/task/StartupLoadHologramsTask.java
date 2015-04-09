/*  1:   */ package com.gmail.filoghost.holographicdisplays.task;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/*  5:   */ 
/*  6:   */ public class StartupLoadHologramsTask
/*  7:   */   implements Runnable
/*  8:   */ {
/*  9:   */   public void run()
/* 10:   */   {
/* 11:10 */     for (NamedHologram hologram : ) {
/* 12:11 */       hologram.refreshAll();
/* 13:   */     }
/* 14:   */   }
/* 15:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.task.StartupLoadHologramsTask
 * JD-Core Version:    0.7.0.1
 */