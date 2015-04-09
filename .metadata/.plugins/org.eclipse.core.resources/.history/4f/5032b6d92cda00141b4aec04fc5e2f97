/*   1:    */ package com.gmail.filoghost.holographicdisplays.object.line;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.api.handler.PickupHandler;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSItem;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.util.Validator;
/*  12:    */ import org.apache.commons.lang.ArrayUtils;
/*  13:    */ import org.bukkit.Location;
/*  14:    */ import org.bukkit.Material;
/*  15:    */ import org.bukkit.World;
/*  16:    */ import org.bukkit.entity.Entity;
/*  17:    */ import org.bukkit.inventory.ItemStack;
/*  18:    */ 
/*  19:    */ public class CraftItemLine
/*  20:    */   extends CraftTouchableLine
/*  21:    */   implements ItemLine
/*  22:    */ {
/*  23:    */   private ItemStack itemStack;
/*  24:    */   private PickupHandler pickupHandler;
/*  25:    */   private NMSItem nmsItem;
/*  26:    */   private NMSEntityBase nmsVehicle;
/*  27:    */   
/*  28:    */   public CraftItemLine(CraftHologram parent, ItemStack itemStack)
/*  29:    */   {
/*  30: 28 */     super(0.7D, parent);
/*  31: 29 */     setItemStack(itemStack);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public ItemStack getItemStack()
/*  35:    */   {
/*  36: 34 */     return this.itemStack;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void setItemStack(ItemStack itemStack)
/*  40:    */   {
/*  41: 39 */     Validator.notNull(itemStack, "itemStack");
/*  42: 40 */     Validator.isTrue(itemStack.getType() != Material.AIR, "itemStack's material cannot be AIR");
/*  43: 41 */     this.itemStack = itemStack;
/*  44: 43 */     if (this.nmsItem != null) {
/*  45: 44 */       this.nmsItem.setItemStackNMS(itemStack);
/*  46:    */     }
/*  47:    */   }
/*  48:    */   
/*  49:    */   public PickupHandler getPickupHandler()
/*  50:    */   {
/*  51: 50 */     return this.pickupHandler;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setPickupHandler(PickupHandler pickupHandler)
/*  55:    */   {
/*  56: 55 */     this.pickupHandler = pickupHandler;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void setTouchHandler(TouchHandler touchHandler)
/*  60:    */   {
/*  61: 60 */     if (this.nmsItem != null)
/*  62:    */     {
/*  63: 62 */       Location loc = this.nmsItem.getBukkitEntityNMS().getLocation();
/*  64: 64 */       if (HolographicDisplays.is1_8()) {
/*  65: 65 */         super.setTouchHandler(touchHandler, loc.getWorld(), loc.getX(), loc.getY() - -1.48D, loc.getZ());
/*  66:    */       } else {
/*  67: 67 */         super.setTouchHandler(touchHandler, loc.getWorld(), loc.getX(), loc.getY() - -0.21D, loc.getZ());
/*  68:    */       }
/*  69:    */     }
/*  70:    */     else
/*  71:    */     {
/*  72: 71 */       super.setTouchHandler(touchHandler, null, 0.0D, 0.0D, 0.0D);
/*  73:    */     }
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void spawn(World world, double x, double y, double z)
/*  77:    */   {
/*  78: 77 */     super.spawn(world, x, y, z);
/*  79: 79 */     if ((this.itemStack != null) && (this.itemStack.getType() != Material.AIR))
/*  80:    */     {
/*  81: 81 */       double offset = HolographicDisplays.is1_8() ? -1.48D : -0.21D;
/*  82:    */       
/*  83: 83 */       this.nmsItem = HolographicDisplays.getNMSManager().spawnNMSItem(world, x, y + offset, z, this, this.itemStack);
/*  84: 85 */       if (HolographicDisplays.is1_8()) {
/*  85: 86 */         this.nmsVehicle = HolographicDisplays.getNMSManager().spawnNMSArmorStand(world, x, y + offset, z, this);
/*  86:    */       } else {
/*  87: 88 */         this.nmsVehicle = HolographicDisplays.getNMSManager().spawnNMSWitherSkull(world, x, y + offset, z, this);
/*  88:    */       }
/*  89: 91 */       this.nmsItem.setPassengerOfNMS(this.nmsVehicle);
/*  90:    */       
/*  91: 93 */       this.nmsItem.setLockTick(true);
/*  92: 94 */       this.nmsVehicle.setLockTick(true);
/*  93:    */     }
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void despawn()
/*  97:    */   {
/*  98:101 */     super.despawn();
/*  99:103 */     if (this.nmsVehicle != null)
/* 100:    */     {
/* 101:104 */       this.nmsVehicle.killEntityNMS();
/* 102:105 */       this.nmsVehicle = null;
/* 103:    */     }
/* 104:108 */     if (this.nmsItem != null)
/* 105:    */     {
/* 106:109 */       this.nmsItem.killEntityNMS();
/* 107:110 */       this.nmsItem = null;
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void teleport(double x, double y, double z)
/* 112:    */   {
/* 113:116 */     super.teleport(x, y, z);
/* 114:    */     
/* 115:118 */     double offset = HolographicDisplays.is1_8() ? -1.48D : -0.21D;
/* 116:120 */     if (this.nmsVehicle != null) {
/* 117:121 */       this.nmsVehicle.setLocationNMS(x, y + offset, z);
/* 118:    */     }
/* 119:124 */     if (this.nmsItem != null) {
/* 120:125 */       this.nmsItem.setLocationNMS(x, y + offset, z);
/* 121:    */     }
/* 122:    */   }
/* 123:    */   
/* 124:    */   public int[] getEntitiesIDs()
/* 125:    */   {
/* 126:131 */     if (isSpawned())
/* 127:    */     {
/* 128:132 */       if (this.touchSlime != null) {
/* 129:133 */         return ArrayUtils.addAll(new int[] { this.nmsVehicle.getIdNMS(), this.nmsItem.getIdNMS() }, this.touchSlime.getEntitiesIDs());
/* 130:    */       }
/* 131:135 */       return new int[] { this.nmsVehicle.getIdNMS(), this.nmsItem.getIdNMS() };
/* 132:    */     }
/* 133:138 */     return new int[0];
/* 134:    */   }
/* 135:    */   
/* 136:    */   public NMSItem getNmsItem()
/* 137:    */   {
/* 138:143 */     return this.nmsItem;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public NMSEntityBase getNmsVehicle()
/* 142:    */   {
/* 143:147 */     return this.nmsVehicle;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public String toString()
/* 147:    */   {
/* 148:152 */     return "CraftItemLine [itemStack=" + this.itemStack + ", pickupHandler=" + this.pickupHandler + "]";
/* 149:    */   }
/* 150:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine
 * JD-Core Version:    0.7.0.1
 */