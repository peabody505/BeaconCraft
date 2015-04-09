/*   1:    */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.bridge.bungeecord.BungeeServerTracker;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.disk.Configuration;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.disk.UnicodeSymbols;
/*  12:    */ import com.gmail.filoghost.holographicdisplays.event.HolographicDisplaysReloadEvent;
/*  13:    */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/*  14:    */ import com.gmail.filoghost.holographicdisplays.exception.HologramNotFoundException;
/*  15:    */ import com.gmail.filoghost.holographicdisplays.exception.InvalidFormatException;
/*  16:    */ import com.gmail.filoghost.holographicdisplays.exception.WorldNotFoundException;
/*  17:    */ import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
/*  18:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/*  19:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/*  20:    */ import com.gmail.filoghost.holographicdisplays.placeholder.AnimationsRegister;
/*  21:    */ import com.gmail.filoghost.holographicdisplays.placeholder.PlaceholdersManager;
/*  22:    */ import java.util.Arrays;
/*  23:    */ import java.util.List;
/*  24:    */ import java.util.Set;
/*  25:    */ import org.bukkit.Bukkit;
/*  26:    */ import org.bukkit.command.CommandSender;
/*  27:    */ import org.bukkit.plugin.PluginManager;
/*  28:    */ 
/*  29:    */ public class ReloadCommand
/*  30:    */   extends HologramSubCommand
/*  31:    */ {
/*  32:    */   public ReloadCommand()
/*  33:    */   {
/*  34: 32 */     super("reload");
/*  35: 33 */     setPermission("holograms.reload");
/*  36:    */   }
/*  37:    */   
/*  38:    */   public String getPossibleArguments()
/*  39:    */   {
/*  40: 38 */     return "";
/*  41:    */   }
/*  42:    */   
/*  43:    */   public int getMinimumArguments()
/*  44:    */   {
/*  45: 43 */     return 0;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void execute(CommandSender sender, String label, String[] args)
/*  49:    */     throws CommandException
/*  50:    */   {
/*  51:    */     try
/*  52:    */     {
/*  53: 50 */       long startMillis = System.currentTimeMillis();
/*  54:    */       
/*  55: 52 */       UnicodeSymbols.load(HolographicDisplays.getInstance());
/*  56: 53 */       Configuration.load(HolographicDisplays.getInstance());
/*  57:    */       
/*  58: 55 */       BungeeServerTracker.resetTrackedServers();
/*  59: 56 */       BungeeServerTracker.startTask(Configuration.bungeeRefreshSeconds);
/*  60:    */       
/*  61: 58 */       HologramDatabase.loadYamlFile(HolographicDisplays.getInstance());
/*  62: 59 */       AnimationsRegister.loadAnimations(HolographicDisplays.getInstance());
/*  63:    */       
/*  64: 61 */       PlaceholdersManager.untrackAll();
/*  65: 62 */       NamedHologramManager.clearAll();
/*  66:    */       
/*  67: 64 */       Set<String> savedHolograms = HologramDatabase.getHolograms();
/*  68: 65 */       if ((savedHolograms != null) && (savedHolograms.size() > 0)) {
/*  69: 66 */         for (String singleSavedHologram : savedHolograms) {
/*  70:    */           try
/*  71:    */           {
/*  72: 68 */             NamedHologram singleHologramEntity = HologramDatabase.loadHologram(singleSavedHologram);
/*  73: 69 */             NamedHologramManager.addHologram(singleHologramEntity);
/*  74:    */           }
/*  75:    */           catch (HologramNotFoundException e)
/*  76:    */           {
/*  77: 71 */             Strings.sendWarning(sender, "Hologram '" + singleSavedHologram + "' not found, skipping it.");
/*  78:    */           }
/*  79:    */           catch (InvalidFormatException e)
/*  80:    */           {
/*  81: 73 */             Strings.sendWarning(sender, "Hologram '" + singleSavedHologram + "' has an invalid location format.");
/*  82:    */           }
/*  83:    */           catch (WorldNotFoundException e)
/*  84:    */           {
/*  85: 75 */             Strings.sendWarning(sender, "Hologram '" + singleSavedHologram + "' was in the world '" + e.getMessage() + "' but it wasn't loaded.");
/*  86:    */           }
/*  87:    */         }
/*  88:    */       }
/*  89: 80 */       for (CraftHologram hologram : NamedHologramManager.getHolograms()) {
/*  90: 81 */         hologram.refreshAll();
/*  91:    */       }
/*  92: 84 */       long endMillis = System.currentTimeMillis();
/*  93:    */       
/*  94: 86 */       sender.sendMessage(Colors.PRIMARY + "Configuration reloaded successfully in " + (endMillis - startMillis) + "ms!");
/*  95:    */     }
/*  96:    */     catch (Exception ex)
/*  97:    */     {
/*  98: 89 */       ex.printStackTrace();
/*  99: 90 */       throw new CommandException("Exception while reloading the configuration. Please look the console.");
/* 100:    */     }
/* 101: 93 */     Bukkit.getPluginManager().callEvent(new HolographicDisplaysReloadEvent());
/* 102:    */   }
/* 103:    */   
/* 104:    */   public List<String> getTutorial()
/* 105:    */   {
/* 106: 98 */     return Arrays.asList(new String[] { "Reloads the holograms from the database." });
/* 107:    */   }
/* 108:    */   
/* 109:    */   public HologramSubCommand.SubCommandType getType()
/* 110:    */   {
/* 111:103 */     return HologramSubCommand.SubCommandType.GENERIC;
/* 112:    */   }
/* 113:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.ReloadCommand
 * JD-Core Version:    0.7.0.1
 */