/*   1:    */ package com.gmail.filoghost.holographicdisplays;
/*   2:    */ 
/*   3:    */ import java.io.BufferedReader;
/*   4:    */ import java.io.IOException;
/*   5:    */ import java.io.InputStreamReader;
/*   6:    */ import java.net.MalformedURLException;
/*   7:    */ import java.net.URL;
/*   8:    */ import java.net.URLConnection;
/*   9:    */ import java.util.logging.Logger;
/*  10:    */ import java.util.regex.Matcher;
/*  11:    */ import java.util.regex.Pattern;
/*  12:    */ import org.bukkit.Bukkit;
/*  13:    */ import org.bukkit.plugin.Plugin;
/*  14:    */ import org.bukkit.plugin.PluginDescriptionFile;
/*  15:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  16:    */ import org.json.simple.JSONArray;
/*  17:    */ import org.json.simple.JSONObject;
/*  18:    */ import org.json.simple.JSONValue;
/*  19:    */ 
/*  20:    */ public final class SimpleUpdater
/*  21:    */ {
/*  22:    */   private Plugin plugin;
/*  23:    */   private int projectId;
/*  24:    */   
/*  25:    */   public SimpleUpdater(Plugin plugin, int projectId)
/*  26:    */   {
/*  27: 38 */     if (plugin == null) {
/*  28: 39 */       throw new NullPointerException("Plugin cannot be null");
/*  29:    */     }
/*  30: 42 */     this.plugin = plugin;
/*  31: 43 */     this.projectId = projectId;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void checkForUpdates(final ResponseHandler responseHandler)
/*  35:    */   {
/*  36: 51 */     Thread updaterThread = new Thread(new Runnable()
/*  37:    */     {
/*  38:    */       public void run()
/*  39:    */       {
/*  40:    */         try
/*  41:    */         {
/*  42: 57 */           JSONArray filesArray = (JSONArray)SimpleUpdater.this.readJson("https://api.curseforge.com/servermods/files?projectIds=" + SimpleUpdater.this.projectId);
/*  43: 59 */           if (filesArray.size() == 0)
/*  44:    */           {
/*  45: 61 */             SimpleUpdater.this.plugin.getLogger().warning("The author of this plugin has misconfigured the Updater system.");
/*  46: 62 */             SimpleUpdater.this.plugin.getLogger().warning("The project ID (" + SimpleUpdater.this.projectId + ") provided for updating is invalid.");
/*  47: 63 */             SimpleUpdater.this.plugin.getLogger().warning("Please notify the author of this error.");
/*  48: 64 */             return;
/*  49:    */           }
/*  50: 67 */           String updateName = (String)((JSONObject)filesArray.get(filesArray.size() - 1)).get("name");
/*  51: 68 */           final String newVersion = SimpleUpdater.this.extractVersion(updateName);
/*  52: 70 */           if (newVersion == null) {
/*  53: 71 */             throw new NumberFormatException();
/*  54:    */           }
/*  55: 74 */           if (SimpleUpdater.this.isNewerVersion(newVersion)) {
/*  56: 75 */             Bukkit.getScheduler().scheduleSyncDelayedTask(SimpleUpdater.this.plugin, new Runnable()
/*  57:    */             {
/*  58:    */               public void run()
/*  59:    */               {
/*  60: 79 */                 this.val$responseHandler.onUpdateFound(newVersion);
/*  61:    */               }
/*  62:    */             });
/*  63:    */           }
/*  64:    */         }
/*  65:    */         catch (IOException e)
/*  66:    */         {
/*  67: 85 */           SimpleUpdater.this.plugin.getLogger().warning("Could not contact BukkitDev to check for updates.");
/*  68:    */         }
/*  69:    */         catch (NumberFormatException e)
/*  70:    */         {
/*  71: 87 */           SimpleUpdater.this.plugin.getLogger().warning("The author of this plugin has misconfigured the Updater system.");
/*  72: 88 */           SimpleUpdater.this.plugin.getLogger().warning("File versions should follow the format 'PluginName vVERSION'");
/*  73: 89 */           SimpleUpdater.this.plugin.getLogger().warning("Please notify the author of this error.");
/*  74:    */         }
/*  75:    */         catch (Exception e)
/*  76:    */         {
/*  77: 91 */           e.printStackTrace();
/*  78: 92 */           SimpleUpdater.this.plugin.getLogger().warning("Unable to check for updates: unhandled exception.");
/*  79:    */         }
/*  80:    */       }
/*  81: 96 */     });
/*  82: 97 */     updaterThread.start();
/*  83:    */   }
/*  84:    */   
/*  85:    */   private Object readJson(String url)
/*  86:    */     throws MalformedURLException, IOException
/*  87:    */   {
/*  88:102 */     URLConnection conn = new URL(url).openConnection();
/*  89:103 */     conn.setConnectTimeout(5000);
/*  90:104 */     conn.setReadTimeout(8000);
/*  91:105 */     conn.addRequestProperty("User-Agent", "Updater (by filoghost)");
/*  92:106 */     conn.setDoOutput(true);
/*  93:    */     
/*  94:108 */     return JSONValue.parse(new BufferedReader(new InputStreamReader(conn.getInputStream())));
/*  95:    */   }
/*  96:    */   
/*  97:    */   private boolean isNewerVersion(String remoteVersion)
/*  98:    */   {
/*  99:120 */     String pluginVersion = this.plugin.getDescription().getVersion();
/* 100:122 */     if ((pluginVersion == null) || (!pluginVersion.matches("v?[0-9\\.]+"))) {
/* 101:124 */       pluginVersion = "0";
/* 102:    */     }
/* 103:127 */     if (!remoteVersion.matches("v?[0-9\\.]+")) {
/* 104:129 */       throw new IllegalArgumentException("fetched version's format is incorrect");
/* 105:    */     }
/* 106:133 */     String[] pluginVersionSplit = pluginVersion.replace("v", "").replaceAll("[\\.]{2,}", ".").split("\\.");
/* 107:134 */     String[] remoteVersionSplit = remoteVersion.replace("v", "").replaceAll("[\\.]{2,}", ".").split("\\.");
/* 108:    */     
/* 109:136 */     int longest = Math.max(pluginVersionSplit.length, remoteVersionSplit.length);
/* 110:    */     
/* 111:138 */     int[] pluginVersionArray = new int[longest];
/* 112:139 */     int[] remoteVersionArray = new int[longest];
/* 113:141 */     for (int i = 0; i < pluginVersionSplit.length; i++) {
/* 114:142 */       pluginVersionArray[i] = Integer.parseInt(pluginVersionSplit[i]);
/* 115:    */     }
/* 116:145 */     for (int i = 0; i < remoteVersionSplit.length; i++) {
/* 117:146 */       remoteVersionArray[i] = Integer.parseInt(remoteVersionSplit[i]);
/* 118:    */     }
/* 119:149 */     for (int i = 0; i < longest; i++)
/* 120:    */     {
/* 121:150 */       int diff = remoteVersionArray[i] - pluginVersionArray[i];
/* 122:151 */       if (diff > 0) {
/* 123:152 */         return true;
/* 124:    */       }
/* 125:153 */       if (diff < 0) {
/* 126:154 */         return false;
/* 127:    */       }
/* 128:    */     }
/* 129:160 */     return false;
/* 130:    */   }
/* 131:    */   
/* 132:    */   private String extractVersion(String input)
/* 133:    */   {
/* 134:164 */     Matcher matcher = Pattern.compile("v[0-9\\.]+").matcher(input);
/* 135:    */     
/* 136:166 */     String result = null;
/* 137:167 */     if (matcher.find()) {
/* 138:168 */       result = matcher.group();
/* 139:    */     }
/* 140:171 */     return result;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public static abstract interface ResponseHandler
/* 144:    */   {
/* 145:    */     public abstract void onUpdateFound(String paramString);
/* 146:    */   }
/* 147:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.SimpleUpdater
 * JD-Core Version:    0.7.0.1
 */