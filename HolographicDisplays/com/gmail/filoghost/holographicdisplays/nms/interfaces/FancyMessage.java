package com.gmail.filoghost.holographicdisplays.nms.interfaces;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract interface FancyMessage
{
  public abstract FancyMessage color(ChatColor paramChatColor);
  
  public abstract FancyMessage style(ChatColor... paramVarArgs);
  
  public abstract FancyMessage file(String paramString);
  
  public abstract FancyMessage link(String paramString);
  
  public abstract FancyMessage suggest(String paramString);
  
  public abstract FancyMessage command(String paramString);
  
  public abstract FancyMessage tooltip(String paramString);
  
  public abstract FancyMessage then(Object paramObject);
  
  public abstract String toJSONString();
  
  public abstract void send(Player paramPlayer);
}


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.nms.interfaces.FancyMessage
 * JD-Core Version:    0.7.0.1
 */