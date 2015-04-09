/*  1:   */ package com.gmail.filoghost.holographicdisplays.commands.main.subs;
/*  2:   */ 
/*  3:   */ import com.gmail.filoghost.holographicdisplays.commands.Colors;
/*  4:   */ import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
/*  5:   */ import com.gmail.filoghost.holographicdisplays.commands.Strings;
/*  6:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
/*  7:   */ import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand.SubCommandType;
/*  8:   */ import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
/*  9:   */ import com.gmail.filoghost.holographicdisplays.event.NamedHologramEditedEvent;
/* 10:   */ import com.gmail.filoghost.holographicdisplays.exception.CommandException;
/* 11:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
/* 12:   */ import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
/* 13:   */ import java.util.Arrays;
/* 14:   */ import java.util.List;
/* 15:   */ import org.bukkit.Bukkit;
/* 16:   */ import org.bukkit.command.CommandSender;
/* 17:   */ import org.bukkit.plugin.PluginManager;
/* 18:   */ 
/* 19:   */ public class RemovelineCommand
/* 20:   */   extends HologramSubCommand
/* 21:   */ {
/* 22:   */   public RemovelineCommand()
/* 23:   */   {
/* 24:22 */     super("removeline");
/* 25:23 */     setPermission("holograms.removeline");
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getPossibleArguments()
/* 29:   */   {
/* 30:28 */     return "<hologramName> <lineNumber>";
/* 31:   */   }
/* 32:   */   
/* 33:   */   public int getMinimumArguments()
/* 34:   */   {
/* 35:33 */     return 2;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void execute(CommandSender sender, String label, String[] args)
/* 39:   */     throws CommandException
/* 40:   */   {
/* 41:39 */     NamedHologram hologram = NamedHologramManager.getHologram(args[0].toLowerCase());
/* 42:40 */     CommandValidator.notNull(hologram, Strings.noSuchHologram(args[0].toLowerCase()));
/* 43:   */     
/* 44:42 */     int lineNumber = CommandValidator.getInteger(args[1]);
/* 45:   */     
/* 46:44 */     CommandValidator.isTrue((lineNumber >= 1) && (lineNumber <= hologram.size()), "The line number must be between 1 and " + hologram.size() + ".");
/* 47:45 */     int index = lineNumber - 1;
/* 48:   */     
/* 49:47 */     CommandValidator.isTrue(hologram.size() > 1, "The hologram should have at least 1 line. If you want to delete it, use /" + label + " delete.");
/* 50:   */     
/* 51:49 */     hologram.removeLine(index);
/* 52:50 */     hologram.refreshAll();
/* 53:   */     
/* 54:52 */     HologramDatabase.saveHologram(hologram);
/* 55:53 */     HologramDatabase.trySaveToDisk();
/* 56:54 */     sender.sendMessage(Colors.PRIMARY + "Line " + lineNumber + " removed!");
/* 57:55 */     Bukkit.getPluginManager().callEvent(new NamedHologramEditedEvent(hologram));
/* 58:   */   }
/* 59:   */   
/* 60:   */   public List<String> getTutorial()
/* 61:   */   {
/* 62:60 */     return Arrays.asList(new String[] { "Removes a line from a hologram." });
/* 63:   */   }
/* 64:   */   
/* 65:   */   public HologramSubCommand.SubCommandType getType()
/* 66:   */   {
/* 67:65 */     return HologramSubCommand.SubCommandType.EDIT_LINES;
/* 68:   */   }
/* 69:   */ }


/* Location:           C:\Users\Rufus\Downloads\HolographicDisplays.jar
 * Qualified Name:     com.gmail.filoghost.holographicdisplays.commands.main.subs.RemovelineCommand
 * JD-Core Version:    0.7.0.1
 */