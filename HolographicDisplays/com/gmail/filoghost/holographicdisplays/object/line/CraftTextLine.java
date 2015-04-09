/*   1:    */ package com.gmail.filoghost.holographicdisplays.object.line;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSCanMount;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSNameable;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.placeholder.PlaceholdersManager;
/*  12:    */ import org.apache.commons.lang.ArrayUtils;
/*  13:    */ import org.bukkit.Location;
/*  14:    */ import org.bukkit.World;
/*  15:    */ import org.bukkit.entity.Entity;
/*  16:    */ 
/*  17:    */ public class CraftTextLine
/*  18:    */   extends CraftTouchableLine
/*  19:    */   implements TextLine
/*  20:    */ {
/*  21:    */   private String text;
/*  22:    */   private NMSNameable nmsNameble;
/*  23:    */   private NMSEntityBase nmsSkullVehicle;
/*  24:    */   
/*  25:    */   public CraftTextLine(CraftHologram parent, String text)
/*  26:    */   {
/*  27: 28 */     super(0.23D, parent);
/*  28: 29 */     setText(text);
/*  29:    */   }
/*  30:    */   
/*  31:    */   public String getText()
/*  32:    */   {
/*  33: 35 */     return this.text;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void setText(String text)
/*  37:    */   {
/*  38: 40 */     this.text = text;
/*  39: 42 */     if (this.nmsNameble != null) {
/*  40: 43 */       if ((text != null) && (!text.isEmpty()))
/*  41:    */       {
/*  42: 44 */         this.nmsNameble.setCustomNameNMS(text);
/*  43: 45 */         if (getParent().isAllowPlaceholders()) {
/*  44: 46 */           PlaceholdersManager.trackIfNecessary(this);
/*  45:    */         }
/*  46:    */       }
/*  47:    */       else
/*  48:    */       {
/*  49: 49 */         this.nmsNameble.setCustomNameNMS("");
/*  50: 50 */         if (getParent().isAllowPlaceholders()) {
/*  51: 51 */           PlaceholdersManager.untrack(this);
/*  52:    */         }
/*  53:    */       }
/*  54:    */     }
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void setTouchHandler(TouchHandler touchHandler)
/*  58:    */   {
/*  59: 59 */     if (this.nmsNameble != null)
/*  60:    */     {
/*  61: 61 */       Location loc = this.nmsNameble.getBukkitEntityNMS().getLocation();
/*  62: 63 */       if (HolographicDisplays.is1_8()) {
/*  63: 64 */         super.setTouchHandler(touchHandler, loc.getWorld(), loc.getX(), loc.getY() - -1.25D, loc.getZ());
/*  64:    */       } else {
/*  65: 66 */         super.setTouchHandler(touchHandler, loc.getWorld(), loc.getX(), loc.getY() - 54.560000000000002D, loc.getZ());
/*  66:    */       }
/*  67:    */     }
/*  68:    */     else
/*  69:    */     {
/*  70: 70 */       super.setTouchHandler(touchHandler, null, 0.0D, 0.0D, 0.0D);
/*  71:    */     }
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void spawn(World world, double x, double y, double z)
/*  75:    */   {
/*  76: 76 */     super.spawn(world, x, y, z);
/*  77: 78 */     if (HolographicDisplays.is1_8())
/*  78:    */     {
/*  79: 79 */       this.nmsNameble = HolographicDisplays.getNMSManager().spawnNMSArmorStand(world, x, y + -1.25D, z, this);
/*  80:    */     }
/*  81:    */     else
/*  82:    */     {
/*  83: 81 */       this.nmsNameble = HolographicDisplays.getNMSManager().spawnNMSHorse(world, x, y + 54.560000000000002D, z, this);
/*  84: 82 */       this.nmsSkullVehicle = HolographicDisplays.getNMSManager().spawnNMSWitherSkull(world, x, y + 54.560000000000002D, z, this);
/*  85:    */       
/*  86:    */ 
/*  87: 85 */       ((NMSCanMount)this.nmsNameble).setPassengerOfNMS(this.nmsSkullVehicle);
/*  88: 86 */       this.nmsSkullVehicle.setLockTick(true);
/*  89:    */     }
/*  90: 89 */     if ((this.text != null) && (!this.text.isEmpty())) {
/*  91: 90 */       this.nmsNameble.setCustomNameNMS(this.text);
/*  92:    */     }
/*  93: 93 */     this.nmsNameble.setLockTick(true);
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void despawn()
/*  97:    */   {
/*  98: 99 */     super.despawn();
/*  99:101 */     if (this.nmsSkullVehicle != null)
/* 100:    */     {
/* 101:102 */       this.nmsSkullVehicle.killEntityNMS();
/* 102:103 */       this.nmsSkullVehicle = null;
/* 103:    */     }
/* 104:106 */     if (this.nmsNameble != null)
/* 105:    */     {
/* 106:107 */       this.nmsNameble.killEntityNMS();
/* 107:108 */       this.nmsNameble = null;
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void teleport(double x, double y, double z)
/* 112:    */   {
/* 113:115 */     super.teleport(x, y, z);
/* 114:117 */     if (this.nmsSkullVehicle != null) {
/* 115:118 */       this.nmsSkullVehicle.setLocationNMS(x, y + 54.560000000000002D, z);
/* 116:    */     }
/* 117:121 */     if (this.nmsNameble != null) {
/* 118:122 */       this.nmsNameble.setLocationNMS(x, y + (HolographicDisplays.is1_8() ? -1.25D : 54.560000000000002D), z);
/* 119:    */     }
/* 120:    */   }
/* 121:    */   
/* 122:    */   public int[] getEntitiesIDs()
/* 123:    */   {
/* 124:128 */     if (isSpawned())
/* 125:    */     {
/* 126:129 */       if (this.nmsSkullVehicle != null)
/* 127:    */       {
/* 128:130 */         if (this.touchSlime != null) {
/* 129:131 */           return ArrayUtils.addAll(new int[] { this.nmsNameble.getIdNMS(), this.nmsSkullVehicle.getIdNMS() }, this.touchSlime.getEntitiesIDs());
/* 130:    */         }
/* 131:133 */         return new int[] { this.nmsNameble.getIdNMS(), this.nmsSkullVehicle.getIdNMS() };
/* 132:    */       }
/* 133:136 */       if (this.touchSlime != null) {
/* 134:137 */         return ArrayUtils.add(this.touchSlime.getEntitiesIDs(), this.nmsNameble.getIdNMS());
/* 135:    */       }
/* 136:139 */       return new int[] { this.nmsNameble.getIdNMS() };
/* 137:    */     }
/* 138:143 */     return new int[0];
/* 139:    */   }
/* 140:    */   
/* 141:    */   public NMSNameable getNmsNameble()
/* 142:    */   {
/* 143:148 */     return this.nmsNameble;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public NMSEntityBase getNmsSkullVehicle()
/* 147:    */   {
/* 148:152 */     return this.nmsSkullVehicle;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public String toString()
/* 152:    */   {
/* 153:158 */     return "CraftTextLine [text=" + this.text + "]";
/* 154:    */   }
/* 155:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.line.CraftTextLine
 * JD-Core Version:    0.7.0.1
 */