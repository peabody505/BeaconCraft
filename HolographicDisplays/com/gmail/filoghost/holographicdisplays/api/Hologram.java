package com.gmail.filoghost.holographicdisplays.api;

import com.gmail.filoghost.holographicdisplays.api.line.HologramLine;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public abstract interface Hologram
{
  public abstract TextLine appendTextLine(String paramString);
  
  public abstract ItemLine appendItemLine(ItemStack paramItemStack);
  
  public abstract TextLine insertTextLine(int paramInt, String paramString);
  
  public abstract ItemLine insertItemLine(int paramInt, ItemStack paramItemStack);
  
  public abstract HologramLine getLine(int paramInt);
  
  public abstract void removeLine(int paramInt);
  
  public abstract void clearLines();
  
  public abstract int size();
  
  public abstract double getHeight();
  
  public abstract void teleport(Location paramLocation);
  
  public abstract void teleport(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3);
  
  public abstract Location getLocation();
  
  public abstract double getX();
  
  public abstract double getY();
  
  public abstract double getZ();
  
  public abstract World getWorld();
  
  public abstract VisibilityManager getVisibilityManager();
  
  public abstract long getCreationTimestamp();
  
  public abstract boolean isAllowPlaceholders();
  
  public abstract void setAllowPlaceholders(boolean paramBoolean);
  
  public abstract void delete();
  
  public abstract boolean isDeleted();
}


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.api.Hologram
 * JD-Core Version:    0.7.0.1
 */