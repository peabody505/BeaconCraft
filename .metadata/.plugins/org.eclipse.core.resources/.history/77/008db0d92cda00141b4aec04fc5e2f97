/*   1:    */ package com.gmail.filoghost.holographicdisplays.placeholder;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*   7:    */ import java.text.SimpleDateFormat;
/*   8:    */ import java.util.Date;
/*   9:    */ import java.util.Iterator;
/*  10:    */ import java.util.Set;
/*  11:    */ import org.bukkit.Bukkit;
/*  12:    */ import org.bukkit.ChatColor;
/*  13:    */ import org.bukkit.plugin.Plugin;
/*  14:    */ 
/*  15:    */ public class PlaceholdersRegister
/*  16:    */ {
/*  17: 18 */   private static final Set<Placeholder> placeholders = ;
/*  18:    */   
/*  19:    */   static
/*  20:    */   {
/*  21: 23 */     register(new Placeholder(HolographicDisplays.getInstance(), "{online}", 1.0D, new PlaceholderReplacer()
/*  22:    */     {
/*  23:    */       public String update()
/*  24:    */       {
/*  25: 28 */         return String.valueOf(Bukkit.getOnlinePlayers().length);
/*  26:    */       }
/*  27: 31 */     }));
/*  28: 32 */     register(new Placeholder(HolographicDisplays.getInstance(), "{max_players}", 10.0D, new PlaceholderReplacer()
/*  29:    */     {
/*  30:    */       public String update()
/*  31:    */       {
/*  32: 36 */         return String.valueOf(Bukkit.getMaxPlayers());
/*  33:    */       }
/*  34: 39 */     }));
/*  35: 40 */     register(new Placeholder(HolographicDisplays.getInstance(), "{motd}", 60.0D, new PlaceholderReplacer()
/*  36:    */     {
/*  37:    */       public String update()
/*  38:    */       {
/*  39: 44 */         return Bukkit.getMotd();
/*  40:    */       }
/*  41: 47 */     }));
/*  42: 48 */     register(new Placeholder(HolographicDisplays.getInstance(), "{time}", 0.9D, new PlaceholderReplacer()
/*  43:    */     {
/*  44:    */       public String update()
/*  45:    */       {
/*  46: 52 */         return Configuration.timeFormat.format(new Date());
/*  47:    */       }
/*  48: 55 */     }));
/*  49: 56 */     register(new Placeholder(HolographicDisplays.getInstance(), "&u", 0.2D, new CyclicPlaceholderReplacer(Utils.arrayToStrings(new Object[] {
/*  50: 57 */       ChatColor.RED, 
/*  51: 58 */       ChatColor.GOLD, 
/*  52: 59 */       ChatColor.YELLOW, 
/*  53: 60 */       ChatColor.GREEN, 
/*  54: 61 */       ChatColor.AQUA, 
/*  55: 62 */       ChatColor.LIGHT_PURPLE }))));
/*  56:    */   }
/*  57:    */   
/*  58:    */   public static boolean register(Placeholder placeholder)
/*  59:    */   {
/*  60: 68 */     if (placeholders.contains(placeholder)) {
/*  61: 69 */       return false;
/*  62:    */     }
/*  63: 72 */     placeholders.add(placeholder);
/*  64: 73 */     return true;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public static Set<String> getTextPlaceholdersByPlugin(Plugin plugin)
/*  68:    */   {
/*  69: 77 */     Set<String> found = Utils.newSet();
/*  70: 79 */     for (Placeholder placeholder : placeholders) {
/*  71: 80 */       if (placeholder.getOwner().equals(plugin)) {
/*  72: 81 */         found.add(placeholder.getTextPlaceholder());
/*  73:    */       }
/*  74:    */     }
/*  75: 85 */     return found;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public static boolean unregister(Plugin plugin, String textPlaceholder)
/*  79:    */   {
/*  80: 90 */     Iterator<Placeholder> iter = placeholders.iterator();
/*  81: 92 */     while (iter.hasNext())
/*  82:    */     {
/*  83: 93 */       Placeholder placeholder = (Placeholder)iter.next();
/*  84: 95 */       if ((placeholder.getOwner().equals(plugin)) && (placeholder.getTextPlaceholder().equals(textPlaceholder)))
/*  85:    */       {
/*  86: 96 */         iter.remove();
/*  87: 98 */         for (DynamicLineData data : PlaceholdersManager.linesToUpdate) {
/*  88: 99 */           if (data.getPlaceholders().contains(placeholder)) {
/*  89:100 */             data.getPlaceholders().remove(placeholder);
/*  90:    */           }
/*  91:    */         }
/*  92:104 */         return true;
/*  93:    */       }
/*  94:    */     }
/*  95:108 */     return false;
/*  96:    */   }
/*  97:    */   
/*  98:    */   protected static Set<Placeholder> getPlaceholders()
/*  99:    */   {
/* 100:112 */     return placeholders;
/* 101:    */   }
/* 102:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.placeholder.PlaceholdersRegister
 * JD-Core Version:    0.7.0.1
 */