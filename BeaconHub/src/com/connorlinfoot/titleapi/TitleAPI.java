/*   1:    */ package com.connorlinfoot.titleapi;
/*   2:    */ 
/*   3:    */ import java.lang.reflect.Field;
/*   4:    */ import java.util.logging.Logger;
/*   5:    */ import net.minecraft.server.v1_8_R1.ChatSerializer;
/*   6:    */ import net.minecraft.server.v1_8_R1.EntityPlayer;
/*   7:    */ import net.minecraft.server.v1_8_R1.EnumTitleAction;
/*   8:    */ import net.minecraft.server.v1_8_R1.IChatBaseComponent;
/*   9:    */ import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
/*  10:    */ import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
/*  11:    */ import net.minecraft.server.v1_8_R1.PlayerConnection;
/*  12:    */ import org.bukkit.Bukkit;
/*  13:    */ import org.bukkit.ChatColor;
/*  14:    */ import org.bukkit.Server;
/*  15:    */ import org.bukkit.command.ConsoleCommandSender;
/*  18:    */ import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
/*  19:    */ import org.bukkit.entity.Player;
/*  20:    */ import org.bukkit.event.EventHandler;
/*  21:    */ import org.bukkit.event.Listener;
/*  22:    */ import org.bukkit.event.player.PlayerJoinEvent;
/*  23:    */ import org.bukkit.plugin.PluginDescriptionFile;
/*  24:    */ import org.bukkit.plugin.PluginManager;
/*  25:    */ import org.bukkit.plugin.java.JavaPlugin;
/*  26:    */ 
/*  27:    */ public class TitleAPI
/*  28:    */   extends JavaPlugin
/*  29:    */   implements Listener
/*  30:    */ {
/*  31:    */   @Deprecated
/*  32:    */   public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message)
/*  33:    */   {
/*  34: 22 */     sendTitle(player, fadeIn, stay, fadeOut, message, null);
/*  35:    */   }
/*  36:    */   
/*  37:    */   @Deprecated
/*  38:    */   public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message)
/*  39:    */   {
/*  40: 27 */     sendTitle(player, fadeIn, stay, fadeOut, null, message);
/*  41:    */   }
/*  42:    */   
/*  43:    */   @Deprecated
/*  44:    */   public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
/*  45:    */   {
/*  46: 32 */     sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
/*  47:    */   }
/*  48:    */   
/*  49:    */   public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
/*  50:    */   {
/*  51: 36 */     PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
/*  52:    */     
/*  53: 38 */     PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
/*  54: 39 */     connection.sendPacket(packetPlayOutTimes);
/*  55: 41 */     if (subtitle != null)
/*  56:    */     {
/*  57: 42 */       subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
/*  58: 43 */       subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
/*  59: 44 */       IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
/*  60: 45 */       PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, titleSub);
/*  61: 46 */       connection.sendPacket(packetPlayOutSubTitle);
/*  62:    */     }
/*  63: 49 */     if (title != null)
/*  64:    */     {
/*  65: 50 */       title = title.replaceAll("%player%", player.getDisplayName());
/*  66: 51 */       title = ChatColor.translateAlternateColorCodes('&', title);
/*  67: 52 */       IChatBaseComponent titleMain = ChatSerializer.a("{\"text\": \"" + title + "\"}");
/*  68: 53 */       PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleMain);
/*  69: 54 */       connection.sendPacket(packetPlayOutTitle);
/*  70:    */     }
/*  71:    */   }
/*  72:    */   
/*  73:    */   public static void sendTabTitle(Player player, String header, String footer)
/*  74:    */   {
/*  75: 59 */     if (header == null) {
/*  76: 59 */       header = "";
/*  77:    */     }
/*  78: 60 */     header = ChatColor.translateAlternateColorCodes('&', header);
/*  79: 62 */     if (footer == null) {
/*  80: 62 */       footer = "";
/*  81:    */     }
/*  82: 63 */     footer = ChatColor.translateAlternateColorCodes('&', footer);
/*  83:    */     
/*  84: 65 */     header = header.replaceAll("%player%", player.getDisplayName());
/*  85: 66 */     footer = footer.replaceAll("%player%", player.getDisplayName());
/*  86:    */     
/*  87: 68 */     PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
/*  88: 69 */     IChatBaseComponent tabTitle = ChatSerializer.a("{\"text\": \"" + header + "\"}");
/*  89: 70 */     IChatBaseComponent tabFoot = ChatSerializer.a("{\"text\": \"" + footer + "\"}");
/*  90: 71 */     PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
/*  91:    */     try
/*  92:    */     {
/*  93: 74 */       Field field = headerPacket.getClass().getDeclaredField("b");
/*  94: 75 */       field.setAccessible(true);
/*  95: 76 */       field.set(headerPacket, tabFoot);
/*  96:    */     }
/*  97:    */     catch (Exception e)
/*  98:    */     {
/*  99: 78 */       e.printStackTrace();
/* 100:    */     }
/* 101:    */     finally
/* 102:    */     {
/* 103: 80 */       connection.sendPacket(headerPacket);
/* 104:    */     }
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void onEnable()
/* 108:    */   {
/* 111: 87 */     Server server = getServer();
/* 112: 88 */     ConsoleCommandSender console = server.getConsoleSender();
/* 113:    */     
/* 114: 90 */     console.sendMessage("");
/* 115: 91 */     console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
/* 116: 92 */     console.sendMessage("");
/* 117: 93 */     console.sendMessage(ChatColor.AQUA + getDescription().getName());
/* 118: 94 */     console.sendMessage(ChatColor.AQUA + "Version " + getDescription().getVersion());
/* 119: 95 */     console.sendMessage("");
/* 120: 96 */     console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
/* 121: 97 */     console.sendMessage("");
/* 122:    */     
/* 123: 99 */     Bukkit.getPluginManager().registerEvents(this, this);
/* 124:    */   }
/* 125:    */   
/* 126:    */   boolean isInteger(String s)
/* 127:    */   {
/* 128:    */     try
/* 129:    */     {
/* 130:104 */       Integer.parseInt(s);
/* 131:    */     }
/* 132:    */     catch (NumberFormatException e)
/* 133:    */     {
/* 134:106 */       return false;
/* 135:    */     }
/* 136:108 */     return true;
/* 137:    */   }
/* 149:    */   
/* 150:    */   public void onDisable()
/* 151:    */   {
/* 152:123 */     getLogger().info(getDescription().getName() + " has been disabled!");
/* 153:    */   }
/* 154:    */ }


/* Location:           C:\Users\Rufus\Downloads\TitleAPI.jar
 * Qualified Name:     com.connorlinfoot.titleapi.TitleAPI
 * JD-Core Version:    0.7.0.1
 */