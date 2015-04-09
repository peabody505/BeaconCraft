/*   1:    */ package com.gmail.filoghost.holographicdisplays.object;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.bridge.protocollib.ProtocolLibHook;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.util.Validator;
/*   7:    */ import java.util.HashSet;
/*   8:    */ import java.util.Map;
/*   9:    */ import java.util.Set;
/*  10:    */ import java.util.concurrent.ConcurrentHashMap;
/*  11:    */ import org.bukkit.Bukkit;
/*  12:    */ import org.bukkit.Location;
/*  13:    */ import org.bukkit.entity.Player;
/*  14:    */ 
/*  15:    */ public class CraftVisibilityManager
/*  16:    */   implements VisibilityManager
/*  17:    */ {
/*  18:    */   private final CraftHologram hologram;
/*  19:    */   private Map<String, Boolean> playersVisibilityMap;
/*  20:    */   private boolean visibleByDefault;
/*  21:    */   private static final int VISIBILITY_DISTANCE_SQUARED = 4096;
/*  22:    */   
/*  23:    */   public CraftVisibilityManager(CraftHologram hologram)
/*  24:    */   {
/*  25: 25 */     Validator.notNull(hologram, "hologram");
/*  26: 26 */     this.hologram = hologram;
/*  27: 27 */     this.visibleByDefault = true;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public boolean isVisibleByDefault()
/*  31:    */   {
/*  32: 32 */     return this.visibleByDefault;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void setVisibleByDefault(boolean visibleByDefault)
/*  36:    */   {
/*  37: 38 */     if (this.visibleByDefault != visibleByDefault)
/*  38:    */     {
/*  39: 40 */       boolean oldVisibleByDefault = this.visibleByDefault;
/*  40: 41 */       this.visibleByDefault = visibleByDefault;
/*  41: 43 */       for (Player player : Bukkit.getOnlinePlayers()) {
/*  42: 45 */         if ((this.playersVisibilityMap == null) || (!this.playersVisibilityMap.containsKey(player.getName().toLowerCase()))) {
/*  43: 50 */           if (oldVisibleByDefault) {
/*  44: 52 */             sendDestroyPacketIfNear(player, this.hologram);
/*  45:    */           } else {
/*  46: 55 */             sendCreatePacketIfNear(player, this.hologram);
/*  47:    */           }
/*  48:    */         }
/*  49:    */       }
/*  50:    */     }
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void showTo(Player player)
/*  54:    */   {
/*  55: 63 */     Validator.notNull(player, "player");
/*  56:    */     
/*  57: 65 */     boolean wasVisible = isVisibleTo(player);
/*  58: 67 */     if (this.playersVisibilityMap == null) {
/*  59: 69 */       this.playersVisibilityMap = new ConcurrentHashMap();
/*  60:    */     }
/*  61: 72 */     this.playersVisibilityMap.put(player.getName().toLowerCase(), Boolean.valueOf(true));
/*  62: 74 */     if (!wasVisible) {
/*  63: 75 */       sendCreatePacketIfNear(player, this.hologram);
/*  64:    */     }
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void hideTo(Player player)
/*  68:    */   {
/*  69: 82 */     Validator.notNull(player, "player");
/*  70:    */     
/*  71: 84 */     boolean wasVisible = isVisibleTo(player);
/*  72: 86 */     if (this.playersVisibilityMap == null) {
/*  73: 88 */       this.playersVisibilityMap = new ConcurrentHashMap();
/*  74:    */     }
/*  75: 91 */     this.playersVisibilityMap.put(player.getName().toLowerCase(), Boolean.valueOf(false));
/*  76: 93 */     if (wasVisible) {
/*  77: 94 */       sendDestroyPacketIfNear(player, this.hologram);
/*  78:    */     }
/*  79:    */   }
/*  80:    */   
/*  81:    */   public boolean isVisibleTo(Player player)
/*  82:    */   {
/*  83:100 */     Validator.notNull(player, "player");
/*  84:102 */     if (this.playersVisibilityMap != null)
/*  85:    */     {
/*  86:104 */       Boolean value = (Boolean)this.playersVisibilityMap.get(player.getName().toLowerCase());
/*  87:105 */       if (value != null) {
/*  88:106 */         return value.booleanValue();
/*  89:    */       }
/*  90:    */     }
/*  91:110 */     return this.visibleByDefault;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void resetVisibility(Player player)
/*  95:    */   {
/*  96:115 */     Validator.notNull(player, "player");
/*  97:117 */     if (this.playersVisibilityMap == null) {
/*  98:118 */       return;
/*  99:    */     }
/* 100:121 */     boolean wasVisible = isVisibleTo(player);
/* 101:    */     
/* 102:123 */     this.playersVisibilityMap.remove(player.getName().toLowerCase());
/* 103:125 */     if ((this.visibleByDefault) && (!wasVisible)) {
/* 104:126 */       sendCreatePacketIfNear(player, this.hologram);
/* 105:128 */     } else if ((!this.visibleByDefault) && (wasVisible)) {
/* 106:129 */       sendDestroyPacketIfNear(player, this.hologram);
/* 107:    */     }
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void resetVisibilityAll()
/* 111:    */   {
/* 112:135 */     if (this.playersVisibilityMap != null)
/* 113:    */     {
/* 114:138 */       Set<String> playerNames = new HashSet(this.playersVisibilityMap.keySet());
/* 115:140 */       for (String playerName : playerNames)
/* 116:    */       {
/* 117:141 */         Player onlinePlayer = Bukkit.getPlayerExact(playerName);
/* 118:142 */         if (onlinePlayer != null) {
/* 119:143 */           resetVisibility(onlinePlayer);
/* 120:    */         }
/* 121:    */       }
/* 122:147 */       this.playersVisibilityMap.clear();
/* 123:148 */       this.playersVisibilityMap = null;
/* 124:    */     }
/* 125:    */   }
/* 126:    */   
/* 127:    */   private static void sendCreatePacketIfNear(Player player, CraftHologram hologram)
/* 128:    */   {
/* 129:153 */     if ((HolographicDisplays.useProtocolLib()) && (isNear(player, hologram))) {
/* 130:154 */       ProtocolLibHook.sendCreateEntitiesPacket(player, hologram);
/* 131:    */     }
/* 132:    */   }
/* 133:    */   
/* 134:    */   private static void sendDestroyPacketIfNear(Player player, CraftHologram hologram)
/* 135:    */   {
/* 136:159 */     if ((HolographicDisplays.useProtocolLib()) && (isNear(player, hologram))) {
/* 137:160 */       ProtocolLibHook.sendDestroyEntitiesPacket(player, hologram);
/* 138:    */     }
/* 139:    */   }
/* 140:    */   
/* 141:    */   private static boolean isNear(Player player, CraftHologram hologram)
/* 142:    */   {
/* 143:165 */     return (player.isOnline()) && (player.getWorld().equals(hologram.getWorld())) && (player.getLocation().distanceSquared(hologram.getLocation()) < 4096.0D);
/* 144:    */   }
/* 145:    */   
/* 146:    */   public String toString()
/* 147:    */   {
/* 148:170 */     return "CraftVisibilityManager [playersMap=" + this.playersVisibilityMap + ", visibleByDefault=" + this.visibleByDefault + "]";
/* 149:    */   }
/* 150:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.CraftVisibilityManager
 * JD-Core Version:    0.7.0.1
 */