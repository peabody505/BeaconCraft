/*  1:   */ package com.gmail.filoghost.holographicdisplays.placeholder;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
/*  4:   */ import org.bukkit.plugin.Plugin;
/*  5:   */ 
/*  6:   */ public class Placeholder
/*  7:   */ {
/*  8:   */   private final Plugin owner;
/*  9:   */   private final String textPlaceholder;
/* 10:   */   private int tenthsToRefresh;
/* 11:   */   private String currentReplacement;
/* 12:   */   private PlaceholderReplacer replacer;
/* 13:   */   
/* 14:   */   public Placeholder(Plugin owner, String textPlaceholder, double refreshRate, PlaceholderReplacer replacer)
/* 15:   */   {
/* 16:24 */     this.owner = owner;
/* 17:25 */     this.textPlaceholder = textPlaceholder;
/* 18:26 */     this.tenthsToRefresh = (refreshRate <= 0.1D ? 1 : (int)(refreshRate * 10.0D));
/* 19:27 */     this.replacer = replacer;
/* 20:28 */     this.currentReplacement = "";
/* 21:   */   }
/* 22:   */   
/* 23:   */   public Plugin getOwner()
/* 24:   */   {
/* 25:32 */     return this.owner;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int getTenthsToRefresh()
/* 29:   */   {
/* 30:36 */     return this.tenthsToRefresh;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setTenthsToRefresh(int tenthsToRefresh)
/* 34:   */   {
/* 35:40 */     this.tenthsToRefresh = tenthsToRefresh;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public String getTextPlaceholder()
/* 39:   */   {
/* 40:44 */     return this.textPlaceholder;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public String getCurrentReplacement()
/* 44:   */   {
/* 45:48 */     return this.currentReplacement;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void setCurrentReplacement(String replacement)
/* 49:   */   {
/* 50:52 */     this.currentReplacement = (replacement != null ? replacement : "null");
/* 51:   */   }
/* 52:   */   
/* 53:   */   public PlaceholderReplacer getReplacer()
/* 54:   */   {
/* 55:56 */     return this.replacer;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void setReplacer(PlaceholderReplacer replacer)
/* 59:   */   {
/* 60:60 */     this.replacer = replacer;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public void update()
/* 64:   */   {
/* 65:64 */     setCurrentReplacement(this.replacer.update());
/* 66:   */   }
/* 67:   */   
/* 68:   */   public boolean equals(Object obj)
/* 69:   */   {
/* 70:70 */     if (obj == null) {
/* 71:71 */       return false;
/* 72:   */     }
/* 73:74 */     if ((obj instanceof Placeholder)) {
/* 74:75 */       return ((Placeholder)obj).textPlaceholder.equals(this.textPlaceholder);
/* 75:   */     }
/* 76:78 */     return false;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public int hashCode()
/* 80:   */   {
/* 81:84 */     return this.textPlaceholder.hashCode();
/* 82:   */   }
/* 83:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.placeholder.Placeholder
 * JD-Core Version:    0.7.0.1
 */