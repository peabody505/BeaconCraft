/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*  8:   */ import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
/*  9:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/* 10:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/* 11:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/* 12:   */ import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
/* 13:   */ import com.gmail.filoghost.holographicdisplays.object.line.CraftTextLine;
/* 14:   */ import java.util.Arrays;
/* 15:   */ import java.util.List;
/* 16:   */ import org.bukkit.command.CommandSender;
/* 17:   */ 
/* 18:   */ public class InfoCommand
/* 19:   */   extends HologramSubCommand
/* 20:   */ {
/* 21:   */   public InfoCommand()
/* 22:   */   {
/* 23:21 */     super("info", new String[] { "details" });
/* 24:22 */     setPermission("holograms.info");
/* 25:   */   }
/* 26:   */   
/* 27:   */   public String getPossibleArguments()
/* 28:   */   {
/* 29:27 */     return "<hologramName>";
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getMinimumArguments()
/* 33:   */   {
/* 34:32 */     return 1;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void execute(CommandSender sender, String label, String[] args)
/* 38:   */     throws CommandException
/* 39:   */   {
/* 40:38 */     String name = args[0].toLowerCase();
/* 41:39 */     NamedHologram hologram = NamedHologramManager.getHologram(name);
/* 42:40 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(name));
/* 43:   */     
/* 44:42 */     sender.sendMessage("");
/* 45:43 */     sender.sendMessage(Strings.formatTitle("Lines of the hologram '" + name + "'"));
/* 46:44 */     int index = 0;
/* 47:46 */     for (CraftHologramLine line : hologram.getLinesUnsafe()) {
/* 48:47 */       sender.sendMessage(Colors.SECONDARY + Colors.BOLD + ++index + Colors.SECONDARY_SHADOW + ". " + Colors.SECONDARY + ((line instanceof CraftTextLine) ? ((CraftTextLine)line).getText() : HologramDatabase.saveLineToString(line)));
/* 49:   */     }
/* 50:   */   }
/* 51:   */   
/* 52:   */   public List<String> getTutorial()
/* 53:   */   {
/* 54:53 */     return Arrays.asList(new String[] { "Shows the lines of a hologram." });
/* 55:   */   }
/* 56:   */   
/* 57:   */   public HologramSubCommand.SubCommandType getType()
/* 58:   */   {
/* 59:58 */     return HologramSubCommand.SubCommandType.EDIT_LINES;
/* 60:   */   }
/* 61:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.InfoCommand
 * JD-Core Version:    0.7.0.1
 */