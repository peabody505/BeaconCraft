/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_7_R2;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.listener.MainListener;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSItem;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.util.ItemUtils;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.util.ReflectionUtils;
/*  10:    */ import net.minecraft.server.v1_7_R2.Blocks;
/*  11:    */ import net.minecraft.server.v1_7_R2.EntityHuman;
/*  12:    */ import net.minecraft.server.v1_7_R2.EntityItem;
/*  13:    */ import net.minecraft.server.v1_7_R2.EntityPlayer;
/*  14:    */ import net.minecraft.server.v1_7_R2.NBTTagCompound;
/*  15:    */ import net.minecraft.server.v1_7_R2.NBTTagList;
/*  16:    */ import net.minecraft.server.v1_7_R2.NBTTagString;
/*  17:    */ import net.minecraft.server.v1_7_R2.World;
/*  18:    */ import org.bukkit.craftbukkit.v1_7_R2.entity.CraftEntity;
/*  19:    */ import org.bukkit.craftbukkit.v1_7_R2.inventory.CraftItemStack;
/*  20:    */ import org.bukkit.entity.Player;
/*  21:    */ 
/*  22:    */ public class EntityNMSItem
/*  23:    */   extends EntityItem
/*  24:    */   implements NMSItem
/*  25:    */ {
/*  26:    */   private boolean lockTick;
/*  27:    */   private CraftItemLine parentPiece;
/*  28:    */   
/*  29:    */   public EntityNMSItem(World world, CraftItemLine piece)
/*  30:    */   {
/*  31: 32 */     super(world);
/*  32: 33 */     this.pickupDelay = 2147483647;
/*  33: 34 */     this.parentPiece = piece;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void h()
/*  37:    */   {
/*  38: 40 */     if (this.ticksLived % 20 == 0) {
/*  39: 42 */       if (this.vehicle == null) {
/*  40: 43 */         die();
/*  41:    */       }
/*  42:    */     }
/*  43: 47 */     if (!this.lockTick) {
/*  44: 48 */       super.h();
/*  45:    */     }
/*  46:    */   }
/*  47:    */   
/*  48:    */   public net.minecraft.server.v1_7_R2.ItemStack getItemStack()
/*  49:    */   {
/*  50: 55 */     StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
/*  51: 56 */     if ((stacktrace.length > 2) && (stacktrace[2].getClassName().contains("EntityInsentient"))) {
/*  52: 57 */       return null;
/*  53:    */     }
/*  54: 60 */     return super.getItemStack();
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void b_(EntityHuman human)
/*  58:    */   {
/*  59: 67 */     if ((this.parentPiece.getPickupHandler() != null) && ((human instanceof EntityPlayer))) {
/*  60: 68 */       MainListener.handleItemLinePickup((Player)human.getBukkitEntity(), this.parentPiece.getPickupHandler(), this.parentPiece.getParent());
/*  61:    */     }
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void b(NBTTagCompound nbttagcompound) {}
/*  65:    */   
/*  66:    */   public boolean c(NBTTagCompound nbttagcompound)
/*  67:    */   {
/*  68: 81 */     return false;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public boolean d(NBTTagCompound nbttagcompound)
/*  72:    */   {
/*  73: 87 */     return false;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void e(NBTTagCompound nbttagcompound) {}
/*  77:    */   
/*  78:    */   public boolean isInvulnerable()
/*  79:    */   {
/*  80:102 */     return true;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void setLockTick(boolean lock)
/*  84:    */   {
/*  85:107 */     this.lockTick = lock;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public void die()
/*  89:    */   {
/*  90:112 */     setLockTick(false);
/*  91:113 */     super.die();
/*  92:    */   }
/*  93:    */   
/*  94:    */   public CraftEntity getBukkitEntity()
/*  95:    */   {
/*  96:118 */     if (this.bukkitEntity == null) {
/*  97:119 */       this.bukkitEntity = new CraftNMSItem(this.world.getServer(), this);
/*  98:    */     }
/*  99:121 */     return this.bukkitEntity;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public boolean isDeadNMS()
/* 103:    */   {
/* 104:126 */     return this.dead;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void killEntityNMS()
/* 108:    */   {
/* 109:131 */     die();
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setLocationNMS(double x, double y, double z)
/* 113:    */   {
/* 114:136 */     super.setPosition(x, y, z);
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setItemStackNMS(org.bukkit.inventory.ItemStack stack)
/* 118:    */   {
/* 119:141 */     net.minecraft.server.v1_7_R2.ItemStack newItem = CraftItemStack.asNMSCopy(stack);
/* 120:143 */     if (newItem == null) {
/* 121:144 */       newItem = new net.minecraft.server.v1_7_R2.ItemStack(Blocks.BEDROCK);
/* 122:    */     }
/* 123:147 */     if (newItem.tag == null) {
/* 124:148 */       newItem.tag = new NBTTagCompound();
/* 125:    */     }
/* 126:150 */     NBTTagCompound display = newItem.tag.getCompound("display");
/* 127:152 */     if (!newItem.tag.hasKey("display")) {
/* 128:153 */       newItem.tag.set("display", display);
/* 129:    */     }
/* 130:156 */     NBTTagList tagList = new NBTTagList();
/* 131:157 */     tagList.add(new NBTTagString(ItemUtils.ANTISTACK_LORE));
/* 132:    */     
/* 133:159 */     display.set("Lore", tagList);
/* 134:160 */     newItem.count = 0;
/* 135:161 */     setItemStack(newItem);
/* 136:    */   }
/* 137:    */   
/* 138:    */   public int getIdNMS()
/* 139:    */   {
/* 140:166 */     return getId();
/* 141:    */   }
/* 142:    */   
/* 143:    */   public CraftItemLine getHologramLine()
/* 144:    */   {
/* 145:171 */     return this.parentPiece;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void allowPickup(boolean pickup)
/* 149:    */   {
/* 150:176 */     if (pickup) {
/* 151:177 */       this.pickupDelay = 0;
/* 152:    */     } else {
/* 153:179 */       this.pickupDelay = 2147483647;
/* 154:    */     }
/* 155:    */   }
/* 156:    */   
/* 157:    */   public org.bukkit.entity.Entity getBukkitEntityNMS()
/* 158:    */   {
/* 159:185 */     return getBukkitEntity();
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void setPassengerOfNMS(NMSEntityBase vehicleBase)
/* 163:    */   {
/* 164:190 */     if ((vehicleBase == null) || (!(vehicleBase instanceof net.minecraft.server.v1_7_R2.Entity))) {
/* 165:192 */       return;
/* 166:    */     }
/* 167:195 */     net.minecraft.server.v1_7_R2.Entity entity = (net.minecraft.server.v1_7_R2.Entity)vehicleBase;
/* 168:    */     try
/* 169:    */     {
/* 170:198 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_7_R2.Entity.class, this, "g", Double.valueOf(0.0D));
/* 171:199 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_7_R2.Entity.class, this, "h", Double.valueOf(0.0D));
/* 172:    */     }
/* 173:    */     catch (Exception ex)
/* 174:    */     {
/* 175:201 */       DebugHandler.handleDebugException(ex);
/* 176:    */     }
/* 177:204 */     if (this.vehicle != null) {
/* 178:205 */       this.vehicle.passenger = null;
/* 179:    */     }
/* 180:208 */     this.vehicle = entity;
/* 181:209 */     entity.passenger = this;
/* 182:    */   }
/* 183:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_7_R2.EntityNMSItem
 * JD-Core Version:    0.7.0.1
 */