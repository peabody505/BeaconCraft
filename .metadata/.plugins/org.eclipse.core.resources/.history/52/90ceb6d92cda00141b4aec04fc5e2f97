/*   1:    */ package com.gmail.filoghost.holographicdisplays.object.line;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSSlime;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
/*   8:    */ import org.bukkit.World;
/*   9:    */ 
/*  10:    */ public class CraftTouchSlimeLine
/*  11:    */   extends CraftHologramLine
/*  12:    */ {
/*  13:    */   private CraftTouchableLine touchablePiece;
/*  14:    */   private NMSSlime nmsSlime;
/*  15:    */   private NMSEntityBase nmsVehicle;
/*  16:    */   
/*  17:    */   protected CraftTouchSlimeLine(CraftHologram parent, CraftTouchableLine touchablePiece)
/*  18:    */   {
/*  19: 24 */     super(0.5D, parent);
/*  20: 25 */     this.touchablePiece = touchablePiece;
/*  21:    */   }
/*  22:    */   
/*  23:    */   public CraftTouchableLine getTouchablePiece()
/*  24:    */   {
/*  25: 29 */     return this.touchablePiece;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void spawn(World world, double x, double y, double z)
/*  29:    */   {
/*  30: 35 */     super.spawn(world, x, y, z);
/*  31:    */     
/*  32: 37 */     double offset = HolographicDisplays.is1_8() ? -1.49D : -0.22D;
/*  33:    */     
/*  34: 39 */     this.nmsSlime = HolographicDisplays.getNMSManager().spawnNMSSlime(world, x, y + offset, z, this);
/*  35: 41 */     if (HolographicDisplays.is1_8()) {
/*  36: 42 */       this.nmsVehicle = HolographicDisplays.getNMSManager().spawnNMSArmorStand(world, x, y + offset, z, this);
/*  37:    */     } else {
/*  38: 44 */       this.nmsVehicle = HolographicDisplays.getNMSManager().spawnNMSWitherSkull(world, x, y + offset, z, this);
/*  39:    */     }
/*  40: 47 */     this.nmsSlime.setPassengerOfNMS(this.nmsVehicle);
/*  41:    */     
/*  42: 49 */     this.nmsSlime.setLockTick(true);
/*  43: 50 */     this.nmsVehicle.setLockTick(true);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void despawn()
/*  47:    */   {
/*  48: 56 */     super.despawn();
/*  49: 58 */     if (this.nmsSlime != null)
/*  50:    */     {
/*  51: 59 */       this.nmsSlime.killEntityNMS();
/*  52: 60 */       this.nmsSlime = null;
/*  53:    */     }
/*  54: 63 */     if (this.nmsVehicle != null)
/*  55:    */     {
/*  56: 64 */       this.nmsVehicle.killEntityNMS();
/*  57: 65 */       this.nmsVehicle = null;
/*  58:    */     }
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void teleport(double x, double y, double z)
/*  62:    */   {
/*  63: 73 */     double offset = HolographicDisplays.is1_8() ? -1.49D : -0.22D;
/*  64: 75 */     if (this.nmsVehicle != null) {
/*  65: 76 */       this.nmsVehicle.setLocationNMS(x, y + offset, z);
/*  66:    */     }
/*  67: 79 */     if (this.nmsSlime != null) {
/*  68: 80 */       this.nmsSlime.setLocationNMS(x, y + offset, z);
/*  69:    */     }
/*  70:    */   }
/*  71:    */   
/*  72:    */   public int[] getEntitiesIDs()
/*  73:    */   {
/*  74: 86 */     if (isSpawned()) {
/*  75: 87 */       return new int[] { this.nmsVehicle.getIdNMS(), this.nmsSlime.getIdNMS() };
/*  76:    */     }
/*  77: 89 */     return new int[0];
/*  78:    */   }
/*  79:    */   
/*  80:    */   public NMSSlime getNmsSlime()
/*  81:    */   {
/*  82: 94 */     return this.nmsSlime;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public NMSEntityBase getNmsVehicle()
/*  86:    */   {
/*  87: 98 */     return this.nmsVehicle;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public String toString()
/*  91:    */   {
/*  92:103 */     return "CraftTouchSlimeLine [touchablePiece=" + this.touchablePiece + "]";
/*  93:    */   }
/*  94:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.object.line.CraftTouchSlimeLine
 * JD-Core Version:    0.7.0.1
 */