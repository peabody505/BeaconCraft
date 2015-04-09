/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.protocollib;
/*  2:   */ 
/*  3:   */ import com.comphenix.protocol.PacketType;
/*  4:   */ import com.comphenix.protocol.PacketType.Play.Server;
/*  5:   */ import com.comphenix.protocol.events.PacketContainer;
/*  6:   */ import com.comphenix.protocol.reflect.StructureModifier;
/*  7:   */ import com.google.common.primitives.Ints;
/*  8:   */ import java.util.List;
/*  9:   */ 
/* 10:   */ public class WrapperPlayServerEntityDestroy
/* 11:   */   extends AbstractPacket
/* 12:   */ {
/* 13:27 */   public static final PacketType TYPE = PacketType.Play.Server.ENTITY_DESTROY;
/* 14:   */   
/* 15:   */   public WrapperPlayServerEntityDestroy()
/* 16:   */   {
/* 17:30 */     super(new PacketContainer(TYPE), TYPE);
/* 18:31 */     this.handle.getModifier().writeDefaults();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public WrapperPlayServerEntityDestroy(PacketContainer packet)
/* 22:   */   {
/* 23:35 */     super(packet, TYPE);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public List<Integer> getEntities()
/* 27:   */   {
/* 28:43 */     return Ints.asList((int[])this.handle.getIntegerArrays().read(0));
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void setEntities(int[] entities)
/* 32:   */   {
/* 33:51 */     this.handle.getIntegerArrays().write(0, entities);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void setEntities(List<Integer> entities)
/* 37:   */   {
/* 38:59 */     setEntities(Ints.toArray(entities));
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.protocollib.WrapperPlayServerEntityDestroy
 * JD-Core Version:    0.7.0.1
 */