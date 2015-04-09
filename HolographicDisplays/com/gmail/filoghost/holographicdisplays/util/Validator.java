/*  1:   */ package com.gmail.filoghost.holographicdisplays.util;
/*  2:   */ 
/*  3:   */ public class Validator
/*  4:   */ {
/*  5:   */   public static void notNull(Object o, String name)
/*  6:   */   {
/*  7: 6 */     if (o == null) {
/*  8: 7 */       throw new NullPointerException(name + " cannot be null");
/*  9:   */     }
/* 10:   */   }
/* 11:   */   
/* 12:   */   public static void isTrue(boolean statement, String message)
/* 13:   */   {
/* 14:12 */     if (!statement) {
/* 15:13 */       throw new IllegalArgumentException(message);
/* 16:   */     }
/* 17:   */   }
/* 18:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.util.Validator
 * JD-Core Version:    0.7.0.1
 */