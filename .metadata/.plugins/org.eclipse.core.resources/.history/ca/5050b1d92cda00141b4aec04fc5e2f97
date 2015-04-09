/*  1:   */ package com.gmail.filoghost.holographicdisplays.placeholder;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
/*  4:   */ 
/*  5:   */ public class CyclicPlaceholderReplacer
/*  6:   */   implements PlaceholderReplacer
/*  7:   */ {
/*  8:   */   String[] frames;
/*  9:   */   private int index;
/* 10:   */   
/* 11:   */   public CyclicPlaceholderReplacer(String[] frames)
/* 12:   */   {
/* 13:11 */     this.frames = frames;
/* 14:12 */     this.index = 0;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public String update()
/* 18:   */   {
/* 19:17 */     String result = this.frames[this.index];
/* 20:   */     
/* 21:19 */     this.index += 1;
/* 22:20 */     if (this.index >= this.frames.length) {
/* 23:21 */       this.index = 0;
/* 24:   */     }
/* 25:24 */     return result;
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.placeholder.CyclicPlaceholderReplacer
 * JD-Core Version:    0.7.0.1
 */