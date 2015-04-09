/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger;
/*  2:   */ 
/*  3:   */ import java.io.ByteArrayOutputStream;
/*  4:   */ import java.io.DataInputStream;
/*  5:   */ import java.io.DataOutputStream;
/*  6:   */ import java.io.IOException;
/*  7:   */ import java.net.Socket;
/*  8:   */ import java.net.SocketTimeoutException;
/*  9:   */ import java.net.UnknownHostException;
/* 10:   */ 
/* 11:   */ final class ServerPingerPostNetty
/* 12:   */   extends ServerPinger
/* 13:   */ {
/* 14:   */   public PingResponse fetchData(ServerAddress serverAddress, int timeout)
/* 15:   */     throws SocketTimeoutException, UnknownHostException, IOException, Exception
/* 16:   */   {
/* 17:16 */     Socket socket = null;
/* 18:17 */     DataOutputStream dataOut = null;
/* 19:18 */     DataInputStream dataIn = null;
/* 20:   */     try
/* 21:   */     {
/* 22:21 */       socket = new Socket(serverAddress.getAddress(), serverAddress.getPort());
/* 23:22 */       socket.setSoTimeout(timeout);
/* 24:23 */       dataOut = new DataOutputStream(socket.getOutputStream());
/* 25:24 */       dataIn = new DataInputStream(socket.getInputStream());
/* 26:25 */       ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
/* 27:26 */       DataOutputStream handshake = new DataOutputStream(byteOut);
/* 28:27 */       handshake.write(0);
/* 29:28 */       PacketUtils.writeVarInt(handshake, 4);
/* 30:29 */       PacketUtils.writeString(handshake, serverAddress.getAddress(), PacketUtils.UTF8);
/* 31:30 */       handshake.writeShort(serverAddress.getPort());
/* 32:31 */       PacketUtils.writeVarInt(handshake, 1);
/* 33:32 */       byte[] bytes = byteOut.toByteArray();
/* 34:33 */       PacketUtils.writeVarInt(dataOut, bytes.length);
/* 35:34 */       dataOut.write(bytes);
/* 36:35 */       bytes = new byte[1];
/* 37:36 */       PacketUtils.writeVarInt(dataOut, bytes.length);
/* 38:37 */       dataOut.write(bytes);
/* 39:38 */       PacketUtils.readVarInt(dataIn);
/* 40:39 */       PacketUtils.readVarInt(dataIn);
/* 41:40 */       byte[] responseData = new byte[PacketUtils.readVarInt(dataIn)];
/* 42:41 */       dataIn.readFully(responseData);
/* 43:42 */       String jsonString = new String(responseData, PacketUtils.UTF8);
/* 44:43 */       return new PingResponse(jsonString, serverAddress);
/* 45:   */     }
/* 46:   */     finally
/* 47:   */     {
/* 48:46 */       PacketUtils.closeQuietly(dataOut);
/* 49:47 */       PacketUtils.closeQuietly(dataIn);
/* 50:48 */       PacketUtils.closeQuietly(socket);
/* 51:   */     }
/* 52:   */   }
/* 53:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.ServerPingerPostNetty
 * JD-Core Version:    0.7.0.1
 */