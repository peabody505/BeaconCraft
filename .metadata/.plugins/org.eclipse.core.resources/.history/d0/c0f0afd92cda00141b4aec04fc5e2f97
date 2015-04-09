/*  1:   */ package com.gmail.filoghost.holographicdisplays.placeholder;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSNameable;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.util.Validator;
/*  7:   */ import java.util.Map;
/*  8:   */ import java.util.Set;
/*  9:   */ 
/* 10:   */ public class DynamicLineData
/* 11:   */ {
/* 12:   */   private final NMSNameable entity;
/* 13:   */   private final String originalName;
/* 14:   */   private Set<Placeholder> placeholders;
/* 15:   */   private final Map<String, Placeholder> animations;
/* 16:   */   private final Map<String, PlaceholderReplacer> replacers;
/* 17:   */   
/* 18:   */   public DynamicLineData(NMSNameable entity, String originalName)
/* 19:   */   {
/* 20:21 */     Validator.notNull(entity, "entity");
/* 21:   */     
/* 22:23 */     this.entity = entity;
/* 23:24 */     this.originalName = originalName;
/* 24:25 */     this.placeholders = Utils.newSet();
/* 25:26 */     this.animations = Utils.newMap();
/* 26:27 */     this.replacers = Utils.newMap();
/* 27:   */   }
/* 28:   */   
/* 29:   */   public NMSNameable getEntity()
/* 30:   */   {
/* 31:31 */     return this.entity;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String getOriginalName()
/* 35:   */   {
/* 36:35 */     return this.originalName;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void setPlaceholders(Set<Placeholder> placeholders)
/* 40:   */   {
/* 41:39 */     this.placeholders = placeholders;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public Set<Placeholder> getPlaceholders()
/* 45:   */   {
/* 46:43 */     return this.placeholders;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public Map<String, PlaceholderReplacer> getReplacers()
/* 50:   */   {
/* 51:47 */     return this.replacers;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public Map<String, Placeholder> getAnimations()
/* 55:   */   {
/* 56:51 */     return this.animations;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public int hashCode()
/* 60:   */   {
/* 61:56 */     return this.entity.hashCode();
/* 62:   */   }
/* 63:   */   
/* 64:   */   public boolean equals(Object obj)
/* 65:   */   {
/* 66:61 */     if (this == obj) {
/* 67:62 */       return true;
/* 68:   */     }
/* 69:63 */     if (obj == null) {
/* 70:64 */       return false;
/* 71:   */     }
/* 72:65 */     if (getClass() != obj.getClass()) {
/* 73:66 */       return false;
/* 74:   */     }
/* 75:67 */     DynamicLineData other = (DynamicLineData)obj;
/* 76:68 */     return this.entity == other.entity;
/* 77:   */   }
/* 78:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.placeholder.DynamicLineData
 * JD-Core Version:    0.7.0.1
 */