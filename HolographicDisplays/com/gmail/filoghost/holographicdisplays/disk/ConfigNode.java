/*  1:   */ package com.gmail.filoghost.holographicdisplays.disk;
/*  2:   */ 
/*  3:   */ import java.util.Arrays;
/*  4:   */ 
/*  5:   */ public enum ConfigNode
/*  6:   */ {
/*  7: 7 */   SPACE_BETWEEN_LINES("space-between-lines", Double.valueOf(0.02D)),  IMAGES_SYMBOL("images.symbol", "[x]"),  TRANSPARENCY_SPACE("images.transparency.space", " [|] "),  TRANSPARENCY_COLOR("images.transparency.color", "&7"),  UPDATE_NOTIFICATION("update-notification", Boolean.valueOf(true)),  BUNGEE_REFRESH_SECONDS("bungee.refresh-seconds", Integer.valueOf(3)),  BUNGEE_USE_REDIS_BUNGEE("bungee.use-RedisBungee", Boolean.valueOf(false)),  BUNGEE_USE_FULL_PINGER("bungee.pinger.enable", Boolean.valueOf(false)),  BUNGEE_PINGER_TIMEOUT("bungee.pinger.timeout", Integer.valueOf(500)),  BUNGEE_PINGER_OFFLINE_MOTD("bungee.pinger.offline-motd", "&cOffline, couldn't get the MOTD."),  BUNGEE_PINGER_ONLINE_FORMAT("bungee.pinger.status.online", "&aOnline"),  BUNGEE_PINGER_OFFLINE_FORMAT("bungee.pinger.status.offline", "&cOffline"),  BUNGEE_PINGER_TRIM_MOTD("bungee.pinger.motd-remove-leading-trailing-spaces", Boolean.valueOf(true)),  BUNGEE_PINGER_SERVERS("bungee.pinger.servers", Arrays.asList(new String[] { "hub: 127.0.0.1:25565", "survival: 127.0.0.1:25566", "minigames: 127.0.0.1:25567" })),  TIME_FORMAT("time.format", "H:mm"),  TIME_ZONE("time.zone", "GMT+1"),  DEBUG("debug", Boolean.valueOf(false));
/*  8:   */   
/*  9:   */   private final String path;
/* 10:   */   private final Object value;
/* 11:   */   
/* 12:   */   private ConfigNode(String path, Object defaultValue)
/* 13:   */   {
/* 14:29 */     this.path = path;
/* 15:30 */     this.value = defaultValue;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public String getPath()
/* 19:   */   {
/* 20:34 */     return this.path;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public Object getDefaultValue()
/* 24:   */   {
/* 25:38 */     return this.value;
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.disk.ConfigNode
 * JD-Core Version:    0.7.0.1
 */