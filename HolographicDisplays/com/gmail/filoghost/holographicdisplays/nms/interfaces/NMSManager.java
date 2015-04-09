package com.gmail.filoghost.holographicdisplays.nms.interfaces;

import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSArmorStand;
import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSHorse;
import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSItem;
import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSSlime;
import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSWitherSkull;
import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
import com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine;
import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchSlimeLine;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public abstract interface NMSManager
{
  public abstract void setup()
    throws Exception;
  
  public abstract NMSArmorStand spawnNMSArmorStand(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, CraftHologramLine paramCraftHologramLine);
  
  public abstract NMSHorse spawnNMSHorse(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, CraftHologramLine paramCraftHologramLine);
  
  public abstract NMSWitherSkull spawnNMSWitherSkull(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, CraftHologramLine paramCraftHologramLine);
  
  public abstract NMSItem spawnNMSItem(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, CraftItemLine paramCraftItemLine, ItemStack paramItemStack);
  
  public abstract NMSSlime spawnNMSSlime(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, CraftTouchSlimeLine paramCraftTouchSlimeLine);
  
  public abstract boolean isNMSEntityBase(Entity paramEntity);
  
  public abstract NMSEntityBase getNMSEntityBase(Entity paramEntity);
  
  public abstract FancyMessage newFancyMessage(String paramString);
  
  public abstract boolean hasChatHoverFeature();
}


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.interfaces.NMSManager
 * JD-Core Version:    0.7.0.1
 */