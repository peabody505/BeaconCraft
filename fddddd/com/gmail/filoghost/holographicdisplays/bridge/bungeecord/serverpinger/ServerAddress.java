/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger;
/*  2:   */ 
/*  3:   */ public class ServerAddress
/*  4:   */ {
/*  5:   */   private String ip;
/*  6:   */   private int port;
/*  7:   */   
/*  8:   */   public ServerAddress(String ip, int port)
/*  9:   */   {
/* 10: 9 */     this.ip = ip;
/* 11:10 */     this.port = port;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public String getAddress()
/* 15:   */   {
/* 16:14 */     return this.ip;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public int getPort()
/* 20:   */   {
/* 21:18 */     return this.port;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public String toString()
/* 25:   */   {
/* 26:23 */     return this.ip + ":" + this.port;
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.ServerAddress
 * JD-Core Version:    0.7.0.1
 */