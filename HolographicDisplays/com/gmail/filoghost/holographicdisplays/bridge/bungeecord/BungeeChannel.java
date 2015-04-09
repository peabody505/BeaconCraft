/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.bungeecord;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*  5:   */ import java.io.ByteArrayInputStream;
/*  6:   */ import java.io.ByteArrayOutputStream;
/*  7:   */ import java.io.DataInputStream;
/*  8:   */ import java.io.DataOutputStream;
/*  9:   */ import java.io.EOFException;
/* 10:   */ import java.io.IOException;
/* 11:   */ import java.util.logging.Logger;
/* 12:   */ import org.bukkit.Bukkit;
/* 13:   */ import org.bukkit.entity.Player;
/* 14:   */ import org.bukkit.plugin.Plugin;
/* 15:   */ import org.bukkit.plugin.messaging.Messenger;
/* 16:   */ import org.bukkit.plugin.messaging.PluginMessageListener;
/* 17:   */ 
/* 18:   */ public class BungeeChannel
/* 19:   */   implements PluginMessageListener
/* 20:   */ {
/* 21:   */   private static BungeeChannel instance;
/* 22:   */   
/* 23:   */   public static BungeeChannel getInstance()
/* 24:   */   {
/* 25:23 */     if (instance == null) {
/* 26:24 */       instance = new BungeeChannel(HolographicDisplays.getInstance());
/* 27:   */     }
/* 28:26 */     return instance;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public BungeeChannel(Plugin plugin)
/* 32:   */   {
/* 33:30 */     Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
/* 34:31 */     Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);
/* 35:32 */     Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "RedisBungee");
/* 36:33 */     Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "RedisBungee", this);
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void onPluginMessageReceived(String channel, Player player, byte[] message)
/* 40:   */   {
/* 41:38 */     if ((!channel.equals("BungeeCord")) && (!channel.equals("RedisBungee"))) {
/* 42:39 */       return;
/* 43:   */     }
/* 44:42 */     DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
/* 45:   */     try
/* 46:   */     {
/* 47:45 */       String subChannel = in.readUTF();
/* 48:47 */       if (subChannel.equals("PlayerCount"))
/* 49:   */       {
/* 50:49 */         String server = in.readUTF();
/* 51:51 */         if (in.available() > 0)
/* 52:   */         {
/* 53:52 */           int online = in.readInt();
/* 54:   */           
/* 55:54 */           BungeeServerInfo serverInfo = BungeeServerTracker.getOrCreateServerInfo(server);
/* 56:55 */           serverInfo.setOnlinePlayers(online);
/* 57:   */         }
/* 58:   */       }
/* 59:   */     }
/* 60:   */     catch (EOFException localEOFException) {}catch (IOException e)
/* 61:   */     {
/* 62:63 */       e.printStackTrace();
/* 63:   */     }
/* 64:   */   }
/* 65:   */   
/* 66:   */   public void askPlayerCount(String server)
/* 67:   */   {
/* 68:69 */     ByteArrayOutputStream b = new ByteArrayOutputStream();
/* 69:70 */     DataOutputStream out = new DataOutputStream(b);
/* 70:   */     try
/* 71:   */     {
/* 72:73 */       out.writeUTF("PlayerCount");
/* 73:74 */       out.writeUTF(server);
/* 74:   */     }
/* 75:   */     catch (IOException e)
/* 76:   */     {
/* 77:77 */       e.printStackTrace();
/* 78:78 */       HolographicDisplays.getInstance().getLogger().warning("I/O Exception while asking for player count on server '" + server + "'.");
/* 79:   */     }
/* 80:82 */     Player[] players = Bukkit.getOnlinePlayers();
/* 81:83 */     if (players.length > 0) {
/* 82:84 */       players[0].sendPluginMessage(HolographicDisplays.getInstance(), Configuration.useRedisBungee ? "RedisBungee" : "BungeeCord", b.toByteArray());
/* 83:   */     }
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.bungeecord.BungeeChannel
 * JD-Core Version:    0.7.0.1
 */