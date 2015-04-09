/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.bungeecord;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*  4:   */ 
/*  5:   */ public class BungeeServerInfo
/*  6:   */ {
/*  7:   */   private volatile boolean isOnline;
/*  8:   */   private volatile int onlinePlayers;
/*  9:   */   private volatile int maxPlayers;
/* 10:   */   private volatile String motd1;
/* 11:   */   private volatile String motd2;
/* 12:   */   private volatile long lastRequest;
/* 13:   */   
/* 14:   */   protected BungeeServerInfo()
/* 15:   */   {
/* 16:18 */     this.isOnline = false;
/* 17:19 */     this.motd1 = "";
/* 18:20 */     this.motd2 = "";
/* 19:21 */     updateLastRequest();
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean isOnline()
/* 23:   */   {
/* 24:25 */     return this.isOnline;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setOnline(boolean isOnline)
/* 28:   */   {
/* 29:29 */     this.isOnline = isOnline;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getOnlinePlayers()
/* 33:   */   {
/* 34:33 */     return this.onlinePlayers;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setOnlinePlayers(int onlinePlayers)
/* 38:   */   {
/* 39:37 */     this.onlinePlayers = onlinePlayers;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public int getMaxPlayers()
/* 43:   */   {
/* 44:41 */     return this.maxPlayers;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setMaxPlayers(int maxPlayers)
/* 48:   */   {
/* 49:45 */     this.maxPlayers = maxPlayers;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public String getMotd1()
/* 53:   */   {
/* 54:49 */     return this.motd1;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public String getMotd2()
/* 58:   */   {
/* 59:53 */     return this.motd2;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void setMotd(String motd)
/* 63:   */   {
/* 64:58 */     if (motd == null)
/* 65:   */     {
/* 66:59 */       this.motd1 = "";
/* 67:60 */       this.motd2 = "";
/* 68:61 */       return;
/* 69:   */     }
/* 70:64 */     if (motd.contains("\n"))
/* 71:   */     {
/* 72:65 */       String[] split = motd.split("\n");
/* 73:66 */       this.motd1 = (Configuration.pingerTrimMotd ? split[0].trim() : split[0]);
/* 74:67 */       this.motd2 = (Configuration.pingerTrimMotd ? split[1].trim() : split[1]);
/* 75:   */     }
/* 76:   */     else
/* 77:   */     {
/* 78:69 */       this.motd1 = (Configuration.pingerTrimMotd ? motd.trim() : motd);
/* 79:70 */       this.motd2 = "";
/* 80:   */     }
/* 81:   */   }
/* 82:   */   
/* 83:   */   public long getLastRequest()
/* 84:   */   {
/* 85:75 */     return this.lastRequest;
/* 86:   */   }
/* 87:   */   
/* 88:   */   public void updateLastRequest()
/* 89:   */   {
/* 90:79 */     this.lastRequest = System.currentTimeMillis();
/* 91:   */   }
/* 92:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.bungeecord.BungeeServerInfo
 * JD-Core Version:    0.7.0.1
 */