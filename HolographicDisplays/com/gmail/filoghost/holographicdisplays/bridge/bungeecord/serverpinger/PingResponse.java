/*  1:   */ package com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*  4:   */ import org.json.simple.JSONObject;
/*  5:   */ import org.json.simple.JSONValue;
/*  6:   */ 
/*  7:   */ public class PingResponse
/*  8:   */ {
/*  9:   */   private boolean isOnline;
/* 10:   */   private String motd;
/* 11:   */   private int onlinePlayers;
/* 12:   */   private int maxPlayers;
/* 13:   */   
/* 14:   */   public PingResponse(boolean isOnline, String motd, int onlinePlayers, int maxPlayers)
/* 15:   */   {
/* 16:18 */     this.isOnline = isOnline;
/* 17:19 */     this.motd = motd;
/* 18:20 */     this.onlinePlayers = onlinePlayers;
/* 19:21 */     this.maxPlayers = maxPlayers;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public PingResponse(String jsonString, ServerAddress address)
/* 23:   */   {
/* 24:26 */     if ((jsonString == null) || (jsonString.isEmpty()))
/* 25:   */     {
/* 26:27 */       this.motd = "Invalid ping response";
/* 27:28 */       DebugHandler.logToConsole("Received empty Json response from IP \"" + address.toString() + "\"!");
/* 28:29 */       return;
/* 29:   */     }
/* 30:32 */     Object jsonObject = JSONValue.parse(jsonString);
/* 31:34 */     if (!(jsonObject instanceof JSONObject))
/* 32:   */     {
/* 33:35 */       this.motd = "Invalid ping response";
/* 34:36 */       DebugHandler.logToConsole("Received invalid Json response from IP \"" + address.toString() + "\": " + jsonString);
/* 35:37 */       return;
/* 36:   */     }
/* 37:40 */     JSONObject json = (JSONObject)jsonObject;
/* 38:41 */     this.isOnline = true;
/* 39:   */     
/* 40:43 */     Object descriptionObject = json.get("description");
/* 41:45 */     if (descriptionObject != null)
/* 42:   */     {
/* 43:46 */       this.motd = descriptionObject.toString();
/* 44:   */     }
/* 45:   */     else
/* 46:   */     {
/* 47:48 */       this.motd = "Invalid ping response";
/* 48:49 */       DebugHandler.logToConsole("Received invalid Json response from IP \"" + address.toString() + "\": " + jsonString);
/* 49:   */     }
/* 50:52 */     Object playersObject = json.get("players");
/* 51:54 */     if ((playersObject instanceof JSONObject))
/* 52:   */     {
/* 53:55 */       JSONObject playersJson = (JSONObject)playersObject;
/* 54:   */       
/* 55:57 */       Object onlineObject = playersJson.get("online");
/* 56:58 */       if ((onlineObject instanceof Number)) {
/* 57:59 */         this.onlinePlayers = ((Number)onlineObject).intValue();
/* 58:   */       }
/* 59:62 */       Object maxObject = playersJson.get("max");
/* 60:63 */       if ((maxObject instanceof Number)) {
/* 61:64 */         this.maxPlayers = ((Number)maxObject).intValue();
/* 62:   */       }
/* 63:   */     }
/* 64:   */   }
/* 65:   */   
/* 66:   */   public boolean isOnline()
/* 67:   */   {
/* 68:70 */     return this.isOnline;
/* 69:   */   }
/* 70:   */   
/* 71:   */   public String getMotd()
/* 72:   */   {
/* 73:74 */     return this.motd;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public int getOnlinePlayers()
/* 77:   */   {
/* 78:78 */     return this.onlinePlayers;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public int getMaxPlayers()
/* 82:   */   {
/* 83:82 */     return this.maxPlayers;
/* 84:   */   }
/* 85:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.bridge.bungeecord.serverpinger.PingResponse
 * JD-Core Version:    0.7.0.1
 */