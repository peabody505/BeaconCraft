/*  1:   */ package com.gmail.filoghost.holographicdisplays.placeholder;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.disk.StringConverter;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.util.DebugHandler;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.util.FileUtils;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  8:   */ import java.io.File;
/*  9:   */ import java.util.List;
/* 10:   */ import java.util.Map;
/* 11:   */ import java.util.logging.Logger;
/* 12:   */ import org.bukkit.plugin.Plugin;
/* 13:   */ 
/* 14:   */ public class AnimationsRegister
/* 15:   */ {
/* 16:18 */   private static final Map<String, Placeholder> animations = ;
/* 17:   */   
/* 18:   */   public static void loadAnimations(Plugin plugin)
/* 19:   */   {
/* 20:21 */     animations.clear();
/* 21:   */     
/* 22:23 */     File animationFolder = new File(plugin.getDataFolder(), "animations");
/* 23:24 */     if (!animationFolder.isDirectory())
/* 24:   */     {
/* 25:25 */       animationFolder.mkdirs();
/* 26:26 */       plugin.saveResource("animations/example.txt", false);
/* 27:27 */       return;
/* 28:   */     }
/* 29:30 */     for (File file : animationFolder.listFiles()) {
/* 30:   */       try
/* 31:   */       {
/* 32:33 */         List<String> lines = FileUtils.readLines(file);
/* 33:34 */         if (lines.size() != 0)
/* 34:   */         {
/* 35:38 */           double speed = 0.5D;
/* 36:39 */           boolean validSpeedFound = false;
/* 37:   */           
/* 38:41 */           String firstLine = ((String)lines.get(0)).trim();
/* 39:42 */           if (firstLine.toLowerCase().startsWith("speed:"))
/* 40:   */           {
/* 41:45 */             lines.remove(0);
/* 42:   */             
/* 43:47 */             firstLine = firstLine.substring("speed:".length()).trim();
/* 44:   */             try
/* 45:   */             {
/* 46:50 */               speed = Double.parseDouble(firstLine);
/* 47:51 */               validSpeedFound = true;
/* 48:   */             }
/* 49:   */             catch (NumberFormatException localNumberFormatException) {}
/* 50:   */           }
/* 51:55 */           if (!validSpeedFound) {
/* 52:56 */             plugin.getLogger().warning("Could not find a valid 'speed: <number>' in the first line of the file '" + file.getName() + "'. Default speed of 0.5 seconds will be used.");
/* 53:   */           }
/* 54:59 */           if (lines.isEmpty())
/* 55:   */           {
/* 56:60 */             lines.add("[No lines: " + file.getName() + "]");
/* 57:61 */             plugin.getLogger().warning("Could not find any line in '" + file.getName() + "' (excluding the speed). You should add at least one more line.");
/* 58:   */           }
/* 59:65 */           for (int i = 0; i < lines.size(); i++) {
/* 60:66 */             lines.set(i, StringConverter.toReadableFormat((String)lines.get(i)));
/* 61:   */           }
/* 62:69 */           animations.put(file.getName(), new Placeholder(HolographicDisplays.getInstance(), file.getName(), speed, new CyclicPlaceholderReplacer((String[])lines.toArray(new String[lines.size()]))));
/* 63:70 */           DebugHandler.handleAnimationLoadSuccess(file.getName(), speed);
/* 64:   */         }
/* 65:   */       }
/* 66:   */       catch (Exception e)
/* 67:   */       {
/* 68:73 */         e.printStackTrace();
/* 69:74 */         plugin.getLogger().severe("Couldn't load the file '" + file.getName() + "'!");
/* 70:   */       }
/* 71:   */     }
/* 72:   */   }
/* 73:   */   
/* 74:   */   public static Map<String, Placeholder> getAnimations()
/* 75:   */   {
/* 76:81 */     return animations;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public static Placeholder getAnimation(String name)
/* 80:   */   {
/* 81:85 */     return (Placeholder)animations.get(name);
/* 82:   */   }
/* 83:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.placeholder.AnimationsRegister
 * JD-Core Version:    0.7.0.1
 */