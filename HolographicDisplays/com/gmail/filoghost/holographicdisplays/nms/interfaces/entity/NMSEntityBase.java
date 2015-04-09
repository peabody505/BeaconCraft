package com.gmail.filoghost.holographicdisplays.nms.interfaces.entity;

import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
import org.bukkit.entity.Entity;

public abstract interface NMSEntityBase
{
  public abstract CraftHologramLine getHologramLine();
  
  public abstract void setLockTick(boolean paramBoolean);
  
  public abstract void setLocationNMS(double paramDouble1, double paramDouble2, double paramDouble3);
  
  public abstract boolean isDeadNMS();
  
  public abstract void killEntityNMS();
  
  public abstract int getIdNMS();
  
  public abstract Entity getBukkitEntityNMS();
}


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase
 * JD-Core Version:    0.7.0.1
 */