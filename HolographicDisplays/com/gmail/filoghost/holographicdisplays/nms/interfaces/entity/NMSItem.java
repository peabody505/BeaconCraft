package com.gmail.filoghost.holographicdisplays.nms.interfaces.entity;

import org.bukkit.inventory.ItemStack;

public abstract interface NMSItem
  extends NMSEntityBase, NMSCanMount
{
  public abstract void setItemStackNMS(ItemStack paramItemStack);
  
  public abstract void allowPickup(boolean paramBoolean);
}


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSItem
 * JD-Core Version:    0.7.0.1
 */