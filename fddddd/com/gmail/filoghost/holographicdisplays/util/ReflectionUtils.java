/*  1:   */ package com.gmail.filoghost.holographicdisplays.util;
/*  2:   */ 
/*  3:   */ import java.lang.reflect.Field;
/*  4:   */ import java.util.Map;
/*  5:   */ 
/*  6:   */ public class ReflectionUtils
/*  7:   */ {
/*  8:   */   public static void putInPrivateStaticMap(Class<?> clazz, String fieldName, Object key, Object value)
/*  9:   */     throws Exception
/* 10:   */   {
/* 11:10 */     Field field = clazz.getDeclaredField(fieldName);
/* 12:11 */     field.setAccessible(true);
/* 13:12 */     Map map = (Map)field.get(null);
/* 14:13 */     map.put(key, value);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static void setPrivateField(Class<?> clazz, Object handle, String fieldName, Object value)
/* 18:   */     throws Exception
/* 19:   */   {
/* 20:17 */     Field field = clazz.getDeclaredField(fieldName);
/* 21:18 */     field.setAccessible(true);
/* 22:19 */     field.set(handle, value);
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.util.ReflectionUtils
 * JD-Core Version:    0.7.0.1
 */