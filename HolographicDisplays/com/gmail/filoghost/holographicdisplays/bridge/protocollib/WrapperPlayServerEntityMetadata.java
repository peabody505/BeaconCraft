/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.protocollib;
/*  2:   */ 
/*  3:   */ import com.comphenix.protocol.PacketType;
/*  4:   */ import com.comphenix.protocol.PacketType.Play.Server;
/*  5:   */ import com.comphenix.protocol.events.PacketContainer;
/*  6:   */ import com.comphenix.protocol.events.PacketEvent;
/*  7:   */ import com.comphenix.protocol.reflect.StructureModifier;
/*  8:   */ import com.comphenix.protocol.wrappers.WrappedWatchableObject;
/*  9:   */ import java.util.List;
/* 10:   */ import org.bukkit.World;
/* 11:   */ import org.bukkit.entity.Entity;
/* 12:   */ import org.bukkit.entity.Player;
/* 13:   */ 
/* 14:   */ public class WrapperPlayServerEntityMetadata
/* 15:   */   extends AbstractPacket
/* 16:   */ {
/* 17:32 */   public static final PacketType TYPE = PacketType.Play.Server.ENTITY_METADATA;
/* 18:   */   
/* 19:   */   public WrapperPlayServerEntityMetadata()
/* 20:   */   {
/* 21:35 */     super(new PacketContainer(TYPE), TYPE);
/* 22:36 */     this.handle.getModifier().writeDefaults();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public WrapperPlayServerEntityMetadata(PacketContainer packet)
/* 26:   */   {
/* 27:40 */     super(packet, TYPE);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int getEntityId()
/* 31:   */   {
/* 32:48 */     return ((Integer)this.handle.getIntegers().read(0)).intValue();
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setEntityId(int value)
/* 36:   */   {
/* 37:56 */     this.handle.getIntegers().write(0, Integer.valueOf(value));
/* 38:   */   }
/* 39:   */   
/* 40:   */   public Entity getEntity(World world)
/* 41:   */   {
/* 42:65 */     return (Entity)this.handle.getEntityModifier(world).read(0);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public Entity getEntity(PacketEvent event)
/* 46:   */   {
/* 47:74 */     return getEntity(event.getPlayer().getWorld());
/* 48:   */   }
/* 49:   */   
/* 50:   */   public List<WrappedWatchableObject> getEntityMetadata()
/* 51:   */   {
/* 52:84 */     return (List)this.handle.getWatchableCollectionModifier().read(0);
/* 53:   */   }
/* 54:   */   
/* 55:   */   public void setEntityMetadata(List<WrappedWatchableObject> value)
/* 56:   */   {
/* 57:92 */     this.handle.getWatchableCollectionModifier().write(0, value);
/* 58:   */   }
/* 59:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.protocollib.WrapperPlayServerEntityMetadata
 * JD-Core Version:    0.7.0.1
 */