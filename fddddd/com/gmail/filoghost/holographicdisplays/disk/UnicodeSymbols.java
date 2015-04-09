/*   1:    */ package com.gmail.filoghost.holographicdisplays.disk;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.util.FileUtils;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.util.HashMap;
/*   7:    */ import java.util.List;
/*   8:    */ import java.util.Map;
/*   9:    */ import java.util.Map.Entry;
/*  10:    */ import java.util.logging.Logger;
/*  11:    */ import org.apache.commons.lang.StringEscapeUtils;
/*  12:    */ import org.bukkit.plugin.Plugin;
/*  13:    */ 
/*  14:    */ public class UnicodeSymbols
/*  15:    */ {
/*  16: 17 */   private static Map<String, String> placeholders = new HashMap();
/*  17:    */   
/*  18:    */   public static void load(Plugin plugin)
/*  19:    */   {
/*  20: 20 */     placeholders.clear();
/*  21:    */     
/*  22: 22 */     File file = new File(plugin.getDataFolder(), "symbols.yml");
/*  23: 24 */     if (!file.exists())
/*  24:    */     {
/*  25: 25 */       plugin.getDataFolder().mkdirs();
/*  26: 26 */       plugin.saveResource("symbols.yml", true);
/*  27:    */     }
/*  28:    */     try
/*  29:    */     {
/*  30: 31 */       lines = FileUtils.readLines(file);
/*  31:    */     }
/*  32:    */     catch (IOException e)
/*  33:    */     {
/*  34:    */       List<String> lines;
/*  35: 33 */       e.printStackTrace();
/*  36: 34 */       plugin.getLogger().warning("I/O error while reading symbols.yml. Was the file in use?");
/*  37: 35 */       return;
/*  38:    */     }
/*  39:    */     catch (Exception e)
/*  40:    */     {
/*  41: 37 */       e.printStackTrace();
/*  42: 38 */       plugin.getLogger().warning("Unhandled exception while reading symbols.yml!"); return;
/*  43:    */     }
/*  44:    */     List<String> lines;
/*  45: 42 */     for (String line : lines) {
/*  46: 45 */       if ((line.length() != 0) && (!line.startsWith("#"))) {
/*  47: 49 */         if (!line.contains(":"))
/*  48:    */         {
/*  49: 50 */           plugin.getLogger().warning("Unable to parse a line(" + line + ") from symbols.yml: it must contain ':' to separate the placeholder and the replacement.");
/*  50:    */         }
/*  51:    */         else
/*  52:    */         {
/*  53: 54 */           int indexOf = line.indexOf(':');
/*  54: 55 */           String placeholder = unquote(line.substring(0, indexOf).trim());
/*  55: 56 */           String replacement = StringEscapeUtils.unescapeJava(unquote(line.substring(indexOf + 1, line.length()).trim()));
/*  56: 58 */           if ((placeholder.isEmpty()) || (replacement.isEmpty())) {
/*  57: 59 */             plugin.getLogger().warning("Unable to parse a line(" + line + ") from symbols.yml: the placeholder and the replacement must have both at least 1 character.");
/*  58: 63 */           } else if (placeholder.length() > 30) {
/*  59: 64 */             plugin.getLogger().warning("Unable to parse a line(" + line + ") from symbols.yml: the placeholder cannot be longer than 30 characters.");
/*  60:    */           } else {
/*  61: 68 */             placeholders.put(placeholder, replacement);
/*  62:    */           }
/*  63:    */         }
/*  64:    */       }
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   protected static String placeholdersToSymbols(String input)
/*  69:    */   {
/*  70: 74 */     for (Map.Entry<String, String> entry : placeholders.entrySet()) {
/*  71: 75 */       input = input.replace((CharSequence)entry.getKey(), (CharSequence)entry.getValue());
/*  72:    */     }
/*  73: 77 */     return input;
/*  74:    */   }
/*  75:    */   
/*  76:    */   protected static String symbolsToPlaceholders(String input)
/*  77:    */   {
/*  78: 82 */     for (Map.Entry<String, String> entry : placeholders.entrySet()) {
/*  79: 83 */       input = input.replace((CharSequence)entry.getValue(), (CharSequence)entry.getKey());
/*  80:    */     }
/*  81: 85 */     return input;
/*  82:    */   }
/*  83:    */   
/*  84:    */   private static String unquote(String input)
/*  85:    */   {
/*  86: 90 */     if (input.length() < 2) {
/*  87: 92 */       return input;
/*  88:    */     }
/*  89: 94 */     if ((input.startsWith("'")) && (input.endsWith("'"))) {
/*  90: 95 */       return input.substring(1, input.length() - 1);
/*  91:    */     }
/*  92: 96 */     if ((input.startsWith("\"")) && (input.endsWith("\""))) {
/*  93: 97 */       return input.substring(1, input.length() - 1);
/*  94:    */     }
/*  95:100 */     return input;
/*  96:    */   }
/*  97:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.disk.UnicodeSymbols
 * JD-Core Version:    0.7.0.1
 */