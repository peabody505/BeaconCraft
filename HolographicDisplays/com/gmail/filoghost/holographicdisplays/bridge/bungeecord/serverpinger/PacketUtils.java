/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger;
/*  2:   */ 
/*  3:   */ import java.io.Closeable;
/*  4:   */ import java.io.DataInputStream;
/*  5:   */ import java.io.DataOutputStream;
/*  6:   */ import java.io.IOException;
/*  7:   */ import java.nio.charset.Charset;
/*  8:   */ 
/*  9:   */ class PacketUtils
/* 10:   */ {
/* 11:14 */   public static final Charset UTF16BE = Charset.forName("UTF-16BE");
/* 12:15 */   public static final Charset UTF8 = Charset.forName("UTF-8");
/* 13:   */   
/* 14:   */   public static void a(DataOutputStream out, String s)
/* 15:   */     throws IOException
/* 16:   */   {
/* 17:18 */     int len = s.length();
/* 18:19 */     byte[] data = new byte[len / 2];
/* 19:20 */     for (int i = 0; i < len; i += 2) {
/* 20:21 */       data[(i / 2)] = ((byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16)));
/* 21:   */     }
/* 22:23 */     out.write(data);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public static void writeString(DataOutputStream out, String s, Charset charset)
/* 26:   */     throws IOException
/* 27:   */   {
/* 28:27 */     if (charset == UTF8) {
/* 29:28 */       writeVarInt(out, s.length());
/* 30:   */     } else {
/* 31:31 */       out.writeShort(s.length());
/* 32:   */     }
/* 33:33 */     out.write(s.getBytes(charset));
/* 34:   */   }
/* 35:   */   
/* 36:   */   public static int readVarInt(DataInputStream in)
/* 37:   */     throws IOException
/* 38:   */   {
/* 39:37 */     int i = 0;
/* 40:38 */     int j = 0;
/* 41:   */     int k;
/* 42:   */     do
/* 43:   */     {
/* 44:40 */       k = in.readByte();
/* 45:41 */       i |= (k & 0x7F) << j++ * 7;
/* 46:42 */       if (j > 5) {
/* 47:43 */         throw new RuntimeException("VarInt too big");
/* 48:   */       }
/* 49:45 */     } while ((k & 0x80) == 128);
/* 50:46 */     return i;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public static void writeVarInt(DataOutputStream out, int paramInt)
/* 54:   */     throws IOException
/* 55:   */   {
/* 56:52 */     while ((paramInt & 0xFFFFFF80) != 0)
/* 57:   */     {
/* 58:53 */       out.write(paramInt & 0x7F | 0x80);
/* 59:54 */       paramInt >>>= 7;
/* 60:   */     }
/* 61:56 */     out.write(paramInt);
/* 62:   */   }
/* 63:   */   
/* 64:   */   public static void closeQuietly(Closeable closeable)
/* 65:   */   {
/* 66:   */     try
/* 67:   */     {
/* 68:61 */       if (closeable != null) {
/* 69:62 */         closeable.close();
/* 70:   */       }
/* 71:   */     }
/* 72:   */     catch (IOException localIOException) {}
/* 73:   */   }
/* 74:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.PacketUtils
 * JD-Core Version:    0.7.0.1
 */