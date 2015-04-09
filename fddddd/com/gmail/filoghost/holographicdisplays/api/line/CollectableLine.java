package com.gmail.filoghost.holographicdisplays.api.line;

import com.gmail.filoghost.holographicdisplays.api.handler.PickupHandler;

public abstract interface CollectableLine
  extends HologramLine
{
  public abstract void setPickupHandler(PickupHandler paramPickupHandler);
  
  public abstract PickupHandler getPickupHandler();
}


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.api.line.CollectableLine
 * JD-Core Version:    0.7.0.1
 */