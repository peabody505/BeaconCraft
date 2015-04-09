/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import java.net.SocketTimeoutException;
/*  5:   */ import java.net.UnknownHostException;
/*  6:   */ 
/*  7:   */ public abstract class ServerPinger
/*  8:   */ {
/*  9:11 */   public static final ServerPinger POST_NETTY_REWRITE = new ServerPingerPostNetty();
/* 10:14 */   public static final ServerPinger PRE_NETTY_REWRITE = new ServerPingerPreNetty();
/* 11:   */   
/* 12:   */   public abstract PingResponse fetchData(ServerAddress paramServerAddress, int paramInt)
/* 13:   */     throws SocketTimeoutException, UnknownHostException, IOException, Exception;
/* 14:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.ServerPinger
 * JD-Core Version:    0.7.0.1
 */