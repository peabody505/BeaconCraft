/*   1:    */ package com.gmail.filoghost.holographicdisplays.disk;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.exception.HologramNotFoundException;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.exception.InvalidFormatException;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.exception.WorldNotFoundException;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTextLine;
/*  12:    */ import com.gmail.filoghost.holographicdisplays.util.ItemUtils;
/*  13:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  14:    */ import java.io.File;
/*  15:    */ import java.io.IOException;
/*  16:    */ import java.util.List;
/*  17:    */ import java.util.Set;
/*  18:    */ import java.util.logging.Logger;
/*  19:    */ import org.bukkit.Location;
/*  20:    */ import org.bukkit.Material;
/*  21:    */ import org.bukkit.configuration.ConfigurationSection;
/*  22:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  23:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  24:    */ import org.bukkit.inventory.ItemStack;
/*  25:    */ import org.bukkit.plugin.Plugin;
/*  26:    */ 
/*  27:    */ public class HologramDatabase
/*  28:    */ {
/*  29:    */   private static File file;
/*  30:    */   private static FileConfiguration config;
/*  31:    */   
/*  32:    */   public static void loadYamlFile(Plugin plugin)
/*  33:    */   {
/*  34: 34 */     file = new File(plugin.getDataFolder(), "database.yml");
/*  35: 36 */     if (!file.exists())
/*  36:    */     {
/*  37: 37 */       plugin.getDataFolder().mkdirs();
/*  38: 38 */       plugin.saveResource("database.yml", true);
/*  39:    */     }
/*  40: 41 */     config = YamlConfiguration.loadConfiguration(file);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public static NamedHologram loadHologram(String name)
/*  44:    */     throws HologramNotFoundException, InvalidFormatException, WorldNotFoundException
/*  45:    */   {
/*  46: 46 */     ConfigurationSection configSection = config.getConfigurationSection(name);
/*  47: 48 */     if (configSection == null) {
/*  48: 49 */       throw new HologramNotFoundException();
/*  49:    */     }
/*  50: 52 */     List<String> lines = configSection.getStringList("lines");
/*  51: 53 */     String locationString = configSection.getString("location");
/*  52: 55 */     if ((lines == null) || (locationString == null) || (lines.size() == 0)) {
/*  53: 56 */       throw new HologramNotFoundException();
/*  54:    */     }
/*  55: 59 */     Location loc = LocationSerializer.locationFromString(locationString);
/*  56:    */     
/*  57: 61 */     NamedHologram hologram = new NamedHologram(loc, name);
/*  58: 62 */     for (int i = 0; i < lines.size(); i++) {
/*  59: 63 */       hologram.getLinesUnsafe().add(readLineFromString((String)lines.get(i), hologram));
/*  60:    */     }
/*  61: 66 */     return hologram;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public static CraftHologramLine readLineFromString(String rawText, CraftHologram hologram)
/*  65:    */   {
/*  66: 70 */     if (rawText.toLowerCase().startsWith("icon:"))
/*  67:    */     {
/*  68: 71 */       String iconMaterial = ItemUtils.stripSpacingChars(rawText.substring("icon:".length(), rawText.length()));
/*  69:    */       
/*  70: 73 */       short dataValue = 0;
/*  71: 75 */       if (iconMaterial.contains(":"))
/*  72:    */       {
/*  73:    */         try
/*  74:    */         {
/*  75: 77 */           dataValue = (short)Integer.parseInt(iconMaterial.split(":")[1]);
/*  76:    */         }
/*  77:    */         catch (NumberFormatException localNumberFormatException) {}
/*  78: 79 */         iconMaterial = iconMaterial.split(":")[0];
/*  79:    */       }
/*  80: 82 */       Material mat = ItemUtils.matchMaterial(iconMaterial);
/*  81: 83 */       if (mat == null) {
/*  82: 84 */         mat = Material.BEDROCK;
/*  83:    */       }
/*  84: 87 */       return new CraftItemLine(hologram, new ItemStack(mat, 1, dataValue));
/*  85:    */     }
/*  86: 91 */     if (rawText.trim().equalsIgnoreCase("{empty}")) {
/*  87: 92 */       return new CraftTextLine(hologram, "");
/*  88:    */     }
/*  89: 94 */     return new CraftTextLine(hologram, StringConverter.toReadableFormat(rawText));
/*  90:    */   }
/*  91:    */   
/*  92:    */   public static String saveLineToString(CraftHologramLine line)
/*  93:    */   {
/*  94:100 */     if ((line instanceof CraftTextLine)) {
/*  95:101 */       return StringConverter.toSaveableFormat(((CraftTextLine)line).getText());
/*  96:    */     }
/*  97:103 */     if ((line instanceof CraftItemLine))
/*  98:    */     {
/*  99:104 */       CraftItemLine itemLine = (CraftItemLine)line;
/* 100:105 */       return "ICON: " + itemLine.getItemStack().getType().toString().replace("_", " ").toLowerCase() + (itemLine.getItemStack().getDurability() != 0 ? ":" + itemLine.getItemStack().getDurability() : "");
/* 101:    */     }
/* 102:108 */     return "Unknown";
/* 103:    */   }
/* 104:    */   
/* 105:    */   public static void deleteHologram(String name)
/* 106:    */   {
/* 107:113 */     config.set(name, null);
/* 108:    */   }
/* 109:    */   
/* 110:    */   public static void saveHologram(NamedHologram hologram)
/* 111:    */   {
/* 112:118 */     ConfigurationSection configSection = config.isConfigurationSection(hologram.getName()) ? config.getConfigurationSection(hologram.getName()) : config.createSection(hologram.getName());
/* 113:    */     
/* 114:120 */     configSection.set("location", LocationSerializer.locationToString(hologram.getLocation()));
/* 115:121 */     List<String> lines = Utils.newList();
/* 116:123 */     for (CraftHologramLine line : hologram.getLinesUnsafe()) {
/* 117:125 */       lines.add(saveLineToString(line));
/* 118:    */     }
/* 119:128 */     configSection.set("lines", lines);
/* 120:    */   }
/* 121:    */   
/* 122:    */   public static Set<String> getHolograms()
/* 123:    */   {
/* 124:132 */     return config.getKeys(false);
/* 125:    */   }
/* 126:    */   
/* 127:    */   public static boolean isExistingHologram(String name)
/* 128:    */   {
/* 129:136 */     return config.isConfigurationSection(name);
/* 130:    */   }
/* 131:    */   
/* 132:    */   public static void saveToDisk()
/* 133:    */     throws IOException
/* 134:    */   {
/* 135:140 */     if ((config != null) && (file != null)) {
/* 136:141 */       config.save(file);
/* 137:    */     }
/* 138:    */   }
/* 139:    */   
/* 140:    */   public static void trySaveToDisk()
/* 141:    */   {
/* 142:    */     try
/* 143:    */     {
/* 144:    */       
/* 145:    */     }
/* 146:    */     catch (IOException ex)
/* 147:    */     {
/* 148:149 */       ex.printStackTrace();
/* 149:150 */       HolographicDisplays.getInstance().getLogger().severe("Unable to save database.yml to disk!");
/* 150:    */     }
/* 151:    */   }
/* 152:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.disk.HologramDatabase
 * JD-Core Version:    0.7.0.1
 */