/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*  4:   */ import java.io.DataInputStream;
/*  5:   */ import java.io.DataOutputStream;
/*  6:   */ import java.io.IOException;
/*  7:   */ import java.net.Socket;
/*  8:   */ import java.net.SocketTimeoutException;
/*  9:   */ import java.net.UnknownHostException;
/* 10:   */ import java.util.Arrays;
/* 11:   */ 
/* 12:   */ final class ServerPingerPreNetty
/* 13:   */   extends ServerPinger
/* 14:   */ {
/* 15:   */   public PingResponse fetchData(ServerAddress serverAddress, int timeout)
/* 16:   */     throws SocketTimeoutException, UnknownHostException, IOException, Exception
/* 17:   */   {
/* 18:21 */     Socket socket = null;
/* 19:22 */     DataOutputStream dataOut = null;
/* 20:23 */     DataInputStream dataIn = null;
/* 21:   */     try
/* 22:   */     {
/* 23:26 */       socket = new Socket(serverAddress.getAddress(), serverAddress.getPort());
/* 24:27 */       socket.setSoTimeout(timeout);
/* 25:28 */       dataOut = new DataOutputStream(socket.getOutputStream());
/* 26:29 */       dataIn = new DataInputStream(socket.getInputStream());
/* 27:30 */       PacketUtils.a(dataOut, "FE");
/* 28:31 */       PacketUtils.a(dataOut, "01");
/* 29:32 */       dataIn.readByte();
/* 30:33 */       dataIn.readByte();
/* 31:34 */       int length = dataIn.readByte() * 2;
/* 32:35 */       byte[] bytes = new byte[length];
/* 33:36 */       dataIn.readFully(bytes);
/* 34:37 */       socket.close();
/* 35:38 */       String[] info = new String(bytes, PacketUtils.UTF16BE).split(String.valueOf('\000'));
/* 36:   */       PingResponse localPingResponse1;
/* 37:40 */       if (info.length < 6)
/* 38:   */       {
/* 39:41 */         DebugHandler.logToConsole("Received invalid ping response: " + Arrays.toString(info));
/* 40:42 */         return new PingResponse(true, "Invalid ping response", 0, 0);
/* 41:   */       }
/* 42:45 */       PingResponse response = new PingResponse(true, info[3], Integer.parseInt(info[4]), Integer.parseInt(info[5]));
/* 43:   */       
/* 44:   */ 
/* 45:48 */       return response;
/* 46:   */     }
/* 47:   */     finally
/* 48:   */     {
/* 49:51 */       PacketUtils.closeQuietly(dataIn);
/* 50:52 */       PacketUtils.closeQuietly(dataOut);
/* 51:53 */       PacketUtils.closeQuietly(socket);
/* 52:   */     }
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.ServerPingerPreNetty
 * JD-Core Version:    0.7.0.1
 */