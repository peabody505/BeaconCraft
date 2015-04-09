/*   1:    */ package com.gmail.filoghost.holographicdisplays.util;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.HashMap;
/*   5:    */ import java.util.HashSet;
/*   6:    */ import java.util.List;
/*   7:    */ import java.util.Map;
/*   8:    */ import java.util.Set;
/*   9:    */ import org.bukkit.ChatColor;
/*  10:    */ 
/*  11:    */ public class Utils
/*  12:    */ {
/*  13:    */   public static String[] arrayToStrings(Object... array)
/*  14:    */   {
/*  15: 20 */     String[] result = new String[array.length];
/*  16: 21 */     for (int i = 0; i < array.length; i++) {
/*  17: 22 */       result[i] = (array[i] != null ? array[i].toString() : null);
/*  18:    */     }
/*  19: 25 */     return result;
/*  20:    */   }
/*  21:    */   
/*  22:    */   public static String addColors(String text)
/*  23:    */   {
/*  24: 35 */     if (text == null) {
/*  25: 36 */       return null;
/*  26:    */     }
/*  27: 39 */     return ChatColor.translateAlternateColorCodes('&', text);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public static boolean containsIgnoreCase(String toCheck, String content)
/*  31:    */   {
/*  32: 44 */     return toCheck.toLowerCase().contains(content.toLowerCase());
/*  33:    */   }
/*  34:    */   
/*  35:    */   public static <T, V> Map<T, V> newMap()
/*  36:    */   {
/*  37: 49 */     return new HashMap();
/*  38:    */   }
/*  39:    */   
/*  40:    */   public static <T> List<T> newList()
/*  41:    */   {
/*  42: 53 */     return new ArrayList();
/*  43:    */   }
/*  44:    */   
/*  45:    */   public static <T> Set<T> newSet()
/*  46:    */   {
/*  47: 58 */     return new HashSet();
/*  48:    */   }
/*  49:    */   
/*  50:    */   public static <T> T[] listToArray(List<T> list)
/*  51:    */   {
/*  52: 63 */     return list.toArray();
/*  53:    */   }
/*  54:    */   
/*  55:    */   public static int floor(double num)
/*  56:    */   {
/*  57: 67 */     int floor = (int)num;
/*  58: 68 */     return floor == num ? floor : floor - (int)(Double.doubleToRawLongBits(num) >>> 63);
/*  59:    */   }
/*  60:    */   
/*  61:    */   public static double square(double num)
/*  62:    */   {
/*  63: 73 */     return num * num;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public static String join(String[] elements, String separator, int startIndex, int endIndex)
/*  67:    */   {
/*  68: 78 */     Validator.isTrue((startIndex >= 0) && (startIndex < elements.length), "startIndex out of bounds");
/*  69: 79 */     Validator.isTrue((endIndex >= 0) && (endIndex <= elements.length), "endIndex out of bounds");
/*  70: 80 */     Validator.isTrue(startIndex <= endIndex, "startIndex lower than endIndex");
/*  71:    */     
/*  72: 82 */     StringBuilder result = new StringBuilder();
/*  73: 84 */     while (startIndex < endIndex)
/*  74:    */     {
/*  75: 85 */       if (result.length() != 0) {
/*  76: 86 */         result.append(separator);
/*  77:    */       }
/*  78: 89 */       if (elements[startIndex] != null) {
/*  79: 90 */         result.append(elements[startIndex]);
/*  80:    */       }
/*  81: 92 */       startIndex++;
/*  82:    */     }
/*  83: 95 */     return result.toString();
/*  84:    */   }
/*  85:    */   
/*  86:    */   public static String join(String[] elements, String separator)
/*  87:    */   {
/*  88: 99 */     return join(elements, separator, 0, elements.length);
/*  89:    */   }
/*  90:    */   
/*  91:    */   public static String join(List<String> elements, String separator, int startIndex, int size)
/*  92:    */   {
/*  93:103 */     return join((String[])elements.toArray(new String[elements.size()]), separator, startIndex, size);
/*  94:    */   }
/*  95:    */   
/*  96:    */   public static String join(List<String> elements, String separator)
/*  97:    */   {
/*  98:107 */     return join(elements, separator, 0, elements.size());
/*  99:    */   }
/* 100:    */   
/* 101:    */   public static String sanitize(String s)
/* 102:    */   {
/* 103:111 */     return s != null ? s : "null";
/* 104:    */   }
/* 105:    */   
/* 106:    */   public static boolean isThereNonNull(Object... objects)
/* 107:    */   {
/* 108:115 */     if (objects == null) {
/* 109:116 */       return false;
/* 110:    */     }
/* 111:119 */     for (int i = 0; i < objects.length; i++) {
/* 112:120 */       if (objects[i] != null) {
/* 113:121 */         return true;
/* 114:    */       }
/* 115:    */     }
/* 116:125 */     return false;
/* 117:    */   }
/* 118:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.util.Utils
 * JD-Core Version:    0.7.0.1
 */