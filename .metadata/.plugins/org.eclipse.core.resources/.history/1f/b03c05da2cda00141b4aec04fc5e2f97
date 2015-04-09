/*   1:    */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.disk.StringConverter;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.event.NamedHologramEditedEvent;
/*  12:    */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/*  13:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/*  14:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/*  15:    */ import com.gmail.filoghost.holographicdisplays.util.FileUtils;
/*  16:    */ import java.io.File;
/*  17:    */ import java.io.FileNotFoundException;
/*  18:    */ import java.io.IOException;
/*  19:    */ import java.util.Arrays;
/*  20:    */ import java.util.List;
/*  21:    */ import org.bukkit.Bukkit;
/*  22:    */ import org.bukkit.ChatColor;
/*  23:    */ import org.bukkit.command.CommandSender;
/*  24:    */ import org.bukkit.plugin.PluginManager;
/*  25:    */ 
/*  26:    */ public class ReadtextCommand
/*  27:    */   extends HologramSubCommand
/*  28:    */ {
/*  29:    */   public ReadtextCommand()
/*  30:    */   {
/*  31: 29 */     super("readtext", new String[] { "readlines" });
/*  32: 30 */     setPermission("holograms.readtext");
/*  33:    */   }
/*  34:    */   
/*  35:    */   public String getPossibleArguments()
/*  36:    */   {
/*  37: 35 */     return "<hologramName> <fileWithExtension>";
/*  38:    */   }
/*  39:    */   
/*  40:    */   public int getMinimumArguments()
/*  41:    */   {
/*  42: 40 */     return 2;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void execute(CommandSender sender, String label, String[] args)
/*  46:    */     throws CommandException
/*  47:    */   {
/*  48: 45 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/*  49: 46 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/*  50:    */     try
/*  51:    */     {
/*  52: 49 */       List<String> lines = FileUtils.readLines(new File(HolographicDisplays.getInstance().getDataFolder(), args[1]));
/*  53: 50 */       hologram.clearLines();
/*  54:    */       
/*  55: 52 */       int linesAmount = lines.size();
/*  56: 53 */       if (linesAmount > 40)
/*  57:    */       {
/*  58: 54 */         Strings.sendWarning(sender, "The file contained more than 40 lines, that have been limited.");
/*  59: 55 */         linesAmount = 40;
/*  60:    */       }
/*  61: 58 */       for (int i = 0; i < linesAmount; i++) {
/*  62: 59 */         hologram.appendTextLine(StringConverter.toReadableFormat((String)lines.get(i)));
/*  63:    */       }
/*  64: 62 */       hologram.refreshAll();
/*  65:    */       
/*  66: 64 */       HologramDatabase.saveHologram(hologram);
/*  67: 65 */       HologramDatabase.trySaveToDisk();
/*  68: 67 */       if ((args[1].contains(".")) && 
/*  69: 68 */         (isImageExtension(args[1].substring(args[1].lastIndexOf('.') + 1)))) {
/*  70: 69 */         Strings.sendWarning(sender, "The read file has an image's extension. If it is an image, you should use /" + label + " readimage.");
/*  71:    */       }
/*  72: 73 */       sender.sendMessage(Colors.PRIMARY + "The lines were pasted into the hologram!");
/*  73: 74 */       Bukkit.getPluginManager().callEvent(new NamedHologramEditedEvent(hologram));
/*  74:    */     }
/*  75:    */     catch (FileNotFoundException e)
/*  76:    */     {
/*  77: 77 */       throw new CommandException("A file named '" + args[1] + "' doesn't exist in the plugin's folder.");
/*  78:    */     }
/*  79:    */     catch (IOException e)
/*  80:    */     {
/*  81: 79 */       throw new CommandException("I/O exception while reading the file. Is it in use?");
/*  82:    */     }
/*  83:    */     catch (Exception e)
/*  84:    */     {
/*  85: 81 */       e.printStackTrace();
/*  86: 82 */       throw new CommandException("Unhandled exception while reading the file! Please look the console.");
/*  87:    */     }
/*  88:    */   }
/*  89:    */   
/*  90:    */   public List<String> getTutorial()
/*  91:    */   {
/*  92: 96 */     return Arrays.asList(new String[] { "Reads the lines from a text file. Tutorial:", "1) Create a new text file in the plugin's folder", "2) Do not use spaces in the name", "3) Each line will be a line in the hologram", "4) Do /holograms readlines <hologramName> <fileWithExtension>", "", "Example: you have a file named 'info.txt', and you want", "to paste it in the hologram named 'test'. In this case you", "would execute " + ChatColor.YELLOW + "/holograms readlines test info.txt" });
/*  93:    */   }
/*  94:    */   
/*  95:    */   public HologramSubCommand.SubCommandType getType()
/*  96:    */   {
/*  97:101 */     return HologramSubCommand.SubCommandType.EDIT_LINES;
/*  98:    */   }
/*  99:    */   
/* 100:    */   private boolean isImageExtension(String input)
/* 101:    */   {
/* 102:105 */     return Arrays.asList(new String[] { "jpg", "png", "jpeg", "gif" }).contains(input.toLowerCase());
/* 103:    */   }
/* 104:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.ReadtextCommand
 * JD-Core Version:    0.7.0.1
 */