/*   1:    */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*   2:    */ 
/*   3:    */ import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
/*   4:    */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*   5:    */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*   6:    */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*   7:    */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*   8:    */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*   9:    */ import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
/*  10:    */ import com.gmail.filoghost.holographicdisplays.event.NamedHologramEditedEvent;
/*  11:    */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/*  12:    */ import com.gmail.filoghost.holographicdisplays.exception.TooWideException;
/*  13:    */ import com.gmail.filoghost.holographicdisplays.exception.UnreadableImageException;
/*  14:    */ import com.gmail.filoghost.holographicdisplays.image.ImageMessage;
/*  15:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/*  16:    */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/*  17:    */ import com.gmail.filoghost.holographicdisplays.util.FileUtils;
/*  18:    */ import com.gmail.filoghost.holographicdisplays.util.Utils;
/*  19:    */ import java.awt.image.BufferedImage;
/*  20:    */ import java.io.File;
/*  21:    */ import java.io.FileNotFoundException;
/*  22:    */ import java.io.IOException;
/*  23:    */ import java.net.MalformedURLException;
/*  24:    */ import java.net.URL;
/*  25:    */ import java.util.Arrays;
/*  26:    */ import java.util.List;
/*  27:    */ import org.bukkit.Bukkit;
/*  28:    */ import org.bukkit.ChatColor;
/*  29:    */ import org.bukkit.command.CommandSender;
/*  30:    */ import org.bukkit.plugin.PluginManager;
/*  31:    */ 
/*  32:    */ public class ReadimageCommand
/*  33:    */   extends HologramSubCommand
/*  34:    */ {
/*  35:    */   public ReadimageCommand()
/*  36:    */   {
/*  37: 36 */     super("readimage", new String[] { "image" });
/*  38: 37 */     setPermission("holograms.readimage");
/*  39:    */   }
/*  40:    */   
/*  41:    */   public String getPossibleArguments()
/*  42:    */   {
/*  43: 42 */     return "<hologram> <imageWithExtension> <width>";
/*  44:    */   }
/*  45:    */   
/*  46:    */   public int getMinimumArguments()
/*  47:    */   {
/*  48: 47 */     return 3;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void execute(CommandSender sender, String label, String[] args)
/*  52:    */     throws CommandException
/*  53:    */   {
/*  54: 54 */     boolean append = false;
/*  55:    */     
/*  56: 56 */     List<String> newArgs = Utils.newList();
/*  57: 58 */     for (int i = 0; i < args.length; i++) {
/*  58: 59 */       if ((args[i].equalsIgnoreCase("-a")) || (args[i].equalsIgnoreCase("-append"))) {
/*  59: 60 */         append = true;
/*  60:    */       } else {
/*  61: 62 */         newArgs.add(args[i]);
/*  62:    */       }
/*  63:    */     }
/*  64: 66 */     args = (String[])newArgs.toArray(new String[0]);
/*  65:    */     
/*  66: 68 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/*  67: 69 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/*  68:    */     
/*  69: 71 */     int width = CommandValidator.getInteger(args[2]);
/*  70:    */     
/*  71: 73 */     CommandValidator.isTrue(width >= 2, "The width of the image must be 2 or greater.");
/*  72:    */     
/*  73: 75 */     boolean isUrl = false;
/*  74:    */     try
/*  75:    */     {
/*  76: 79 */       String fileName = args[1];
/*  77: 80 */       BufferedImage image = null;
/*  78: 82 */       if ((fileName.startsWith("http://")) || (fileName.startsWith("https://")))
/*  79:    */       {
/*  80: 83 */         isUrl = true;
/*  81: 84 */         image = FileUtils.readImage(new URL(fileName));
/*  82:    */       }
/*  83:    */       else
/*  84:    */       {
/*  85: 87 */         if (fileName.matches(".*[a-zA-Z0-9\\-]+\\.[a-zA-Z0-9\\-]{1,4}\\/.+")) {
/*  86: 88 */           Strings.sendWarning(sender, "The image path seems to be an URL. If so, please use http:// or https:// in the path.");
/*  87:    */         }
/*  88: 91 */         image = FileUtils.readImage(new File(HolographicDisplays.getInstance().getDataFolder(), fileName));
/*  89:    */       }
/*  90: 94 */       if (!append) {
/*  91: 95 */         hologram.clearLines();
/*  92:    */       }
/*  93: 98 */       ImageMessage imageMessage = new ImageMessage(image, width);
/*  94: 99 */       String[] newLines = imageMessage.getLines();
/*  95:100 */       for (int i = 0; i < newLines.length; i++) {
/*  96:101 */         hologram.appendTextLine(newLines[i]);
/*  97:    */       }
/*  98:104 */       hologram.refreshAll();
/*  99:106 */       if (newLines.length < 5) {
/* 100:107 */         sender.sendMessage(Strings.TIP_PREFIX + "The image has a very low height. You can increase it by increasing the width, it will scale automatically.");
/* 101:    */       }
/* 102:110 */       HologramDatabase.saveHologram(hologram);
/* 103:111 */       HologramDatabase.trySaveToDisk();
/* 104:113 */       if (append) {
/* 105:114 */         sender.sendMessage(Colors.PRIMARY + "The image was appended int the end of the hologram!");
/* 106:    */       } else {
/* 107:116 */         sender.sendMessage(Colors.PRIMARY + "The image was drawn in the hologram!");
/* 108:    */       }
/* 109:118 */       Bukkit.getPluginManager().callEvent(new NamedHologramEditedEvent(hologram));
/* 110:    */     }
/* 111:    */     catch (MalformedURLException e)
/* 112:    */     {
/* 113:121 */       throw new CommandException("The provided URL was not valid.");
/* 114:    */     }
/* 115:    */     catch (TooWideException e)
/* 116:    */     {
/* 117:123 */       throw new CommandException("The image is too large. Max width allowed is 150 pixels.");
/* 118:    */     }
/* 119:    */     catch (UnreadableImageException e)
/* 120:    */     {
/* 121:125 */       throw new CommandException("The plugin was unable to read the image. Be sure that the format is supported.");
/* 122:    */     }
/* 123:    */     catch (FileNotFoundException e)
/* 124:    */     {
/* 125:127 */       throw new CommandException("The image \"" + args[1] + "\" doesn't exist in the plugin's folder.");
/* 126:    */     }
/* 127:    */     catch (IOException e)
/* 128:    */     {
/* 129:129 */       e.printStackTrace();
/* 130:130 */       throw new CommandException("I/O exception while reading the image. " + (isUrl ? "Is the URL valid?" : "Is it in use?"));
/* 131:    */     }
/* 132:    */     catch (Exception e)
/* 133:    */     {
/* 134:132 */       e.printStackTrace();
/* 135:133 */       throw new CommandException("Unhandled exception while reading the image! Please look the console.");
/* 136:    */     }
/* 137:    */   }
/* 138:    */   
/* 139:    */   public List<String> getTutorial()
/* 140:    */   {
/* 141:152 */     return Arrays.asList(new String[] { "Reads an image from a file. Tutorial:", "1) Move the image in the plugin's folder", "2) Do not use spaces in the name", "3) Do /holograms read <hologram> <image> <width>", "4) Choose <width> to automatically resize the image", "5) (Optional) Use the flag '-a' if you only want to append", "   the image to the hologram without clearing the lines", "", "Example: you have an image named 'logo.png', you want to append", "it to the lines of the hologram named 'test', with a width of", "50 pixels. In this case you would execute the following command:", ChatColor.YELLOW + "/holograms readimage test logo.png 50 -a", "", "The symbols used to create the image are taken from the config.yml." });
/* 142:    */   }
/* 143:    */   
/* 144:    */   public HologramSubCommand.SubCommandType getType()
/* 145:    */   {
/* 146:157 */     return HologramSubCommand.SubCommandType.EDIT_LINES;
/* 147:    */   }
/* 148:    */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.ReadimageCommand
 * JD-Core Version:    0.7.0.1
 */