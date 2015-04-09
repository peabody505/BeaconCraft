/*   1:    */ package com.gmail.filoghost.holographicdisplays.bridge.protocollib;
/*   2:    */ 
/*   3:    */ import com.comphenix.protocol.PacketType;
/*   4:    */ import com.comphenix.protocol.PacketType.Play.Server;
/*   5:    */ import com.comphenix.protocol.events.PacketContainer;
/*   6:    */ import com.comphenix.protocol.events.PacketEvent;
/*   7:    */ import com.comphenix.protocol.reflect.StructureModifier;
/*   8:    */ import org.bukkit.World;
/*   9:    */ import org.bukkit.entity.Entity;
/*  10:    */ import org.bukkit.entity.Player;
/*  11:    */ 
/*  12:    */ public class WrapperPlayServerAttachEntity
/*  13:    */   extends AbstractPacket
/*  14:    */ {
/*  15: 28 */   public static final PacketType TYPE = PacketType.Play.Server.ATTACH_ENTITY;
/*  16:    */   
/*  17:    */   public WrapperPlayServerAttachEntity()
/*  18:    */   {
/*  19: 31 */     super(new PacketContainer(TYPE), TYPE);
/*  20: 32 */     this.handle.getModifier().writeDefaults();
/*  21:    */   }
/*  22:    */   
/*  23:    */   public WrapperPlayServerAttachEntity(PacketContainer packet)
/*  24:    */   {
/*  25: 36 */     super(packet, TYPE);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public boolean getLeached()
/*  29:    */   {
/*  30: 44 */     return ((Integer)this.handle.getIntegers().read(0)).intValue() != 0;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void setLeached(boolean value)
/*  34:    */   {
/*  35: 52 */     this.handle.getIntegers().write(0, Integer.valueOf(value ? 1 : 0));
/*  36:    */   }
/*  37:    */   
/*  38:    */   public int getEntityId()
/*  39:    */   {
/*  40: 60 */     return ((Integer)this.handle.getIntegers().read(1)).intValue();
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void setEntityId(int value)
/*  44:    */   {
/*  45: 68 */     this.handle.getIntegers().write(1, Integer.valueOf(value));
/*  46:    */   }
/*  47:    */   
/*  48:    */   public Entity getEntity(World world)
/*  49:    */   {
/*  50: 77 */     return (Entity)this.handle.getEntityModifier(world).read(1);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public Entity getEntity(PacketEvent event)
/*  54:    */   {
/*  55: 86 */     return getEntity(event.getPlayer().getWorld());
/*  56:    */   }
/*  57:    */   
/*  58:    */   public int getVehicleId()
/*  59:    */   {
/*  60: 94 */     return ((Integer)this.handle.getIntegers().read(2)).intValue();
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void setVehicleId(int value)
/*  64:    */   {
/*  65:102 */     this.handle.getIntegers().write(2, Integer.valueOf(value));
/*  66:    */   }
/*  67:    */   
/*  68:    */   public Entity getVehicle(World world)
/*  69:    */   {
/*  70:111 */     return (Entity)this.handle.getEntityModifier(world).read(2);
/*  71:    */   }
/*  72:    */   
/*  73:    */   public Entity getVehicle(PacketEvent event)
/*  74:    */   {
/*  75:120 */     return getVehicle(event.getPlayer().getWorld());
/*  76:    */   }
/*  77:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.protocollib.WrapperPlayServerAttachEntity
 * JD-Core Version:    0.7.0.1
 */