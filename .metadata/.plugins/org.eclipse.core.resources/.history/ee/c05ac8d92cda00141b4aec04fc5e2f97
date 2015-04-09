/*   1:    */ package com.gmail.filoghost.holographicdisplays.nms.v1_8_R1;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.listener.MainListener;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSItem;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.util.ItemUtils;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.util.ReflectionUtils;
/*  10:    */ import net.minecraft.server.v1_8_R1.Blocks;
/*  11:    */ import net.minecraft.server.v1_8_R1.DamageSource;
/*  12:    */ import net.minecraft.server.v1_8_R1.EntityHuman;
/*  13:    */ import net.minecraft.server.v1_8_R1.EntityItem;
/*  14:    */ import net.minecraft.server.v1_8_R1.EntityPlayer;
/*  15:    */ import net.minecraft.server.v1_8_R1.NBTTagCompound;
/*  16:    */ import net.minecraft.server.v1_8_R1.NBTTagList;
/*  17:    */ import net.minecraft.server.v1_8_R1.NBTTagString;
/*  18:    */ import net.minecraft.server.v1_8_R1.World;
/*  19:    */ import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
/*  20:    */ import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
/*  21:    */ import org.bukkit.entity.Player;
/*  22:    */ 
/*  23:    */ public class EntityNMSItem
/*  24:    */   extends EntityItem
/*  25:    */   implements NMSItem
/*  26:    */ {
/*  27:    */   private boolean lockTick;
/*  28:    */   private CraftItemLine parentPiece;
/*  29:    */   
/*  30:    */   public EntityNMSItem(World world, CraftItemLine piece)
/*  31:    */   {
/*  32: 33 */     super(world);
/*  33: 34 */     this.pickupDelay = 2147483647;
/*  34: 35 */     this.parentPiece = piece;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void s_()
/*  38:    */   {
/*  39: 41 */     if (this.ticksLived % 20 == 0) {
/*  40: 43 */       if (this.vehicle == null) {
/*  41: 44 */         die();
/*  42:    */       }
/*  43:    */     }
/*  44: 48 */     if (!this.lockTick) {
/*  45: 49 */       super.s_();
/*  46:    */     }
/*  47:    */   }
/*  48:    */   
/*  49:    */   public net.minecraft.server.v1_8_R1.ItemStack getItemStack()
/*  50:    */   {
/*  51: 56 */     StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
/*  52: 57 */     if ((stacktrace.length > 2) && (stacktrace[2].getClassName().contains("EntityInsentient"))) {
/*  53: 58 */       return null;
/*  54:    */     }
/*  55: 61 */     return super.getItemStack();
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void d(EntityHuman human)
/*  59:    */   {
/*  60: 68 */     if ((human.locY < this.locY - 1.5D) || (human.locY > this.locY + 1.0D)) {
/*  61: 70 */       return;
/*  62:    */     }
/*  63: 73 */     if ((this.parentPiece.getPickupHandler() != null) && ((human instanceof EntityPlayer))) {
/*  64: 74 */       MainListener.handleItemLinePickup((Player)human.getBukkitEntity(), this.parentPiece.getPickupHandler(), this.parentPiece.getParent());
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void b(NBTTagCompound nbttagcompound) {}
/*  69:    */   
/*  70:    */   public boolean c(NBTTagCompound nbttagcompound)
/*  71:    */   {
/*  72: 87 */     return false;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public boolean d(NBTTagCompound nbttagcompound)
/*  76:    */   {
/*  77: 93 */     return false;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void e(NBTTagCompound nbttagcompound) {}
/*  81:    */   
/*  82:    */   public boolean isInvulnerable(DamageSource source)
/*  83:    */   {
/*  84:108 */     return true;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void setLockTick(boolean lock)
/*  88:    */   {
/*  89:113 */     this.lockTick = lock;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void die()
/*  93:    */   {
/*  94:118 */     setLockTick(false);
/*  95:119 */     super.die();
/*  96:    */   }
/*  97:    */   
/*  98:    */   public CraftEntity getBukkitEntity()
/*  99:    */   {
/* 100:124 */     if (this.bukkitEntity == null) {
/* 101:125 */       this.bukkitEntity = new CraftNMSItem(this.world.getServer(), this);
/* 102:    */     }
/* 103:127 */     return this.bukkitEntity;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public boolean isDeadNMS()
/* 107:    */   {
/* 108:132 */     return this.dead;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void killEntityNMS()
/* 112:    */   {
/* 113:137 */     die();
/* 114:    */   }
/* 115:    */   
/* 116:    */   public void setLocationNMS(double x, double y, double z)
/* 117:    */   {
/* 118:142 */     super.setPosition(x, y, z);
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void setItemStackNMS(org.bukkit.inventory.ItemStack stack)
/* 122:    */   {
/* 123:147 */     net.minecraft.server.v1_8_R1.ItemStack newItem = CraftItemStack.asNMSCopy(stack);
/* 124:149 */     if (newItem == null) {
/* 125:150 */       newItem = new net.minecraft.server.v1_8_R1.ItemStack(Blocks.BEDROCK);
/* 126:    */     }
/* 127:153 */     if (newItem.getTag() == null) {
/* 128:154 */       newItem.setTag(new NBTTagCompound());
/* 129:    */     }
/* 130:156 */     NBTTagCompound display = newItem.getTag().getCompound("display");
/* 131:158 */     if (!newItem.getTag().hasKey("display")) {
/* 132:159 */       newItem.getTag().set("display", display);
/* 133:    */     }
/* 134:162 */     NBTTagList tagList = new NBTTagList();
/* 135:163 */     tagList.add(new NBTTagString(ItemUtils.ANTISTACK_LORE));
/* 136:    */     
/* 137:165 */     display.set("Lore", tagList);
/* 138:166 */     newItem.count = 0;
/* 139:167 */     setItemStack(newItem);
/* 140:    */   }
/* 141:    */   
/* 142:    */   public int getIdNMS()
/* 143:    */   {
/* 144:172 */     return getId();
/* 145:    */   }
/* 146:    */   
/* 147:    */   public CraftItemLine getHologramLine()
/* 148:    */   {
/* 149:177 */     return this.parentPiece;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public void allowPickup(boolean pickup)
/* 153:    */   {
/* 154:182 */     if (pickup) {
/* 155:183 */       this.pickupDelay = 0;
/* 156:    */     } else {
/* 157:185 */       this.pickupDelay = 2147483647;
/* 158:    */     }
/* 159:    */   }
/* 160:    */   
/* 161:    */   public org.bukkit.entity.Entity getBukkitEntityNMS()
/* 162:    */   {
/* 163:191 */     return getBukkitEntity();
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void setPassengerOfNMS(NMSEntityBase vehicleBase)
/* 167:    */   {
/* 168:196 */     if ((vehicleBase == null) || (!(vehicleBase instanceof net.minecraft.server.v1_8_R1.Entity))) {
/* 169:198 */       return;
/* 170:    */     }
/* 171:201 */     net.minecraft.server.v1_8_R1.Entity entity = (net.minecraft.server.v1_8_R1.Entity)vehicleBase;
/* 172:    */     try
/* 173:    */     {
/* 174:204 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_8_R1.Entity.class, this, "ap", Double.valueOf(0.0D));
/* 175:205 */       ReflectionUtils.setPrivateField(net.minecraft.server.v1_8_R1.Entity.class, this, "aq", Double.valueOf(0.0D));
/* 176:    */     }
/* 177:    */     catch (Exception ex)
/* 178:    */     {
/* 179:207 */       DebugHandler.handleDebugException(ex);
/* 180:    */     }
/* 181:210 */     if (this.vehicle != null) {
/* 182:211 */       this.vehicle.passenger = null;
/* 183:    */     }
/* 184:214 */     this.vehicle = entity;
/* 185:215 */     entity.passenger = this;
/* 186:    */   }
/* 187:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.v1_8_R1.EntityNMSItem
 * JD-Core Version:    0.7.0.1
 */